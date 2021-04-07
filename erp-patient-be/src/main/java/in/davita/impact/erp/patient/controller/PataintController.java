package in.davita.impact.erp.patient.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import in.davita.impact.erp.patient.model.Patient;
import in.davita.impact.erp.patient.service.PatientServices;

@RestController()
@RequestMapping(value ="/healthcare")
public class PataintController {

	@Autowired
	PatientServices patientServices;
	
	@PostMapping("/pataint/")
	public Patient addPatientDetails(@Valid @ModelAttribute Patient patient , BindingResult result) {
		
		return patientServices.addNewPatient(patient);
		
		
	}
	@PutMapping("/patient/{userid}")
	public Patient updatePatientDetails(@Valid @ModelAttribute Patient patient , @PathVariable("userid") String id, BindingResult result) {
		
		
		
		return patient;
	}
	@GetMapping("/patient/{userid}")
	public Patient getPatientDetailsByID(@PathVariable("userid") String id) {
		
		Patient patientById = patientServices.getPatientById(id);
		return patientById;
		
	}
	@GetMapping("/patient/")
	public List<Patient>  getAllPatientDetails() {
		
		List<Patient> allPatient = patientServices.getAllPatient();
		return allPatient;
	}
	
	
}
