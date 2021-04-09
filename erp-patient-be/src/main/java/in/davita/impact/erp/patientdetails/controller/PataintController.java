package in.davita.impact.erp.patientdetails.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import in.davita.impact.erp.patientdetails.model.LanguageKnown;
import in.davita.impact.erp.patientdetails.model.PatientDetails;
import in.davita.impact.erp.patientdetails.repository.PatientRepository;
import in.davita.impact.erp.patientdetails.service.PatientServices;

@RestController()
@RequestMapping(value ="/healthcare")
public class PataintController {
	
	//private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(PataintController.class);
	@Autowired
	PatientServices patientServices;
	
	@Autowired
	PatientRepository patientRepository;
	
	@PostMapping("/patient/")
	@ResponseStatus(code =HttpStatus.ACCEPTED)
	public PatientDetails addPatientDetails(@Valid @RequestBody PatientDetails patient , BindingResult result) {
		//LOGGER.debug("inside addPatientDetails");
		return patientServices.addNewPatient(patient);
	}
	@PutMapping("/patient/{userid}")
	public ResponseEntity<PatientDetails> updatePatientDetails(@Valid @RequestBody PatientDetails patient , @PathVariable("userid") String id, BindingResult result) {
		Optional<PatientDetails> findById = patientRepository.findById(patient.getId());
		if(!findById.isPresent()) 
			return ResponseEntity.notFound().build();
		PatientDetails updatePatient = patientServices.updatePatient(patient);	
		return new ResponseEntity<PatientDetails>(updatePatient, HttpStatus.OK);
		
	}
	@GetMapping("/patient/{userid}")
	public PatientDetails getPatientDetailsByID(@PathVariable("userid") String id) {
	//	LOGGER.info("getPatientDetailsByID...");
		PatientDetails patientById = patientServices.getPatientById(id);
		return patientById;
		
	}
	@GetMapping("/patient/")
	public List<PatientDetails>  getAllPatientDetails() {
		List<PatientDetails> allPatient = patientServices.getAllPatient();
		return allPatient;
	}
	
	
}
