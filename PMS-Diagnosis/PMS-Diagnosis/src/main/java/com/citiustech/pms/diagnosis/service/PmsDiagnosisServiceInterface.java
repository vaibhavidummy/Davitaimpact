package com.citiustech.pms.diagnosis.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.citiustech.pms.diagnosis.model.Diagnosis;
import com.citiustech.pms.diagnosis.model.DiagnosisMaster;
import com.citiustech.pms.diagnosis.model.DiagnosisModel;

public interface PmsDiagnosisServiceInterface {

	Diagnosis addDiagnosis(Diagnosis diagnosis) throws Exception;
	List<DiagnosisMaster> getAllDiagnosis();
	List<Diagnosis> getDiagnosisByVisitId( DiagnosisModel diagnosisModel);

}