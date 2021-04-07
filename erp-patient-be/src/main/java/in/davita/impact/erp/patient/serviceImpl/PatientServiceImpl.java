package in.davita.impact.erp.patient.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import in.davita.impact.erp.patient.model.Patient;
import in.davita.impact.erp.patient.repository.PatientRepository;
import in.davita.impact.erp.patient.service.PatientServices;

public class PatientServiceImpl implements PatientServices  {

	@Autowired
	PatientRepository patientRepository;
	
	@Override
	public Patient addNewPatient(Patient patient) {
		
		
		
		Patient addedPatient = patientRepository.save(patient);
		return addedPatient;
	}

	@Override
	public Patient updatePatient(Patient patient) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Patient getPatientById(String id) {
		Optional<Patient> findById = patientRepository.findById(id);
		Patient patient = findById.get();
		return patient;
	}

	@Override
	public List<Patient> getAllPatient() {
		List<Patient> findAll = patientRepository.findAll();
		return findAll;
	}

}
