package com.app.myconame.service;

import java.util.List;

import com.app.myconame.entity.Customer;

public interface ICustomerReportService {
	
	List<Customer> getCustomersByPlan(Integer planId);
	List<Customer> getCustomersByStatus(Integer statusId);
}
