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

import com.davita.impact.erp.patient.model.Allergies;
import com.davita.impact.erp.patient.model.LanguageKnown;
import com.davita.impact.erp.patient.repository.AllergiesRepo;

@ExtendWith(MockitoExtension.class)
class allergiesServicesImplTest {

	@Mock
	AllergiesRepo allergiesRepo;

	@Mock
	Allergies allergies;

	@InjectMocks
	allergiesServicesImpl allergiesServicesImpl;

	@Test
	void getAllAllergies() {

		List<Allergies> Expected = new ArrayList<>();
		Expected.add(allergies);

		when(allergiesRepo.findAll()).thenReturn(Expected);
		List<Allergies> allergiesActual = allergiesServicesImpl.getAllAllergies();

		assertEquals(Expected, allergiesActual);

	}

}
