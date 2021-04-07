package in.davita.impact.erp.patient.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Appointment {
	
	@Id
	private String appointmentId;
	
	
	//@NotBlank(message="PhysicianId is Required")
	@NotNull(message = "Physician Id is Mandatory")
	private String physicianId;
	
	//@NotNull
	//@NotBlank
	private String patientId;
	
	/*
	 * @NotNull
	 * 
	 * @NotBlank
	 * 
	 * @FutureOrPresent private Date AppointmentDate;
	 * 
	 * @FutureOrPresent private Date reScheduleDate;
	 * 
	 * 
	 * private String appointmentStatus;
	 */
}
