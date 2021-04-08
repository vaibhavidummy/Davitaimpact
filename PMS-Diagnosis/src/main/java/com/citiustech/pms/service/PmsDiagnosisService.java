package com.citiustech.pms.service;

import org.springframework.stereotype.Service;

import com.citiustech.pms.model.Diagnosis;



@Service
public interface PmsDiagnosisService {
	
	Diagnosis addUser(Diagnosis diagnosis);

}
