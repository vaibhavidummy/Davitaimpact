package in.davita.impact.erp.patient.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.davita.impact.erp.patient.model.PatientDetails;

@Service
public interface PatientServices {

	public PatientDetails addNewPatient(PatientDetails patient);
	public PatientDetails updatePatient(PatientDetails patient);
	public PatientDetails getPatientById(String id);
	public List<PatientDetails> getAllPatient();
}
