package com.citiustech.pms.diagnosis.service;

import org.springframework.stereotype.Service;

import com.citiustech.pms.diagnosis.model.Diagnosis;
import com.citiustech.pms.diagnosis.model.DiagnosisModel;
import com.citiustech.pms.diagnosis.model.DiagnosisSuccess;


@Service
public interface PmsDiagnosisServiceInterface {
	
	Diagnosis addDiagnosis(Diagnosis diagnosis);
	
	DiagnosisSuccess getAllDiagnosis();
	
	DiagnosisSuccess getDiagnosisDescription(DiagnosisModel diagnosisModel);
	
	DiagnosisSuccess getProcedureByVisitId(String patientVisitId);

}