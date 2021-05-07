package com.davita.impact.erp.patient.repository;

import javax.transaction.Transactional;

import org.hibernate.annotations.SQLInsert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.davita.impact.erp.patient.model.PatientDetails;
@Repository
public interface PatientRepository extends JpaRepository<PatientDetails, String>{

	@Modifying
	@Transactional
	@Query(value="UPDATE patientdetails_allergies SET allergies_id=:allergies_id WHERE patientdetails_id=:patientdetails_id AND allergies_id=:old_allergies_id", nativeQuery = true )
	int updateAllergy(@Param("patientdetails_id") String patientdetails_id,@Param("allergies_id") int allergies_id,@Param("old_allergies_id") int old_allergies_id);
	
	
}
