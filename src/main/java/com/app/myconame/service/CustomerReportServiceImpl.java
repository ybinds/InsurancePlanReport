package com.app.myconame.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.myconame.dto.SearchCustomer;
import com.app.myconame.entity.Customer;
import com.app.myconame.exception.NoSuchPlanExistsException;
import com.app.myconame.exception.NoSuchStatusExistsException;
import com.app.myconame.repository.CustomerRepository;
import com.app.myconame.repository.PlanRepository;
import com.app.myconame.repository.StatusRepository;

@Service
@Transactional
public class CustomerReportServiceImpl implements ICustomerReportService {

	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private PlanRepository planRepo;

	@Autowired
	private StatusRepository statusRepo;

	/*
	 * public List<Customer> getAllCustomers() { return customerRepo.findAll(); }
	 * 
	 * public List<Customer> getCustomersByPlan(Integer planId) { if(planId == null
	 * || !planRepo.existsById(planId)) throw new
	 * NoSuchPlanExistsException("NO SUCH PLAN EXISTS"); else return
	 * customerRepo.getCustomersByPlan(planId); }
	 * 
	 * public List<Customer> getCustomersByStatus(Integer statusId) { if(statusId ==
	 * null || !statusRepo.existsById(statusId)) throw new
	 * NoSuchStatusExistsException("NO SUCH STATUS EXISTS"); else return
	 * customerRepo.getCustomersByStatus(statusId); }
	 * 
	 * public List<Customer> getCustomersByStatusAndPlan(Integer statusId, Integer
	 * planId) { if(statusId == null || !statusRepo.existsById(statusId)) throw new
	 * NoSuchStatusExistsException("NO SUCH STATUS EXISTS"); else if(planId == null
	 * || !planRepo.existsById(planId)) throw new
	 * NoSuchPlanExistsException("NO SUCH PLAN EXISTS"); else return
	 * customerRepo.getCustomersByStatusAndPlan(statusId, planId); }
	 */

	// Logic to navigate through the search object and retrieve customers accordingly
	// Start Date and End Date logic has to be added as I did not have clarity about those yet
	public List<Customer> getCustomersBySearch(SearchCustomer scustomer) {
		List<Customer> customers = null;
		if (scustomer.getPlan_id() != null && scustomer.getStatus_id() != null) { // When planId and statusId are given
			// check for valid status id and plan id
			if (scustomer.getStatus_id() == null || !statusRepo.existsById(scustomer.getStatus_id()))
				throw new NoSuchStatusExistsException("NO SUCH STATUS EXISTS");
			else if (scustomer.getPlan_id() == null || !planRepo.existsById(scustomer.getPlan_id()))
				throw new NoSuchPlanExistsException("NO SUCH PLAN EXISTS");
			else
				customers = customerRepo.getCustomersByStatusAndPlan(scustomer.getStatus_id(), scustomer.getPlan_id());
		} else if (scustomer.getPlan_id() != null) { // When only planId is given
			// check for valid plan id
			if (scustomer.getPlan_id() == null || !planRepo.existsById(scustomer.getPlan_id()))
				throw new NoSuchPlanExistsException("NO SUCH PLAN EXISTS");
			else
				customers = customerRepo.getCustomersByPlan(scustomer.getPlan_id());
		} else if (scustomer.getStatus_id() != null) { // When only statusId is given
			// check for valid status id
			if (scustomer.getStatus_id() == null || !statusRepo.existsById(scustomer.getStatus_id()))
				throw new NoSuchStatusExistsException("NO SUCH STATUS EXISTS");
			else
				customers = customerRepo.getCustomersByStatus(scustomer.getStatus_id());
		} else { // When empty form is submitted
			customers = customerRepo.findAll(); // default when no selection in search is made
		}
		return customers;
	}

	public void downloadAsExcel(HttpServletResponse response, SearchCustomer scustomer) throws IOException {
		List<Customer> customers = getCustomersBySearch(scustomer);
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Insurance Plan and Customer Report");
		HSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("S.No.");
		row.createCell(1).setCellValue("Name");
		row.createCell(2).setCellValue("Email");
		row.createCell(3).setCellValue("Mobile Number");
		row.createCell(4).setCellValue("Gender");
		row.createCell(5).setCellValue("SSN");
		int dataRowIndex = 1;
		for(Customer c: customers) {
			HSSFRow dataRow = sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(dataRowIndex);
			dataRow.createCell(0).setCellValue(c.getCustomerName());
			dataRow.createCell(0).setCellValue(c.getCustomerEmail());
			dataRow.createCell(0).setCellValue(c.getCustomerPhone());
			dataRow.createCell(0).setCellValue(c.getCustomerGender());
			dataRow.createCell(0).setCellValue(c.getCustomerSSN());
			dataRowIndex++;
		}
		ServletOutputStream ops = response.getOutputStream();
		workbook.write(ops);
		workbook.close();
		ops.close();
	}

}
