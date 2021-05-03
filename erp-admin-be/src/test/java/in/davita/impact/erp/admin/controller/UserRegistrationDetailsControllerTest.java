package in.davita.impact.erp.admin.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;


import in.davita.impact.erp.admin.model.Role;
import in.davita.impact.erp.admin.model.Title;
import in.davita.impact.erp.admin.model.UserCredentials;
import in.davita.impact.erp.admin.model.UserRegistrationDetail;
import in.davita.impact.erp.admin.model.UserRegistrationDetailResponse;
import in.davita.impact.erp.admin.service.UserRegistrationDetailService;

@ImportAutoConfiguration(RefreshAutoConfiguration.class)
@WebMvcTest(UserRegistrationDetailsController.class)
//@RunWith(SpringRunner.class)
class UserRegistrationDetailsControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@MockBean
	private UserRegistrationDetailService userService;
	
	
	UserCredentials userCredential = new UserCredentials("userCredId", "user@email.com", "userpassword",
			new UserRegistrationDetail());
	@SuppressWarnings("deprecation")
	UserRegistrationDetail userDetail = new UserRegistrationDetail("userId", Title.Mr, "Name", "surname",
			LocalDate.of(2020, 1, 8), new Long(1234567890), Role.Doctor, userCredential, "I", new Boolean(true),
			new Boolean(true));
	
	Optional<UserRegistrationDetail> userOptional = Optional.of(userDetail);
	
	@SuppressWarnings("deprecation")
	UserRegistrationDetailResponse userRegistrationResponse1 = new UserRegistrationDetailResponse(
			"userId1", Title.Mr, "Name", "surname",
			LocalDate.of(2020, 1, 8), new Long(1234567890), Role.Doctor,"I", new Boolean(true),
			new Boolean(true),"user1@email.com"	);
	@SuppressWarnings("deprecation")
	UserRegistrationDetailResponse userRegistrationResponse2 = new UserRegistrationDetailResponse(
			"userId2", Title.Mr, "Name", "surname",
			LocalDate.of(2020, 1, 8), new Long(1234567890), Role.Patient,"I", new Boolean(true),
			new Boolean(true),"user2@email.com"	);
	
	
	@Test
	public void testAddUser() throws Exception {
			when(userService.addUser(Mockito.any(UserRegistrationDetail.class))).thenReturn(new String("userId"));
		RequestBuilder request = MockMvcRequestBuilders.post("https://localhost:8080/registration")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(userDetail));
		    MvcResult result = mockMvc.perform(request)
					.andExpect(status().isCreated())
					.andReturn();
         String resultContext= result.getResponse().getContentAsString();
         assertNotNull(resultContext);
         assertEquals("userId", resultContext);
	
	}
	
	
	@Test
	public void testAddUser_Null() throws Exception {
			when(userService.addUser(Mockito.any(UserRegistrationDetail.class))).thenReturn(null);
		RequestBuilder request = MockMvcRequestBuilders.post("https://localhost:8080/registration")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(userDetail));
		    MvcResult result = mockMvc.perform(request)
					.andExpect(status().isCreated())
					.andReturn();
         String resultContext= result.getResponse().getContentAsString();
         assertEquals("", resultContext);
	
	}

	
	@Test
	public void testUpdateUser() throws Exception {
		when(userService.updateUser(Mockito.any(UserRegistrationDetail.class))).thenReturn(new String("userId"));
		RequestBuilder request = MockMvcRequestBuilders.put("https://localhost:8080/registration/userId")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(userDetail));
		    MvcResult result = mockMvc.perform(request)
					.andExpect(status().isOk())
					.andReturn();
         String resultContext= result.getResponse().getContentAsString();
         assertNotNull(resultContext);
         assertEquals("userId", resultContext);
}
	
	@Test
	public void testUpdateUser_Null() throws Exception {
		when(userService.updateUser(Mockito.any(UserRegistrationDetail.class))).thenReturn(null);
		RequestBuilder request = MockMvcRequestBuilders.put("https://localhost:8080/registration/userId")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(userDetail));
		    MvcResult result = mockMvc.perform(request)
					.andExpect(status().isOk())
					.andReturn();
         String resultContext= result.getResponse().getContentAsString();
         assertEquals("", resultContext);
}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testGetUser() throws Exception {
		when(userService.getUser(Mockito.anyString())).thenReturn(userOptional);
		RequestBuilder request = MockMvcRequestBuilders.get("https://localhost:8080/registration/userId")
				.contentType(MediaType.APPLICATION_JSON);
		    MvcResult result = mockMvc.perform(request)
					.andExpect(status().isOk())
					.andReturn();
         String resultContext= result.getResponse().getContentAsString();
        Optional<UserRegistrationDetail> response= objectMapper.readValue(resultContext, Optional.class);
        assertNotNull(response);
        
}
	
	@Test
	public void testGetUser_Null() throws Exception {
		when(userService.getUser(Mockito.anyString())).thenReturn(null);
		RequestBuilder request = MockMvcRequestBuilders.get("https://localhost:8080/registration/userId")
				.contentType(MediaType.APPLICATION_JSON);
		    MvcResult result = mockMvc.perform(request)
					.andExpect(status().isOk())
					.andReturn();
         String resultContext= result.getResponse().getContentAsString();
        assertEquals("", resultContext);
        
}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testGetAllUsers() throws Exception {
		when(userService.getAllUsers()).thenReturn(Arrays.asList(userRegistrationResponse1,userRegistrationResponse2));
		RequestBuilder request = MockMvcRequestBuilders.get("https://localhost:8080/registration")
				.contentType(MediaType.APPLICATION_JSON);
				//.content(objectMapper.writeValueAsString(userDetail));
		    MvcResult result = mockMvc.perform(request)
					.andExpect(status().isOk())
					.andReturn();
         String resultContext= result.getResponse().getContentAsString();
        List<UserRegistrationDetailResponse> response= objectMapper.readValue(resultContext, List.class);
        assertNotNull(response);
}

	@Test
	public void testGetAllUsers_Null() throws Exception {
		List<UserRegistrationDetailResponse>list=null;
		when(userService.getAllUsers()).thenReturn(list);
		RequestBuilder request = MockMvcRequestBuilders.get("https://localhost:8080/registration")
				.contentType(MediaType.APPLICATION_JSON);
				//.content(objectMapper.writeValueAsString(userDetail));
		    MvcResult result = mockMvc.perform(request)
					.andExpect(status().isOk())
					.andReturn();
         String resultContext= result.getResponse().getContentAsString();
        assertEquals("", resultContext);
}
	
	@Test
	public void testDisableUser() throws Exception {
		String userId="userId";
		when(userService.disableUser(Mockito.anyString())).thenReturn(true);
		RequestBuilder request = MockMvcRequestBuilders.delete("https://localhost:8080/registration/"+userId)
				.contentType(MediaType.APPLICATION_JSON);
		    MvcResult result = mockMvc.perform(request)
					.andExpect(status().isOk())
					.andReturn();
         String resultContext= result.getResponse().getContentAsString();
        Boolean response= objectMapper.readValue(resultContext,Boolean.class);
        assertEquals(true, response);
        
   }
	
	@Test
	public void testDisableUser_False() throws Exception {
		String userId="userId";
		when(userService.disableUser(Mockito.anyString())).thenReturn(false);
		RequestBuilder request = MockMvcRequestBuilders.delete("https://localhost:8080/registration/"+userId)
				.contentType(MediaType.APPLICATION_JSON);
		    MvcResult result = mockMvc.perform(request)
					.andExpect(status().isOk())
					.andReturn();
         String resultContext= result.getResponse().getContentAsString();
        Boolean response= objectMapper.readValue(resultContext,Boolean.class);
        assertEquals(false, response);
        
   }
	
	@Test
	public void testCheckForExistingEmail() throws Exception {
		String email="user@email.com";
		when(userService.checkForExistingEmail(Mockito.anyString())).thenReturn(userDetail);
		RequestBuilder request = MockMvcRequestBuilders.put("https://localhost:8080/registration/existingemail/"+email)
				.contentType(MediaType.APPLICATION_JSON);
		    MvcResult result = mockMvc.perform(request)
					.andExpect(status().isOk())
					.andReturn();
         String resultContext= result.getResponse().getContentAsString();
        UserRegistrationDetail response= objectMapper.readValue(resultContext,UserRegistrationDetail.class);
       assertNotNull(response);
     assertEquals("user@email.com",response.getUserCredentials().getEmail());
        
   }
	
	@Test
	public void testCheckForExistingEmail_Null() throws Exception {
		String email="user@email.com";
		when(userService.checkForExistingEmail(Mockito.anyString())).thenReturn(null);
		RequestBuilder request = MockMvcRequestBuilders.put("https://localhost:8080/registration/existingemail/"+email)
				.contentType(MediaType.APPLICATION_JSON);
		    MvcResult result = mockMvc.perform(request)
					.andExpect(status().isOk())
					.andReturn();
         String resultContext= result.getResponse().getContentAsString();
 
     assertEquals("",resultContext);
        
   }

	@Test
	public void testAfterFirstAuthParamterChange() throws Exception {
		String userId="userId";
		boolean isPasswordChangeReq =true;
		boolean isPersonalDeatilRequired=true;
		when(userService.afterFirstAuthParamterChange(Mockito.anyBoolean(),Mockito.anyBoolean(),Mockito.anyString()))
		.thenReturn(true);
		RequestBuilder request = MockMvcRequestBuilders
				.put("https://localhost:8080/registration/afterfirstauth?isPasswordChangeReq="+isPasswordChangeReq+"&isPersonalDeatilRequired="
						+isPersonalDeatilRequired +"&userId="+userId)
				.contentType(MediaType.APPLICATION_JSON);
		    MvcResult result = mockMvc.perform(request)
					.andExpect(status().isOk())
					.andReturn();
         String resultContext= result.getResponse().getContentAsString();
        Boolean response= objectMapper.readValue(resultContext,Boolean.class);
       assertNotNull(response);
       assertEquals(true, response);
        
   }
	
	@Test
	public void testAfterFirstAuthParamterChange_False() throws Exception {
		String userId="userId";
		boolean isPasswordChangeReq =true;
		boolean isPersonalDeatilRequired=true;
		when(userService.afterFirstAuthParamterChange(Mockito.anyBoolean(),Mockito.anyBoolean(),Mockito.anyString()))
		.thenReturn(false);
		RequestBuilder request = MockMvcRequestBuilders
				.put("https://localhost:8080/registration/afterfirstauth?isPasswordChangeReq="+isPasswordChangeReq+"&isPersonalDeatilRequired="
						+isPersonalDeatilRequired +"&userId="+userId)
				.contentType(MediaType.APPLICATION_JSON);
		    MvcResult result = mockMvc.perform(request)
					.andExpect(status().isOk())
					.andReturn();
         String resultContext= result.getResponse().getContentAsString();
        Boolean response= objectMapper.readValue(resultContext,Boolean.class);
       assertEquals(false, response);
        
   }
	
	@SuppressWarnings("unchecked")
	@Test
	public void testGetAllUsersByRole() throws Exception {
		String role="Patient";
		when(userService.getAllUsersByRole(Mockito.anyString()))
		.thenReturn(Arrays.asList(userRegistrationResponse1,userRegistrationResponse2));
		RequestBuilder request = MockMvcRequestBuilders
				.get("https://localhost:8080/registration/role/"+role)
				.contentType(MediaType.APPLICATION_JSON);
		    MvcResult result = mockMvc.perform(request)
					.andExpect(status().isOk())
					.andReturn();
         String resultContext= result.getResponse().getContentAsString();
        List<UserRegistrationDetailResponse> response= objectMapper.readValue(resultContext,List.class);
       assertNotNull(response); 
       assertEquals(2,response.size());
   }
	
	@Test
	public void testGetAllUsersByRole_Null() throws Exception {
		List<UserRegistrationDetailResponse>list=null;
		String role="Patient";
		when(userService.getAllUsersByRole(Mockito.anyString()))
		.thenReturn(list);
		RequestBuilder request = MockMvcRequestBuilders
				.get("https://localhost:8080/registration/role/"+role)
				.contentType(MediaType.APPLICATION_JSON);
		    MvcResult result = mockMvc.perform(request)
					.andExpect(status().isOk())
					.andReturn();
         String resultContext= result.getResponse().getContentAsString();
       assertEquals("",resultContext);
   }
}