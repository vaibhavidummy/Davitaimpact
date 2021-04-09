package com.medication.repository;

import java.util.Optional;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import com.medication.model.MedicationOnVisit;

public interface MedicationVisitRepository extends CassandraRepository<MedicationOnVisit, String> {

	@Query("select * from medication_visit where patient_id=?1 and visit_id=?0")
	public Optional<MedicationOnVisit> getMedicationFromPatientandVisit(String visitId, String patientId);

}
