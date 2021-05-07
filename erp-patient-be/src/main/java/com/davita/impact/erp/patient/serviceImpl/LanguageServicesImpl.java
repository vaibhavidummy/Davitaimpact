package com.davita.impact.erp.patient.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davita.impact.erp.patient.model.LanguageKnown;
import com.davita.impact.erp.patient.repository.LanguageRepo;
import com.davita.impact.erp.patient.service.LanguageServices;

@Service
public class LanguageServicesImpl implements LanguageServices {

	@Autowired
	LanguageRepo languageRepo;
	
	
	@Override
	public List<LanguageKnown> getAllLangwages() {
	
		List<LanguageKnown> findAll = languageRepo.findAll();
		return findAll;
	}

}
