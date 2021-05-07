package com.davita.impact.erp.patient.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Inbox implements Serializable{
	
	
	private static final long serialVersionUID = 4L;
	
	private String id;
	
	private String name;
	
	private String type;
	
	private String subject;
	
	private Status status;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm")
	private LocalTime startTime; 
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate date;
	
	private String appointmentId;

}

