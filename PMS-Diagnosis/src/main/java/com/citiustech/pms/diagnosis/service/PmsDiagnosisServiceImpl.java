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
		LOGGER.info("Get in addDiagnosis ");
		
		if (Objects.isNull(diagnosisAdd.getDiagonosisId()) ||  diagnosisAdd.getDiagonosisId().isEmpty()) {
			throw new DiagnosisException("Diagnosis Id cannot be Empty or Null");
		} else if (Objects.isNull(diagnosisAdd.getName()) || diagnosisAdd.getName().isEmpty()) {
			throw new DiagnosisException(" Diagnosis Name cannot be Empty or Null");
		} else if (Objects.isNull(diagnosisAdd.getDescription()) || diagnosisAdd.getDescription().isEmpty()) {
			throw new DiagnosisException("Diagnosis Description cannot be Empty or Null");
		}

		diagnosis = diagnosisRepo.save(diagnosisAdd);
		LOGGER.info("Diagnosis Added Successfully ");

		return diagnosis;

	}	

	@Override
	public DiagnosisSuccess getAllDiagnosis() {

		List<DiagnosisMaster> diagnosisMaster = diagnosisMasterRepo.findAll();
		LOGGER.info("Get Master Data: {} ",diagnosisMaster);

		if (diagnosisMaster.isEmpty()) {
			throw new DiagnosisException("No Data available");
		}
		return new DiagnosisSuccess.DiagnosisDto().setDiagnosisMaster(diagnosisMaster).setSuccessFlag(Boolean.TRUE).build();
	}

	@Override
	@Transactional(rollbackFor =  Exception.class)
	public DiagnosisSuccess getDiagnosisDescription(DiagnosisModel diagnosisModel) {
		LOGGER.info("procedureDetailDesc : {} ",diagnosisModel);

		List<Diagnosis> diagnosisList = new ArrayList<>();

		if (diagnosisModel.getPatient_visit_id().isEmpty()) {
			throw new DiagnosisException("Patient Visit Id cannot be Empty");
		} else if (diagnosisModel.getDiagnosis_details().isEmpty()) {
			throw new DiagnosisException("Diagnosis Description cannot be Empty");
		}
		LOGGER.info("Size : {} ",diagnosisModel.getDiagnosis_details().size());

		for (int i = 0; i < diagnosisModel.getDiagnosis_details().size(); i++) {
			Diagnosis diagnosisDesc = new Diagnosis();

			diagnosisDesc.setPatient_visit_id(diagnosisModel.getPatient_visit_id());
			diagnosisDesc.setDiagonosisId(diagnosisModel.getDiagnosis_details().get(i).getId());
			diagnosisDesc.setName(diagnosisModel.getDiagnosis_details().get(i).getName());
			diagnosisDesc.setDescription(diagnosisModel.getDiagnosis_details().get(i).getDescription());

			diagnosisList.add(diagnosisDesc);
		}

		diagnosisRepo.saveAll(diagnosisList);
		LOGGER.info("Data Save Successfully");

		return new DiagnosisSuccess.DiagnosisDto().setMessage("Added Successfully").setSuccessFlag(Boolean.TRUE).build();
	}


	@Override
	public DiagnosisSuccess getProcedureByVisitId(String patientVisitId) {

		List<Diagnosis> getPatientVisitId = new ArrayList<>();

		if (!patientVisitId.isEmpty()) {
			getPatientVisitId = diagnosisRepo.checkForExistingPatientVisitId(patientVisitId);
			LOGGER.info("List of Patient Visit Id : {} ",getPatientVisitId);

			if (getPatientVisitId.isEmpty()) {
				throw new DiagnosisException("Patient Visit Id Not Found");
			}
		}
		return new DiagnosisSuccess.DiagnosisDto().setDiagnosis(getPatientVisitId).setSuccessFlag(Boolean.TRUE).build();
	}

}