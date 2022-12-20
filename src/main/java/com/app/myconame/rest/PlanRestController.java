package com.app.myconame.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.myconame.entity.Plan;
import com.app.myconame.service.IPlanService;

@RestController
@CrossOrigin
@RequestMapping("/v1/api")
public class PlanRestController {

	@Autowired
	private IPlanService service;
	
	@GetMapping("/plans")
	public ResponseEntity<List<Plan>> getAllPlans(){
		return ResponseEntity.ok(service.getAllPlans());
	}
}
