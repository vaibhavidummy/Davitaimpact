package com.citiustech.pms.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.pms.model.Diagnosis;
import com.citiustech.pms.repository.DiagnosisRepo;
import com.citiustech.pms.service.PmsDiagnosisServiceImpl;

@RestController
@RequestMapping(value="/healthcare/diagnosis")
public class PmsDiagnosisController {

	public PmsDiagnosisController()
	{
		System.out.println("inside the rest controler");
	}
	
@Autowired
 Diagnosis diagnosis;
@Autowired
PmsDiagnosisServiceImpl pmsDiagnosisServiceImpl;

@PostMapping("/creatediagnosis/")
public Diagnosis createDiagnosis(@RequestBody Diagnosis diagnosis, BindingResult result)
{
return	pmsDiagnosisServiceImpl.addUser(diagnosis);
	

	
}





@GetMapping("/getdiagnosis/")
public String getDiagnosis(Diagnosis diagnosis)
{
	return diagnosis.getDiagnosis();
}


}
