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
	public PatientDetails creteVisitId(PatientVisit patientVisit) throws Exception {
		
		Optional<PatientDetails> findById = patientRepository.findById(patientVisit.getPataintIdfk());
		if(!findById.isPresent())
			throw new Exception("Pataient Details Id  not Matching");
		 Set<PatientVisit> find = patientVisitRepository.findByPataintIdfkAndAppointmentIdfkAndAppointmentStatus(patientVisit.getPataintIdfk(), patientVisit.getAppointmentIdfk(), patientVisit.isAppointmentStatus());
		if(!find.isEmpty())
			throw new Exception("Pataient Has allrady done visit with this Appointment Id");
		PatientDetails patientDetails = findById.get();
		List<PatientVisit> visit=new ArrayList<PatientVisit>();
		visit.add(patientVisit);
		patientDetails.setPatientVisit(visit);
		return patientRepository.save(patientDetails);
		//return patientVisitRepository.save(patientVisit);
	}


	@Override
	public List<Diagnosis> visitDetails(String id) {
		ResponseEntity<List<Diagnosis>> dignosis;
		// do rest api to procedure  and all 
		// dignosis =  restTemplate.getForObject("http://localhost:8081/healthcare/diagnosis/1/"+id, List<Diagnosis.class>);
		 dignosis =  restTemplate.exchange("http://DIAGNOSIS/healthcare/diagnosis/1/"+id, HttpMethod.GET,null,new ParameterizedTypeReference<List<Diagnosis>>() {});
			
		 List<Diagnosis> dig=dignosis.getBody();
		int i=0;
		 for (Diagnosis diagnosis : dig) {
			
		
		 System.out.println("\n\n\n\n\n");
		System.out.println("------------------ Dignosis --------------");
		System.out.println("ID ===== "+dig.get(i).getDiagonosisId());
		System.out.println("Discription ===== "+dig.get(i).getDescription());
		System.out.println("Visit Id  ===== "+dig.get(i).getPatient_visit_id());
		System.out.println("\n\n\n\n\n");
		i++;
		}
		
		
		return dig;
	}


	@Override
	public PatientVisit getVistDetails(String visitId) throws Exception {
		Optional<PatientVisit> findById = patientVisitRepository.findById(visitId);
		if(!findById.isPresent())
			throw new Exception("Invalid VisitId Please check it ..........");
		 PatientVisit patientVisit = findById.get();
		
		return patientVisit;
	}

	/*
	 * @Autowired PatientVisitRepository patientVisitDetailsRepo;
	 * 
	 * @Override
	 * 
	 * @Transactional public List<PatientVisit> getAllVisitDetails() {
	 * 
	 * 
	 * List<PatientVisit> findAll = patientVisitDetailsRepo.findAll(); return
	 * findAll;
	 * 
	 * }
	 * 
	 * @Override
	 * 
	 * @Transactional public List<PatientVisit> getPatientVisitDetailsByID(int id) {
	 * 
	 * //patientVisitDetailsRepo return null; }
	 * 
	 * @Override
	 * 
	 * @Transactional public PatientVisit getPatientVisitDetailsByVisitID(int
	 * visitId) {
	 * 
	 * Optional<PatientVisit> findById = patientVisitDetailsRepo.findById(visitId);
	 * PatientVisit patientVisitDetails = findById.get(); return
	 * patientVisitDetails; }
	 */

}
