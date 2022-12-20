package com.app.myconame.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.myconame.entity.Plan;

public interface PlanRepository extends JpaRepository<Plan, Integer> {

}
