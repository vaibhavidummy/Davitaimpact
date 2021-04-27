package com.davita.impact.erp.patient.model;

import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AppointmentStatistics {
	

	@JsonFormat(pattern="yyyy-MM-dd")
	LocalDate date;
	
	long count;
	

}
