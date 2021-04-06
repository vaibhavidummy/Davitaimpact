package in.davita.impact.erp.admin.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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
@RequestMapping(value = "user-registration-service")
@Api(value = "User registration service controller")
public class UserRegistrationDetailsController {

	@Autowired
	private UserRegistrationDetailService userRegistrationService;

	@ApiOperation(value = "Method for User registartion")
	@ApiResponse(code = 201, message = "Response is ResponseEntity<UserRegistrationDetail>", response = UserRegistrationDetail.class)
	@PostMapping(value = "/user_register")
	public ResponseEntity<UserRegistrationDetail> addUser(
			@ApiParam(value = "UserRegistration class model") @RequestBody @Valid UserRegistrationDetail userRegistrationDtls) {
		UserRegistrationDetail userRegistrationStatus = null;
		userRegistrationStatus = userRegistrationService.addUser(userRegistrationDtls);
		return new ResponseEntity<UserRegistrationDetail>(userRegistrationStatus, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Method for updating UserRegistration detail")
	@ApiResponse(code = 200, message = "Response is UserRegistrationDetail model", response = UserRegistrationDetail.class)
	@PutMapping(value = "/user_register")
	public ResponseEntity<UserRegistrationDetail> UpdateUser(
			@ApiParam(value = "UserRegistration class model") @RequestBody @Valid UserRegistrationDetail userRegistrationDtls) {
		UserRegistrationDetail userRegistrationStatus = null;
		userRegistrationStatus = userRegistrationService.UpdateUser(userRegistrationDtls);
		return new ResponseEntity<UserRegistrationDetail>(userRegistrationStatus, HttpStatus.OK);
	}

	@ApiOperation(value = "Method to get existing UserRegistration deatil by userId")
	@ApiResponse(code = 200, message = "Response is UserRegistrationDetail model", response = UserRegistrationDetail.class)
	@GetMapping(value = "/user_register/{userId}")
	public ResponseEntity<Optional<UserRegistrationDetail>> getUser(
			@ApiParam(value = "UserId") @PathVariable("userId") String userId) {
		Optional<UserRegistrationDetail> userRegistrationStatus = null;
		userRegistrationStatus = userRegistrationService.getUser(userId);
		return new ResponseEntity<Optional<UserRegistrationDetail>>(userRegistrationStatus, HttpStatus.OK);
	}

	@ApiOperation(value = "Method to disable existing UserRegistration deatil by userId")
	@ApiResponse(code = 200, message = "Response is Integer", response = Integer.class)
	@DeleteMapping(value = "/user_register/{userId}")
	public ResponseEntity<Integer> DisableUser(@ApiParam(value = "UserId") @PathVariable("userId") String userId) {
		int userRegistrationStatus = 0;
		userRegistrationStatus = userRegistrationService.DisableUser(userId);
		return new ResponseEntity<Integer>(userRegistrationStatus, HttpStatus.OK);
	}

	@ApiOperation(value = "Method to get list of All existing UserRegistration deatils")
	@ApiResponse(code = 200, message = "Response is List of UserRegistrationDetail", response = List.class)
	@GetMapping(value = "/user_register")
	public ResponseEntity<List<UserRegistrationDetail>> getAllUser() {
		List<UserRegistrationDetail> userRegistrationStatus = null;
		userRegistrationStatus = userRegistrationService.getAllUser();
		return new ResponseEntity<List<UserRegistrationDetail>>(userRegistrationStatus, HttpStatus.OK);
	}

	/* 
	 * -Controller level method to valid specific User registration detail as per emailId
	 * - which is used to check whether email id is already part of any other user And also used to
	 *  get user as per email id while ResetPassword operation
	 */
	@ApiOperation(value = "Method to check existing email by email")
	@ApiResponse(code = 200, message = "Response is String", response = String.class)
	@PutMapping(value = "/user_register/{email}")
	public ResponseEntity<String> checkForExistingEmail(
			@ApiParam(value = "email") @PathVariable("email") String email) {
		String userRegistrationStatus = null;
		userRegistrationStatus = userRegistrationService.checkForExistingEmail(email.trim());
		return new ResponseEntity<String>(userRegistrationStatus, HttpStatus.OK);
	}
	
	/* 
	 * -Controller level method to valid specific User registration detail as per userName
	 * - which is used to check whether userName is already part of any other user And also used to
	 */
	@ApiOperation(value = "Method to check existing UserName by UserName")
	@ApiResponse(code = 200, message = "Response is String", response = String.class)
	@PutMapping(value = "/user_register/user/{userName}")
	public ResponseEntity<String> checkForExistingUsername(
			@ApiParam("userName") @PathVariable("userName") String userName) {
		String userRegistrationStatus = null;
		userRegistrationStatus = userRegistrationService.checkForExistingUsername(userName.trim());
		return new ResponseEntity<String>(userRegistrationStatus, HttpStatus.OK);
	}

}
