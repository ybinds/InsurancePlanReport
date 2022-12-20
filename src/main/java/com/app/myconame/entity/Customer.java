package com.app.myconame.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="customers")
public class Customer {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer customerId;
	@Column(name="name")
	private String customerName;
	@Column(name="email")
	private String customerEmail;
	@Column(name="phone")
	private String customerPhone;
	@Column(name="gender")
	private String customerGender;
	@Column(name="ssn")
	private String customerSSN;
	
	@ManyToOne
	@JoinColumn(name="planId")
	private Plan planObj;
	
	@ManyToOne
	@JoinColumn(name="statusId")
	private Status statusObj;
	
	@JsonFormat(pattern = "mm/dd/yyyy")
	private LocalDate customerPlanStartDate;
	
	@JsonFormat(pattern = "mm/dd/yyyy")
	private LocalDate customerPlanEndDate;
	
	//private Boolean customerActive;
}
