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
import com.medication.model.MedicationDto;
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

	public MedicationDto getMedications() throws NoNodeAvailableException {
		logger.info("Reached get medication");
		List<Medication> medicationList = new ArrayList<>();
		try {
			medicationList = medicationRepository.findAll();
			if (medicationList.isEmpty()) {
				throw new MedicationException("No data available"); 
			}
		} catch (Exception ex) {
			throw new MedicationException("Problem connecting to server, Contact Admin");
		}
		return new MedicationDto.MedicationDtoBuilder().setMedicationList(medicationList).setSuccessFlag(Boolean.TRUE).build();
	}

	// save and update
	public MedicationDto saveMedication(Medication medication) {
		Medication savedMedication = null;
		try {
			savedMedication = medicationRepository.save(medication);
			logger.info("Medication Saved successfully {}",medication);
		} catch (Exception ex) {
			throw new MedicationException("Medication Data not saved");
		}
		return new MedicationDto.MedicationDtoBuilder().setMedication(savedMedication).setSuccessFlag(Boolean.TRUE).build();
	}

	public MedicationDto getMedicationFromPatientandVisit(String visitId, String patientId) {
		logger.info("Reached getMedicationFromPatientandVisit");
		MedicationOnVisit medicationOnVisit =  medicationVisitRepository.getMedicationFromPatientandVisit(visitId, patientId)
				.orElseThrow(() -> new MedicationException("Medication With given Ids Not Found"));
		return new MedicationDto.MedicationDtoBuilder().setMedicationOnVisit(medicationOnVisit).setSuccessFlag(Boolean.TRUE).build();
	}

	public MedicationDto saveMedicationForPatientOnVisit(MedicationOnVisit medicationOnVisit) {
		MedicationOnVisit savedMedicationOnVisit = null;
		try {
			savedMedicationOnVisit = medicationVisitRepository.save(medicationOnVisit);
			logger.info("MedicationOnVisit Saved successfully {}",medicationOnVisit);
		} catch (Exception ex) {
			throw new MedicationException("MedicationOnVisit Data not saved");
		}
		return new MedicationDto.MedicationDtoBuilder().setMedicationOnVisit(savedMedicationOnVisit).setSuccessFlag(Boolean.TRUE).build();
	}

	public MedicationDto getAllMedicationForPatient(String patientId) {
		logger.info("Reached getAllMedicationForPatient");
		List<MedicationOnVisit> medicationOnVisitList = new ArrayList<>();
		try {
			medicationOnVisitList = medicationVisitRepository.getAllMedicationForPatient(patientId);
		} catch (Exception ex) {
			throw new MedicationException("No data available");
		}
		return new MedicationDto.MedicationDtoBuilder().setMedicationOnVisitList(medicationOnVisitList).setSuccessFlag(Boolean.TRUE).build();
	}

}
