package com.davita.impact.erp.patient.controller;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.mockito.*;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.davita.impact.erp.patient.controller.AppointmentController;
import com.davita.impact.erp.patient.model.Appointment;
import com.davita.impact.erp.patient.model.AppointmentStatistics;
import com.davita.impact.erp.patient.model.Status;
import com.davita.impact.erp.patient.service.AppointmentService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;



//@ImportAutoConfiguration(RefreshAutoConfiguration.class)
@WebMvcTest(AppointmentController.class)
@RunWith(SpringRunner.class)
class AppointmentControllerTest  {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AppointmentService appointmentService;	
	
	ObjectMapper objectMapper = new ObjectMapper();
	 
	  
	  @Test 
	  public void createAppointment() throws Exception
	  { 
		   Appointment mockAppointment =  new Appointment("1", "10","john",
					  "140","Smith", LocalDate.of(2021, 12, 12), LocalTime.parse("09:00"), LocalTime.parse("09:30"),"Meeting title",
					  Status.PENDING, "New Appointment", "reason", "1");
		   
		   Mockito.when(appointmentService.addAppointment(Mockito.any())).thenReturn(
					  mockAppointment); 
		  
		   
		   RequestBuilder request = MockMvcRequestBuilders.post("/appointment")
	                									   .contentType(MediaType.APPLICATION_JSON)
	                									   .content(objectMapper.writeValueAsString(mockAppointment));
		   MvcResult mvcResult = mockMvc.perform(request)
				   						.andExpect(status().isCreated())
				   						.andReturn();
		   String resultContext= mvcResult.getResponse().getContentAsString();
		   String response=objectMapper.readValue(resultContext, String.class);
		   assertNotNull(response);
	  }
	
	  @Test 
	  public void updateAppointment() throws Exception
	  {
		Appointment mockAppointment =  new Appointment("1", "10","john",
				  "140","Smith", LocalDate.of(2021, 12, 12), LocalTime.parse("09:00"), LocalTime.parse("09:30"),"Meeting title",
				  Status.PENDING, "New Appointment", "reason", "1");
	   
	   Mockito.when(appointmentService.updateAppointment(Mockito.any())).thenReturn(
				  mockAppointment); 
	  
	   
	   RequestBuilder request = MockMvcRequestBuilders.put("/appointment/1")
                									   .contentType(MediaType.APPLICATION_JSON)
                									   .content(objectMapper.writeValueAsString(mockAppointment));
	   MvcResult mvcResult = mockMvc.perform(request)
			   						.andExpect(status().isOk())
			   						.andReturn();
	   String resultContext= mvcResult.getResponse().getContentAsString();
	   String response=objectMapper.readValue(resultContext, String.class);
	   assertNotNull(response);
		  
	  }
	  
	  @Test 
	  public void getAppointmentForAppointmentId() throws Exception
	  { 
		  Optional<Appointment> mockAppointment = Optional.of(new Appointment("1", "10","john",
					  "140","Smith", LocalDate.of(2021, 12, 12),LocalTime.parse("09:00"), LocalTime.parse("09:30"),"Meeting title",
					  Status.PENDING, "New Appointment", "reason", "1"));
		   
		   Mockito.when(appointmentService.getAppointment(Mockito.any())).thenReturn(
					  mockAppointment); 
		  
		   
		   RequestBuilder request = MockMvcRequestBuilders.get("/appointment/1")
	                									   .contentType(MediaType.APPLICATION_JSON);
		   
		   
		   MvcResult mvcResult = mockMvc.perform(request)
				   						.andExpect(status().isOk())
				   						.andReturn();
		   String resultContext= mvcResult.getResponse().getContentAsString();
		   Appointment response=objectMapper.readValue(resultContext, Appointment.class);
		   assertNotNull(response);
	  }
	  
	  @Test
	  public void getScheduledAppointmentByUseIdandDate() throws Exception
	  {
		  List<Appointment> mockAppointment = Stream.of(new Appointment("1", "10","john", "140","Smith",
												  LocalDate.of(2021, 12, 12), LocalTime.parse("09:00"), LocalTime.parse("09:30"),"Meeting title", Status.PENDING, "New Appointment", null, "1"),
											  new Appointment("2", "10","john", "140","Smith",
											  LocalDate.of(2021, 12, 12), LocalTime.parse("09:00"), LocalTime.parse("09:30"),"Meeting title", Status.PENDING, "New Appointment", null, "1"))
											  .collect(Collectors.toList());
	   Mockito.when(appointmentService.getUserAppointmentforDate(Mockito.any(),Mockito.any())).thenReturn(
				  mockAppointment); 
	   RequestBuilder request = MockMvcRequestBuilders.get("/appointment/byuseridanddate?userId=1&date=2021-12-12")
                									   .contentType(MediaType.APPLICATION_JSON);
	   
	   MvcResult mvcResult = mockMvc.perform(request)
			   						.andExpect(status().isOk())
			   						.andReturn();
	   String resultContext= mvcResult.getResponse().getContentAsString();
	   List<Appointment> response= objectMapper.readValue(resultContext, new TypeReference<List<Appointment>>(){});
	   assertNotNull(response);
	  }
	  
	  @Test
	  public void getScheduledAppointmentByUseIdandbetweenDate() throws Exception
	  {
		  AppointmentStatistics appointmentStatistics1= new AppointmentStatistics(LocalDate.of(2021, 12, 10),2);
		  AppointmentStatistics appointmentStatistics2= new AppointmentStatistics(LocalDate.of(2021, 12, 12),3);
			
	   List<AppointmentStatistics> mockAppointment = Stream.of(appointmentStatistics1,appointmentStatistics2)
					  											  .collect(Collectors.toList());
	   Mockito.when(appointmentService.getUserAppointmentBetweenDate(Mockito.any(), Mockito.any(),Mockito.any())).thenReturn(
				  mockAppointment); 
	   RequestBuilder request = MockMvcRequestBuilders.get("/appointment/byuseridandwithindate?userId=11&startDate=2021-12-11&endDate=2021-12-15")
                									   .contentType(MediaType.APPLICATION_JSON);
	   
	   MvcResult mvcResult = mockMvc.perform(request)
			   						.andExpect(status().isOk())
			   						.andReturn();
	   String resultContext= mvcResult.getResponse().getContentAsString();
	   List<AppointmentStatistics> response= objectMapper.readValue(resultContext, new TypeReference<List<AppointmentStatistics>>(){});
	   assertNotNull(response);
	  }
	  
	  @Test
	  public void updateAppointmentStatus() throws Exception
	  {
			
	   
	   Mockito.when(appointmentService.updateAppointmentStatus(Mockito.any(),Mockito.any())).thenReturn("1"); 
	   RequestBuilder request = MockMvcRequestBuilders.put("/appointment/1/ACCEPTED")
                									   .contentType(MediaType.APPLICATION_JSON);
	   
	   MvcResult mvcResult = mockMvc.perform(request)
			   						.andExpect(status().isOk())
			   						.andReturn();
	   String resultContext= mvcResult.getResponse().getContentAsString();
	   String response=objectMapper.readValue(resultContext, String.class);
	   assertNotNull(response);
	  }
	  
	  
}
