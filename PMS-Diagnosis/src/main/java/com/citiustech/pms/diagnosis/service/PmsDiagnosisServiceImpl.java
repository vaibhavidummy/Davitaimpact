package com.citiustech.pms.diagnosis.service;

import java.util.List;
import java.util.Objects;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citiustech.pms.diagnosis.model.Diagnosis;
import com.citiustech.pms.diagnosis.model.DiagnosisMaster;
import com.citiustech.pms.diagnosis.repository.DiagnosisMasterRepo;
import com.citiustech.pms.diagnosis.repository.DiagnosisRepo;

@Service
public class PmsDiagnosisServiceImpl implements PmsDiagnosisServiceInterface {
	@Autowired
	DiagnosisRepo diagnosisRepo;
	@Autowired
	DiagnosisMasterRepo   diagnosisMasterRepo;

	
	  @Override
	  
	  @Transactional 
	  public Diagnosis addDiagnosis(Diagnosis diagnosis) { Diagnosis
	  diagnosisAdd = diagnosisRepo.save(diagnosis);
	  
	  if (Objects.isNull(diagnosis.getId())) throw new IllegalArgumentException();
	  else if (Objects.isNull(diagnosis.getName()) ||
	  Objects.isNull(diagnosis.getDescription())) throw new
	  IllegalArgumentException();
	  
	  return diagnosisAdd;
	  
	  }
	 
	
	
	@Override
	@Transactional
	public List<DiagnosisMaster> getAllDiagnosis()
	{
		
		List<DiagnosisMaster> diagnosisMaster= diagnosisMasterRepo.findAll();
		return diagnosisMaster;
		
	}
	
	
	@Override
	@Transactional
	public Diagnosis getDiagnosisByVisitId(Diagnosis diagnosis)
	{
		Diagnosis diagnosisGetById = diagnosisRepo.save(diagnosis);
		if (Objects.isNull(diagnosis.getId()))
			throw new IllegalArgumentException();
		else if(Objects.isNull(diagnosis.getName()))
			throw new IllegalArgumentException();
		return diagnosisGetById;
		
	}


	
}
