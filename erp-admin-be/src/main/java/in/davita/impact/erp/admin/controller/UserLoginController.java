package in.davita.impact.erp.admin.controller;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.davita.impact.erp.admin.model.Login;
import in.davita.impact.erp.admin.model.UserRegistrationDetail;
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
public class UserLoginController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserLoginController.class);
	@Autowired
	private UserLoginService userLoginService;
	
	@Autowired
	UserRegistrationDetailService userRegistrationDetailService;

	@ApiOperation(value = "Method for User login")
	@ApiResponse(code = 200, message = "Response is ResponseEntity<UserRegistrationDetail>", response = UserRegistrationDetail.class)
	@PostMapping(value = "/login")
	public ResponseEntity<UserRegistrationDetail> userLogin(
			@ApiParam(value = "Login class model") @RequestBody @Valid Login loginDtls) {
		LOGGER.info("Inside login user method of UserLoginController");
		UserRegistrationDetail userLoginStatus = null;
		userLoginStatus = userLoginService.userLogin(loginDtls.getEmail(), loginDtls.getPassword());
		if (null != userLoginStatus) {
			LOGGER.info("user loged successfully");
			/* After first time login some attribute needs to be change */
			boolean isPasswordChangeReq = false;
			boolean isPersonalDeatilRequired = false;
			int updateResult;
			/* below code work in all condition even if PATIENT forgot a password at first time login */
			if (userLoginStatus.getIsPasswordChangeRequired()||userLoginStatus.getIsPersonalDetailsRequired()) {
					updateResult=userRegistrationDetailService.afterFirstAuthParamterChange(isPasswordChangeReq, 
						isPersonalDeatilRequired,userLoginStatus.getUser_Id());
			}
		}
		return new ResponseEntity<UserRegistrationDetail>(userLoginStatus, HttpStatus.OK);
	}

	@ApiOperation(value = "Method for token set and mail send")
	@ApiResponse(code = 200, message = "Response is ResponseEntity<Boolean>", response = Boolean.class)
	@PostMapping("/resetpassword")
	public ResponseEntity<Boolean> forgotPassword(HttpServletRequest request,
			@ApiParam(value = "Login class model") @RequestParam("email") String userEmail) {
		UserRegistrationDetail userRegistrationDetail = null;
		userRegistrationDetail = userRegistrationDetailService.checkForExistingEmail(userEmail);
		boolean mailSendStatus = false;
		if (null != userRegistrationDetail) {
			String genPassword = userLoginService.generateCommonLangPassword();
			userRegistrationDetail.getUserCredentials().setPassword(genPassword);
			userRegistrationDetail.setIsPasswordChangeRequired(true);
			UserRegistrationDetail updateStatus = userRegistrationDetailService.updateUser(userRegistrationDetail);
			if (null != updateStatus) {
				mailSendStatus = userLoginService.sendEmail(userRegistrationDetail.getUserCredentials().getEmail(),
						genPassword);
			}
		}
		if (mailSendStatus) {
			LOGGER.info("Mail send successfully");
		}
		return new ResponseEntity<Boolean>(mailSendStatus, HttpStatus.OK);
	}
	
	
	
}
