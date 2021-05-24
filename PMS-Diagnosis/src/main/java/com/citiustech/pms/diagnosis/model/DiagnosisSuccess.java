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
	
	public static class DiagnosisDto{
		
	private String message;
	private boolean successFlag;
	private List<DiagnosisMaster> diagnosisMaster;
	private List<Diagnosis> diagnosis ;
	
	public String getMessage() {
		return message;
	}
	public DiagnosisDto setMessage(String message) {
		this.message = message;
		return this;
	}
	public boolean isSuccessFlag() {
		return successFlag;
	}
	public DiagnosisDto setSuccessFlag(boolean successFlag) {
		this.successFlag = successFlag;
		return this;
	}
	public List<DiagnosisMaster> getDiagnosisMaster() {
		return diagnosisMaster;
	}
	public DiagnosisDto setDiagnosisMaster(List<DiagnosisMaster> diagnosisMaster) {
		this.diagnosisMaster = diagnosisMaster;
		return this;
	}
	public List<Diagnosis> getDiagnosis() {
		return diagnosis;
	}
	public DiagnosisDto setDiagnosis(List<Diagnosis> diagnosis) {
		this.diagnosis = diagnosis;
		return this;
	}
		
	public DiagnosisSuccess build()
	{
		return new DiagnosisSuccess(this.getMessage(), this.isSuccessFlag(),  this.getDiagnosisMaster() , this.getDiagnosis());
	}
	
  }
}
