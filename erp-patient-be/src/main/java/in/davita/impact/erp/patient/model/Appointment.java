package in.davita.impact.erp.patient.model;



import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Appointment {
	
	@Id
	@GenericGenerator(name = "appointment_sequence_id", strategy = "in.davita.impact.erp.patient.utilities.AppointmentIdGenerator")
	@GeneratedValue(generator = "appointment_sequence_id")
	private String appointmentId;
	
	
	@NotBlank(message="PhysicianId is Required")
	private String physicianId;
	
	@NotBlank(message="patientId is Required")
	private String patientId;

	
	@JsonFormat(pattern="yyyy-MM-dd")
	@NotNull(message="Appointment date is required")
	@FutureOrPresent(message="Appointment date must be future or present date") 
    @Column(nullable = false)
	 private Date appointmentDate;
	
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@FutureOrPresent(message="Appointment date must be past or present date") 
	private Date reScheduleDate;
	
	@Pattern(regexp = "Available|Booked")
	private String appointmentStatus;
	
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm:ss")
	@Temporal(TemporalType.TIME)
	@NotNull(message="Appointment Start time is required") 
	private Date startTime;
	  
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm:ss")
	@Temporal(TemporalType.TIME)
    @NotNull(message="Appointment End time is required") 
	private Date endTime;
	 
	
	
	
}
