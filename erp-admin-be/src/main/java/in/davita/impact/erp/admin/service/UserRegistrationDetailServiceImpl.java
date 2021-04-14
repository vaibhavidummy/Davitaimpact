package in.davita.impact.erp.admin.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.davita.impact.erp.admin.exception.EntityDetailsNotFoundException;
import in.davita.impact.erp.admin.model.Role;
import in.davita.impact.erp.admin.model.UserRegistrationDetail;
import in.davita.impact.erp.admin.repository.UserRegistrationDetailRepository;
/**
 * 'User registration service interface implementation' 
 * @version 1.0 06-04-2021
 * @author PrashantW3
 * */
@Service
@Transactional
public class UserRegistrationDetailServiceImpl implements UserRegistrationDetailService {

	@Autowired
	private UserRegistrationDetailRepository userRegistrationDetailRepository;

	@Override
	public UserRegistrationDetail addUser(UserRegistrationDetail userRegistrationDtls) {
		Boolean isPasswordChangeRequired=userRegistrationDtls.getRole().equals(Role.Patient)?false:true;
		userRegistrationDtls.setIsPasswordChangeRequired(isPasswordChangeRequired);
		Boolean isPersonalDtlsRequired=userRegistrationDtls.getRole().equals(Role.Patient)?true:false;
		userRegistrationDtls.setIsPersonalDetailsRequired(isPersonalDtlsRequired);
		return userRegistrationDetailRepository.save(userRegistrationDtls);
	}
	
	@Override
	public UserRegistrationDetail updateUser(UserRegistrationDetail userRegistrationDtls) {
		return userRegistrationDetailRepository.save(userRegistrationDtls);
	}

	
	@Override
	public Optional<UserRegistrationDetail> getUser(String userId) {
		Optional<UserRegistrationDetail> result = null;
		if (!userId.isEmpty()) {
			result = userRegistrationDetailRepository.findById(userId);
			if (result.isEmpty()) {
				throw new EntityDetailsNotFoundException("User registration detail not found",
						new Object[] { userId });
			}
		}
		return result;
	}

	@Override
	public int disableUser(String userId) {
		int result = 0;
		if (null != userId) {
			if (userRegistrationDetailRepository.existsById(userId)) {
				result = userRegistrationDetailRepository.DisableUser(userId);
			}
		}
		return result;
	}

	@Override
	public List<UserRegistrationDetail> getAllUsers() {

		return userRegistrationDetailRepository.findAll();
	}

	@Override
	public UserRegistrationDetail checkForExistingEmail(String email) {
		UserRegistrationDetail result=null;
		if(!email.isEmpty())
		{
			result=userRegistrationDetailRepository.checkForExistingEmail(email);
			if (null==result) {
				throw new EntityDetailsNotFoundException("User registration details not found",
						new Object [] {email});
			}
		}
		return result;
	}

	@Override
	public int afterFirstAuthParamterChange(boolean isPasswordChangeReq, boolean isPersonalDeatilRequired,
			String userId) {
		return userRegistrationDetailRepository.afterFirstAuthParamterChange(isPasswordChangeReq,isPersonalDeatilRequired,
				userId);
	}

	
}
