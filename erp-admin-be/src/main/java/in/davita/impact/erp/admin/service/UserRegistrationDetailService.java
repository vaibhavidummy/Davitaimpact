package in.davita.impact.erp.admin.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import in.davita.impact.erp.admin.model.UserRegistrationDetail;
import in.davita.impact.erp.admin.model.UserRegistrationDetailResponse;
/**
 * 'User registration service interface' 
 * @version 1.0 06-04-2021
 * @author PrashantW3
 * */
@Service
public interface UserRegistrationDetailService {

	String addUser(@Valid UserRegistrationDetail userRegistrationDtls);
	
	String updateUser( @Valid UserRegistrationDetail userRegistrationDtls);
	
	Optional<UserRegistrationDetail> getUser(String userId);

	Boolean disableUser(String userId);

	List<UserRegistrationDetailResponse> getAllUsers();

	UserRegistrationDetail checkForExistingEmail(String email);

	Boolean afterFirstAuthParamterChange(boolean isPasswordChangeReq, boolean isPersonalDeatilRequired,
			String userId);

	List<UserRegistrationDetailResponse> getAllUsersByRole(String role);

	

	

}
