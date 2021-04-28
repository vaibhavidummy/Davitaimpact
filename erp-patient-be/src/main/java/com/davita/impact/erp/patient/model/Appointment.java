package com.davita.impact.erp.patient.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import com.davita.impact.erp.patient.utilities.Auditable;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Appointment extends Auditable<String> {
	
	@Id
	@GenericGenerator(name = "appointment_sequence_id", strategy = "com.davita.impact.erp.patient.utilities.AppointmentIdGenerator")
	@GeneratedValue(generator = "appointment_sequence_id")
	private String appointmentId;
	
	@NotBlank(message="Physician Id is Required")
	private String physicianId;
	
	@NotBlank(message="Physician Name is Required")
	private String physicianName;
	
	@NotBlank(message="PatientId is Required")
	private String patientId;
	
	@NotBlank(message="Patient Name is Required")
	private String patientName;
	
	@NotNull(message="Appointment date is required")
	@FutureOrPresent(message="Appointment date must be future or present date") 
    @Column(nullable = false)
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate date;
	

	@NotNull(message="Appointment Start time is required")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm")
	@Temporal(TemporalType.TIME)
	private Date startTime;
	
	@NotNull(message="Appointment end time is required")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm")
	@Temporal(TemporalType.TIME)
	private Date endTime;
	
	@NotBlank(message="Meeting Title is Required")
	@Size(max = 50, message = "Meeting tile length should be 50")
	private String meetingTitle;
	
	@NotNull(message = "Appointment Status is required")
	@Enumerated(EnumType.STRING)
	private Status status;
	
	private String description;
	
	private String reasonForChange;
	
	private String patientVisitDetailId;
	
	
}