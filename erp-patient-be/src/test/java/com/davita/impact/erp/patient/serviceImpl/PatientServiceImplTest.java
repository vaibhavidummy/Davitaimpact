package com.davita.impact.erp.patient.serviceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.davita.impact.erp.patient.exception.EntityDetailsNotFoundException;
import com.davita.impact.erp.patient.model.Allergies;
import com.davita.impact.erp.patient.model.Appointment;
import com.davita.impact.erp.patient.model.BasicDetails;
import com.davita.impact.erp.patient.model.LanguageKnown;
import com.davita.impact.erp.patient.model.PatientDetails;
import com.davita.impact.erp.patient.repository.AllergiesRepo;
import com.davita.impact.erp.patient.repository.LanguageKnownRepository;
import com.davita.impact.erp.patient.repository.LanguageRepo;
import com.davita.impact.erp.patient.repository.PatientRepository;
import com.davita.impact.erp.patient.service.PatientServices;

@ExtendWith(MockitoExtension.class)
class PatientServiceImplTest {

	@Mock
	PatientRepository patientRepository;

	@Mock
	LanguageRepo languageRepo;

	@Mock
	AllergiesRepo allergiesRepo;

	@InjectMocks
	PatientServiceImpl patientServiceImpl;

	@Test
	void getPatientById() {
		// given
		String id = "aaopp";
		PatientDetails Expected = new PatientDetails();
		Expected.setId("aaopp");
		// when;
		when(patientRepository.findById(id)).thenReturn(Optional.of(Expected));

		// then
		PatientDetails patientDetailsActual = patientServiceImpl.getPatientById(id);
		assertEquals(Expected, patientDetailsActual);

	}

	@Test
	void getAllPatient() {

		String name = "sachin";
		PatientDetails patientdetails = new PatientDetails();
		BasicDetails basicDetails = new BasicDetails();
		basicDetails.setFirstName("Sachin");
		patientdetails.setBasicDetails(basicDetails);
		List<PatientDetails> Expected = new ArrayList<>();
		Expected.add(patientdetails);
		// when;
		when(patientRepository.findAll()).thenReturn(Expected);

		// then
		List<PatientDetails> patientDetailsActual = patientServiceImpl.getAllPatient();

		assertEquals(Expected.stream().findAny().get().getBasicDetails().getFirstName(),
				patientDetailsActual.stream().findAny().get().getBasicDetails().getFirstName());

	}

	@Test
	void findPatientbyId() {

		// given
		String id = "aaopp";
		PatientDetails Expected = new PatientDetails();
		Expected.setId("aaopp");
		// when;
		when(patientRepository.findById(id)).thenReturn(Optional.of(Expected));

		// then
		PatientDetails patientDetailsActual = patientServiceImpl.findPatientbyId(id);
		assertEquals(Expected, patientDetailsActual);

	}

	@Test
	void updatePatient() throws Exception {

		// given

		LanguageKnown lang = new LanguageKnown();
		lang.setId(1);
		lang.setName("Engilsh");
		String id = "CTdvtr";
		int lang_id = 1;
		Allergies allergi = new Allergies();
		allergi.setId(1);
		int alargy_id = 1;

		List<Integer> list_lang = new ArrayList<>();
		list_lang.add(1);

		List<Integer> list_alergy = new ArrayList<>();
		list_alergy.add(1);

		PatientDetails patient = new PatientDetails();
		patient.setLanguageKnown(list_lang);
		patient.setAllergies(list_alergy);

		// when

		when(patientRepository.findById(patient.getId())).thenReturn(Optional.of(patient));

		when(languageRepo.findById(lang_id)).thenReturn(Optional.of(lang));

		when(allergiesRepo.findById(alargy_id)).thenReturn(Optional.of(allergi));

		when(patientRepository.save(patient)).thenReturn(patient);

		// then

		// PatientDetails patientDetailsActual=patientServices.updatePatient( patient);

		PatientDetails patientDetailsActual = patientServiceImpl.updatePatient(patient);

		assertEquals(patient, patientDetailsActual);

	}

	@Test
	void updatePatientwithInvalidId() throws Exception {
		PatientDetails patient = new PatientDetails();

		Optional<PatientDetails> mockPatientDetails = Optional.empty();
		when(patientRepository.findById(patient.getId())).thenReturn(mockPatientDetails);

		try {
			patientServiceImpl.updatePatient(patient);
		} catch (EntityDetailsNotFoundException e) {

			assertNotNull(e);
		}

	}

	@Test
	void getPatientByIdInvalidPataientId() {
		PatientDetails patient = new PatientDetails();
		patient.setId("zzz");
		
		Optional<PatientDetails> mockPatientDetails = Optional.empty();
		when(patientRepository.findById(patient.getId())).thenReturn(mockPatientDetails);

		try {
			patientServiceImpl.getPatientById(patient.getId());
		} catch (EntityDetailsNotFoundException e) {

			assertNotNull(e);
		}

	}
	
	
	@Test
	void findPatientbyIdInvalidPatientId() {
		PatientDetails patient = new PatientDetails();
		patient.setId("zzz");
		Optional<PatientDetails> mockPatientDetails = Optional.empty();
		when(patientRepository.findById(patient.getId())).thenReturn(mockPatientDetails);

		try {
			patientServiceImpl.findPatientbyId(patient.getId());
		} catch (EntityDetailsNotFoundException e) {

			assertNotNull(e);
		}
		
	}

	@Test
	void addNewPatient() {

		// given

		LanguageKnown lang = new LanguageKnown();
		lang.setId(1);
		lang.setName("Engilsh");
		String id = "CTdvtr";
		int lang_id = 1;
		Allergies allergi = new Allergies();
		allergi.setId(1);
		int alargy_id = 1;

		List<Integer> list_lang = new ArrayList<>();
		list_lang.add(1);

		List<Integer> list_alergy = new ArrayList<>();
		list_alergy.add(1);

		PatientDetails patient = new PatientDetails();
		patient.setLanguageKnown(list_lang);
		patient.setAllergies(list_alergy);

		// when

		when(languageRepo.findById(lang_id)).thenReturn(Optional.of(lang));

		when(allergiesRepo.findById(alargy_id)).thenReturn(Optional.of(allergi));

		when(patientRepository.save(patient)).thenReturn(patient);

		// then

		// PatientDetails patientDetailsActual=patientServices.updatePatient( patient);

		PatientDetails patientDetailsActual = patientServiceImpl.addNewPatient(patient);

		assertEquals(patient, patientDetailsActual);

	}

}
