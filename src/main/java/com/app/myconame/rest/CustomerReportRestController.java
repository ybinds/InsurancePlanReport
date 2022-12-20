package com.app.myconame.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.myconame.entity.Customer;
import com.app.myconame.exception.NoSuchPlanExistsException;
import com.app.myconame.exception.NoSuchStatusExistsException;
import com.app.myconame.service.ICustomerReportService;

@RestController
@RequestMapping("/v1/api/report")
public class CustomerReportRestController {

	@Autowired
	private ICustomerReportService service;
	
	@GetMapping("/byplan/{planId}")
	public ResponseEntity<List<Customer>> getCustomersByplanId(
			@PathVariable("planId") Integer planId){
		ResponseEntity<List<Customer>> response = null;
		try {
			List<Customer> customers = service.getCustomersByPlan(planId);
			response = ResponseEntity.ok(customers);
		}catch(NoSuchPlanExistsException nsfe) {
			nsfe.printStackTrace();
			throw nsfe;
		}
		return response;
	}
	
	@GetMapping("/bystatus/{statusId}")
	public ResponseEntity<List<Customer>> getCustomersByStatusId(
			@PathVariable("statusId") Integer statusId){
		ResponseEntity<List<Customer>> response = null;
		try {
			List<Customer> customers = service.getCustomersByStatus(statusId);
			response = ResponseEntity.ok(customers);
		}catch(NoSuchStatusExistsException nsse) {
			nsse.printStackTrace();
			throw nsse;
		}
		return response;
	}
}
