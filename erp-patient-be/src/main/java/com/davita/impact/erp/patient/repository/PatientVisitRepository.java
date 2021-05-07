package com.davita.impact.erp.patient.repository;


import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.davita.impact.erp.patient.model.PatientVisit;

public interface PatientVisitRepository extends JpaRepository<PatientVisit,String>{

	public Set<PatientVisit> findByPataintIdfkAndAppointmentIdfkAndAppointmentStatus(String pataintId_fk,String appoinmentId,boolean status);
	
}
