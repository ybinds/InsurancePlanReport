package com.app.myconame.service;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.myconame.dto.SearchCustomer;
import com.app.myconame.entity.Customer;
import com.app.myconame.exception.NoSuchPlanExistsException;
import com.app.myconame.exception.NoSuchStatusExistsException;
import com.app.myconame.repository.CustomerRepository;
import com.app.myconame.repository.PlanRepository;
import com.app.myconame.repository.StatusRepository;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Service
@Transactional
public class CustomerReportServiceImpl implements ICustomerReportService {

	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private PlanRepository planRepo;

	@Autowired
	private StatusRepository statusRepo;

	// Logic to navigate through the search object and retrieve customers accordingly
	// Start Date and End Date logic has to be added as I did not have clarity about those yet
	public List<Customer> getCustomersBySearch(SearchCustomer scustomer) {
		Customer customer = new Customer();
		if (scustomer.getPlan_id() != null && scustomer.getStatus_id() != null) { // When planId and statusId are given
			// check for valid status id and plan id
			if (scustomer.getStatus_id() == null || !statusRepo.existsById(scustomer.getStatus_id()))
				throw new NoSuchStatusExistsException("NO SUCH STATUS EXISTS");
			else if (scustomer.getPlan_id() == null || !planRepo.existsById(scustomer.getPlan_id()))
				throw new NoSuchPlanExistsException("NO SUCH PLAN EXISTS");
			else
				//customers = customerRepo.getCustomersByStatusAndPlan(scustomer.getStatus_id(), scustomer.getPlan_id());
				customer.setPlanObj(planRepo.findById(scustomer.getPlan_id()).get());
				customer.setStatusObj(statusRepo.findById(scustomer.getStatus_id()).get());
		} else if (scustomer.getPlan_id() != null) { // When only planId is given
			// check for valid plan id
			if (scustomer.getPlan_id() == null || !planRepo.existsById(scustomer.getPlan_id()))
				throw new NoSuchPlanExistsException("NO SUCH PLAN EXISTS");
			else
				customer.setPlanObj(planRepo.findById(scustomer.getPlan_id()).get());
		} else if (scustomer.getStatus_id() != null) { // When only statusId is given
			// check for valid status id
			if (scustomer.getStatus_id() == null || !statusRepo.existsById(scustomer.getStatus_id()))
				throw new NoSuchStatusExistsException("NO SUCH STATUS EXISTS");
			else
				customer.setStatusObj(statusRepo.findById(scustomer.getStatus_id()).get());
		} 
		Example<Customer> example = Example.of(customer);
		return customerRepo.findAll(example);
	}

	public void downloadAsExcel(HttpServletResponse response, SearchCustomer scustomer) throws IOException {
		List<Customer> customers = getCustomersBySearch(scustomer);
		//List<Customer> customers = customerRepo.findAll();
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Insurance Plan and Customer Report");
		XSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("S.No.");
		row.createCell(1).setCellValue("Name");
		row.createCell(2).setCellValue("Email");
		row.createCell(3).setCellValue("Mobile Number");
		row.createCell(4).setCellValue("Gender");
		row.createCell(5).setCellValue("SSN");
		row.createCell(6).setCellValue("Plan");
		row.createCell(7).setCellValue("Status");
		int dataRowIndex = 1;
		for(Customer c: customers) {
			XSSFRow dataRow = sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(dataRowIndex);
			dataRow.createCell(1).setCellValue(c.getCustomerName());
			dataRow.createCell(2).setCellValue(c.getCustomerEmail());
			dataRow.createCell(3).setCellValue(c.getCustomerPhone());
			dataRow.createCell(4).setCellValue(c.getCustomerGender());
			dataRow.createCell(5).setCellValue(c.getCustomerSSN());
			dataRow.createCell(6).setCellValue(c.getPlanObj().getPlanName());
			dataRow.createCell(7).setCellValue(c.getStatusObj().getStatusName());
			dataRowIndex++;
		}
		ServletOutputStream ops = response.getOutputStream();
		workbook.write(ops);
		workbook.close();
		ops.close();
	}

	public void downloadAsPdf(HttpServletResponse response, SearchCustomer scustomer) throws DocumentException, IOException {
		 // Creating the Object of Document
	    Document document = new Document(PageSize.A4);
	    // Getting instance of PdfWriter
	    PdfWriter.getInstance(document, response.getOutputStream());
	    // Opening the created document to change it
	    document.open();
	    // Creating font
	    // Setting font style and size
	    Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
	    fontTitle.setSize(20);
	    fontTitle.setColor(Color.DARK_GRAY);
	    // Creating paragraph
	    Paragraph p = new Paragraph("INSURANCE PLAN CUSTOMERS LIST", fontTitle);
	    // Aligning the paragraph in the document
	    p.setAlignment(Paragraph.ALIGN_CENTER);
	    // Adding the created paragraph in the document
	    document.add(p);
	    
	    // Creating a table of the 4 columns
	    PdfPTable table = new PdfPTable(8);
	    // Setting width of the table, its columns and spacing
	    table.setWidthPercentage(100f);
	    table.setWidths(new int[] {2,3,6,4,3,4,2,3});
	    table.setSpacingBefore(5);
	    // Create Table Cells for the table header
	    PdfPCell cell = new PdfPCell();
	    // Setting the background color and padding of the table cell
	    cell.setBackgroundColor(CMYKColor.GRAY);
	    cell.setPadding(5);
	    // Creating font
	    // Setting font style and size
	    Font font = FontFactory.getFont(FontFactory.HELVETICA);
	    font.setColor(CMYKColor.WHITE);
	    // Adding headings in the created table cell or  header
	    // Adding Cell to table
	    cell.setPhrase(new Phrase("ID", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("Name", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("Email", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("Mobile Number", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("Gender", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("SSN", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("Plan", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("Status", font));
	    table.addCell(cell);
	    
	    List<Customer> customers = getCustomersBySearch(scustomer);
	    //List<Customer> customers = customerRepo.findAll();
	    // Iterating the list of customers
	    for (Customer customer: customers) {
	      // Adding id
	      table.addCell(String.valueOf(customer.getCustomerId()));
	      // Adding name
	      table.addCell(customer.getCustomerName());
	      // Adding email
	      table.addCell(customer.getCustomerEmail());
	      // Adding mobile
	      table.addCell(customer.getCustomerPhone());
	      // Adding gender
	      table.addCell(customer.getCustomerGender());
	      // Adding ssn
	      table.addCell(customer.getCustomerSSN());
	      // Adding plan
	      table.addCell(customer.getPlanObj().getPlanName());
	      // Adding status
	      table.addCell(customer.getStatusObj().getStatusName());
	    }
	    // Adding the created table to the document
	    document.add(table);
	    // Closing the document
	    document.close();
	}
}
