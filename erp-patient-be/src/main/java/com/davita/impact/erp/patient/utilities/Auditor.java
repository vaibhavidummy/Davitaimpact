package com.davita.impact.erp.patient.utilities;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.davita.impact.erp.patient.model.PatientDetails;

public class Auditor implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		return Optional.ofNullable("Chetan");
		
		
		// for dynamic Auditor Name following code  but not test proper yet To test comment frist return statement 
		//       and Uncomment following code.. 
		/*
		 * Authentication authentication =
		 * SecurityContextHolder.getContext().getAuthentication();
		 * 
		 * if (authentication == null || !authentication.isAuthenticated()) { return
		 * null; }
		 * 
		 * return Optional.ofNullable(((PatientDetails)
		 * authentication.getPrincipal()).getBasicDetails().getFirstName());
		 * 
		 */   
	} 
		
	

	
}
