package com.app.myconame.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.myconame.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	/*@Query("SELECT c FROM Customer c INNER JOIN c.planObj AS p WHERE p.planId=:planId")
	List<Customer> getCustomersByPlan(Integer planId);

	@Query("SELECT c FROM Customer c INNER JOIN c.statusObj AS s WHERE s.statusId=:statusId")
	List<Customer> getCustomersByStatus(Integer statusId);
	
	@Query("SELECT c FROM Customer c INNER JOIN c.statusObj AS s INNER JOIN c.planObj AS p WHERE s.statusId=:statusId AND p.planId=:planId")
	List<Customer> getCustomersByStatusAndPlan(Integer statusId, Integer planId);*/
}
