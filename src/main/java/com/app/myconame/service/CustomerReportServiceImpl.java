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

	public List<Customer> getAllCustomers() {
		return customerRepo.findAll();
	}
	
	public List<Customer> getCustomersByPlan(Integer planId) {
		if(planId == null || !planRepo.existsById(planId))
			throw new NoSuchPlanExistsException("NO SUCH PLAN EXISTS"); 
		else 	
			return customerRepo.getCustomersByPlan(planId);
	}

	public List<Customer> getCustomersByStatus(Integer statusId) {
		if(statusId == null || !statusRepo.existsById(statusId))
			throw new NoSuchStatusExistsException("NO SUCH STATUS EXISTS"); 
		else 	
			return customerRepo.getCustomersByStatus(statusId);
	}

	public List<Customer> getCustomersByStatusAndPlan(Integer statusId, Integer planId) {
		if(statusId == null || !statusRepo.existsById(statusId))
			throw new NoSuchStatusExistsException("NO SUCH STATUS EXISTS"); 
		else if(planId == null || !planRepo.existsById(planId))
			throw new NoSuchPlanExistsException("NO SUCH PLAN EXISTS");
		else
			return customerRepo.getCustomersByStatusAndPlan(statusId, planId);
	}
}
