package com.medication.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medication.model.Medication;
import com.medication.model.MedicationDto;
import com.medication.model.MedicationOnVisit;
import com.medication.service.impl.MedicationServiceImpl;

@RequestMapping(value = "/medication")
@RestController
public class MedicationController {

	@Autowired
	private MedicationServiceImpl medicationServiceImpl;

	@GetMapping(path = "/getList")
	public ResponseEntity<MedicationDto> getMedications() {
		return new ResponseEntity<>(medicationServiceImpl.getMedications(), HttpStatus.OK);
	}

	@PostMapping(path = "/saveMedication")
	public ResponseEntity<MedicationDto> saveMedication(@RequestBody @Valid Medication medication) {
		return new ResponseEntity<>(medicationServiceImpl.saveMedication(medication), HttpStatus.CREATED);
	}

	@GetMapping(path = "/getList/{visitId}/{patientId}")
	public ResponseEntity<MedicationDto> getMedicationForPatientOnVisit(@PathVariable String visitId,@PathVariable String patientId) {
		return new ResponseEntity<>(medicationServiceImpl.getMedicationFromPatientandVisit(visitId,patientId), HttpStatus.OK);
	}
	
	
	@PostMapping(path = "/saveList")
	public ResponseEntity<MedicationDto> saveMedicationForPatientOnVisit(@RequestBody @Valid MedicationOnVisit medicationOnVisit) {
		return new ResponseEntity<>(medicationServiceImpl.saveMedicationForPatientOnVisit(medicationOnVisit), HttpStatus.CREATED);
	}
	
	@GetMapping(path = "/getAllMedications/{patientId}")
	public ResponseEntity<MedicationDto>  getAllMedicationForPatient(@PathVariable String patientId) {
		return new ResponseEntity<>(medicationServiceImpl.getAllMedicationForPatient(patientId), HttpStatus.OK);
	}
	
}
