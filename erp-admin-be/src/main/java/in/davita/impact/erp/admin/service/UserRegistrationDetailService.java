package in.davita.impact.erp.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.davita.impact.erp.admin.model.UserRegistrationDetail;
import io.swagger.annotations.Api;
/**
 * 'User registration service interface' 
 * @version 1.0 06-04-2021
 * @author PrashantW3
 * */

@Service
public interface UserRegistrationDetailService {

	UserRegistrationDetail addUser(UserRegistrationDetail userRegistrationDtls);
	
	UserRegistrationDetail UpdateUser(UserRegistrationDetail userRegistrationDtls);
	
	Optional<UserRegistrationDetail> getUser(String userId);

	int DisableUser(String userId);

	List<UserRegistrationDetail> getAllUser();

	String checkForExistingEmail(String email);

	String checkForExistingUsername(String userName);

}
