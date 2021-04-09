package com.medication.controller;

import java.util.List;
import java.util.Optional;

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
import com.medication.model.MedicationOnVisit;
import com.medication.service.impl.MedicationServiceImpl;

@RequestMapping(value = "/medication")
@RestController
public class MedicationController {

	@Autowired
	private MedicationServiceImpl medicationServiceImpl;

	@GetMapping(path = "/getList")
	public ResponseEntity<List<Medication>> getMedications() {
		return new ResponseEntity<>(medicationServiceImpl.getMedications(), HttpStatus.OK);
	}

	@PostMapping(path = "/saveMedication")
	public void saveMedication(@RequestBody Medication medication) {
		medicationServiceImpl.saveMedication(medication);
	}

	@GetMapping(path = "/getList/{visitId}/{patientId}")
	public Optional<MedicationOnVisit> getMedicationForPatientOnVisit(@PathVariable String visitId,@PathVariable String patientId) {
		
		return medicationServiceImpl.getMedicationFromPatientandVisit(visitId,patientId);
	}
	
	
	@PostMapping(path = "/saveList")
	public String saveMedicationForPatientOnVisit(@RequestBody MedicationOnVisit medicationOnVisit) {

		return medicationServiceImpl.saveMedicationForPatientOnVisit(medicationOnVisit);
	}
}
