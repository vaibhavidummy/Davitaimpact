package in.davita.impact.erp.admin.controller;


import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.davita.impact.erp.admin.model.Login;
import in.davita.impact.erp.admin.model.Role;
import in.davita.impact.erp.admin.model.UpdatePassword;
import in.davita.impact.erp.admin.model.UserRegistrationDetail;
import in.davita.impact.erp.admin.model.UserRegistrationDetailResponse;
import in.davita.impact.erp.admin.service.UserLoginService;
import in.davita.impact.erp.admin.service.UserRegistrationDetailService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * 'User login service controller' Bounded Context
 * REST Controller Service
 * @version 1.0 08-04-2021
 * @author PrashantW3
 * */
@RestController
@RequestMapping(value = "authentication")
@Api(value = "User login controller")
@CrossOrigin(origins="*", allowedHeaders="*")
public class UserLoginController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserLoginController.class);
	@Autowired
	private UserLoginService userLoginService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	UserRegistrationDetailService userRegistrationDetailService;

	@ApiOperation(value = "Method for User login")
	@ApiResponse(code = 200, message = "Response is ResponseEntity<UserRegistrationDetailResponse>", response = UserRegistrationDetail.class)
	@PostMapping(value = "/login")
	public ResponseEntity<UserRegistrationDetailResponse> userLogin(
			@ApiParam(value = "Login class model") @RequestBody @Valid Login loginDtls) {
		LOGGER.info("Inside login user method of UserLoginController");
		UserRegistrationDetailResponse userLoginStatus = userLoginService.userLogin(loginDtls.getEmail().trim(), loginDtls.getPassword().trim());
		if (null != userLoginStatus) {
			LOGGER.info("user loged successfully");
		}
		return new ResponseEntity<UserRegistrationDetailResponse>(userLoginStatus, HttpStatus.OK);
	}

	@ApiOperation(value = "Method for token set and mail send")
	@ApiResponse(code = 200, message = "Response is ResponseEntity<Boolean>", response = Boolean.class)
	@PostMapping("/forgotpassword")
	public ResponseEntity<Boolean> forgotPassword(@ApiParam(value = "Login class model") 
	@RequestParam("email") String email) {
		UserRegistrationDetail userRegistrationDetail = null;
		userRegistrationDetail = userRegistrationDetailService.checkForExistingEmail(email);
		boolean mailSendStatus = false;
		String genPassword=null;
		if (null != userRegistrationDetail) {
			 genPassword= userLoginService.generateCommonLangPassword();
			mailSendStatus = userLoginService.sendEmail(userRegistrationDetail.getUserCredentials().getEmail(),
					genPassword);
		}
		if (mailSendStatus) {
			LOGGER.info("Mail sent successfully");
			userRegistrationDetail.getUserCredentials().setPassword(passwordEncoder.encode(genPassword));
			userRegistrationDetail.setIsPasswordChangeRequired(true);
		    userRegistrationDetailService.updateUser(userRegistrationDetail);
		}
		return new ResponseEntity<Boolean>(mailSendStatus, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Method for updating password details")
	@ApiResponse(code = 200, message = "Response is Boolean model", response = UserRegistrationDetail.class)
	@PutMapping(value="/updatepassword")
	public ResponseEntity<Boolean> updatePassword(
			@ApiParam(value = "UserRegistration class model") @RequestBody @Valid UpdatePassword updatePassword) {
		LOGGER.info("Inside update password method of UserLoginController");
		Boolean passwordUpdateStatus = false;
		passwordUpdateStatus = userLoginService.updatePassword(updatePassword);
		if(passwordUpdateStatus) {
			LOGGER.info("Password Updated::{}",passwordUpdateStatus);
			boolean isPasswordChangeReq = false;
			boolean isPersonalDeatilRequired = false;
			UserRegistrationDetail usercurrentStatus = userRegistrationDetailService.checkForExistingEmail(updatePassword.getEmail());
			//TODO/* 1)below code work in all condition even if PATIENT forgot a password at first time login */
			/* 2)In Normal scenario of patient, parameter change required after patientDetails entered into database  and for 
			 * that we required microservices communication so please complete that scenerio after communication done*/
			if (usercurrentStatus.getIsPasswordChangeRequired()||usercurrentStatus.getIsPersonalDetailsRequired()) {
				if(usercurrentStatus.getRole().equals(Role.Patient)) {
						isPersonalDeatilRequired=usercurrentStatus.getIsPersonalDetailsRequired();
					}
						userRegistrationDetailService.afterFirstAuthParamterChange(isPasswordChangeReq, 
						isPersonalDeatilRequired,usercurrentStatus.getUserId());
			}
		}
		return new ResponseEntity<Boolean>(passwordUpdateStatus, HttpStatus.OK);
	}
	
	
}
