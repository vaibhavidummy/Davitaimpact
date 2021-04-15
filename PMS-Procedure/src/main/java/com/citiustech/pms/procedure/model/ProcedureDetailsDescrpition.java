package com.citiustech.pms.procedure.model;



import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ProcedureDetailsDescrpition {

	private String id;
	
	private String name;
	
	private String description;
}