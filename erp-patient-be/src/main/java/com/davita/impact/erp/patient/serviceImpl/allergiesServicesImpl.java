package com.davita.impact.erp.patient.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davita.impact.erp.patient.model.Allergies;
import com.davita.impact.erp.patient.repository.AllergiesRepo;
import com.davita.impact.erp.patient.service.allergiesServices;

@Service
public class allergiesServicesImpl implements allergiesServices{

	@Autowired
	 AllergiesRepo allergiesRepo;
	
	@Override
	public List<Allergies> getAllAllergies() {
		
		
		List<Allergies> findAll = allergiesRepo.findAll();
		return findAll;
	}

}
