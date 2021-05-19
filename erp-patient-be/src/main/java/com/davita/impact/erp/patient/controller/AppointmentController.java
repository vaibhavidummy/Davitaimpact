package com.davita.impact.erp.patient.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.davita.impact.erp.patient.model.Appointment;
import com.davita.impact.erp.patient.model.AppointmentStatistics;
import com.davita.impact.erp.patient.model.Inbox;
import com.davita.impact.erp.patient.model.Status;
import com.davita.impact.erp.patient.service.AppointmentService;
import com.davita.impact.erp.patient.feign.client.InboxServiceClient;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * 'Appointment service controller' Bounded Context
 * REST Controller Service
 * @version 1.0 09-04-2021
 * @author VaibhaviB
 * */
@RestController
@RequestMapping(value="appointment")
@CrossOrigin(origins="*", allowedHeaders="*")
@Api(value = "Appointment service controller")
public class AppointmentController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentController.class);
	
	@Autowired
	AppointmentService appointmentService;
	
	
	@ApiOperation(value = "Create the Appointment", response = Appointment.class)
	@ApiResponse(code = 201, message = "Successfully created appointment")
	@PostMapping()
	public ResponseEntity<String> createAppointment(@Valid @RequestBody Appointment appointment) {
		LOGGER.info("Inside create Appointment method of AppointmentController");
		Appointment appointmentStatus = null;
		appointmentStatus = appointmentService.addAppointment(appointment);
		if(appointmentStatus!=null) {
			LOGGER.info("Saved Data::{}",appointmentStatus);
		}
		return new ResponseEntity<String>(appointmentStatus.getAppointmentId(), HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Update the Appointment", response = Appointment.class)
	@ApiResponse(code = 200, message = "Successfully updated appointment")
	@PutMapping(value="/{appointmentId}")
	public ResponseEntity<String> updateAppointment(@Valid @RequestBody Appointment appointment, @PathVariable String appointmentId) {
		LOGGER.info("Inside update Appointment method of AppointmentController");
		Appointment appointmentStatus = null;
		appointment.setAppointmentId(appointmentId);
		appointmentStatus = appointmentService.updateAppointment(appointment, appointmentId);
		if(appointmentStatus!=null) {
			LOGGER.info("Updated Data::{}",appointmentStatus);
			
		}
		return new ResponseEntity<String>(appointmentStatus.getAppointmentId(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Retrieves the Appointment by Appointment ID", response = Appointment.class)
	@ApiResponse(code = 200, message = "Successfully retrieved appointment")
	@GetMapping(value="/{appointmentId}")
	public ResponseEntity<Optional<Appointment>> getAppointment(@PathVariable String appointmentId) {
		LOGGER.info("Inside get appointment method of AppointmentController");
		Optional<Appointment> appointmentStatus=null;
		appointmentStatus =  appointmentService.getAppointment(appointmentId);
		LOGGER.info("Get Appointment Data::{}",appointmentStatus);
		return new ResponseEntity<Optional<Appointment>>(appointmentStatus, HttpStatus.OK);
	}
	 
	
	@ApiOperation(value = "Retrieves the User Scheduled Appointment for date", response = Appointment.class)
	@ApiResponse(code = 200, message = "Successfully retrieved appointment")
	@GetMapping(value="/byuseridanddate")
	public ResponseEntity<List<Appointment>> getScheduledAppointmentByUseIdandDate( @RequestParam String userId,@RequestParam @DateTimeFormat(iso =
			 DateTimeFormat.ISO.DATE) LocalDate date) {
		LOGGER.info("Inside get appointment by user Id and date method of AppointmentController");
		List<Appointment> appointmentStatus=null;
		appointmentStatus = appointmentService.getUserAppointmentforDate(userId, date);
		return new ResponseEntity<List<Appointment>>(appointmentStatus, HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "Retrieves the User Scheduled Appointment between the date", response = Appointment.class)
	@ApiResponse(code = 200, message = "Successfully retrieved appointment")
	@GetMapping(value="/byuseridandwithindate")
	public ResponseEntity<List<AppointmentStatistics>> getScheduledAppointmentByUseIdandbetweenDate( @RequestParam String userId,@RequestParam @DateTimeFormat(iso =
			 DateTimeFormat.ISO.DATE) LocalDate startDate,@RequestParam @DateTimeFormat(iso =
			 DateTimeFormat.ISO.DATE) LocalDate endDate ) {
		LOGGER.info("Inside get appointment by user Id and  between date method of AppointmentController");
		List<AppointmentStatistics> appointmentStatus=null;
		appointmentStatus = appointmentService.getUserAppointmentBetweenDate(userId, startDate,endDate);
		return new ResponseEntity<List<AppointmentStatistics>>(appointmentStatus, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Update the Appointment Status", response = Appointment.class)
	@ApiResponse(code = 200, message = "Successfully updated appointment")
	@PutMapping(value="/{appointmentId}/{status}")
	public ResponseEntity<String> updateAppointmentStatus(@PathVariable String appointmentId, @PathVariable Status status) {
		LOGGER.info("Inside update Appointment status method of AppointmentController");
		String appointmentIdStatus = null;
		appointmentIdStatus = appointmentService.updateAppointmentStatus(appointmentId,status);
		if(appointmentIdStatus!=null) {
			LOGGER.info("Updated Data::{}",appointmentIdStatus);
		}
		return new ResponseEntity<String>(appointmentIdStatus, HttpStatus.OK);
	}
	
	/*
	 * @ApiOperation(value =
	 * "Retrieves the physician Scheduled Appointment for date", response =
	 * Appointment.class)
	 * 
	 * @ApiResponse(code = 200, message = "Successfully retrieved appointment")
	 * 
	 * @GetMapping(value="/byphysicianidanddate") public ResponseEntity<List<Date>>
	 * getScheduledAppointmentbyPhysicianIdByDate( @RequestParam String
	 * physicianId,@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	 * LocalDate date) { LOGGER.
	 * info("Inside get appointment by physcian Id and date method of AppointmentController"
	 * ); List<Date> appointmentStatus=null; appointmentStatus =
	 * appointmentService.getPhysicianAppointmentforDate(physicianId, date); return
	 * new ResponseEntity<List<Date>>(appointmentStatus, HttpStatus.OK);
	 * 
	 * }
	 */
	
}
