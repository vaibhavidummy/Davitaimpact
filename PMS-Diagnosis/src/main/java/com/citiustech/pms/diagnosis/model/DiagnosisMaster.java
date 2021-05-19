package com.citiustech.pms.diagnosis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Component
@Data
@Entity 
@AllArgsConstructor
@NoArgsConstructor
public class DiagnosisMaster {
	
	@Id
	@Column(name = "DiagnosisId")
	private String diagnosisId;

	@Column(name = "DiagnosisDescription")
	private String diagnosisName;

}
