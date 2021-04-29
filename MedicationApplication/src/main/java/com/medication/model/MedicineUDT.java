package com.medication.model;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@UserDefinedType(value = "medicine")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicineUDT {

	@Column(value = "medicine_name")
	private String medicineName;
	private String dosage;
	private String description;

}
