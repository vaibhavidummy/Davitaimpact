package com.citiustech.pms.diagnosis.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.citiustech.pms.diagnosis.exception.DiagnosisException;
import com.citiustech.pms.diagnosis.model.Diagnosis;
import com.citiustech.pms.diagnosis.model.DiagnosisMaster;
import com.citiustech.pms.diagnosis.model.DiagnosisModel;
import com.citiustech.pms.diagnosis.model.DiagnosisSuccess;
import com.citiustech.pms.diagnosis.repository.DiagnosisMasterRepo;
import com.citiustech.pms.diagnosis.repository.DiagnosisRepo;


@Service
public class PmsDiagnosisServiceImpl implements PmsDiagnosisServiceInterface {
	
	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(PmsDiagnosisServiceImpl.class);
	
	@Autowired
	private DiagnosisRepo diagnosisRepo;
	
	@Autowired
	private DiagnosisMasterRepo diagnosisMasterRepo;
	
	@Autowired
	private Diagnosis diagnosis;
	

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Diagnosis addDiagnosis(Diagnosis diagnosisAdd) {
		if (Objects.isNull(diagnosisAdd.getDiagonosisId()) ||  diagnosisAdd.getDiagonosisId().isEmpty()) {
			throw new DiagnosisException("Diagnosis Id cannot be Empty or Null");
		} else if (Objects.isNull(diagnosisAdd.getName()) || diagnosisAdd.getName().isEmpty()) {
			throw new DiagnosisException(" Diagnosis Name cannot be Empty or Null");
		} else if (Objects.isNull(diagnosisAdd.getDescription()) || diagnosisAdd.getDescription().isEmpty()) {
			throw new DiagnosisException("Diagnosis Description cannot be Empty or Null");
		}

		diagnosis = diagnosisRepo.save(diagnosisAdd);

		return diagnosis;

	}	

//	@Override
//	@Transactional
//	public List<DiagnosisMaster> getAllDiagnosis() {
//
//		List<DiagnosisMaster> diagnosisMaster = diagnosisMasterRepo.findAll();
//		return diagnosisMaster;
//
//	}
//
//	@Override
//	public List<Diagnosis> getDiagnosisByVisitId(DiagnosisModel diagnosisModel) {
//
//		System.out.println("inside the service impll class");
//
//		for (int i = 0; i < diagnosisModel.getDiagnosis_details().size(); i++) {
//			Diagnosis diagnosis1 = new Diagnosis();
//			diagnosis1.setPatient_visit_id(diagnosisModel.getPatient_visit_id());
//			diagnosis1.setName(diagnosisModel.getDiagnosis_details().get(i).getName());
//			diagnosis1.setId(diagnosisModel.getDiagnosis_details().get(i).getId());
//			diagnosis1.setDescription(diagnosisModel.getDiagnosis_details().get(i).getDescription());
//			System.out.println("the diagnosis" + diagnosis1.getId());
//			diagnosisRepo.save(diagnosis1);
//
//		}
//
//		return diagnosisRepo.findAll();
//		
//		  Diagnosis diagnosisGetById = diagnosisRepo.save(diagnosis); if
//		  (Objects.isNull(diagnosis.getId())) throw new IllegalArgumentException();
//		  else if(Objects.isNull(diagnosis.getName())) throw new
//		  IllegalArgumentException(); return diagnosisGetById;
//		 
//
//	}

	@Override
	public DiagnosisSuccess getAllDiagnosis() {
		
		List<DiagnosisMaster> diagnosisMaster = diagnosisMasterRepo.findAll();
		
		if(diagnosisMaster.isEmpty())
		{
			throw new DiagnosisException("Diagnosis Master data is not present");
		}
		DiagnosisSuccess diagnosisSuccess = new DiagnosisSuccess();
		 diagnosisSuccess.setSuccessFlag(Boolean.TRUE);
		 diagnosisSuccess.setDiagnosisMaster(diagnosisMaster);
        return diagnosisSuccess;
	}

	@Override
	@Transactional(rollbackFor =  Exception.class)
	public DiagnosisSuccess getDiagnosisDescription(DiagnosisModel diagnosisModel) {
		
	LOGGER.info("diagnosisModel : "+diagnosisModel);
		
		List<Diagnosis> diagnosisList = new ArrayList<>();
		
		LOGGER.info("diagnosisList value : "+diagnosisList);
		
		if(diagnosisModel.getPatient_visit_id().isEmpty())
		{
			throw new DiagnosisException("Patient Visit Id cannot be empty");
		}
		else if(diagnosisModel.getDiagnosis_details().isEmpty())
		{
			throw new DiagnosisException("Diagnosis Description cannot be empty");
		}
		
		LOGGER.info(" DiagnosisDetail size: "+diagnosisModel.getDiagnosis_details().size());
		
			for(int i =0; i < diagnosisModel.getDiagnosis_details().size(); i++)
			{
				Diagnosis diagnosis = new Diagnosis();

				diagnosis.setPatient_visit_id(diagnosisModel.getPatient_visit_id());
				diagnosis.setDiagonosisId(diagnosisModel.getDiagnosis_details().get(i).getId());
				diagnosis.setName(diagnosisModel.getDiagnosis_details().get(i).getName());
				diagnosis.setDescription(diagnosisModel.getDiagnosis_details().get(i).getDescription());
				
				diagnosisList.add(diagnosis);
			}
			
			diagnosisRepo.saveAll(diagnosisList);
			
			 DiagnosisSuccess diagnosisSuccess = new DiagnosisSuccess();
			 diagnosisSuccess.setMessage("Added Successfully");
			 diagnosisSuccess.setSuccessFlag(Boolean.TRUE);
			 
			 return diagnosisSuccess;
	}


	@Override
	public DiagnosisSuccess getProcedureByVisitId(String patientVisitId) {
		
		List<Diagnosis> getPatientVisitId = new ArrayList<>();
		
		if(!patientVisitId.isEmpty())
		{
			 getPatientVisitId = diagnosisRepo.checkForExistingPatientVisitId(patientVisitId);
			 
			if (getPatientVisitId.isEmpty()) {
				throw new DiagnosisException("Patient Visit Id cannot be blank");
			}
		}
		
		 DiagnosisSuccess diagnosisSuccess = new DiagnosisSuccess();
		 diagnosisSuccess.setSuccessFlag(Boolean.TRUE);
		 diagnosisSuccess.setDiagnosis(getPatientVisitId);
		 return diagnosisSuccess;
	}

}
