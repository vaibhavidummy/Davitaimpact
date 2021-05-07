package com.davita.impact.erp.patient.comman;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseOnOk {

	private String id;
	private String message;
	private int status;
	
	public ResponseOnOk() {
		
	}
}
