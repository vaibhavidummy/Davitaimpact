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
import org.springframework.web.bind.annotation.RestController;

import in.davita.impact.erp.admin.model.UserRegistrationDetail;
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
	@ApiResponse(code = 201, message = "Response is ResponseEntity<UserRegistrationDetail>", response = UserRegistrationDetail.class)
	@PostMapping()
	public ResponseEntity<UserRegistrationDetail> addUser(
			@ApiParam(value = "UserRegistration class model") @RequestBody @Valid UserRegistrationDetail userRegistrationDtls) {
		LOGGER.info("Inside add user method of UserRegistrationDeatilController");
		UserRegistrationDetail userRegistrationStatus = null;
		userRegistrationStatus = userRegistrationService.addUser(userRegistrationDtls);
		if(null!=userRegistrationStatus) {
			LOGGER.info("Saved Data::{}",userRegistrationStatus);
			
		}
		return new ResponseEntity<UserRegistrationDetail>(userRegistrationStatus, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Method for updating UserRegistration detail")
	@ApiResponse(code = 200, message = "Response is UserRegistrationDetail model", response = UserRegistrationDetail.class)
	@PutMapping()
	public ResponseEntity<UserRegistrationDetail> updateUser(
			@ApiParam(value = "UserRegistration class model") @RequestBody @Valid UserRegistrationDetail userRegistrationDtls) {
		LOGGER.info("Inside update user method of UserRegistrationDeatilController");
		UserRegistrationDetail userRegistrationStatus = null;
		userRegistrationStatus = userRegistrationService.updateUser(userRegistrationDtls);
		if(null!=userRegistrationStatus) {
			LOGGER.info("Updated Data::{}",userRegistrationStatus);
		}
		return new ResponseEntity<UserRegistrationDetail>(userRegistrationStatus, HttpStatus.OK);
	}

	@ApiOperation(value = "Method to get existing UserRegistration deatil by userId")
	@ApiResponse(code = 200, message = "Response is UserRegistrationDetail model", response = UserRegistrationDetail.class)
	@GetMapping(value = "/{userId}")
	public ResponseEntity<Optional<UserRegistrationDetail>> getUser(
			@ApiParam(value = "UserId") @PathVariable("userId") String userId) {
		LOGGER.info("Inside get user method of UserRegistrationDeatilController");
		Optional<UserRegistrationDetail> userRegistrationStatus = null;
		userRegistrationStatus = userRegistrationService.getUser(userId);
		if(!userRegistrationStatus.isEmpty()) {
			LOGGER.info("Get user Data::{}",userRegistrationStatus);
		}
		return new ResponseEntity<Optional<UserRegistrationDetail>>(userRegistrationStatus, HttpStatus.OK);
	}

	@ApiOperation(value = "Method to disable existing UserRegistration deatil by userId")
	@ApiResponse(code = 200, message = "Response is Integer", response = Integer.class)
	@DeleteMapping(value = "/{userId}")
	public ResponseEntity<Integer> disableUser(@ApiParam(value = "UserId") @PathVariable("userId") String userId) {
		LOGGER.info("Inside disable user method of UserRegistrationDeatilController");
		int userRegistrationStatus = 0;
		userRegistrationStatus = userRegistrationService.disableUser(userId);
		if(userRegistrationStatus>0) {
			LOGGER.info("Disabled User Data::{}",userRegistrationStatus);
		}
		return new ResponseEntity<Integer>(userRegistrationStatus, HttpStatus.OK);
	}

	@ApiOperation(value = "Method to get list of All existing UserRegistration deatils")
	@ApiResponse(code = 200, message = "Response is List of UserRegistrationDetail", response = List.class)
	@GetMapping()
	public ResponseEntity<List<UserRegistrationDetail>> getAllUsers() {
		LOGGER.info("Inside get all user method of UserRegistrationDeatilController");
		List<UserRegistrationDetail> userRegistrationStatus = null;
		userRegistrationStatus = userRegistrationService.getAllUsers();
		if(null!=userRegistrationStatus) {
			LOGGER.info("List Of User Data::{}",userRegistrationStatus);
		}
		return new ResponseEntity<List<UserRegistrationDetail>>(userRegistrationStatus, HttpStatus.OK);
	}

	/* 
	 * -Controller level method to valid specific User registration detail as per emailId
	 * - which is used to check whether email id is already part of any other user And also used to
	 *  get user as per email id while ResetPassword operation
	 */
	@ApiOperation(value = "Method to check existing email by email")
	@ApiResponse(code = 200, message = "Response is String", response = String.class)
	@PutMapping(value = "/{email}")
	public ResponseEntity<UserRegistrationDetail> checkForExistingEmail(
			@ApiParam(value = "email") @PathVariable("email") String email) {
		LOGGER.info("Inside check for existing email by email method of UserRegistrationDeatilController");
		UserRegistrationDetail userRegistrationStatus = null;
		userRegistrationStatus = userRegistrationService.checkForExistingEmail(email.trim());
		if(null != userRegistrationStatus) {
			LOGGER.info("Email checked successfully Data::{}",userRegistrationStatus);
		}
		return new ResponseEntity<UserRegistrationDetail>(userRegistrationStatus, HttpStatus.OK);
	}
	
	

}
