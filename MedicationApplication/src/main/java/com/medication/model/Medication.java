package com.medication.model;

import javax.validation.constraints.NotBlank;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("medication")
public class Medication {

	@NotBlank(message = "Medicine Name cannot be Blank")
	@PrimaryKeyColumn(name = "medicine_name", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	private String medicineName;
	private String dosage;
	private String description;
	
}
