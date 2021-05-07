package com.davita.impact.erp.patient.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.davita.impact.erp.patient.model.Allergies;

@Service
public interface allergiesServices {

	public List<Allergies> getAllAllergies();
}
