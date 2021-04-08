package com.citiustech.pms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.citiustech.pms.model.Diagnosis;
import com.citiustech.pms.repository.DiagnosisRepo;
@Component
public class PmsDiagnosisServiceImpl implements PmsDiagnosisService{
	@Autowired
	DiagnosisRepo diagnosisRepo;
	
	public Diagnosis addUser(Diagnosis diagnosis)
	{
		 diagnosisRepo.save(diagnosis);
		 return diagnosis;

		
	}
}
