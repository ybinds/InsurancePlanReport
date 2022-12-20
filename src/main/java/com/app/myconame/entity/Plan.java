package com.app.myconame.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="plans")
public class Plan {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer planId;
	
	private String planName;
	private Boolean planActive;
}
