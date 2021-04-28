package com.citiustech.pms.diagnosis.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DiagnosisSuccess {

	private String message;
	private boolean successFlag;
	private List<DiagnosisMaster> diagnosisMaster;
	private List<Diagnosis> diagnosis ;
}
