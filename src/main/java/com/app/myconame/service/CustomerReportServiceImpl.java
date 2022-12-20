package com.app.myconame.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	public List<Customer> getCustomersByStatusAndPlan(Integer statusId, Integer planId) {
		// retrieve records that match both status and plan ids. 
		// SELECT * FROM customers WHERE plan_id=:planId AND status_id=statuId
		// I do not know how to join these in spring framework to get the data
		return null;
	}
	
	public List<Customer> getCustomersByPlan(Integer planId) {
//		if(planId == null || !planRepo.existsById(planId))
//			throw new NoSuchPlanExistsException("NO SUCH PLAN EXISTS"); 
//		else 	
//			return customerRepo.getByPlan(planId);
		return null;
	}

	public List<Customer> getCustomersByStatus(Integer statusId) {
//		if(statusId == null || !statusRepo.existsById(statusId))
//			throw new NoSuchStatusExistsException("NO SUCH STATUS EXISTS"); 
//		else 	
//			return customerRepo.getByStatus(statusId);
		return null;
	}

	
}
