package com.app.myconame.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchCustomer {

	private Integer plan_id;
	private Integer status_id;
	
	@JsonFormat(pattern="MM/dd/yyyy")
	private LocalDate start_date;
	
	@JsonFormat(pattern="MM/dd/yyyy")
	private LocalDate end_date;
}
