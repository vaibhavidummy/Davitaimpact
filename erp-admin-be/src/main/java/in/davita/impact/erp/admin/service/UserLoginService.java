package in.davita.impact.erp.admin.service;


import in.davita.impact.erp.admin.model.UserRegistrationDetail;
/**
 * 'User login service interface' 
 * @version 1.0 08-04-2021
 * @author PrashantW3
 * */
public interface UserLoginService {

	UserRegistrationDetail userLogin(String email, String pass);

	boolean sendEmail(String recipientEmail, String link);

	String generateCommonLangPassword();
}
