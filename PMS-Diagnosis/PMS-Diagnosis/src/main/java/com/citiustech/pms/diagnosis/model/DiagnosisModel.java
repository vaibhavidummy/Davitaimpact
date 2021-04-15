package com.citiustech.pms.diagnosis.model;

import java.util.List;

import javax.persistence.Column;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class DiagnosisModel {

	private String patient_visit_id;

	private List<DiagnosisDetails> diagnosis_details;

}
