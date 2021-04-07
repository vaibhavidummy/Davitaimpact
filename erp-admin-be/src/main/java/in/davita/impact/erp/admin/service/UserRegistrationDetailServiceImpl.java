package in.davita.impact.erp.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.davita.impact.erp.admin.model.UserRegistrationDetail;
import in.davita.impact.erp.admin.repository.UserRegistrationDetailRepository;
/**
 * 'User registration service interface implementation' 
 * @version 1.0 06-04-2021
 * @author PrashantW3
 * */
@Service
public class UserRegistrationDetailServiceImpl implements UserRegistrationDetailService {

	@Autowired
	private UserRegistrationDetailRepository userRegistrationDetailRepository;

	@Override
	public UserRegistrationDetail addUser(UserRegistrationDetail userRegistrationDtls) {
		return userRegistrationDetailRepository.save(userRegistrationDtls);
	}
	
	@Override
	public UserRegistrationDetail UpdateUser(UserRegistrationDetail userRegistrationDtls) {
		return userRegistrationDetailRepository.save(userRegistrationDtls);
	}

	
	@Override
	public Optional<UserRegistrationDetail> getUser(String userId) {
		return userRegistrationDetailRepository.findById(userId);
	}

	@Override
	public int DisableUser(String userId) {
		int result = 0;
		if (null != userId) {
			if (userRegistrationDetailRepository.existsById(userId)) {
				result = userRegistrationDetailRepository.DisableUser(userId);
			}
		}
		return result;
	}

	@Override
	public List<UserRegistrationDetail> getAllUser() {

		return userRegistrationDetailRepository.findAll();
	}

	@Override
	public String checkForExistingEmail(String email) {
		String result=null;
		if(!email.isEmpty())
		{
			result=userRegistrationDetailRepository.checkForExistingEmail(email);
		}
		return result;
	}

	@Override
	public String checkForExistingUsername(String userName) {
		String result=null;
		if(!userName.isEmpty())
		{
			result=userRegistrationDetailRepository.checkForExistingUsername(userName);
		}
		return result;
	}



	
}
