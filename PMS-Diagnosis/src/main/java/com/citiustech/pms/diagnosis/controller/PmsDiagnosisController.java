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

import com.citiustech.pms.diagnosis.model.Diagnosis;
import com.citiustech.pms.diagnosis.model.DiagnosisModel;
import com.citiustech.pms.diagnosis.model.DiagnosisSuccess;
import com.citiustech.pms.diagnosis.service.PmsDiagnosisServiceInterface;

@RestController
@RequestMapping(value = "/healthcare/diagnosis")
public class PmsDiagnosisController {

	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(PmsDiagnosisController.class);

	@Autowired
	private PmsDiagnosisServiceInterface pmsDiagnosisService;
	
	@PostMapping("/addDiagnosis")
	public ResponseEntity<Diagnosis> addDiagnosis(@RequestBody Diagnosis diagnosisDetail) {
		
		LOGGER.info("Reached addDiagnosis");
		
		Diagnosis addDiagnosisDetail = null;
		addDiagnosisDetail = pmsDiagnosisService.addDiagnosis(diagnosisDetail);
		return new ResponseEntity<Diagnosis>(addDiagnosisDetail, HttpStatus.CREATED);
	}
	
	@GetMapping("/getalldiagnosis")
    public ResponseEntity<DiagnosisSuccess> getAllDiagnosis() {
		
		LOGGER.info("Reached getAllDiagnosis ");
		
		DiagnosisSuccess diagnosisSuccess =  pmsDiagnosisService.getAllDiagnosis();
		return new ResponseEntity<DiagnosisSuccess>(diagnosisSuccess, HttpStatus.OK);
    }
	
	@GetMapping("/{patientVisitId}")
    public ResponseEntity<DiagnosisSuccess> getDiagnosisByVisitId(@PathVariable("patientVisitId") String patientVisitId) 
    { 
		LOGGER.info("Reached getDiagnosisByVisitId ");
		
		DiagnosisSuccess procedureByVisitId =null;
		procedureByVisitId = pmsDiagnosisService.getProcedureByVisitId(patientVisitId);
         return new ResponseEntity<DiagnosisSuccess>(procedureByVisitId , HttpStatus.OK);
    }

	@PostMapping("/diagnosisDetailDesc")
	public ResponseEntity<DiagnosisSuccess> getDiagnosisDetailDesc(@RequestBody DiagnosisModel diagnosisModel) 
    { 
		LOGGER.info("Reached getDiagnosisDetailDesc: {} ",diagnosisModel);
		
		DiagnosisSuccess diagnosisSuccess =  pmsDiagnosisService.getDiagnosisDescription(diagnosisModel);
		return new ResponseEntity<DiagnosisSuccess>(diagnosisSuccess, HttpStatus.CREATED);
    }	
	
}