package com.davita.impact.erp.patient.serviceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.davita.impact.erp.patient.model.LanguageKnown;
import com.davita.impact.erp.patient.model.PatientVisit;
import com.davita.impact.erp.patient.repository.LanguageRepo;

@ExtendWith(MockitoExtension.class)
class LanguageServicesImplTest {

	@Mock
	LanguageRepo languageRepo;

	@Mock
	LanguageKnown languageKnown;

	@InjectMocks
	LanguageServicesImpl languageServicesImpl;

	@Test
	void getAllLangwages() {
		// LanguageKnown languageKnown =new LanguageKnown();
		// patientVisit.setPataintDetailIdfk("AK1234");
		languageKnown.setId(1);
		languageKnown.setName("Eng");

		List<LanguageKnown> Expected = new ArrayList<>();
		Expected.add(languageKnown);

		when(languageRepo.findAll()).thenReturn(Expected);
		List<LanguageKnown> languageKnownActual = languageServicesImpl.getAllLangwages();

		assertEquals(Expected, languageKnownActual);

	}

}
