package com.app.myconame.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.myconame.entity.Status;
import com.app.myconame.repository.StatusRepository;

@Service
public class StatusServiceImpl implements IStatusService{

	@Autowired
	private StatusRepository repo;
	
	public List<Status> getAllStatus() {
		return repo.findAll();
	}

}
