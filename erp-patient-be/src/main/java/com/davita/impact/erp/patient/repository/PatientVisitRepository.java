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

	
	public Set<PatientVisit> findByUserIdfkAndAppointmentIdfkAndAppointmentStatus(String pataintDetailIdfk,
			String appointmentIdfk, boolean appointmentStatus);
	
	
	@Transactional
	@Query(value="SELECT * FROM patient_visit where user_idfk=:user_idfk", nativeQuery = true )
	public List<PatientVisit> getAllVistofPatient(@Param("user_idfk") String user_idfk);
	
	@Query(value="SELECT * FROM patient_visit where user_id_fk=:user_id_fk", nativeQuery = true )
	public Optional<PatientVisit> checkValidPatientDetailsId(@Param("user_id_fk") String user_id_fk);
	
}
