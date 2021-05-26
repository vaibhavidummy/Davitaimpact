package com.davita.impact.erp.patient.exception;

/**
* Exception is raised when
* '{@link in.davita.impact.erp.admin.controller.AppointmentServiceImpl#createAppointment(com.davita.impact.erp.admin.model.Appointment)}'
* operation
* @version 1.0 20-04-2021
* @author VaibhaviB
**/
public class ServiceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -3226630364916511528L;
	public ServiceNotFoundException(String message) {
		super(message);
	}

	

}
