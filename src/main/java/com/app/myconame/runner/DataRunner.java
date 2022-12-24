package com.app.myconame.runner;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.app.myconame.entity.Customer;
import com.app.myconame.entity.Plan;
import com.app.myconame.entity.Status;
import com.app.myconame.repository.CustomerRepository;
import com.app.myconame.repository.PlanRepository;
import com.app.myconame.repository.StatusRepository;

//@Component
public class DataRunner implements CommandLineRunner {

	@Autowired
	private StatusRepository srepo;
	
	@Autowired
	private PlanRepository prepo;
	
	@Autowired
	private CustomerRepository crepo;
	
	public void run(String... args) throws Exception {

		List<Plan> plist = prepo.findAll();
		
		List<Status> slist = srepo.findAll();
		
		crepo.saveAll(
				Arrays.asList(
						new Customer(101, "Ajay", "ajay@gmail.com", "1231231234", "Male", "789456123", plist.get(2), slist.get(0)),
						new Customer(101, "Bhanu", "bhanu@gmail.com", "4564564561", "Female", "446589654", plist.get(3), slist.get(1)),
						new Customer(101, "Chandu", "chandu@gmail.com", "7897897894", "Male", "98756845", plist.get(2), slist.get(2)),
						new Customer(101, "Dhanush", "dhanush@gmail.com", "3213213214", "Male", "54785695", plist.get(1), slist.get(2))
						));
	}

}
