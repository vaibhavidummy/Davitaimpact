package com.citiustech.pms.diagnosis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.citiustech.pms.diagnosis.model.Diagnosis;

@Repository
public interface DiagnosisRepo extends JpaRepository<Diagnosis, Long> {

	@Query(value="SELECT * FROM diagnosis WHERE patient_visit_id=:patientVisitId", nativeQuery = true )
	List<Diagnosis> checkForExistingPatientVisitId(@Param("patientVisitId") String patientVisitId);
}
