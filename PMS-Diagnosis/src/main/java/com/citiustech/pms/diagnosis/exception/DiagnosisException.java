package com.citiustech.pms.diagnosis.exception;

public class DiagnosisException extends RuntimeException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8466083638263956448L;

	public DiagnosisException(String message ) {
		super(message);
	}
	
	public DiagnosisException() {}
}
