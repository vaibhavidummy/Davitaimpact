package com.davita.impact.erp.patient.service;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.constraints.AssertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.davita.impact.erp.patient.exception.EntityDetailsFoundException;
import com.davita.impact.erp.patient.exception.EntityDetailsNotFoundException;
import com.davita.impact.erp.patient.feign.client.InboxServiceClient;
import com.davita.impact.erp.patient.model.Appointment;
import com.davita.impact.erp.patient.model.AppointmentStatistics;
import com.davita.impact.erp.patient.model.Status;
import com.davita.impact.erp.patient.repository.AppointmentRepository;
import com.davita.impact.erp.patient.service.AppointmentServiceImpl;
import com.sun.xml.bind.v2.schemagen.xmlschema.Appinfo;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceTest {
	
	@InjectMocks
	private AppointmentServiceImpl appointmentService;
	
	@Mock
	private AppointmentRepository appointmentRepository;
	
	@Mock
	InboxServiceClient inboxServiceClient;

	@Test
	void addAppointment() throws ParseException
	{
		Appointment mockAppointmentInput=new Appointment("1", "10","john", "140","Smith",
				 LocalDate.of(2021, 12, 12),LocalTime.parse("09:00"), LocalTime.parse("09:30"),"Meeting title", Status.PENDING, "New Appointment", null, "1");
		Appointment mockAppointmentOutput=new Appointment("1", "10","john", "140","Smith",
				 LocalDate.of(2021, 12, 12), LocalTime.parse("09:00"), LocalTime.parse("09:30"),"Meeting title", Status.PENDING, "New Appointment", null, "1");
		Mockito.when(appointmentRepository.save(mockAppointmentInput)).thenReturn(mockAppointmentOutput);
		Mockito.when(appointmentRepository.checkForExistingAppointment("10", LocalDate.of(2021, 12, 12),  LocalTime.parse("09:00"), LocalTime.parse("09:30")))
												.thenReturn(null);
		Mockito.when(inboxServiceClient.createInbox(Mockito.any())).thenReturn("INBOX1");
		
		Appointment result=appointmentService.addAppointment(mockAppointmentInput);
		Appointment expected=mockAppointmentOutput;
		assertEquals(expected, result);
		
	}
	

	
	@Test
	void updateAppointment() throws ParseException
	{
		Appointment mockAppointment=new Appointment("1", "10","john", "140","Smith",
				 LocalDate.of(2021, 12, 12), LocalTime.parse("09:00"), LocalTime.parse("09:30"),"Meeting title", Status.PENDING, "New Appointment", null, "1");
		Optional<Appointment> optionalMockAppointment=Optional.of(mockAppointment);
				
		Mockito.when(appointmentRepository.save(mockAppointment)).thenReturn(mockAppointment);
		Mockito.when(appointmentRepository.findById("1")).thenReturn(optionalMockAppointment);
		
		Appointment result=appointmentService.updateAppointment(mockAppointment,"1");
		Appointment expected=mockAppointment;
		assertEquals(expected, result);
	}
	
	@Test
	void updateAppointmentWhenAppointmentNotExistsForGivenId() throws ParseException
	{
		Appointment mockAppointment=new Appointment("1", "10","john", "140","Smith",
				 LocalDate.of(2021, 12, 12), LocalTime.parse("09:00"), LocalTime.parse("09:30"),"Meeting title", Status.PENDING, "New Appointment", null, "1");
		Optional<Appointment> optionalMockAppointment=Optional.empty();
				
		
		Mockito.when(appointmentRepository.findById("1")).thenReturn(optionalMockAppointment);
		try
		{
			appointmentService.updateAppointment(mockAppointment,"1");
		}catch(EntityDetailsNotFoundException e)
		{
			assertNotNull(e);
		}
	}
	
	@Test
	void getAppointment() throws ParseException
	{
	 Optional<Appointment> mockAppointment =Optional.of( new Appointment("1", "10","john", "140","Smith",
				  LocalDate.of(2021, 12, 12), LocalTime.parse("09:00"), LocalTime.parse("09:30"),"Meeting title", Status.PENDING, "New Appointment", null, "1"));
	   Mockito.when(appointmentRepository.findById("1")).thenReturn(mockAppointment);
	   Optional<Appointment> result=appointmentService.getAppointment("1"); 
	   Optional<Appointment> expected=Optional.of( new Appointment("1", "10","john", "140","Smith",
				  LocalDate.of(2021, 12, 12), LocalTime.parse("09:00"), LocalTime.parse("09:30"),"Meeting title", Status.PENDING, "New Appointment", null, "1"));;
	   assertEquals(expected,result);
		
	}
	
	@Test
	void getAppointmentForInvalidAppointmetId() throws ParseException
	{
	 Optional<Appointment> mockAppointment1 = Optional.empty();
	   Mockito.when(appointmentRepository.findById("2")).thenReturn(mockAppointment1);
	  
		try {
			appointmentService.getAppointment("2");
			} catch (EntityDetailsNotFoundException e) {
				
				assertNotNull(e);
			} 
	}
	
	
	
	@Test
	void getUserAppointmentforDate() throws ParseException
	{
		List<Appointment> mockAppointment = Stream.of(new Appointment("1", "10","john", "140","Smith",
				 									  LocalDate.of(2021, 12, 12), LocalTime.parse("09:00"), LocalTime.parse("09:30"),"Meeting title", Status.PENDING, "New Appointment", null, "1"),
													  new Appointment("2", "10","john", "140","Smith",
													  LocalDate.of(2021, 12, 12), LocalTime.parse("09:00"), LocalTime.parse("09:30"),"Meeting title", Status.PENDING, "New Appointment", null, "1"))
													  .collect(Collectors.toList());	
	   Mockito.when(appointmentRepository.findAllByUserIdByDate("10",LocalDate.of(2021, 12, 12))).thenReturn(mockAppointment);
	   List<Appointment> result=appointmentService.getUserAppointmentforDate("10", LocalDate.of(2021, 12, 12) ); 
	   List<Appointment> expected=mockAppointment;
	   assertEquals(expected,result);
		
	}
 
	@Test
	void getUserAppointmentforDateWhenNoRecordPresent() throws ParseException
	{
	   List<Appointment> mockAppointment = new ArrayList<>();
	   Mockito.when(appointmentRepository.findAllByUserIdByDate("10",LocalDate.of(2021, 12, 12))).thenReturn(mockAppointment);
		try {
				   appointmentService.getUserAppointmentforDate("10", LocalDate.of(2021, 12, 12) ); 
			} catch (EntityDetailsNotFoundException e) {
						
						assertNotNull(e);
			} 
		
	}
	
	@Test
	void getUserAppointmentBetweenDate()
	{
		AppointmentStatistics appointmentStatistics1= new AppointmentStatistics(LocalDate.of(2021, 12, 10),2);
		
		AppointmentStatistics appointmentStatistics2= new AppointmentStatistics(LocalDate.of(2021, 12, 12),3);
		
		List<AppointmentStatistics> mockAppointment = Stream.of(appointmentStatistics1,appointmentStatistics2)
				  											  .collect(Collectors.toList());
		Mockito.when(appointmentRepository.findAllByUserIdBetweenDate("10",LocalDate.of(2021, 12, 10),LocalDate.of(2021, 12, 15) )).thenReturn(mockAppointment);
		List<AppointmentStatistics> result = appointmentService.getUserAppointmentBetweenDate("10", LocalDate.of(2021, 12, 10),LocalDate.of(2021, 12, 15));
		List<AppointmentStatistics> expected=mockAppointment;
		assertEquals(expected,result);
	}
	
	@Test
	void getUserAppointmentBetweenDateWhenNoRecordPresent() throws ParseException
	{
	   List<AppointmentStatistics> mockAppointment = new ArrayList<>();
	   Mockito.when(appointmentRepository.findAllByUserIdBetweenDate(Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(mockAppointment);
	   
		try {
			appointmentService.getUserAppointmentBetweenDate("10", LocalDate.of(2021, 12, 10),LocalDate.of(2021, 12, 15)); 
			} catch (EntityDetailsNotFoundException e) {
						assertNotNull(e);
			} 
	}
	  
	@Test
	void updateAppointmentStatus() throws ParseException {
		Optional<Appointment> mockAppointment =Optional.of( new Appointment("1", "10","john", "140","Smith",
				  LocalDate.of(2021, 12, 12), LocalTime.parse("09:00"), LocalTime.parse("09:30"),"Meeting title", Status.PENDING, "New Appointment", null, "1"));
		Mockito.when(appointmentRepository.findById("1")).thenReturn(mockAppointment);
		Mockito.when(appointmentRepository.save(mockAppointment.get())).thenReturn(mockAppointment.get());
		String result = appointmentService.updateAppointmentStatus("1",Status.REJECTED);
		String expected="1";
		assertEquals(expected,result);
	}
	
	@Test
	void updateAppointmentStatusForNonExistingAppointmentId() throws ParseException {
		Optional<Appointment> mockAppointment =	Optional.empty();
		Mockito.when(appointmentRepository.findById("1")).thenReturn(mockAppointment);
		try{
			appointmentService.updateAppointmentStatus("1",Status.REJECTED);
		}catch(EntityDetailsNotFoundException e)
		{
			assertNotNull(e);
		}
		
	}
	
	@Test
	void checkForExistingAppointmentWhenAppointmentisNotPresent() throws ParseException
	{
		Appointment mockAppointmentInput=new Appointment(null, "10","john", "140","Smith",
				 LocalDate.of(2021, 12, 12), LocalTime.parse("09:00"), LocalTime.parse("09:30"),"Meeting title", Status.PENDING, "New Appointment", null, "1");
		Appointment mockAppointment=null;
		Mockito.when(appointmentRepository.checkForExistingAppointment(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(mockAppointment);
		Boolean result = appointmentService.checkForExistingAppointment(mockAppointmentInput);
		assertEquals(false, result);
		
	}
	
	@Test
	void checkForExistingAppointmentWhenAppointmentisPresent() throws ParseException
	{
		Appointment mockAppointmentInput=new Appointment(null, "10","john", "140","Smith",
				 LocalDate.of(2021, 12, 12), LocalTime.parse("09:00"), LocalTime.parse("09:30"),"Meeting title", Status.PENDING, "New Appointment", null, "1");
		Appointment mockAppointment=new Appointment("1", "10","john", "140","Smith",
				 LocalDate.of(2021, 12, 12), LocalTime.parse("09:00"), LocalTime.parse("09:30"),"Meeting title", Status.PENDING, "New Appointment", null, "1");
		
		Mockito.when(appointmentRepository.checkForExistingAppointment(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(mockAppointment);
		try {
				appointmentService.checkForExistingAppointment(mockAppointmentInput);
		}catch(EntityDetailsFoundException e)
		{
			assertNotNull(e);
		}
	}
	
}
