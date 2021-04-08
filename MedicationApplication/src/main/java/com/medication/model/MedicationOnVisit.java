package com.medication.model;

import java.util.List;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Frozen;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Table("medication_visit")
@Data
public class MedicationOnVisit {

	@PrimaryKeyColumn(name = "patient_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	private String patientId;
	
	@PrimaryKeyColumn(name = "visit_id", ordinal = 0, type = PrimaryKeyType.CLUSTERED)
	private String visitId;
	
	@Frozen
	private List<Medication> medication;
}
