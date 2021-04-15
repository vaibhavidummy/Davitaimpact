package com.citiustech.pms.diagnosis.controller;

import java.util.List;



import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.pms.diagnosis.model.Diagnosis;
import com.citiustech.pms.diagnosis.model.DiagnosisMaster;
import com.citiustech.pms.diagnosis.model.DiagnosisModel;
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
	
	
	
	
	
	
	  @PostMapping(value="/getDiagnosisByVisitId")
	  
	public List<Diagnosis> getDiagnosisByVisitId(@RequestBody DiagnosisModel diagnosisModel) //need to check with aditya for list of vistid
	  { 
		  System.out.println("inside the controller class for diagnosisModel "+diagnosisModel); 
	return pmsDiagnosisService.getDiagnosisByVisitId(diagnosisModel);
		
		  //return  pmsDiagnosisService.getDiagnosisByVisitId(diagnosisModel);
	  
	  
	  
	  }
	 
	
	
	/*
	 * @GetMapping(value="/getDiagnosisByVisitId") ResponseEntity<Void>
	 * getDiagnosisByVisitId(@RequestBody DiagnosisModel diagnosisModel) {
	 * System.out.println("Parsed object: {}"+ diagnosisModel); return
	 * ResponseEntity.ok().build(); }
	 */
}
