package com.davita.impact.erp.patient.utilities;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

public class Auditor implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		return Optional.ofNullable("Chetan");
	}

	
}
