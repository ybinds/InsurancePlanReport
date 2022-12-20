package com.app.myconame.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/v1/api/report")
public class CustomerReportRestController {

	@Autowired
	private ICustomerReportService service;

	/* Assumptions - I am not considering the dates yet in this scenario.
	 * Ignored the writing to excel and pdf as well*/
	@PostMapping("/search")
	public ResponseEntity<List<Customer>> getCustomers(
			@RequestBody SearchCustomer scustomer){
		ResponseEntity<List<Customer>> response = null;
		List<Customer> customers = null;
		System.out.println(scustomer.getPlan_id() + " | " + scustomer.getStatus_id());
		try {
			if(scustomer.getPlan_id()!=null && scustomer.getStatus_id()!=null) {
				customers =  service.getCustomersByStatusAndPlan(scustomer.getStatus_id(), scustomer.getPlan_id());
			} else if(scustomer.getPlan_id()!=null) {
				customers = service.getCustomersByPlan(scustomer.getPlan_id());
			} else if(scustomer.getStatus_id()!=null) {
				customers = service.getCustomersByStatus(scustomer.getStatus_id());
			}
			response = ResponseEntity.ok(customers);
		} catch(NoSuchPlanExistsException nsfe) {
			nsfe.printStackTrace();
			throw nsfe;
		} catch(NoSuchStatusExistsException nsse) {
			nsse.printStackTrace();
			throw nsse;
		}
		return response;
	}
}
