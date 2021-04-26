package com.citiustech.pms.diagnosis.service;

import com.citiustech.pms.diagnosis.model.Diagnosis;
import com.citiustech.pms.diagnosis.model.DiagnosisModel;
import com.citiustech.pms.diagnosis.model.DiagnosisSuccess;

public interface PmsDiagnosisServiceInterface {

	Diagnosis addDiagnosis(Diagnosis diagnosis) throws Exception;
	
	DiagnosisSuccess getAllDiagnosis();
	
	DiagnosisSuccess getDiagnosisDescription(DiagnosisModel diagnosisModel);

}