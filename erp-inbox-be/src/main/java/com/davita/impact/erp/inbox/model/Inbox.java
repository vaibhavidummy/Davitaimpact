package com.davita.impact.erp.inbox.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Inbox {
	
	@Id
	@GenericGenerator(name = "inbox_sequence_id", strategy = "com.davita.impact.erp.inbox.utilities.InboxIdGenerator")
	@GeneratedValue(generator = "inbox_sequence_id")
	private String id;
	
	private String name;
	
	private String type;
	
	private String subject;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm")
	private LocalTime startTime; 
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate date;
	
	private String appointmentId;

}
