package com.citiustech.pms.diagnosis.model;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DiagnosisModel {

	private String patient_visit_id;

	private List<DiagnosisDetails> diagnosis_details;

}
