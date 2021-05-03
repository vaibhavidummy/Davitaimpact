package in.davita.impact.erp.admin.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.davita.impact.erp.admin.model.UserRegistrationDetail;
import in.davita.impact.erp.admin.model.UserRegistrationDetailResponse;
import in.davita.impact.erp.admin.service.UserRegistrationDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * 'User registration service controller' Bounded Context
 * REST Controller Service
 * @version 1.0 06-04-2021
 * @author PrashantW3
 * */
@RestController
@RequestMapping(value = "registration")
@Api(value = "User registration service controller")
public class UserRegistrationDetailsController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserRegistrationDetailsController.class);
	@Autowired
	private UserRegistrationDetailService userRegistrationService;

	@ApiOperation(value = "Method for User registartion")
	@ApiResponse(code = 201, message = "Response is ResponseEntity<String>", response = String.class)
	@PostMapping()
	public ResponseEntity<String> addUser(
			@ApiParam(value = "UserRegistration class model") @RequestBody @Valid UserRegistrationDetail userRegistrationDtls) {
		LOGGER.info("Inside add user method of UserRegistrationDetailController");
		String userRegistrationStatus = null;
		userRegistrationStatus = userRegistrationService.addUser(userRegistrationDtls);
		if (null != userRegistrationStatus) {
			LOGGER.info("Saved data with Id::{}", userRegistrationStatus);
		}
		return new ResponseEntity<String>(userRegistrationStatus, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Method for updating UserRegistration detail")
	@ApiResponse(code = 200, message = "Response is ResponseEntity<String>", response = String.class)
	@PutMapping("/{userId}")
	public ResponseEntity<String> updateUser(
			@ApiParam(value = "UserRegistration class model") @RequestBody @Valid UserRegistrationDetail userRegistrationDtls,
			@PathVariable("userId")String userId) {
		LOGGER.info("Inside update user method of UserRegistrationDetailController");
		String userRegistrationStatus = null;
		userRegistrationDtls.setUserId(userId);
		userRegistrationStatus = userRegistrationService.updateUser(userRegistrationDtls);
		if (null != userRegistrationStatus) {
			LOGGER.info("Updated data with Id::{}", userRegistrationStatus);
		}
		return new ResponseEntity<String>(userRegistrationStatus, HttpStatus.OK);
	}

	@ApiOperation(value = "Method to get existing UserRegistration detail by userId")
	@ApiResponse(code = 200, message = "Response is Optional<UserRegistrationDetail> model", response = UserRegistrationDetail.class)
	@GetMapping(value = "/{userId}")
	public ResponseEntity<Optional<UserRegistrationDetail>> getUser(
			@ApiParam(value = "UserId") @PathVariable("userId") String userId) {
		LOGGER.info("Inside get user method of UserRegistrationDetailController");
		Optional<UserRegistrationDetail> userRegistrationStatus = null;
		userRegistrationStatus = userRegistrationService.getUser(userId);
		if(null != userRegistrationStatus) {
			LOGGER.info("Get user Data::{}",userRegistrationStatus);
		}
		return new ResponseEntity<Optional<UserRegistrationDetail>>(userRegistrationStatus, HttpStatus.OK);
	}

	@ApiOperation(value = "Method to disable existing UserRegistration detail by userId")
	@ApiResponse(code = 200, message = "Response is ResponseEntity<Boolean>", response = Boolean.class)
	@DeleteMapping(value = "/{userId}")
	public ResponseEntity<Boolean> disableUser(@ApiParam(value = "UserId") @PathVariable("userId") String userId) {
		LOGGER.info("Inside disable user method of UserRegistrationDetailController");
		Boolean userRegistrationStatus = false;
		userRegistrationStatus = userRegistrationService.disableUser(userId);
		if(userRegistrationStatus) {
			LOGGER.info("Disabled User Data::{}",userRegistrationStatus);
		}
		return new ResponseEntity<Boolean>(userRegistrationStatus, HttpStatus.OK);
	}

	@ApiOperation(value = "Method to get list of all existing UserRegistration details")
	@ApiResponse(code = 200, message = "Response is List of ResponseEntity<List<UserRegistrationDetailResponse>>", response = List.class)
	@GetMapping()
	public ResponseEntity<List<UserRegistrationDetailResponse>> getAllUsers() {
		LOGGER.info("Inside get all user method of UserRegistrationDetailController");
		List<UserRegistrationDetailResponse> userRegistrationStatus = null;
		userRegistrationStatus = userRegistrationService.getAllUsers();
		if(null!=userRegistrationStatus) {
			LOGGER.info("List Of User Data::{}",true);
		}
		return new ResponseEntity<List<UserRegistrationDetailResponse>>(userRegistrationStatus, HttpStatus.OK);
	}

	/* 
	 * -Controller level method to valid specific User registration detail as per emailId
	 * - which is used to check whether email id is already part of any other user And also used to
	 *  get user as per email id while ResetPassword operation
	 */
	@ApiOperation(value = "Method to check existing email by email")
	@ApiResponse(code = 200, message = "Response is ResponseEntity<Boolean>", response = Boolean.class)
	@PutMapping(value = "/existingemail/{email}")
	public ResponseEntity<UserRegistrationDetail> checkForExistingEmail(
			@ApiParam(value = "email") @PathVariable("email") String email) {
		LOGGER.info("Inside check for existing email by email method of UserRegistrationDetaiilController");
		//Boolean emailStatus=false;
		UserRegistrationDetail userRegistrationStatus = userRegistrationService.checkForExistingEmail(email.trim());
		if(null != userRegistrationStatus) {
			//emailStatus=true;
			LOGGER.info("Email checked successfully Data::{}",true);
		}
		return new ResponseEntity<UserRegistrationDetail>(userRegistrationStatus, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Method to update data after first auth")
	@ApiResponse(code = 200, message = "Response is ResponseEntity<Boolean>", response = Boolean.class)
	@PutMapping(value = "/afterfirstauth")
	public ResponseEntity<Boolean> afterFirstAuthParamterChange(
			@ApiParam(value = "isPasswordChangeReq") @RequestParam("isPasswordChangeReq") boolean isPasswordChangeReq,
			@ApiParam(value = "isPersonalDeatilRequired")@RequestParam("isPersonalDeatilRequired") boolean isPersonalDeatilRequired,
			@ApiParam(value = "userId") @RequestParam("userId") String userId) {
		Boolean changeStatus = false;
		changeStatus = userRegistrationService.afterFirstAuthParamterChange(isPasswordChangeReq,
				isPersonalDeatilRequired, userId);
		if (changeStatus) {
			LOGGER.info("after auth data upadted::{}", changeStatus);
		}
		return new ResponseEntity<Boolean>(changeStatus, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Method to get list of all existing UserRegistration details by Role")
	@ApiResponse(code = 200, message = "Response is List of ResponseEntity<List<UserRegistrationDetailResponse>>", response = List.class)
	@GetMapping(value="/role/{role}")
	public ResponseEntity<List<UserRegistrationDetailResponse>> getAllUsersByRole(@PathVariable("role")String role) {
		LOGGER.info("Inside get all user by role method of UserRegistrationDetailController");
		List<UserRegistrationDetailResponse> userRegistrationStatus = null;
		userRegistrationStatus = userRegistrationService.getAllUsersByRole(role);
		if(null!=userRegistrationStatus) {
			LOGGER.info("List Of User Data by role::{}",true);
		}
		return new ResponseEntity<List<UserRegistrationDetailResponse>>(userRegistrationStatus, HttpStatus.OK);
	}
}
