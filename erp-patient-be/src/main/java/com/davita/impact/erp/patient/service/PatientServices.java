package com.davita.impact.erp.patient.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.davita.impact.erp.patient.model.Allergies;
import com.davita.impact.erp.patient.model.PatientDetails;

//@Service
public interface PatientServices {

	public PatientDetails addNewPatient(PatientDetails patient);

	public PatientDetails updatePatient(PatientDetails patient) throws Exception;

	public PatientDetails getPatientById(String id);

	public List<PatientDetails> getAllPatient();

	public PatientDetails addNewAllergy(Allergies allergies, String id);

	public PatientDetails findPatientbyId(String id);
}
