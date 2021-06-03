/**
 * 'PatientDetails service controller' Bounded Context
 * REST Controller Service
 * @version 1.0 23-04-2021
 * @author Chetan Phalke
 * */
package com.davita.impact.erp.patient.controller;



import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.davita.impact.erp.patient.comman.ResponseOnOk;
import com.davita.impact.erp.patient.model.Allergies;
import com.davita.impact.erp.patient.model.LanguageKnown;
import com.davita.impact.erp.patient.model.PatientDetails;
import com.davita.impact.erp.patient.service.LanguageServices;
import com.davita.impact.erp.patient.service.PatientServices;
import com.davita.impact.erp.patient.service.PatientVisitServices;
import com.davita.impact.erp.patient.service.allergiesServices;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;

@RestController
@Api(value = "Patient Details service controller")
@RequestMapping(value = "/healthcare")
public class PatientController {

	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(PatientController.class);
	@Autowired
	PatientServices patientServices;

	@Autowired
	PatientVisitServices patientVisitServices;

	@Autowired
	allergiesServices allergiesServices;

	@Autowired
	LanguageServices languageServices;
	
	

	
	/* insert new Patainent Details */
	@PostMapping("/patient/")
	@ResponseBody
	@ApiResponse(code = 201, message = "Patient Details Added Successfully", response = ResponseOnOk.class)
	// @Transactional
	public ResponseEntity<ResponseOnOk> createUser(@Valid @RequestBody PatientDetails patient) {
		LOGGER.info("Inside createUser method of PatientController For Patient Name >>> "+patient.getBasicDetails().getFirstName());
		PatientDetails addNewPatients = patientServices.addNewPatient(patient);
		ResponseOnOk responseOnOk = new ResponseOnOk();
		responseOnOk.setId(addNewPatients.getId());
		responseOnOk.setMessage("Patient Details Added Successfully");
		responseOnOk.setStatus(201);

		// return new ResponseEntity<String>("Patient Details Added Successfully
		// "+addNewPatients.getId(),HttpStatus.CREATED);
		return new ResponseEntity<ResponseOnOk>(responseOnOk, HttpStatus.CREATED);
		// return addNewPatients.getId();
	}

	
	/* update patient Details */
	@PutMapping("/patient/")
	@ResponseBody
	@ApiResponse(code = 200, message = "Patient Details Added Successfully", response = ResponseOnOk.class)
	// @Transactional
	public ResponseEntity<ResponseOnOk> updatePatientDetails(@Valid @RequestBody PatientDetails patient)
			throws Exception {
		LOGGER.info("Inside updatePatientDetails method of PatientController for PatientDetails Id >>>"+patient.getId());
		PatientDetails updatePatient = patientServices.updatePatient(patient);
		ResponseOnOk responseOnOk = new ResponseOnOk();
		responseOnOk.setId(updatePatient.getId());
		responseOnOk.setMessage("Patient Details Update Successfully");
		responseOnOk.setStatus(200);
		return new ResponseEntity<ResponseOnOk>(responseOnOk, HttpStatus.OK);

	}

	
	/* get  Details of Specific Patient */
	@GetMapping("/patient/{patientID}")
	public ResponseEntity<PatientDetails> getPatientDetailsByID(@PathVariable("patientID") String id) {
		LOGGER.info("Inside getPatientDetailsByID method of PatientController fro PatientId >>> "+id);
		// LOGGER.info("getPatientDetailsByID...");
		PatientDetails patientById = patientServices.getPatientById(id);
		return new ResponseEntity<PatientDetails>(patientById, HttpStatus.OK);

	}

	/* for fetch all patientDetails */
	@GetMapping("/patient/")
	public List<PatientDetails> getAllPatientDetails() {
		LOGGER.info("Inside getAllPatientDetails method of PatientController");
		List<PatientDetails> allPatient = patientServices.getAllPatient();
		return allPatient;
	}

	/* Find All Allergy */

	@GetMapping("/allergies")
	public List<Allergies> getAllAllergiesDetails() {
		LOGGER.info("Inside getAllAllergiesDetails method of PatientController");
		List<Allergies> allAllergies = allergiesServices.getAllAllergies();
		return allAllergies;
	}

	/* Find All Languages */

	@GetMapping("/languages")
	public List<LanguageKnown> getAllLangugeDetails() {
		LOGGER.info("Inside getAllLangugeDetails method of PatientController");
		List<LanguageKnown> allLanguageKnown = languageServices.getAllLangwages();
		return allLanguageKnown;
	}

	
	/*
	 * Any instance of RuntimeException within the endpoint functions and return a
	 * 500 response.
	 */
	/*
	 * @ExceptionHandler(RuntimeException.class) public final
	 * ResponseEntity<Exception> handleAllExceptions(RuntimeException ex) {
	 * LOGGER.info("Inside createUser method of PatientController"); return new
	 * ResponseEntity<Exception>(ex, HttpStatus.INTERNAL_SERVER_ERROR); }
	 */

	
	
	
}