package com.app.myconame.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.myconame.entity.Plan;
import com.app.myconame.repository.PlanRepository;

@Service
public class PlanServiceImpl implements IPlanService {

	@Autowired
	private PlanRepository repo;
	
	public List<Plan> getAllPlans() {
		return repo.findAll();
	}

}
