package com.app.myconame.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.myconame.entity.Status;
import com.app.myconame.service.IStatusService;

@RestController
@CrossOrigin
@RequestMapping("/v1/api")
public class StatusRestController {

	@Autowired
	private IStatusService service;
	
	@GetMapping("/statuses")
	public ResponseEntity<List<Status>> getAllStatus(){
		return ResponseEntity.ok(service.getAllStatus());
	}
}
