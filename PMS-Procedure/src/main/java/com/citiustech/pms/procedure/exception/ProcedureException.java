package com.citiustech.pms.procedure.exception;


public class ProcedureException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProcedureException(String message ) {
		super(message);
	}
	
	public ProcedureException() {}
}