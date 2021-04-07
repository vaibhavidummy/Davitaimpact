package in.davita.impact.erp.patient.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.davita.impact.erp.patient.model.Patient;

@Service
public interface PatientServices {

	public Patient addNewPatient(Patient patient);
	public Patient updatePatient(Patient patient);
	public Patient getPatientById(String id);
	public List<Patient> getAllPatient();
}
