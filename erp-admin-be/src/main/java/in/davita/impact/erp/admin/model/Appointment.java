package in.davita.impact.erp.admin.model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;


public class Appointment {

	/*
	 * private String title;
	 * 
	 * private String Description;
	 * 
	 * private String Physician; private LocalDate date; private LocalTime time;
	 * private String status; private String Reason; // �In case of edit or delete
	 */	
	
		private String appointmentId;
		private String physicianId;
		private String patientId;
		private Date AppointmentDate;
		private Date reScheduleDate;
		private String appointmentStatus;
		
		
}
