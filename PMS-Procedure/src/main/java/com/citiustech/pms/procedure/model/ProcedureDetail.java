package com.citiustech.pms.procedure.model;

import java.util.List;

import lombok.Data;

@Data
public class ProcedureDetail {

	private String patient_visit_id;
	
	private List<ProcedureDetailsDescrpition> procedure_details;
}