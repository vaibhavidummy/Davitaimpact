package com.citiustech.pms.diagnosis.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@Entity
@Table(name = "diagnosis_master", schema = "davita")
public class DiagnosisMaster implements Serializable {
	
	@Id
	@Column(name = "DiagnosisId")
	private String diagnosisId;

	@Column(name = "DiagnosisName")
	private String diagnosisName;

}
