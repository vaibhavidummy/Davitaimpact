package com.medication.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medication.model.Medication;
import com.medication.model.MedicationOnVisit;
import com.medication.repository.MedicationRepository;
import com.medication.repository.MedicationVisitRepository;
@Transactional
@Service
public class MedicationServiceImpl {

	@Autowired
	private MedicationRepository medicationRepository;

	@Autowired
	private MedicationVisitRepository medicationVisitRepository;

	public List<Medication> getMedications() {
		return medicationRepository.findAll();
	}

	// save and update
	public void saveMedication(Medication medication) {
		medicationRepository.save(medication);
	}

	public Optional<MedicationOnVisit> getMedicationFromPatientandVisit(String visitId,String patientId) {
		return medicationVisitRepository.getMedicationFromPatientandVisit(visitId, patientId);
	}

    
	public String saveMedicationForPatientOnVisit(MedicationOnVisit medicationOnVisit) {
		medicationVisitRepository.save(medicationOnVisit);
		return "added";
	}
}
