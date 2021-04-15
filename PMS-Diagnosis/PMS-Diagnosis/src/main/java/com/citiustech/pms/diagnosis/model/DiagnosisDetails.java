package com.citiustech.pms.diagnosis.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString

public class DiagnosisDetails {
	
	private String id;
	private String name;
	private String description;

}
