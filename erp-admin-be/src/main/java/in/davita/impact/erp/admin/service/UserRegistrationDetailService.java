package in.davita.impact.erp.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import in.davita.impact.erp.admin.model.UserRegistrationDetail;
/**
 * 'User registration service interface' 
 * @version 1.0 06-04-2021
 * @author PrashantW3
 * */
@Service
public interface UserRegistrationDetailService {

	UserRegistrationDetail addUser(UserRegistrationDetail userRegistrationDtls);
	
	UserRegistrationDetail updateUser(UserRegistrationDetail userRegistrationDtls);
	
	Optional<UserRegistrationDetail> getUser(String userId);

	int disableUser(String userId);

	List<UserRegistrationDetail> getAllUsers();

	UserRegistrationDetail checkForExistingEmail(String email);

	int afterFirstAuthParamterChange(boolean isPasswordChangeReq, boolean isPersonalDeatilRequired,
			String userId);

	

}
