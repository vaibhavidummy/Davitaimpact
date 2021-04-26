package com.citiustech.pms.diagnosis.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	@ResponseStatus(value = HttpStatus.OK)
	public DiagnosisSuccess getAllDiagnosis() {
		LOGGER.info("inside getAllDiagnosis");
		return pmsDiagnosisService.getAllDiagnosis();
	}

	@PostMapping("/diagnosisDetailDesc")
	public ResponseEntity<DiagnosisSuccess> getDiagnosisDetailDesc(@RequestBody DiagnosisModel diagnosisModel) 
    { 
		LOGGER.info("inside getDiagnosisDetailDesc: "+diagnosisModel);
		
		DiagnosisSuccess diagnosisSuccess =  pmsDiagnosisService.getDiagnosisDescription(diagnosisModel);
		return new ResponseEntity<DiagnosisSuccess>(diagnosisSuccess, HttpStatus.CREATED);
    }	
}
