package com.medication.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medication.model.Medication;
import com.medication.model.MedicationOnVisit;
import com.medication.repository.MedicationRepository;
import com.medication.repository.MedicationVisitRepository;


@Service
public class MedicationServiceImpl {

	private static final Logger logger =LoggerFactory.getLogger(MedicationServiceImpl.class);
	@Autowired
	private MedicationRepository medicationRepository;

	@Autowired
	private MedicationVisitRepository medicationVisitRepository;

	public List<Medication> getMedications() {
		logger.info("reachrd get medication");
		return medicationRepository.findAll();
	}

	// save and update
	public void saveMedication(Medication medication) {
		medicationRepository.save(medication);
	}

	
	public Optional<MedicationOnVisit> getMedicationFromPatientandVisit(String visitId, String patientId) {
		return medicationVisitRepository.getMedicationFromPatientandVisit(visitId, patientId);
	}

	public String saveMedicationForPatientOnVisit(MedicationOnVisit medicationOnVisit) {
		medicationVisitRepository.save(medicationOnVisit);
		return "added";
	}

	public List<MedicationOnVisit> getAllMedicationForPatient(String patientId) {
		return medicationVisitRepository.getAllMedicationForPatient(patientId);

	}

}
