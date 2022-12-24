package com.app.myconame.rest;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.filters.CorsFilter;
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
import com.lowagie.text.DocumentException;

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
		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
	    String currentDateTime = dateFormat.format(new Date());
		String headerValue = "attachment; filename=customers-plans" + currentDateTime + "xls";
		response.setHeader(headerKey, headerValue);
		service.downloadAsExcel(response,scustomer);
		response.flushBuffer();
	}
	
	// Method to write into excel not sure what type of request call would this be either	
	@PostMapping("/pdf")
	public void downloadAsPdf(
			@RequestBody SearchCustomer scustomer,
			HttpServletResponse response
			) throws DocumentException, IOException{
		// logic to download as pdf sheet
		response.setContentType("application/pdf");
	    DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
	    String currentDateTime = dateFormat.format(new Date());
	    String headerkey = "Content-Disposition";
	    String headervalue = "attachment; filename=customers-plans" + currentDateTime + ".pdf";
	    response.setHeader(headerkey, headervalue);
	    service.downloadAsPdf(response, scustomer);
	}
}
