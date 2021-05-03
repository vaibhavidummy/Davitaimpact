package in.davita.impact.erp.admin.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import in.davita.impact.erp.admin.exception.EntityDetailsNotFoundException;
import in.davita.impact.erp.admin.model.Role;
import in.davita.impact.erp.admin.model.Title;
import in.davita.impact.erp.admin.model.UpdatePassword;
import in.davita.impact.erp.admin.model.UserCredentials;
import in.davita.impact.erp.admin.model.UserRegistrationDetail;
import in.davita.impact.erp.admin.model.UserRegistrationDetailResponse;
import in.davita.impact.erp.admin.repository.UserLoginRepository;

@ExtendWith(MockitoExtension.class)
class UserLoginServiceImplTest {
	
	@Mock
	UserRegistrationDetailService userRegistrationDetailService;
	
	@Mock
	PasswordEncoder passwordEncoder;
	
	@InjectMocks
	UserLoginServiceImpl impl;
	
	@Mock 
	UserLoginRepository userLoginRepository;
	
	

	UserCredentials userCredential = new UserCredentials("userCredId", "user@email.com", "userpassword",
			new UserRegistrationDetail());
	@SuppressWarnings("deprecation")
	UserRegistrationDetail userDetail = new UserRegistrationDetail("userId", Title.Mr, "Name", "surname",
			LocalDate.of(2020, 1, 8), new Long(1234567890), Role.Doctor, userCredential, "I", new Boolean(true),
			new Boolean(true));
	
	@SuppressWarnings("deprecation")
	UserRegistrationDetailResponse userRegistrationResponse1 = new UserRegistrationDetailResponse(
			"userId", Title.Mr, "Name", "surname",
			LocalDate.of(2020, 1, 8), new Long(1234567890), Role.Doctor,"I", new Boolean(true),
			new Boolean(true),"user1@email.com"	);
	
	UpdatePassword updatePassword=new UpdatePassword("user@email.com","newPassword","oldPassword");

	
	@Test
	void testUserLogin_InputAsEmptyScenario1() {
		UserRegistrationDetailResponse response = impl.userLogin("", "");
		assertEquals(null,response );
	}
	
	@Test
	void testUserLogin_InputAsEmptyScenario2() {
		UserRegistrationDetailResponse response = impl.userLogin("", "passwordPrasent");
		assertEquals(null,response );
	}

	
	@Test
	void testUserLogin() throws Exception{
		when(userRegistrationDetailService.checkForExistingEmail(Mockito.anyString())).thenReturn(userDetail);
		when(passwordEncoder.matches(Mockito.anyString(),Mockito.anyString())).thenReturn(true);
		UserRegistrationDetailResponse response = impl.userLogin("user@email.com", "userPassword");
		assertEquals("userId", response.getUserId());
	}
	
	
	@Test
	void testUserLogin_Exception() throws Exception {
		when(userRegistrationDetailService.checkForExistingEmail(Mockito.anyString())).thenReturn(userDetail);
		when(passwordEncoder.matches(Mockito.anyString(),Mockito.anyString())).thenReturn(false);
		try {
		impl.userLogin("user@email.com", "userPassword");
		}catch(EntityDetailsNotFoundException e)
		{
			assertEquals("Sorry bad credentials", e.getMessage());
		}
		
	}

	@Test
	void testGenerateCommonLangPassword() {
		String response = impl.generateCommonLangPassword();
		assertNotNull(response);

	}

	@Test
	void testSendEmail() {
		boolean response = impl.sendEmail("ToPerson@email.com", "generatedPassword");
		assertEquals(true, response);
	}
	
	@Test
	void testSendEmail_Exception() {
		boolean response=false;
		try {
		response = impl.sendEmail("", "generatedPassword");
		}catch(Exception e)
		{
			assertNotNull(e);
		}
		assertEquals(false, response);
	}

	@Test
	void testUpdatePassword() throws Exception {
		when(userRegistrationDetailService.checkForExistingEmail(Mockito.anyString())).thenReturn(userDetail);
		when(passwordEncoder.matches(Mockito.anyString(),Mockito.anyString())).thenReturn(true);
		when(userLoginRepository.updatePassword("user@email.com",passwordEncoder.encode("userPassword"))).thenReturn(1);
		Boolean response = impl.updatePassword(updatePassword);
		assertEquals(true, response);
	}
	
	@Test
	void testUpdatePassword_UpdatePasswordResultzero() throws Exception{
		when(userRegistrationDetailService.checkForExistingEmail(Mockito.anyString())).thenReturn(userDetail);
		when(passwordEncoder.matches(Mockito.anyString(),Mockito.anyString())).thenReturn(true);
		when(userLoginRepository.updatePassword("user@email.com",passwordEncoder.encode("userPassword"))).thenReturn(0);
	Boolean result=	impl.updatePassword(updatePassword);
	assertEquals(false, result);
		
	}
	
	@Test
	void testUpdatePassword_ExceptionAndPasswordDontMatch() throws Exception{
		when(userRegistrationDetailService.checkForExistingEmail(Mockito.anyString())).thenReturn(userDetail);
		when(passwordEncoder.matches(Mockito.anyString(),Mockito.anyString())).thenReturn(false);
		try {
		impl.updatePassword(updatePassword);
		}catch(EntityDetailsNotFoundException e)
		{
			assertNotNull(e);
		}
		
	}

}
