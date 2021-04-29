package com.citiustech.pms.diagnosis.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.pms.diagnosis.model.DiagnosisModel;
import com.citiustech.pms.diagnosis.model.DiagnosisSuccess;
import com.citiustech.pms.diagnosis.service.PmsDiagnosisServiceInterface;

@RestController
@RequestMapping(value = "/healthcare/diagnosis")
public class PmsDiagnosisController {

	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(PmsDiagnosisController.class);

	@Autowired
	private PmsDiagnosisServiceInterface pmsDiagnosisService;
	
	/*
	 * @PostMapping("/diagnosis") public Diagnosis createDiagnosis(@RequestBody
	 * Diagnosis diagnosis, BindingResult result) {
	 * LOGGER.info("ADD DIAGNOSIS METHOD"); return
	 * pmsDiagnosisService.addDiagnosis(diagnosis);
	 * 
	 * }
	 */

	@GetMapping("/getalldiagnosis")
    public ResponseEntity<DiagnosisSuccess> getAllDiagnosis() {
		
		LOGGER.info("inside getAllDiagnosis ");
		
		DiagnosisSuccess diagnosisSuccess =  pmsDiagnosisService.getAllDiagnosis();
		return new ResponseEntity<DiagnosisSuccess>(diagnosisSuccess, HttpStatus.OK);
    }
	
	@GetMapping("/{patientVisitId}")
    public ResponseEntity<DiagnosisSuccess> getProcedureByVisitId(@PathVariable("patientVisitId") String patientVisitId) 
    { 
		DiagnosisSuccess procedureByVisitId =null;
		procedureByVisitId = pmsDiagnosisService.getProcedureByVisitId(patientVisitId);
         return new ResponseEntity<DiagnosisSuccess>(procedureByVisitId , HttpStatus.OK);
    }

	@PostMapping("/diagnosisDetailDesc")
	public ResponseEntity<DiagnosisSuccess> getDiagnosisDetailDesc(@RequestBody DiagnosisModel diagnosisModel) 
    { 
		LOGGER.info("inside getDiagnosisDetailDesc: "+diagnosisModel);
		
		DiagnosisSuccess diagnosisSuccess =  pmsDiagnosisService.getDiagnosisDescription(diagnosisModel);
		return new ResponseEntity<DiagnosisSuccess>(diagnosisSuccess, HttpStatus.CREATED);
    }	
	
}