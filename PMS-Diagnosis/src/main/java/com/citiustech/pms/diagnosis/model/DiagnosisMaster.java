package com.citiustech.pms.diagnosis.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "diagnosis_master", schema = "davita")
public class DiagnosisMaster implements Serializable {
	
	@Id
	@Column(name = "DiagnosisId")
	private String diagnosisId;

	@Column(name = "DiagnosisDescription")
	private String diagnosisName;

}
