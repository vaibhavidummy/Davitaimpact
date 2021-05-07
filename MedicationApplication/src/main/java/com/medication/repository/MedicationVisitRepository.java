package com.medication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;

import com.medication.model.MedicationOnVisit;

public interface MedicationVisitRepository extends CassandraRepository<MedicationOnVisit, String> {

	@Query("select * from medication_visit where patient_id=:patientId and visit_id=:visitId")
	public Optional<MedicationOnVisit> getMedicationFromPatientandVisit(@Param("visitId") String visitId,
			@Param("patientId") String patientId);

	@Query("select * from medication_visit where patient_id=:patientId")
	public List<MedicationOnVisit> getAllMedicationForPatient(@Param("patientId") String patientId);
}
