package in.davita.impact.erp.admin.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.davita.impact.erp.admin.model.Login;
import in.davita.impact.erp.admin.model.Role;
import in.davita.impact.erp.admin.model.Title;
import in.davita.impact.erp.admin.model.UpdatePassword;
import in.davita.impact.erp.admin.model.UserCredentials;
import in.davita.impact.erp.admin.model.UserRegistrationDetail;
import in.davita.impact.erp.admin.model.UserRegistrationDetailResponse;
import in.davita.impact.erp.admin.service.UserLoginService;
import in.davita.impact.erp.admin.service.UserRegistrationDetailService;

@ImportAutoConfiguration(RefreshAutoConfiguration.class)
@WebMvcTest(UserLoginController.class)
//@RunWith(SpringRunner.class)
class UserLoginControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	PasswordEncoder encoder;
	
	@MockBean
	private UserLoginService userLoginService;
	
	@MockBean
	private UserRegistrationDetailService userRegistrationDetailService;
	
	Login loginDtls= new Login("user@email.com","userPassword");
	
	UpdatePassword updatePassword=new UpdatePassword("user@email.com","newPassword","oldPassword");
	
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

	@Test
	void testUserLogin() throws Exception {
		when(userLoginService.userLogin(Mockito.anyString(),Mockito.anyString())).thenReturn(userRegistrationResponse1);
		RequestBuilder request = MockMvcRequestBuilders.post("https://localhost:8080/authentication/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(loginDtls));
		    MvcResult result = mockMvc.perform(request)
					.andExpect(status().isOk())
					.andReturn();
         String resultContext= result.getResponse().getContentAsString();
         UserRegistrationDetailResponse response= objectMapper.readValue(resultContext, 
        		 UserRegistrationDetailResponse.class);
         assertNotNull(response);
         assertEquals("userId", response.getUserId());
	}

	@Test
	void testUserLogin_Null() throws Exception {
		UserRegistrationDetailResponse nullDetails=null;
		when(userLoginService.userLogin(Mockito.anyString(),Mockito.anyString())).thenReturn(nullDetails);
		RequestBuilder request = MockMvcRequestBuilders.post("https://localhost:8080/authentication/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(loginDtls));
		    MvcResult result = mockMvc.perform(request)
					.andExpect(status().isOk())
					.andReturn();
         String resultContext= result.getResponse().getContentAsString();
         assertEquals("", resultContext);
	}

	

	@Test
	void testForgotPassword() throws Exception{
		String email="user@email.com";
		when(userRegistrationDetailService.checkForExistingEmail(Mockito.anyString()))
		.thenReturn(userDetail);
		when(userLoginService.generateCommonLangPassword()).thenReturn("generatedPassword");
		when(userLoginService.sendEmail(Mockito.anyString(),Mockito.anyString()))
		.thenReturn(true);
		when(userRegistrationDetailService.updateUser(Mockito.any(UserRegistrationDetail.class)))
		.thenReturn("updatedPassword");
		
		RequestBuilder request = MockMvcRequestBuilders.post("https://localhost:8080/authentication/forgotpassword?email="+email)
				.contentType(MediaType.APPLICATION_JSON);
		    @SuppressWarnings("unused")
			MvcResult result = mockMvc.perform(request)
					.andExpect(status().isOk())
					.andReturn();
	         String resultContext= result.getResponse().getContentAsString();
	         Boolean response= objectMapper.readValue(resultContext, Boolean.class);
	         assertEquals(true, response);

	}

	@Test
	void testForgotPassword_MailNotExitsAndMailSendStatusFalse() throws Exception{
		String email="user@email.com";
		UserRegistrationDetail nullUserRegistration=null;
		when(userRegistrationDetailService.checkForExistingEmail(Mockito.anyString()))
		.thenReturn(nullUserRegistration);
		when(userLoginService.generateCommonLangPassword()).thenReturn("generatedPassword");
		when(userLoginService.sendEmail(Mockito.anyString(),Mockito.anyString()))
		.thenReturn(false);
		when(userRegistrationDetailService.updateUser(Mockito.any(UserRegistrationDetail.class)))
		.thenReturn("updatedPassword");
		
		RequestBuilder request = MockMvcRequestBuilders.post("https://localhost:8080/authentication/forgotpassword?email="+email)
				.contentType(MediaType.APPLICATION_JSON);
		    @SuppressWarnings("unused")
			MvcResult result = mockMvc.perform(request)
					.andExpect(status().isOk())
					.andReturn();
		    String resultContext= result.getResponse().getContentAsString();
	         Boolean response= objectMapper.readValue(resultContext, Boolean.class);
	         assertEquals(false, response);
	}

	
	@Test
	void testUpdatePassword() throws Exception {
		userDetail.setRole(Role.Patient);
		when(userLoginService.updatePassword(Mockito.any(UpdatePassword.class))).thenReturn(true);
		when(userRegistrationDetailService.checkForExistingEmail(Mockito.anyString())).thenReturn(userDetail);
		RequestBuilder request = MockMvcRequestBuilders.put("https://localhost:8080/authentication/updatepassword")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(updatePassword));
		    MvcResult result = mockMvc.perform(request)
					.andExpect(status().isOk())
					.andReturn();
         String resultContext= result.getResponse().getContentAsString();
         Boolean response= objectMapper.readValue(resultContext, Boolean.class);
         assertEquals(true, response);
         
	}
	
	
	@Test
	void testUpdatePassword_UpdateStatusFalseAndOtherScenario() throws Exception {
		when(userLoginService.updatePassword(Mockito.any(UpdatePassword.class))).thenReturn(false);
		when(userRegistrationDetailService.checkForExistingEmail(Mockito.anyString())).thenReturn(userDetail);
		RequestBuilder request = MockMvcRequestBuilders.put("https://localhost:8080/authentication/updatepassword")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(updatePassword));
		    MvcResult result = mockMvc.perform(request)
					.andExpect(status().isOk())
					.andReturn();
         String resultContext= result.getResponse().getContentAsString();
         Boolean response= objectMapper.readValue(resultContext, Boolean.class);
         assertEquals(false, response);
         
	}

	@Test
	void testUpdatePassword_OtherScenario1() throws Exception {
		userDetail.setIsPasswordChangeRequired(false);
		userDetail.setIsPersonalDetailsRequired(false);
		when(userLoginService.updatePassword(Mockito.any(UpdatePassword.class))).thenReturn(true);
		when(userRegistrationDetailService.checkForExistingEmail(Mockito.anyString())).thenReturn(userDetail);
		RequestBuilder request = MockMvcRequestBuilders.put("https://localhost:8080/authentication/updatepassword")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(updatePassword));
		    MvcResult result = mockMvc.perform(request)
					.andExpect(status().isOk())
					.andReturn();
         String resultContext= result.getResponse().getContentAsString();
         Boolean response= objectMapper.readValue(resultContext, Boolean.class);
         assertEquals(true, response);
         
	}
	
	@Test
	void testUpdatePassword_OtherScenario2() throws Exception {
		userDetail.setIsPasswordChangeRequired(true);
		userDetail.setIsPersonalDetailsRequired(false);
		when(userLoginService.updatePassword(Mockito.any(UpdatePassword.class))).thenReturn(true);
		when(userRegistrationDetailService.checkForExistingEmail(Mockito.anyString())).thenReturn(userDetail);
		RequestBuilder request = MockMvcRequestBuilders.put("https://localhost:8080/authentication/updatepassword")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(updatePassword));
		    MvcResult result = mockMvc.perform(request)
					.andExpect(status().isOk())
					.andReturn();
         String resultContext= result.getResponse().getContentAsString();
         Boolean response= objectMapper.readValue(resultContext, Boolean.class);
         assertEquals(true, response);
         
	}

	@Test
	void testUpdatePassword_OtherScenario3() throws Exception {
		userDetail.setIsPasswordChangeRequired(false);
		userDetail.setIsPersonalDetailsRequired(true);
		when(userLoginService.updatePassword(Mockito.any(UpdatePassword.class))).thenReturn(true);
		when(userRegistrationDetailService.checkForExistingEmail(Mockito.anyString())).thenReturn(userDetail);
		RequestBuilder request = MockMvcRequestBuilders.put("https://localhost:8080/authentication/updatepassword")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(updatePassword));
		    MvcResult result = mockMvc.perform(request)
					.andExpect(status().isOk())
					.andReturn();
         String resultContext= result.getResponse().getContentAsString();
         Boolean response= objectMapper.readValue(resultContext, Boolean.class);
         assertEquals(true, response);
         
	}

}
