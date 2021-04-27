package com.davita.impact.erp.patient.service;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.davita.impact.erp.patient.model.Appointment;
import com.davita.impact.erp.patient.model.AppointmentStatistics;
import com.davita.impact.erp.patient.model.Status;
import com.davita.impact.erp.patient.repository.AppointmentRepository;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceTest {
	
	@InjectMocks
	private AppointmentServiceImpl appointmentService;
	
	@Mock
	private AppointmentRepository appointmentRepository;

	@Test
	void addAppointment()
	{
		Appointment mockAppointmentInput=new Appointment("1", "10","john", "140","Smith",
				 LocalDate.of(2021, 12, 12), Date.valueOf("9:00"), Date.valueOf("9:30"),"Meeting title", Status.PENDING, "New Appointment", null, "1");
		Appointment mockAppointmentOutput=new Appointment("1", "10","john", "140","Smith",
				 LocalDate.of(2021, 12, 12), Date.valueOf("9:00"), Date.valueOf("9:30"),"Meeting title", Status.PENDING, "New Appointment", null, "1");
		Mockito.when(appointmentRepository.save(mockAppointmentInput)).thenReturn(mockAppointmentOutput);
		Mockito.when(appointmentRepository.checkForExistingAppointment("10", LocalDate.of(2021, 12, 12),  Date.valueOf("9:00"), Date.valueOf("9:30")))
												.thenReturn(null);
		Appointment result=appointmentService.addAppointment(mockAppointmentInput);
		Appointment expected=mockAppointmentOutput;
		assertEquals(expected, result);
	}
	
	
	
	@Test
	void updateAppointment()
	{
		Appointment mockAppointment=new Appointment("1", "10","john", "140","Smith",
				 LocalDate.of(2021, 12, 12), Date.valueOf("9:00"), Date.valueOf("9:30"),"Meeting title", Status.PENDING, "New Appointment", null, "1");
		
		
		Mockito.when(appointmentRepository.save(mockAppointment)).thenReturn(mockAppointment);
		
		Appointment result=appointmentService.updateAppointment(mockAppointment);
		Appointment expected=mockAppointment;
		assertEquals(expected, result);
	}
	
	@Test
	void getUserAppointmentforDate()
	{
		List<Appointment> mockAppointment = Stream.of(new Appointment("1", "10","john", "140","Smith",
				 									  LocalDate.of(2021, 12, 12), Date.valueOf("9:00"), Date.valueOf("9:30"),"Meeting title", Status.PENDING, "New Appointment", null, "1"),
													  new Appointment("2", "10","john", "140","Smith",
													  LocalDate.of(2021, 12, 12), Date.valueOf("9:00"), Date.valueOf("9:30"),"Meeting title", Status.PENDING, "New Appointment", null, "1"))
													  .collect(Collectors.toList());	
	   Mockito.when(appointmentRepository.findAllByUserIdByDate("10",LocalDate.of(2021, 12, 12))).thenReturn(mockAppointment);
	   List<Appointment> result=appointmentService.getUserAppointmentforDate("10", LocalDate.of(2021, 12, 12) ); 
	   List<Appointment> expected=mockAppointment;
	   assertEquals(expected,result);
		
	}

	@Test
	void getUserAppointmentBetweenDate()
	{
		List<AppointmentStatistics> mockAppointment = Stream.of(new AppointmentStatistics(LocalDate.of(2021, 12, 10), 2),
																new AppointmentStatistics(LocalDate.of(2021, 12, 15), 3))
				  											  .collect(Collectors.toList());
		Mockito.when(appointmentRepository.findAllByUserIdBetweenDate("10",LocalDate.of(2021, 12, 10),LocalDate.of(2021, 12, 15) )).thenReturn(mockAppointment);
		List<AppointmentStatistics> result = appointmentService.getUserAppointmentBetweenDate("10", LocalDate.of(2021, 12, 10),LocalDate.of(2021, 12, 15));
		List<AppointmentStatistics> expected=mockAppointment;
		assertEquals(expected,result);
	}
	  
	@Test
	void updateAppointmentStatus() {
		Optional<Appointment> mockAppointment =Optional.of( new Appointment("1", "10","john", "140","Smith",
				  LocalDate.of(2021, 12, 12), Date.valueOf("9:00"), Date.valueOf("9:30"),"Meeting title", Status.PENDING, "New Appointment", null, "1"));
		Mockito.when(appointmentRepository.findById("1")).thenReturn(mockAppointment);
		Mockito.when(appointmentRepository.save(mockAppointment.get())).thenReturn(mockAppointment.get());
		String result = appointmentService.updateAppointmentStatus("1",Status.REJECTED);
		String expected="1";
		assertEquals(expected,result);
	}
	
	
	// To-do
	// write test case for exception, duplicate record, no record found for getappointment, getappointmentdate, gaet betweendate, while updating status appoinmentid not present
}
