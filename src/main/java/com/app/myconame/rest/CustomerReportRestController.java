package com.app.myconame.rest;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.myconame.dto.SearchCustomer;
import com.app.myconame.entity.Customer;
import com.app.myconame.exception.NoSuchPlanExistsException;
import com.app.myconame.exception.NoSuchStatusExistsException;
import com.app.myconame.service.ICustomerReportService;

@RestController
@CrossOrigin
@RequestMapping("/v1/api/report")
public class CustomerReportRestController {

	@Autowired
	private ICustomerReportService service;

	/* Assumptions - I am not considering the dates yet in this scenario.*/
	@PostMapping("/search")
	public ResponseEntity<List<Customer>> getCustomers(
			@RequestBody SearchCustomer scustomer){
		List<Customer> customers = null;
		ResponseEntity<List<Customer>> response = null;
		try {
			customers = service.getCustomersBySearch(scustomer); //condition based search is written in service
			response = ResponseEntity.ok(customers);
		} catch (NoSuchPlanExistsException nsfe) {
			nsfe.printStackTrace();
			throw nsfe;
		} catch(NoSuchStatusExistsException nsse) {
			nsse.printStackTrace();
			throw nsse;
		}
		return response;
	}

	// Method to write into excel not sure what type of request call would this be either
	@PostMapping("/excel")
	public void downloadAsExcel(
			@RequestBody SearchCustomer scustomer,
			HttpServletResponse response
			) throws IOException {
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=customers-insurance.xls";
		response.setHeader(headerKey, headerValue);
		service.downloadAsExcel(response, scustomer);
	}
	
	// Method to write into excel not sure what type of request call would this be either	
	public void downloadAsPdf(
			@RequestBody SearchCustomer scustomer
			) {
		// logic to download as pdf sheet
		//List<Customer> customers = getCustomersBySearch(scustomer);
		// and then print these into pdf sheet, I am not sure of the logic, 
		// so don't even know about service methods
	}
}
