package com.davita.impact.erp.patient.service;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.davita.impact.erp.patient.model.Appointment;
import com.davita.impact.erp.patient.model.AppointmentStatistics;
import com.davita.impact.erp.patient.model.Status;


public interface AppointmentService {
	
	Appointment addAppointment(Appointment appointment);
	
	Appointment updateAppointment(Appointment appointmentDtls);
	
	Optional<Appointment> getAppointment(String appointmentId);

	List<Appointment> getUserAppointmentforDate(String userId, LocalDate date);

	List<AppointmentStatistics> getUserAppointmentBetweenDate(String userId, LocalDate startDate, LocalDate endDate);
	
	String updateAppointmentStatus(String appointmentId,Status status);

	Boolean checkForExistingAppointment(Appointment appointment);
	

	/*
	 * List<Date> getPhysicianAppointmentforDate(String physicianId,LocalDate date);
	 */
}
