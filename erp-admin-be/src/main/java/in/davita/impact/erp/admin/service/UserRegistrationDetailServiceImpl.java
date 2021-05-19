package in.davita.impact.erp.admin.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.commons.lang3.EnumUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.davita.impact.erp.admin.exception.EntityDetailsAlreadyExistException;
import in.davita.impact.erp.admin.exception.EntityDetailsNotFoundException;
import in.davita.impact.erp.admin.model.Role;
import in.davita.impact.erp.admin.model.UserRegistrationDetail;
import in.davita.impact.erp.admin.model.UserRegistrationDetailResponse;
import in.davita.impact.erp.admin.repository.UserRegistrationDetailRepository;
import in.davita.impact.erp.admin.util.Utility;
/**
 * 'User registration service interface implementation' 
 * @version 1.0 06-04-2021
 * @author PrashantW3
 * */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserRegistrationDetailServiceImpl implements UserRegistrationDetailService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserLoginServiceImpl.class);
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRegistrationDetailRepository userRegistrationDetailRepository;

	@Override
	public String addUser(@Valid UserRegistrationDetail userRegistrationDtls) {
		LOGGER.info("inside addUser method of UserRegistrationDetailServiceImpl");
		Boolean isPasswordChangeRequired = userRegistrationDtls.getRole().equals(Role.Patient) ? false : true;
		userRegistrationDtls.setIsPasswordChangeRequired(isPasswordChangeRequired);
		Boolean isPersonalDtlsRequired = userRegistrationDtls.getRole().equals(Role.Patient) ? true : false;
		userRegistrationDtls.setIsPersonalDetailsRequired(isPersonalDtlsRequired);
		UserRegistrationDetail result = null;
		userRegistrationDtls.getUserCredentials()
				.setPassword(passwordEncoder.encode(userRegistrationDtls.getUserCredentials().getPassword()));
		if (null == userRegistrationDetailRepository
				.checkForExistingEmail(userRegistrationDtls.getUserCredentials().getEmail())) {
			result = userRegistrationDetailRepository.save(userRegistrationDtls);
			if (null != result) {
				return result.getUserId();
			}
		} else {
			throw new EntityDetailsAlreadyExistException("User with email already present",
					new Object[] { userRegistrationDtls.getUserCredentials().getEmail() });
		}
		return null;
	}

	@Override
	public String updateUser(@Valid UserRegistrationDetail userRegistrationDtls) {
		LOGGER.info("inside updateUser method of UserRegistrationDetailServiceImpl");
		UserRegistrationDetail result = null;
		if (userRegistrationDetailRepository.existsById(userRegistrationDtls.getUserId())) {
			result = userRegistrationDetailRepository.save(userRegistrationDtls);
			if (null != result) {
				return result.getUserId();
			} 
		} else {
			throw new EntityDetailsNotFoundException("User registration detail not found..",
					new Object[] { userRegistrationDtls.getUserId() });
		}
		return null;
	}

	@Override
	public Optional<UserRegistrationDetail> getUser(String userId) {
		LOGGER.info("inside getUser method of UserRegistrationDetailServiceImpl");
		Optional<UserRegistrationDetail> result = null;
		if (!userId.isEmpty()) {
			result = userRegistrationDetailRepository.findById(userId);
			if (!result.isPresent()) {
				throw new EntityDetailsNotFoundException("User registration detail not found",
						new Object[] { userId });
			}
		}
		return result;
	}

	@Override
	public Boolean disableUser(String userId) {
		LOGGER.info("inside disableUser method of UserRegistrationDetailServiceImpl");
		int result = 0;
		if (!userId.isEmpty()) {
			if (userRegistrationDetailRepository.existsById(userId)) {
				result = userRegistrationDetailRepository.disableUser(userId);
				if(result>0) {
					return true;
					}
				return false;
			}else {
				throw new EntityDetailsNotFoundException("User registration detail not found", new Object[]
						   {userId});
			}
		}
		return false;
	}

	@Override
	public List<UserRegistrationDetailResponse> getAllUsers() {
		LOGGER.info("inside getAllUsers method of UserRegistrationDetailServiceImpl");
		List<UserRegistrationDetail> result= userRegistrationDetailRepository.findAll();
		List<UserRegistrationDetailResponse> uiResponseList= new ArrayList<UserRegistrationDetailResponse>();
		for (Iterator<UserRegistrationDetail> iterator = result.iterator(); iterator.hasNext();) {
			 UserRegistrationDetailResponse resObject = new Utility().bindDataToResponse(iterator.next());
			 uiResponseList.add(resObject);
		}
		return uiResponseList;
	}

	@Override
	public UserRegistrationDetail checkForExistingEmail(String email) {
		LOGGER.info("inside checkForExistingEmail method of UserRegistrationDetailServiceImpl");
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
	public Boolean afterFirstAuthParamterChange(boolean isPasswordChangeReq, boolean isPersonalDeatilRequired,
			String userId) {
		int result = 0;
		if (!userId.isEmpty()) {
			result = userRegistrationDetailRepository.afterFirstAuthParamterChange(isPasswordChangeReq,
					isPersonalDeatilRequired, userId);
			if (result > 0) {
				return true;
			} else {
				throw new EntityDetailsNotFoundException("User details not found", new Object[] { userId });
			}
		}
		return false;
	}

	@Override
	public List<UserRegistrationDetailResponse> getAllUsersByRole(String role) {
		LOGGER.info("inside getAllUsers by role method of UserRegistrationDetailServiceImpl");
		List<UserRegistrationDetail> result = null;
		List<UserRegistrationDetailResponse> uiResponseList = null;
		if (EnumUtils.isValidEnum(Role.class,role)) {
			result = userRegistrationDetailRepository.findByrole(Role.valueOf(role));
			if(0==result.size()) {
				return uiResponseList;
			}
			uiResponseList = new ArrayList<UserRegistrationDetailResponse>();
			for (Iterator<UserRegistrationDetail> iterator = result.iterator(); iterator.hasNext();) {
				UserRegistrationDetailResponse resObject = new Utility().bindDataToResponse(iterator.next());
				uiResponseList.add(resObject);
			}
		}
		return uiResponseList;
	}
	
	
	
}
