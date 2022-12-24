package com.app.myconame.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.app.myconame.dto.SearchCustomer;
import com.app.myconame.entity.Customer;
import com.lowagie.text.DocumentException;

public interface ICustomerReportService {
	
	/*List<Customer> getAllCustomers();
	List<Customer> getCustomersByPlan(Integer planId);
	List<Customer> getCustomersByStatus(Integer statusId);
	List<Customer> getCustomersByStatusAndPlan(Integer statusId, Integer planId);*/
	
	List<Customer> getCustomersBySearch(SearchCustomer scustomer);
	void downloadAsExcel(HttpServletResponse response, SearchCustomer scustomer) throws IOException;
	void downloadAsPdf(HttpServletResponse response, SearchCustomer scustomer) throws DocumentException, IOException;
}
