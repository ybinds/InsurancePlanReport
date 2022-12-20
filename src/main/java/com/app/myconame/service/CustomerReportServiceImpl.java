package com.app.myconame.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.myconame.entity.Customer;
import com.app.myconame.exception.NoSuchPlanExistsException;
import com.app.myconame.repository.CustomerRepository;
import com.app.myconame.repository.PlanRepository;

@Service
@Transactional
public class CustomerReportServiceImpl implements ICustomerReportService {
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private PlanRepository planRepo;

	public List<Customer> getCustomersByPlan(Integer planId) {
		if(planId == null || !planRepo.existsById(planId))
			throw new NoSuchPlanExistsException("NO SUCH PLAN EXISTS"); 
		else 	
			return customerRepo.findByPlanObj(planId);
	}

	public List<Customer> getCustomersByStatus(Integer statusId) {
		if(statusId == null || !planRepo.existsById(statusId))
			throw new NoSuchPlanExistsException("NO SUCH STATUS EXISTS"); 
		else 	
			return customerRepo.findByStatusObj(statusId);
	}

}
