package com.medication.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.medication.exception.MedicationException;
import com.medication.model.Medication;
import com.medication.model.MedicationDto;
import com.medication.model.MedicationOnVisit;
import com.medication.repository.MedicationRepository;
import com.medication.repository.MedicationVisitRepository;

@ExtendWith(MockitoExtension.class)
class MedicationServiceImplTest {
	@Mock
	private MedicationRepository medicationRepository;

	@Mock
	private MedicationVisitRepository medicationVisitRepository;

	@InjectMocks
	private MedicationServiceImpl medicationServiceImpl;

	@Mock
	private Medication medication;

	@Mock
	private MedicationOnVisit medicationOnVisit;

	@Mock
	private List<MedicationOnVisit> medicationOnVisitList;
	
	@Mock
	private List<Medication> medicationList;

	@Test
	void testGetMedications() {
		// given
		MedicationDto MedicationDtoExpected = new MedicationDto.MedicationDtoBuilder().setMedicationList(medicationList)
				.setSuccessFlag(Boolean.TRUE).build();
		// when
		when(medicationRepository.findAll()).thenReturn(medicationList);
		when(medicationList.isEmpty()).thenReturn(Boolean.FALSE);
		MedicationDto MedicationDtoActual = medicationServiceImpl.getMedications();
		// then
		assertEquals(MedicationDtoExpected.getMedicationList(), MedicationDtoActual.getMedicationList());
	}

	@Test
	void testGetMedicationsWhen_MedicationListIs_Empty() {
		// given
		// when
		when(medicationRepository.findAll()).thenReturn(medicationList);
		when(medicationList.isEmpty()).thenReturn(Boolean.TRUE);
		// then
		assertThrows(MedicationException.class, () -> {
			medicationServiceImpl.getMedications();
		});
	}

	@Test
	void testSaveMedication() {
		MedicationDto MedicationDtoExpected = new MedicationDto.MedicationDtoBuilder().setMedication(medication)
				.setSuccessFlag(Boolean.TRUE).build();
		// when
		when(medicationRepository.save(medication)).thenReturn(medication);
		MedicationDto MedicationDtoActual = medicationServiceImpl.saveMedication(medication);
		// then
		assertEquals(MedicationDtoExpected, MedicationDtoActual);

	}

	@Test
	void testSaveMedication_ThrowsException() {
		// given
		// when
		when(medicationServiceImpl.saveMedication(medication)).thenThrow(MedicationException.class);
		// then
		assertThrows(MedicationException.class, () -> {
			medicationServiceImpl.saveMedication(medication);
		});

	}

	@Test
	void testGetMedicationFromPatientandVisit() {
		String visitId = "1";
		String patientId = "1";
		MedicationDto MedicationDtoExpected = new MedicationDto.MedicationDtoBuilder()
				.setMedicationOnVisit(medicationOnVisit).setSuccessFlag(Boolean.TRUE).build();
		Optional<MedicationOnVisit> medicationOnVisitOptional = Optional.ofNullable(medicationOnVisit);
		// when
		when(medicationVisitRepository.getMedicationFromPatientandVisit(visitId, patientId))
				.thenReturn(medicationOnVisitOptional);
		MedicationDto MedicationDtoActual = medicationServiceImpl.getMedicationFromPatientandVisit(visitId, patientId);
		// then
		assertEquals(MedicationDtoExpected.getMedicationOnVisit(), MedicationDtoActual.getMedicationOnVisit());

	}

	@Test
	void testGetMedicationFromPatientandVisit_ThrowsException() {
		String visitId = "1";
		String patientId = "1";
		Optional<MedicationOnVisit> medicationOnVisitOptional = Optional.empty();
		// when
		when(medicationVisitRepository.getMedicationFromPatientandVisit(visitId, patientId))
				.thenReturn(medicationOnVisitOptional);
		// then
		assertThrows(MedicationException.class, () -> {
			medicationServiceImpl.getMedicationFromPatientandVisit(visitId, patientId);
		});

	}

	@Test
	void testSaveMedicationForPatientOnVisit() {

		// given
		MedicationDto MedicationDtoExpected = new MedicationDto.MedicationDtoBuilder()
				.setMedicationOnVisit(medicationOnVisit).setSuccessFlag(Boolean.TRUE).build();
		// when
		when(medicationVisitRepository.save(medicationOnVisit)).thenReturn(medicationOnVisit);
		MedicationDto MedicationDtoActual = medicationServiceImpl.saveMedicationForPatientOnVisit(medicationOnVisit);
		// then
		assertEquals(MedicationDtoExpected, MedicationDtoActual);

	}

	@Test
	void testSaveMedicationForPatientOnVisit_ThrowsException() {

		// given
		// when
		when(medicationVisitRepository.save(medicationOnVisit)).thenThrow(MedicationException.class);
		// then
		assertThrows(MedicationException.class, ()->{
			medicationServiceImpl.saveMedicationForPatientOnVisit(medicationOnVisit);
		});

	}
	
	@Test
	void testGetAllMedicationForPatient() {
		String patientId = "1";
		MedicationDto MedicationDtoExpected = new MedicationDto.MedicationDtoBuilder().setMedicationOnVisitList(medicationOnVisitList)
				.setSuccessFlag(Boolean.TRUE).build();
		// when
		when(medicationVisitRepository.getAllMedicationForPatient(patientId)).thenReturn(medicationOnVisitList);
		MedicationDto MedicationDtoActual = medicationServiceImpl.getAllMedicationForPatient(patientId);
		// then
		assertEquals(MedicationDtoExpected.getMedicationList(), MedicationDtoActual.getMedicationList());

	}
	
	@Test
	void testGetAllMedicationForPatient_ThrowsException() {
		String patientId = "1";
		// when
		when(medicationVisitRepository.getAllMedicationForPatient(patientId)).thenThrow(MedicationException.class);
		// then
		assertThrows(MedicationException.class, ()->{
			medicationServiceImpl.getAllMedicationForPatient(patientId);
		});

	}

}
