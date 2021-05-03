package in.davita.impact.erp.admin.service;

import in.davita.impact.erp.admin.model.UpdatePassword;
import in.davita.impact.erp.admin.model.UserRegistrationDetailResponse;
/**
 * 'User login service interface' 
 * @version 1.0 08-04-2021
 * @author PrashantW3
 * */
public interface UserLoginService {

	UserRegistrationDetailResponse userLogin(String email, String pass);

	boolean sendEmail(String recipientEmail, String link);

	String generateCommonLangPassword();

	Boolean updatePassword(UpdatePassword updatePassword);
}
