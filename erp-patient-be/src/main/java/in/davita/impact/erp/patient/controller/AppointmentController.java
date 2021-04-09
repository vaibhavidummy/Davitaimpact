package in.davita.impact.erp.patient.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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

/**
 * 'Appointment service controller' Bounded Context
 * REST Controller Service
 * @version 1.0 09-04-2021
 * @author VaibhaviB
 * */
@RestController
@RequestMapping(value="appointment-service")
public class AppointmentController {
	
	@Autowired
	AppointmentService appointmentService;
	
	@PostMapping(value="/appointment")
	public ResponseEntity<Appointment> createAppointment(@Valid @RequestBody Appointment appointment) {
		Appointment AppointmentStatus = null;
		AppointmentStatus = appointmentService.addAppointment(appointment);
		return new ResponseEntity<Appointment>(AppointmentStatus, HttpStatus.CREATED);
	}
	
	@PutMapping(value="/appointment/{appointmentId}")
	public ResponseEntity<Appointment> updateAppointment(@Valid @RequestBody Appointment appointment, @PathVariable String appointmentId) {
		Appointment AppointmentStatus = null;
		appointment.setAppointmentId(appointmentId);
		AppointmentStatus = appointmentService.UpdateAppointment(appointment);
		return new ResponseEntity<Appointment>(AppointmentStatus, HttpStatus.OK);
	}
	
	@GetMapping(value="/appointment")
	public ResponseEntity<List<Appointment>> GetAllAppointment() {
		List<Appointment> AppointmentStatus=null;
		AppointmentStatus = appointmentService.getAllAppointments();
		return new ResponseEntity<List<Appointment>>(AppointmentStatus, HttpStatus.OK);
	}
	
	@GetMapping(value="/appointment/{appointmentId}")
	public ResponseEntity<Optional<Appointment>> GetAppointment(@PathVariable String appointmentId) {
		Optional<Appointment> AppointmentStatus=null;
		AppointmentStatus =  appointmentService.getAppointment(appointmentId);
		return new ResponseEntity<Optional<Appointment>>(AppointmentStatus, HttpStatus.OK);
	}
	
}
