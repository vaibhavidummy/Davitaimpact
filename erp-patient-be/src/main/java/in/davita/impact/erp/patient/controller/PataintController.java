package in.davita.impact.erp.patient.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import in.davita.impact.erp.patient.model.LanguageKnown;
import in.davita.impact.erp.patient.model.PatientDetails;
import in.davita.impact.erp.patient.model.PatientVisitDetails;
import in.davita.impact.erp.patient.repository.PatientRepository;
import in.davita.impact.erp.patient.service.PatientServices;
import in.davita.impact.erp.patient.service.PatientVisitDetailsServices;

@RestController()
@RequestMapping(value ="/healthcare")
public class PataintController {
	
	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(PataintController.class);
	@Autowired
	PatientServices patientServices;
	
	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	PatientVisitDetailsServices patientVisitDetailsServices;
	
	/*
	 * @PostMapping("/patient/")
	 * 
	 * @ResponseStatus(code =HttpStatus.ACCEPTED) public
	 * ResponseEntity<PatientDetails> addPatientDetails(@Valid @RequestBody
	 * PatientDetails patient , BindingResult result) {
	 * //LOGGER.debug("inside addPatientDetails"); PatientDetails addNewPatients =
	 * patientServices.addNewPatient(patient); return new
	 * ResponseEntity<PatientDetails>(addNewPatients, HttpStatus.OK); }
	 */
	
	@PostMapping("/patient/")
	@ResponseBody
	public String createUser(@RequestBody PatientDetails patient){
		
		 PatientDetails addNewPatients = patientServices.addNewPatient(patient);
	     return addNewPatients.getId();
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
	
	/*
	 * Patient VisiteDetails
	 */	
	@ResponseBody
	@GetMapping("/visitdetails")
	public List<PatientVisitDetails> getAllVisitDetails() {
		List<PatientVisitDetails> allVisitDetails = patientVisitDetailsServices.getAllVisitDetails();
		return allVisitDetails;
	}
	
	@GetMapping("/visitdetails/{patientId}")
	@ResponseBody
	public List<PatientVisitDetails> getVisitDetailsByPatientId(@PathVariable("patientId") int id) {
		List<PatientVisitDetails> patientVisitDetailsByID = patientVisitDetailsServices.getPatientVisitDetailsByID(id);
		return patientVisitDetailsByID;
	}
	@GetMapping("/visitdetails/{patientvisitId}")
	@ResponseBody
	public PatientVisitDetails getVisitDetailsByPatientVisitId(@PathVariable("patientvisitId") int visitid) {
		 PatientVisitDetails patientVisitDetailsByVisitID = patientVisitDetailsServices.getPatientVisitDetailsByVisitID(visitid);
		return patientVisitDetailsByVisitID;
	}
	
	
}
