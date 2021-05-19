package com.davita.impact.erp.patient.serviceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.davita.impact.erp.patient.exception.EntityDetailsNotFoundException;
import com.davita.impact.erp.patient.model.Allergies;
import com.davita.impact.erp.patient.model.LanguageKnown;
import com.davita.impact.erp.patient.model.PatientDetails;
import com.davita.impact.erp.patient.model.PatientVisit;
import com.davita.impact.erp.patient.repository.PatientRepository;
import com.davita.impact.erp.patient.repository.PatientVisitRepository;

@ExtendWith(MockitoExtension.class)
class PatientVisitServicesImplTest {

	@Mock
	PatientVisitRepository patientVisitRepository;

	@Mock
	PatientRepository patientRepository;

	@InjectMocks
	PatientVisitServicesImpl patientVisitServicesImpl;

	@Mock
	PatientVisit patientVisit;

	@Mock
	PatientDetails patientDetails;

	// patientVisit.setAppointmentIdfk("CT1234");

	@Test
	void creteVisitId() throws Exception {
		// givan

		patientVisit.setUserIdfk("CT9875");

		// patientVisit.setAppointmentIdfk("CT1234");

		// PatientDetails patientDetails = new PatientDetails ();

		Set<PatientVisit> set = new HashSet<PatientVisit>();

		// when

		when(patientRepository.findById(patientVisit.getUserIdfk())).thenReturn(Optional.of(patientDetails));

		when(patientVisitRepository.findByUserIdfkAndAppointmentIdfkAndAppointmentStatus(
				patientVisit.getUserIdfk(), patientVisit.getAppointmentIdfk(),
				patientVisit.isAppointmentStatus())).thenReturn(set);
		when(patientVisitRepository.save(patientVisit)).thenReturn(patientVisit);
		// then

		PatientVisit creteVisitActual = patientVisitServicesImpl.creteVisitId(patientVisit);

		assertEquals(patientVisit.getAppointmentIdfk(), creteVisitActual.getAppointmentIdfk());

	}


	
	@Test
	public void getAllVistofPatient() throws Exception {
		patientVisit.setUserIdfk("AK1234");

		List<PatientVisit> Expected = new ArrayList<>();
		Expected.add(patientVisit);

		when(patientVisitRepository.getAllVistofPatient(patientVisit.getUserIdfk())).thenReturn(Expected);
		List<PatientVisit> allVistofPatientActual = patientVisitServicesImpl
				.getAllVistofPatient(patientVisit.getUserIdfk());
		assertEquals(Expected, allVistofPatientActual);
	}
	
	
	@Test
	public void getVistDetails()throws Exception{
		patientVisit.setId("CT345");
		
		
		when(patientVisitRepository.findById(patientVisit.getId())).thenReturn(Optional.of(patientVisit));
		PatientVisit vistDetailsActual = patientVisitServicesImpl.getVistDetails(patientVisit.getId());
		
		assertEquals(patientVisit, vistDetailsActual);
	}
	
	
	@Test
	void creteVisitIdInvalidPataientId() {
		PatientDetails patient = new PatientDetails();
		patient.setId("zzz");
		
		Optional<PatientDetails> mockVisitDetails = Optional.empty();
		when(patientRepository.findById(patientVisit.getUserIdfk())).thenReturn(mockVisitDetails);

		try {
			patientVisitServicesImpl.creteVisitId(patientVisit);
		} catch (EntityDetailsNotFoundException e) {

			assertNotNull(e);
		} 
	}
	
	@Test
	void getVistDetailsInvalidVisitId()
	{
		PatientDetails patient = new PatientDetails();
		
		
		Optional<PatientVisit> mockVisitDetails = Optional.empty();
		when(patientVisitRepository.findById(patientVisit.getId())).thenReturn(mockVisitDetails);

		try {
			patientVisitServicesImpl.getVistDetails(patientVisit.getId());
		} catch (EntityDetailsNotFoundException e) {

			assertNotNull(e);
		} 
	}
	
	/*
	 * @Test void getAllVistofPatientInvaidPatientDetailsId() {
	 * 
	 * Optional<PatientVisit> mockVisitDetails = Optional.empty();
	 * doReturn(mockVisitDetails).when(patientVisitRepository.getAllVistofPatient(
	 * patientVisit.getPataintDetailIdfk()));
	 * 
	 * try { patientVisitServicesImpl.getAllVistofPatient(patientVisit.
	 * getPataintDetailIdfk()); } catch (EntityDetailsNotFoundException e) {
	 * 
	 * assertNotNull(e); }
	 * 
	 * 
	 * }
	 * 
	 * //@Test void creteVisitIdAllredayUseSameVisitIdPataientId() { PatientDetails
	 * patient = new PatientDetails(); Set<PatientVisit> value =new HashSet<>();
	 * value.add(patientVisit); patient.setId("zzz");
	 * 
	 * Optional<PatientDetails> mockVisitDetails = Optional.empty();
	 * when(patientVisitRepository.
	 * findByPataintDetailIdfkAndAppointmentIdfkAndAppointmentStatus(
	 * patientVisit.getPataintDetailIdfk(), patientVisit.getAppointmentIdfk(),
	 * patientVisit.isAppointmentStatus())).thenReturn(value);
	 * 
	 * try { patientVisitServicesImpl.creteVisitId(patientVisit); } catch
	 * (EntityDetailsNotFoundException e) {
	 * 
	 * assertNotNull(e); }
	 * 
	 * }
	 */	
}
