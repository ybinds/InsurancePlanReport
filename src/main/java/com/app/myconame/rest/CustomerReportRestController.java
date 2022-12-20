package com.app.myconame.rest;

import java.util.List;

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

	/* Assumptions - I am not considering the dates yet in this scenario.
	 * Ignored the writing to excel and pdf as well*/
	@PostMapping("/search")
	public ResponseEntity<List<Customer>> getCustomers(
			@RequestBody SearchCustomer scustomer){
		return ResponseEntity.ok(getCustomersBySearch(scustomer)); //search method written below
	}

	// Method to write into excel not sure what type of request call would this be either
	public void downloadAsExcel(
			@RequestBody SearchCustomer scustomer
			) {
		// logic to download as Excel sheet
		List<Customer> customers = getCustomersBySearch(scustomer);
		// and then print these into excel sheet, I am not sure of the logic
		// so don't even know about service methods
	}
	
	// Method to write into excel not sure what type of request call would this be either	
	public void downloadAsPdf(
			@RequestBody SearchCustomer scustomer
			) {
		// logic to download as pdf sheet
		List<Customer> customers = getCustomersBySearch(scustomer);
		// and then print these into pdf sheet, I am not sure of the logic, 
		// so don't even know about service methods
	}
	
	// Logic to navigate through the search object and retrieve customers accordingly
	// Start Date and End Date logic has to be added as I did not have clarity about those yet
	private List<Customer> getCustomersBySearch(SearchCustomer scustomer) {
		List<Customer> customers = null;
		try {
			if(scustomer.getPlan_id()!=null && scustomer.getStatus_id()!=null) { //When planId and statusId are given
				customers = service.getCustomersByStatusAndPlan(scustomer.getStatus_id(), scustomer.getPlan_id());
			} else if(scustomer.getPlan_id()!=null) { // When only planId is given
				customers = service.getCustomersByPlan(scustomer.getPlan_id());
			} else if(scustomer.getStatus_id()!=null) { // When only statusId is given
				customers = service.getCustomersByStatus(scustomer.getStatus_id());
			} else { // When empty form is submitted
				customers = service.getAllCustomers();
			}
		} catch(NoSuchPlanExistsException nsfe) {
			nsfe.printStackTrace();
			throw nsfe;
		} catch(NoSuchStatusExistsException nsse) {
			nsse.printStackTrace();
			throw nsse;
		}
		return customers;
	}
}
