package com.citiustech.pms.diagnosis.service;

import java.util.List;

import com.citiustech.pms.diagnosis.model.Diagnosis;
import com.citiustech.pms.diagnosis.model.DiagnosisMaster;

public interface PmsDiagnosisServiceInterface {

	Diagnosis addDiagnosis(Diagnosis diagnosis);
	List<DiagnosisMaster> getAllDiagnosis();
	 Diagnosis getDiagnosisByVisitId(Diagnosis diagnosis);

}
