package com.medication.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.medication.model.Medication;

public interface MedicationRepository extends CassandraRepository<Medication, String> {

}
