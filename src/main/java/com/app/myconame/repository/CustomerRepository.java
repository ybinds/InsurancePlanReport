package com.app.myconame.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.myconame.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

//	List<Customer> getByPlan(Integer planId);
//	List<Customer> getByStatus(Integer statusId);
//	List<Customer> getByStatusAndPlan(Integer statusId, Integer planId);
}
