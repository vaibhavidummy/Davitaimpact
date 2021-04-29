package in.davita.impact.erp.appointment.model;

import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Appointment { 
	
	@Id
	@PrimaryKeyColumn(
		      name = "id",type = PrimaryKeyType.PARTITIONED)
	//@GenericGenerator(name = "appointment_sequence_id", strategy = "in.davita.impact.erp.patient.utilities.AppointmentIdGenerator")
	//@GeneratedValue(generator = "appointment_sequence_id")
	// check how to generate primary key
	private String id;
	
	
	@NotBlank(message="PhysicianId is Required")
	private String physicianId;
	
	@NotBlank(message="patientId is Required")
	private String patientId;

	
	@JsonFormat(pattern="yyyy-MM-dd")
	@NotNull(message="Appointment date is required")
	@FutureOrPresent(message="Appointment date must be future or present date") 
    //@Column(nullable = false) - check how to do with cassandra
	 private Date appointmentDate;
	
	
	@Pattern(regexp = "Available|Booked")
	private String appointmentStatus;
	
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm:ss")
	//@Temporal(TemporalType.TIME) - check this
	@NotNull(message="Appointment Start time is required") 
	private Date startTime;
	
	private String description;
	
	private String reasonForChange;
	  
	
}
