package in.davita.impact.erp.patient.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import in.davita.impact.erp.patient.model.Appointment;
import in.davita.impact.erp.patient.service.AppointmentService;
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
@Api(value = "Appointment service controller")
public class AppointmentController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentController.class);
	
	@Autowired
	AppointmentService appointmentService;
	
	@ApiOperation(value = "Create the Appointment", response = Appointment.class)
	@ApiResponse(code = 201, message = "Successfully created appointment")
	@PostMapping()
	public ResponseEntity<Appointment> createAppointment(@Valid @RequestBody Appointment appointment) {
		LOGGER.info("Inside create Appointment method of AppointmentController");
		Appointment appointmentStatus = null;
		appointmentStatus = appointmentService.addAppointment(appointment);
		if(appointmentStatus!=null) {
			LOGGER.info("Saved Data::{}",appointmentStatus);
			
		}
		return new ResponseEntity<Appointment>(appointmentStatus, HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Update the Appointment", response = Appointment.class)
	@ApiResponse(code = 200, message = "Successfully updated appointment")
	@PutMapping(value="/{appointmentId}")
	public ResponseEntity<Appointment> updateAppointment(@Valid @RequestBody Appointment appointment, @PathVariable String appointmentId) {
		LOGGER.info("Inside update Appointment method of AppointmentController");
		Appointment appointmentStatus = null;
		appointment.setAppointmentId(appointmentId);
		appointmentStatus = appointmentService.UpdateAppointment(appointment);
		if(appointmentStatus!=null) {
			LOGGER.info("Updated Data::{}",appointmentStatus);
			
		}
		return new ResponseEntity<Appointment>(appointmentStatus, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Retrieves all Appointment", response = Appointment.class)
	@ApiResponse(code = 200, message = "Successfully retrieved all appointment")
	@GetMapping()
	public ResponseEntity<List<Appointment>> GetAllAppointment() {
		LOGGER.info("Inside get all user method of AppointmentController");
		List<Appointment> appointmentStatus=null;
		appointmentStatus = appointmentService.getAllAppointments();
		if(!appointmentStatus.isEmpty()) {
			LOGGER.info("List of Appointment Data::{}",appointmentStatus);
		}
		return new ResponseEntity<List<Appointment>>(appointmentStatus, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Retrieves the Appointment by Appointment ID", response = Appointment.class)
	@ApiResponse(code = 200, message = "Successfully retrieved appointment")
	@GetMapping(value="/{appointmentId}")
	public ResponseEntity<Optional<Appointment>> getAppointment(@PathVariable String appointmentId) {
		LOGGER.info("Inside get user method of AppointmentController");
		Optional<Appointment> appointmentStatus=null;
		appointmentStatus =  appointmentService.getAppointment(appointmentId);
		LOGGER.info("Get Appointment Data::{}",appointmentStatus);
		return new ResponseEntity<Optional<Appointment>>(appointmentStatus, HttpStatus.OK);
	}
	
}
