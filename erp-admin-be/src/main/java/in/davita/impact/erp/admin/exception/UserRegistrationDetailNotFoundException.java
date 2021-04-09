package in.davita.impact.erp.admin.exception;


/**
* Exception is raised when
* '{@link in.davita.impact.erp.admin.controller.UserRegistrationDetailController#getUser(in.davita.impact.erp.admin.model.UserRegistrationDetail)}'
* operation
* @version 1.0 07-04-2021
* @author PrashantW3
**/


public class UserRegistrationDetailNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -3226630364916511527L;
	private Object[] args; 
	
	public UserRegistrationDetailNotFoundException() {
		super();
	}
	
	public UserRegistrationDetailNotFoundException(String message) {
		super(message);
	}
	
	public UserRegistrationDetailNotFoundException(String message, Object[] args) {
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
