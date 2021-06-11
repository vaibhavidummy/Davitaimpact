package com.citiustech.pms.diagnosis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.citiustech.pms.diagnosis.model.Diagnosis;

@Repository
public interface DiagnosisRepo extends JpaRepository<Diagnosis, Long> {

	List<Diagnosis> findByPatientVisitId(@Param("patientVisitId") String patientVisitId);
}
