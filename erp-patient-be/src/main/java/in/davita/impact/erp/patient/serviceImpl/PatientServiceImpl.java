package in.davita.impact.erp.patient.serviceImpl;

import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.davita.impact.erp.patient.model.Allergies;
import in.davita.impact.erp.patient.model.PatientDetails;
import in.davita.impact.erp.patient.repository.LanguageKnownRepository;
import in.davita.impact.erp.patient.repository.PatientRepository;
import in.davita.impact.erp.patient.service.PatientServices;
@Service
//@Transactional(rollbackFor = Exception.class, noRollbackFor = FileNotFoundException.class)
public class PatientServiceImpl implements PatientServices  {

	@Autowired
	PatientRepository patientRepository;
	@Autowired
	LanguageKnownRepository languageKnownRepository;
	
	
	
	@Override
	@Transactional
	public PatientDetails addNewPatient(PatientDetails patient) {
		 
		
		/*
		 * if( patient.getProperty() == null) throw new IllegalArgumentException();
		 */
		
		PatientDetails addedPatient =patientRepository.save(patient);
		return addedPatient;
	}

	@Override
	@Transactional
	public PatientDetails updatePatient(PatientDetails patient) {
		
		/*
		 * Set<Allergies> allergies = patient.getAllergies();
		 * patient.setAllergies(allergies);
		 */
			PatientDetails save = patientRepository.save(patient);
		return save;
	}

	@Override
	@Transactional
	public PatientDetails getPatientById(String id) {
		Optional<PatientDetails> findById = patientRepository.findById(id);
		PatientDetails patient = findById.get();
		return patient;
	}

	@Override
	@Transactional
	public List<PatientDetails> getAllPatient() {
		List<PatientDetails> findAll = patientRepository.findAll();
		return findAll;
	}

}
