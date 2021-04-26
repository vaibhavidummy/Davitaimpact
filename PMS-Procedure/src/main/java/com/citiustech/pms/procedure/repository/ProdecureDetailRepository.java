package com.citiustech.pms.procedure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.citiustech.pms.procedure.model.ProcedureMain;


@Repository
public interface ProdecureDetailRepository extends JpaRepository<ProcedureMain, Long> {

	@Query(value="SELECT * FROM procedure_main  WHERE patient_visit_id=:patientVisitId", nativeQuery = true )
	List<ProcedureMain> checkForExistingPatientVisitId(@Param("patientVisitId") String patientVisitId);
}