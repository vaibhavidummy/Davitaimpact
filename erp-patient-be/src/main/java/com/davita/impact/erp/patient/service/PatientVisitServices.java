package com.davita.impact.erp.patient.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.davita.impact.erp.patient.comman.Diagnosis;
import com.davita.impact.erp.patient.model.PatientDetails;
import com.davita.impact.erp.patient.model.PatientVisit;
@Service
public interface PatientVisitServices {

	public PatientVisit creteVisitId(PatientVisit patientVisit)throws Exception;
	
	public List<Diagnosis> visitDetails(String id);
	
	public PatientVisit getVistDetails(String visitId)throws Exception;
	
	
	public List<PatientVisit> getAllVistofPatient(String patientDetailsId) throws Exception;
	
	/*
	 * public List<PatientVisit> getAllVisitDetails();
	 * 
	 * public List<PatientVisit> getPatientVisitDetailsByID(int id);
	 * 
	 * public PatientVisit getPatientVisitDetailsByVisitID(int visitID);
	 */
	
}
