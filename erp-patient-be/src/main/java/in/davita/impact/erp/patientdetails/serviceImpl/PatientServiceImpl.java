package in.davita.impact.erp.patientdetails.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.davita.impact.erp.patientdetails.controller.PataintController;
import in.davita.impact.erp.patientdetails.model.LanguageKnown;
import in.davita.impact.erp.patientdetails.model.PatientDetails;
import in.davita.impact.erp.patientdetails.repository.LanguageKnownRepository;
import in.davita.impact.erp.patientdetails.repository.PatientRepository;
import in.davita.impact.erp.patientdetails.service.PatientServices;
@Service
public class PatientServiceImpl implements PatientServices  {

	@Autowired
	PatientRepository patientRepository;
	@Autowired
	LanguageKnownRepository languageKnownRepository;
	
	
	
	@Override
	public PatientDetails addNewPatient(PatientDetails patient) {
		
		
		/*
		 * String id2 = patient.getId(); List<LanguageKnown> languageKnow =
		 * patient.getLanguageKnow(); //Patient.thisLanguageKnown.this.setPatient(id2);
		 */		
	
		PatientDetails addedPatient =patientRepository.save(patient);
		return addedPatient;
	}

	@Override
	public PatientDetails updatePatient(PatientDetails patient) {
			PatientDetails save = patientRepository.save(patient);
		return save;
	}

	@Override
	public PatientDetails getPatientById(String id) {
		Optional<PatientDetails> findById = patientRepository.findById(id);
		PatientDetails patient = findById.get();
		return patient;
	}

	@Override
	public List<PatientDetails> getAllPatient() {
		List<PatientDetails> findAll = patientRepository.findAll();
		return findAll;
	}

}
