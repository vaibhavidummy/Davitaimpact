/**
 * 'VisitController service controller' Bounded Context
 * REST Controller Service
 * @version 1.0 23-04-2021
 * @author Chetan Phalke
 * */
package com.davita.impact.erp.patient.controller;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.davita.impact.erp.patient.comman.Diagnosis;
import com.davita.impact.erp.patient.comman.ResponseOnOk;
import com.davita.impact.erp.patient.comman.VisitDetailsRespons;
import com.davita.impact.erp.patient.model.PatientDetails;
import com.davita.impact.erp.patient.model.PatientVisit;
import com.davita.impact.erp.patient.repository.PatientVisitRepository;
import com.davita.impact.erp.patient.service.PatientVisitServices;

import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping(value = "/healthcare/visit")
public class VisitController {

	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(VisitController.class);
	@Autowired
	PatientVisitServices patientVisitServices;
	
	
	@PostMapping("/createvisit/")
	@ResponseBody
	@ApiResponse(code = 201, message = "Visit Id Created Successfully", response = ResponseOnOk.class)
	// @Transactional
	public ResponseEntity<ResponseOnOk> createVisit(@RequestBody PatientVisit patientVisit) throws Exception {
		LOGGER.info("Inside createVisit method of VisitController for User Id >>> "+patientVisit.getUserIdfk());
		  PatientVisit creteVisitId = patientVisitServices.creteVisitId(patientVisit);
		ResponseOnOk responseOnOk = new ResponseOnOk();
		responseOnOk.setId(creteVisitId.getId());
		responseOnOk.setMessage("Patient Visit Id Created Successfully");
		responseOnOk.setStatus(201);

		// return new ResponseEntity<String>("Patient Details Added Successfully
		// "+addNewPatients.getId(),HttpStatus.CREATED);
		return new ResponseEntity<ResponseOnOk>(responseOnOk, HttpStatus.CREATED);
		// return addNewPatients.getId();
	}

	//------------- search all VisitId ---------------
	
	@GetMapping("/myvisit/{userId}")
	@ResponseBody
	@ApiResponse(code = 200, message = "Patient Visit Id Details Shown")
	public List<PatientVisit> showPatientVisit(@PathVariable("userId") String id) throws Exception {
		LOGGER.info("Inside showPatientVisit method of VisitController for User Id >>> "+id);
		List<PatientVisit> allVisitofPatient = patientVisitServices.getAllVistofPatient(id);
		return allVisitofPatient;
		
	}
	
	
}
