package com.citiustech.pms.diagnosis.controller;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.pms.diagnosis.model.Diagnosis;
import com.citiustech.pms.diagnosis.model.DiagnosisMaster;
import com.citiustech.pms.diagnosis.service.PmsDiagnosisServiceInterface;

@RestController
@RequestMapping(value = "/healthcare")
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
	public List<DiagnosisMaster> getAllDiagnosis() {
		 System.out.println("the all diagnosis is"+ pmsDiagnosisService.getAllDiagnosis());
		 return pmsDiagnosisService.getAllDiagnosis();
		 
		

	}
	
	
	@GetMapping("/getDiagnosisByVisitId")
	public Diagnosis getDiagnosisByVisitId(@RequestBody Diagnosis diagnosis, BindingResult result) //need to check with aditya for list of vistid
	{ 
	
		 return pmsDiagnosisService.getDiagnosisByVisitId(diagnosis);
		 
		 

	}
}
