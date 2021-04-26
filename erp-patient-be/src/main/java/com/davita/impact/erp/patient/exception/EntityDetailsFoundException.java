package com.davita.impact.erp.patient.exception;


/**
* Exception is raised when
* '{@link in.davita.impact.erp.admin.controller.AppointmentController#getAppointment(com.davita.impact.erp.admin.model.Appointment)}'
* operation
* @version 1.0 20-04-2021
* @author VaibhaviB
**/
public class EntityDetailsFoundException extends RuntimeException{
	private static final long serialVersionUID = -3226630364916511527L;
	private Object[] args; 
	
	public EntityDetailsFoundException(String message, Object[] args) {
		super(message);
		this.args = args;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}
}

