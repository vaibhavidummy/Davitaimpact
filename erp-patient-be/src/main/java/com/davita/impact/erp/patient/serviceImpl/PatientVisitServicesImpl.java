package com.davita.impact.erp.patient.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.davita.impact.erp.patient.comman.Diagnosis;
import com.davita.impact.erp.patient.exception.EntityDetailsNotFoundException;
import com.davita.impact.erp.patient.model.PatientDetails;
import com.davita.impact.erp.patient.model.PatientVisit;
import com.davita.impact.erp.patient.repository.PatientRepository;
import com.davita.impact.erp.patient.repository.PatientVisitRepository;
import com.davita.impact.erp.patient.service.PatientVisitServices;

@Service
public class PatientVisitServicesImpl implements PatientVisitServices {

	@Autowired
	PatientVisitRepository patientVisitRepository;

	@Autowired
	PatientRepository patientRepository;

	@Autowired
	RestTemplate restTemplate;

	@Override
	@Transactional
	public PatientVisit creteVisitId(PatientVisit patientVisit) /* throws Exception */ {

		Optional<PatientDetails> findById = patientRepository.findById(patientVisit.getPataintDetailIdfk());
		if (!findById.isPresent()) {
			// return ResponseEntity.notFound().build();
			throw new EntityDetailsNotFoundException("Pataient Details Id not found",
					new Object[]{ patientVisit.getPataintDetailIdfk() });
		}
		
		Set<PatientVisit> find = patientVisitRepository.findByPataintDetailIdfkAndAppointmentIdfkAndAppointmentStatus(
				patientVisit.getPataintDetailIdfk(), patientVisit.getAppointmentIdfk(),
				patientVisit.isAppointmentStatus());
		if (!find.isEmpty()) {
			// return ResponseEntity.notFound().build();
			throw new EntityDetailsNotFoundException("Pataient Has allrady done visit with this Appointment Id",
					new Object[]{ patientVisit.getAppointmentIdfk() });
		}
		
		return patientVisitRepository.save(patientVisit);
	}

	@Override
	@Transactional
	public PatientVisit getVistDetails(String visitId)/* throws Exception */ {
		Optional<PatientVisit> findById = patientVisitRepository.findById(visitId);
		if (!findById.isPresent()) {
			// return ResponseEntity.notFound().build();
			throw new EntityDetailsNotFoundException("Invalid VisitId Please check it ..........",
					new Object[]{ visitId });
		}
		PatientVisit patientVisit = findById.get();

		return patientVisit;
	}

	@Transactional
	public List<PatientVisit> getAllVistofPatient(String patientDetailsId) /* throws Exception */ {

		List<PatientVisit> allVistofPatient = patientVisitRepository.getAllVistofPatient(patientDetailsId);
		if(allVistofPatient.isEmpty())
		{
			throw new EntityDetailsNotFoundException("PaitentDetails Id is Invalid Please check it ..........",
					new Object[]{ patientDetailsId });
		}
			
		
		if (allVistofPatient.isEmpty()) {
			// return ResponseEntity.notFound().build();
			throw new EntityDetailsNotFoundException("PaitentDetails Id has no Privious Visit ",
					new Object[]{ patientDetailsId });
		}
		
		return allVistofPatient;
	}

	@Override
	public List<Diagnosis> visitDetails(String id) {
		/* ResponseEntity<List<Diagnosis>> dignosis; */// do rest api to procedure and all
		// dignosis =
		/*
		 * restTemplate.getForObject("http://localhost:8081/healthcare/diagnosis/1/"+id,
		 * List<Diagnosis.class>); dignosis =
		 * restTemplate.exchange("http://DIAGNOSIS/healthcare/diagnosis/1/"+id,
		 * HttpMethod.GET,null,new ParameterizedTypeReference<List<Diagnosis>>() {});
		 * 
		 * List<Diagnosis> dig=dignosis.getBody(); int i=0; for (Diagnosis diagnosis :
		 * dig) {
		 * 
		 * 
		 * System.out.println("\n\n\n\n\n");
		 * System.out.println("------------------ Dignosis --------------");
		 * System.out.println("ID ===== "+dig.get(i).getDiagonosisId());
		 * System.out.println("Discription ===== "+dig.get(i).getDescription());
		 * System.out.println("Visit Id  ===== "+dig.get(i).getPatient_visit_id());
		 * System.out.println("\n\n\n\n\n"); i++; }
		 * 
		 * 
		 * return dig;
		 */
		return null;
	}

}
