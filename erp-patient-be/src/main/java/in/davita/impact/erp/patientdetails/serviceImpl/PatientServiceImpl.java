package in.davita.impact.erp.patientdetails.serviceImpl;

import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.davita.impact.erp.patientdetails.model.PatientDetails;
import in.davita.impact.erp.patientdetails.repository.LanguageKnownRepository;
import in.davita.impact.erp.patientdetails.repository.PatientRepository;
import in.davita.impact.erp.patientdetails.service.PatientServices;
@Service
@Transactional(rollbackFor = Exception.class, noRollbackFor = FileNotFoundException.class)
public class PatientServiceImpl implements PatientServices  {

	@Autowired
	PatientRepository patientRepository;
	@Autowired
	LanguageKnownRepository languageKnownRepository;
	
	
	
	@Override

	public PatientDetails addNewPatient(PatientDetails patient) {
		 
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
