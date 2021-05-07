package com.medication.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.medication.model.Medication;
import com.medication.model.MedicationDto;
import com.medication.model.MedicationOnVisit;
import com.medication.service.impl.MedicationServiceImpl;

@ExtendWith(MockitoExtension.class)
class MedicationControllerTest {

	@Mock
	private MedicationServiceImpl medicationServiceImpl;

	@InjectMocks
	private MedicationController medicationController;

	@Mock
	private Medication medication;

	@Mock
	private MedicationOnVisit medicationOnVisit;
	
	@Mock
	private List<MedicationOnVisit> medicationOnVisitList;

	@Test
	void testGetMedications() {
		// given
		List<Medication> medicationList = new ArrayList<>();
		medicationList.add(medication);
		MedicationDto MedicationDtoExpected = new MedicationDto.MedicationDtoBuilder().setMedicationList(medicationList)
				.setSuccessFlag(Boolean.TRUE).build();
		// when
		when(medicationServiceImpl.getMedications()).thenReturn(MedicationDtoExpected);
		MedicationDto MedicationDtoActual = medicationController.getMedications().getBody();
		// then
		assertEquals(MedicationDtoExpected.getMedicationList().size(), MedicationDtoActual.getMedicationList().size());
	}

	@Test
	void testSaveMedication() {
		// given
		MedicationDto MedicationDtoExpected = new MedicationDto.MedicationDtoBuilder().setMedication(medication)
				.setSuccessFlag(Boolean.TRUE).build();
		// when
		when(medicationServiceImpl.saveMedication(medication)).thenReturn(MedicationDtoExpected);
		MedicationDto MedicationDtoActual = medicationController.saveMedication(medication).getBody();
		// then
		assertEquals(MedicationDtoExpected, MedicationDtoActual);

	}

	@Test
	void testGetMedicationForPatientOnVisit() {

		// given
		String visitId = "1";
		String patientId = "1";
		MedicationDto MedicationDtoExpected = new MedicationDto.MedicationDtoBuilder()
				.setMedicationOnVisit(medicationOnVisit).setSuccessFlag(Boolean.TRUE).build();
		// when
		when(medicationServiceImpl.getMedicationFromPatientandVisit(visitId, patientId))
				.thenReturn(MedicationDtoExpected);
		MedicationDto MedicationDtoActual = medicationController.getMedicationForPatientOnVisit(visitId, patientId)
				.getBody();
		// then
		assertEquals(MedicationDtoExpected.getMedicationOnVisit(), MedicationDtoActual.getMedicationOnVisit());

	}

	@Test
	void testSaveMedicationForPatientOnVisit() {

		// given
		MedicationDto MedicationDtoExpected = new MedicationDto.MedicationDtoBuilder()
				.setMedicationOnVisit(medicationOnVisit).setSuccessFlag(Boolean.TRUE).build();
		// when
		when(medicationServiceImpl.saveMedicationForPatientOnVisit(medicationOnVisit))
				.thenReturn(MedicationDtoExpected);
		MedicationDto MedicationDtoActual = medicationController.saveMedicationForPatientOnVisit(medicationOnVisit)
				.getBody();
		// then
		assertEquals(MedicationDtoExpected, MedicationDtoActual);

	}

	@Test
	void testGetAllMedicationForPatient() {

		// given
		String patientId = "1";
		MedicationDto MedicationDtoExpected = new MedicationDto.MedicationDtoBuilder().setMedicationOnVisitList(medicationOnVisitList)
				.setSuccessFlag(Boolean.TRUE).build();
		// when
		when(medicationServiceImpl.getAllMedicationForPatient(patientId)).thenReturn(MedicationDtoExpected);
		MedicationDto MedicationDtoActual = medicationController.getAllMedicationForPatient(patientId).getBody();
		// then
		assertEquals(MedicationDtoExpected.getMedicationList(), MedicationDtoActual.getMedicationList());

	}

}
