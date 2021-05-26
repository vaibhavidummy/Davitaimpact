package com.davita.impact.erp.patient.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.davita.impact.erp.patient.comman.ResponseOnOk;
import com.davita.impact.erp.patient.model.PatientDetails;
import com.davita.impact.erp.patient.model.PatientVisit;
import com.davita.impact.erp.patient.service.PatientVisitServices;
@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

	@Mock
	PatientVisitServices patientVisitServices;
	
	@Mock
	PatientVisit patientVisit;
	
	@Mock
	PatientVisit patientVisit1;
	
	@InjectMocks
	private VisitController visitController;
	@Test
	public void createVisit() throws Exception {
		
		ResponseOnOk responseOnOk = new ResponseOnOk();
		responseOnOk.setId(patientVisit.getId());
		responseOnOk.setMessage("Visit Id Created Successfully");
		
		

		ResponseEntity<ResponseOnOk> putexpectedPatientDetails = new ResponseEntity<ResponseOnOk>(responseOnOk,
				HttpStatus.OK);
		when(patientVisitServices.creteVisitId(patientVisit)).thenReturn(patientVisit1);
		
		ResponseEntity<ResponseOnOk> patientVisitDetailsActual = visitController.createVisit(patientVisit);
				

		assertEquals(putexpectedPatientDetails.getBody().getId(),  patientVisitDetailsActual.getBody().getId());

	}
	
	@Test
	public void getAllVistofPatient() throws Exception {
		String  patientDetailsId =patientVisit.getUserIdfk();
		
		List<PatientVisit> expectedVisitListPatient = Arrays.asList(patientVisit);
		when(patientVisitServices.getAllVistofPatient(patientDetailsId)).thenReturn(expectedVisitListPatient);
		// doReturn(expectedList).when(languageServices).getAllLangwages();
		List<PatientVisit> getPatientVisitActuals = visitController.showPatientVisit(patientDetailsId);
		assertEquals(expectedVisitListPatient.stream().map(PatientVisit::getId).collect(Collectors.toList()), getPatientVisitActuals.stream().map(PatientVisit::getId).collect(Collectors.toList()));
		
	}
	

}
