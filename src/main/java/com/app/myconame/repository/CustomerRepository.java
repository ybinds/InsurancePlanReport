package com.app.myconame.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.myconame.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	List<Customer> findByPlanObj(Integer planId);
	List<Customer> findByStatusObj(Integer statusId);
}
