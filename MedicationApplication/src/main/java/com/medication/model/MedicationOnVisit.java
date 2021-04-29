package com.medication.model;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.Frozen;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Table("medication_visit")
@Data
public class MedicationOnVisit {

	@NotBlank(message = "Patient Id cannot be Blank")
	@PrimaryKeyColumn(name = "patient_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	private String patientId;
	@NotBlank(message = "Visit Id cannot be Blank")
	@PrimaryKeyColumn(name = "visit_id", ordinal = 0, type = PrimaryKeyType.CLUSTERED)
	private String visitId;
	
	@NotEmpty(message = "Medication List must not be Empty")
	@Frozen
	private List<MedicineUDT> medication;
	
	
	@Column(value = "audit_info")
	@Frozen
	private AuditUDT auditInfo;
}
