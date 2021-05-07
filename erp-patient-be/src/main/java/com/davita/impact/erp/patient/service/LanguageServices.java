package com.davita.impact.erp.patient.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.davita.impact.erp.patient.model.LanguageKnown;

@Service
public interface LanguageServices {

	public List<LanguageKnown> getAllLangwages();
}
