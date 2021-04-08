package com.medication.model;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@UserDefinedType(value="medicine")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Medication {

	
	@PrimaryKeyColumn(name = "medicine_name", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	private String medicineName;
	@PrimaryKeyColumn(name = "status", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
	private String status;
	private String dosage;
	private String description;
	
}
