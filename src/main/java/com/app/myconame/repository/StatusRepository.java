package com.app.myconame.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.myconame.entity.Status;

public interface StatusRepository extends JpaRepository<Status, Integer> {

}
