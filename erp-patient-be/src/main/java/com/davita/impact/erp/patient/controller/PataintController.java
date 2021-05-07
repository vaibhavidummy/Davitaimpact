package com.davita.impact.erp.patient.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

import com.davita.impact.erp.patient.comman.ResponseOnOk;
import com.davita.impact.erp.patient.model.Allergies;
import com.davita.impact.erp.patient.model.LanguageKnown;
import com.davita.impact.erp.patient.model.PatientDetails;
import com.davita.impact.erp.patient.model.PatientVisit;
import com.davita.impact.erp.patient.repository.PatientRepository;
import com.davita.impact.erp.patient.service.LanguageServices;
import com.davita.impact.erp.patient.service.PatientServices;
import com.davita.impact.erp.patient.service.PatientVisitServices;
import com.davita.impact.erp.patient.service.allergiesServices;

import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping(value = "/healthcare")
public class PataintController {

	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(PataintController.class);
	@Autowired
	PatientServices patientServices;

	@Autowired
	PatientVisitServices patientVisitServices;

	@Autowired
	allergiesServices allergiesServices;

	@Autowired
	LanguageServices languageServices;
	
	

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
	@ApiResponse(code = 201, message = "Patient Details Added Successfully", response = ResponseOnOk.class)
	// @Transactional
	public ResponseEntity<ResponseOnOk> createUser(@RequestBody PatientDetails patient) {
		PatientDetails addNewPatients = patientServices.addNewPatient(patient);
		ResponseOnOk responseOnOk = new ResponseOnOk();
		responseOnOk.setId(addNewPatients.getId());
		responseOnOk.setMessage("Patient Details Added Successfully");
		responseOnOk.setStatus(201);

		// return new ResponseEntity<String>("Patient Details Added Successfully
		// "+addNewPatients.getId(),HttpStatus.CREATED);
		return new ResponseEntity<ResponseOnOk>(responseOnOk, HttpStatus.CREATED);
		// return addNewPatients.getId();
	}

	/*
	 * @PutMapping("/patient/{userid}") //@Transactional public ResponseEntity<Map>
	 * updatePatientDetails(@Valid @RequestBody PatientDetails patient
	 * , @PathVariable("userid") String id, BindingResult result) {
	 * Optional<PatientDetails> findById =
	 * patientRepository.findById(patient.getId()); if(!findById.isPresent()) return
	 * ResponseEntity.notFound().build(); PatientDetails updatePatient =
	 * patientServices.updatePatient(patient); Map map = new HashMap();
	 * map.put("status",200);
	 * map.put("message","Patient Details Update Successfully");
	 * map.put("id",updatePatient.getId()); Map result1 = new HashMap();
	 * result1.put("result",map); return new ResponseEntity<Map>(result1,
	 * HttpStatus.OK);
	 * 
	 * }
	 */

	@PutMapping("/patient/")
	@ResponseBody
	@ApiResponse(code = 200, message = "Patient Details Added Successfully", response = ResponseOnOk.class)
	// @Transactional
	public ResponseEntity<ResponseOnOk> updatePatientDetails(@Valid @RequestBody PatientDetails patient)
			throws Exception {

		PatientDetails updatePatient = patientServices.updatePatient(patient);
		ResponseOnOk responseOnOk = new ResponseOnOk();
		responseOnOk.setId(updatePatient.getId());
		responseOnOk.setMessage("Patient Details Update Successfully");
		responseOnOk.setStatus(200);
		return new ResponseEntity<ResponseOnOk>(responseOnOk, HttpStatus.OK);

	}

	@GetMapping("/patient/{userid}")
	public ResponseEntity<PatientDetails> getPatientDetailsByID(@PathVariable("userid") String id) {
		// LOGGER.info("getPatientDetailsByID...");
		PatientDetails patientById = patientServices.getPatientById(id);
		return new ResponseEntity<PatientDetails>(patientById, HttpStatus.OK);

	}

	@GetMapping("/patient/")
	public List<PatientDetails> getAllPatientDetails() {
		List<PatientDetails> allPatient = patientServices.getAllPatient();
		return allPatient;
	}

	/* Find All Allergy */

	@GetMapping("/allergies")
	public List<Allergies> getAllAllergiesDetails() {
		List<Allergies> allAllergies = allergiesServices.getAllAllergies();
		return allAllergies;
	}

	/* Find All Languages */

	@GetMapping("/languages")
	public List<LanguageKnown> getAllLangugeDetails() {
		List<LanguageKnown> allLanguageKnown = languageServices.getAllLangwages();
		return allLanguageKnown;
	}

	/*
	 * Patient VisiteDetails
	 */

	/*
	 * @ResponseBody
	 * 
	 * @GetMapping("/visitdetails") public List<PatientVisitDetails>
	 * getAllVisitDetails() { List<PatientVisitDetails> allVisitDetails =
	 * patientVisitDetailsServices.getAllVisitDetails(); return allVisitDetails; }
	 * 
	 * @GetMapping("/visitdetails/{patientId}")
	 * 
	 * @ResponseBody public List<PatientVisitDetails>
	 * getVisitDetailsByPatientId(@PathVariable("patientId") int id) {
	 * List<PatientVisitDetails> patientVisitDetailsByID =
	 * patientVisitDetailsServices.getPatientVisitDetailsByID(id); return
	 * patientVisitDetailsByID; }
	 * 
	 * @GetMapping("/visitdetails/{patientvisitId}")
	 * 
	 * @ResponseBody public PatientVisitDetails
	 * getVisitDetailsByPatientVisitId(@PathVariable("patientvisitId") int visitid)
	 * { PatientVisitDetails patientVisitDetailsByVisitID =
	 * patientVisitDetailsServices.getPatientVisitDetailsByVisitID(visitid); return
	 * patientVisitDetailsByVisitID; }
	 */

	
/* Create Patient Visit */
	
	/*
	 * @PostMapping("/createVisitId")
	 * 
	 * @ResponseBody
	 * 
	 * @ApiResponse(code = 200, message = "Patient Details Added Successfully",
	 * response = ResponseOnOk.class) public ResponseEntity<ResponseOnOk>
	 * createPatientVisit(@Valid @RequestBody PatientVisit patientvisit) throws
	 * Exception {
	 * 
	 * PatientVisit cretevisitId = patientVisitServices.cretevisitId(patientvisit);
	 * ResponseOnOk responseOnOk = new ResponseOnOk();
	 * responseOnOk.setId(cretevisitId.getId());
	 * responseOnOk.setMessage("Patient Visit Id Created Successfully");
	 * responseOnOk.setStatus(201); return new
	 * ResponseEntity<ResponseOnOk>(responseOnOk, HttpStatus.OK);
	 * 
	 * }
	 */
	
	
	
	
	/*
	 * Any instance of RuntimeException within the endpoint functions and return a
	 * 500 response.
	 */
	@ExceptionHandler(RuntimeException.class)
	public final ResponseEntity<Exception> handleAllExceptions(RuntimeException ex) {
		return new ResponseEntity<Exception>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	
	
}
