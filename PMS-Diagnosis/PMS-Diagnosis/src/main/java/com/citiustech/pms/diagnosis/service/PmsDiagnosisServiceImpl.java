package com.citiustech.pms.diagnosis.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.citiustech.pms.diagnosis.model.Diagnosis;
import com.citiustech.pms.diagnosis.model.DiagnosisMaster;
import com.citiustech.pms.diagnosis.model.DiagnosisModel;
import com.citiustech.pms.diagnosis.repository.DiagnosisMasterRepo;
import com.citiustech.pms.diagnosis.repository.DiagnosisRepo;

@Service
//@Transactional //it'll catch the exceptionand it wont insert into the table
public class PmsDiagnosisServiceImpl implements PmsDiagnosisServiceInterface {
	@Autowired
	DiagnosisRepo diagnosisRepo;
	@Autowired
	DiagnosisMasterRepo diagnosisMasterRepo;
	
	/* @Autowired Diagnosis diagnosis1; */
	 

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Diagnosis addDiagnosis(Diagnosis diagnosis) // throws Exception //defered
	{
		Diagnosis diagnosisAdd = diagnosisRepo.save(diagnosis);
		if (Objects.isNull(diagnosis.getId()))
			throw new IllegalArgumentException();
		else if (Objects.isNull(diagnosis.getName()) || Objects.isNull(diagnosis.getDescription()))
			throw new IllegalArgumentException();

		return diagnosisAdd;

	}

	@Override
	@Transactional
	public List<DiagnosisMaster> getAllDiagnosis() {

		List<DiagnosisMaster> diagnosisMaster = diagnosisMasterRepo.findAll();
		return diagnosisMaster;

	}

	@Override
	public List<Diagnosis> getDiagnosisByVisitId(DiagnosisModel diagnosisModel) {

		System.out.println("inside the service impll class");

		for (int i = 0; i < diagnosisModel.getDiagnosis_details().size(); i++) {
			Diagnosis diagnosis1 = new Diagnosis();
			diagnosis1.setPatient_visit_id(diagnosisModel.getPatient_visit_id());
			diagnosis1.setName(diagnosisModel.getDiagnosis_details().get(i).getName());
			diagnosis1.setId(diagnosisModel.getDiagnosis_details().get(i).getId());
			diagnosis1.setDescription(diagnosisModel.getDiagnosis_details().get(i).getDescription());
			System.out.println("the diagnosis" + diagnosis1.getId());
			diagnosisRepo.save(diagnosis1);

		}

		return diagnosisRepo.findAll();
		/*
		 * Diagnosis diagnosisGetById = diagnosisRepo.save(diagnosis); if
		 * (Objects.isNull(diagnosis.getId())) throw new IllegalArgumentException();
		 * else if(Objects.isNull(diagnosis.getName())) throw new
		 * IllegalArgumentException(); return diagnosisGetById;
		 */

	}

}
