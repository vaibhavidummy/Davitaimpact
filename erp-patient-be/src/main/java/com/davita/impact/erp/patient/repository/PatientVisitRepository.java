package com.davita.impact.erp.patient.repository;


import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.davita.impact.erp.patient.model.PatientVisit;
@Repository
public interface PatientVisitRepository extends JpaRepository<PatientVisit,String>{

	
	public Set<PatientVisit> findByPataintDetailIdfkAndAppointmentIdfkAndAppointmentStatus(String pataintDetailIdfk,
			String appointmentIdfk, boolean appointmentStatus);
	
	
	@Transactional
	@Query(value="SELECT * FROM patient_schema.patient_visit where pataint_detail_idfk=:pataint_detail_idfk", nativeQuery = true )
	public List<PatientVisit> getAllVistofPatient(@Param("pataint_detail_idfk") String pataint_detail_idfk);
	
	@Query(value="SELECT * FROM patient_schema.patient_visit where pataint_detail_idfk=:pataint_detail_idfk", nativeQuery = true )
	public Optional<PatientVisit> checkValidPatientDetailsId(@Param("pataint_detail_idfk") String pataint_detail_idfk);
	
}
