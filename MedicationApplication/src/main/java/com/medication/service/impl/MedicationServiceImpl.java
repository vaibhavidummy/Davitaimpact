package com.medication.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datastax.oss.driver.api.core.NoNodeAvailableException;
import com.medication.exception.MedicationException;
import com.medication.model.Medication;
import com.medication.model.MedicationOnVisit;
import com.medication.repository.MedicationRepository;
import com.medication.repository.MedicationVisitRepository;

@Service
public class MedicationServiceImpl {

	private static final Logger logger = LoggerFactory.getLogger(MedicationServiceImpl.class);
	@Autowired
	private MedicationRepository medicationRepository;

	@Autowired
	private MedicationVisitRepository medicationVisitRepository;

	public List<Medication> getMedications() throws NoNodeAvailableException {
		logger.info("Reached get medication");
		List<Medication> medicationList = new ArrayList<>();
		try {
			medicationList = medicationRepository.findAll();
		} catch (Exception ex) {
			throw new MedicationException("No data available");
		}
		return medicationList;
	}

	// save and update
	public Medication saveMedication(Medication medication) {
		Medication savedMedication = null;
		try {
			savedMedication = medicationRepository.save(medication);
			logger.info("Medication Saved successfully");
		} catch (Exception ex) {
			throw new MedicationException("Medication Data not saved");
		}
		return savedMedication;
	}

	public MedicationOnVisit getMedicationFromPatientandVisit(String visitId, String patientId) {
		logger.info("Reached getMedicationFromPatientandVisit");
		return medicationVisitRepository.getMedicationFromPatientandVisit(visitId, patientId)
				.orElseThrow(() -> new MedicationException("Medication With given Ids Not Found"));
	}

	public MedicationOnVisit saveMedicationForPatientOnVisit(MedicationOnVisit medicationOnVisit) {
		MedicationOnVisit savedMedicationOnVisit = null;
		try {
			savedMedicationOnVisit = medicationVisitRepository.save(medicationOnVisit);
			logger.info("MedicationOnVisit Saved successfully");
		} catch (Exception ex) {
			throw new MedicationException("MedicationOnVisit Data not saved");
		}
		return savedMedicationOnVisit;
	}

	public List<MedicationOnVisit> getAllMedicationForPatient(String patientId) {
		logger.info("Reached getAllMedicationForPatient");
		List<MedicationOnVisit> medicationOnVisitList = new ArrayList<>();
		try {
			medicationOnVisitList = medicationVisitRepository.getAllMedicationForPatient(patientId);
		} catch (Exception ex) {
			throw new MedicationException("No data available");
		}
		return medicationOnVisitList;
	}

}
