package in.davita.impact.erp.admin.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.davita.impact.erp.admin.exception.EntityDetailsAlreadyExistException;
import in.davita.impact.erp.admin.exception.EntityDetailsNotFoundException;
import in.davita.impact.erp.admin.model.Role;
import in.davita.impact.erp.admin.model.Title;
import in.davita.impact.erp.admin.model.UserCredentials;
import in.davita.impact.erp.admin.model.UserRegistrationDetail;
import in.davita.impact.erp.admin.model.UserRegistrationDetailResponse;
import in.davita.impact.erp.admin.repository.UserRegistrationDetailRepository;

@ExtendWith(MockitoExtension.class)
class UserRegistrationDetailServiceImplTest {

	@Autowired
	ObjectMapper objectMapper;
	
	@InjectMocks
	UserRegistrationDetailServiceImpl impl;

	@Mock
	PasswordEncoder encoder;

	@Mock
	UserRegistrationDetailRepository userRepository;

	UserCredentials userCredential = new UserCredentials("userCredId", "user@email.com", "userpassword",
			new UserRegistrationDetail());
	@SuppressWarnings("deprecation")
	UserRegistrationDetail userDetail = new UserRegistrationDetail("userId", Title.Mr, "Name", "surname",
			LocalDate.of(2020, 1, 8), new Long(1234567890), Role.Doctor, userCredential, "I", new Boolean(true),
			new Boolean(true));

	@SuppressWarnings("deprecation")
	UserRegistrationDetail userDetailSecond = new UserRegistrationDetail("userId", Title.Mr, "Name", "surname",
			LocalDate.of(2020, 1, 8), new Long(1234567890), Role.Doctor, userCredential, "I", new Boolean(true),
			new Boolean(true));

	@Test
	void testAddUser_RoleAsPatient() throws Exception {
		userDetail.setRole(Role.Patient);
		when(userRepository.save(Mockito.any(UserRegistrationDetail.class))).thenReturn(userDetail);
		userDetail.getUserCredentials().setPassword(encoder.encode(userDetail.getUserCredentials().getPassword()));
		String userId = impl.addUser(userDetail);
		assertEquals("userId", userId);

	}

	@Test
	void testAddUser() throws Exception {
		when(userRepository.save(Mockito.any(UserRegistrationDetail.class))).thenReturn(userDetail);
		userDetail.getUserCredentials().setPassword(encoder.encode(userDetail.getUserCredentials().getPassword()));
		String userId = impl.addUser(userDetail);
		assertEquals("userId", userId);

	}

	@Test
	void testAddUser_Null() throws Exception {
		when(userRepository.save(Mockito.any(UserRegistrationDetail.class))).thenReturn(null);
		userDetail.getUserCredentials().setPassword(encoder.encode(userDetail.getUserCredentials().getPassword()));
		String userId = impl.addUser(userDetail);
		assertEquals(null, userId);
	}

@Test
	void testAddUser_Exception() throws Exception {
		when(userRepository.checkForExistingEmail(Mockito.anyString())).thenReturn(userDetail);
		try {
			impl.addUser(userDetail);
		} catch (EntityDetailsAlreadyExistException e) {
			assertEquals("User with email already present", e.getMessage());
		}
	}

	@Test
	void testUpdateUser() throws Exception {
		when(userRepository.existsById(Mockito.anyString())).thenReturn(true);
		when(userRepository.save(Mockito.any(UserRegistrationDetail.class))).thenReturn(userDetail);
		String userId = impl.updateUser(userDetail);
		assertEquals("userId", userId);
	}

	@Test
	void testUpdateUser_Null() throws Exception {
		when(userRepository.existsById(Mockito.anyString())).thenReturn(true);
		when(userRepository.save(Mockito.any(UserRegistrationDetail.class))).thenReturn(null);
		String userId = impl.updateUser(userDetail);
		assertEquals(null, userId);

	}

	@Test
	void testUpdateUser_Exception() throws Exception {
		when(userRepository.existsById(Mockito.anyString())).thenReturn(false);
		try {
			impl.updateUser(userDetail);
		} catch (EntityDetailsNotFoundException e) {
			assertEquals("User registration detail not found..", e.getMessage());
		}
	}

	@Test
	void testGetUser_EmptyUserIdAsInput() throws Exception {
		Optional<UserRegistrationDetail> result = impl.getUser("");
		assertEquals(null, result);

	}

	@Test
	void testGetUser() {
		when(userRepository.findById(Mockito.anyString())).thenReturn(Optional.of(userDetail));
		Optional<UserRegistrationDetail> userDetailResponse = impl.getUser("userId");
		assertEquals("userId", userDetailResponse.get().getUserId());
	}

	@Test
	void testGetUser_Empty() {
		UserRegistrationDetail details = null;

		when(userRepository.findById(Mockito.anyString())).thenReturn(Optional.ofNullable(details));
		try {
			impl.getUser("userId");
		} catch (EntityDetailsNotFoundException e) {
			assertEquals("User registration detail not found", e.getMessage());
		}

	}

	@Test
	void testDisableUser_EmptyUserIdAsInput() throws Exception {
		Boolean result = impl.disableUser("");
		assertEquals(false, result);

	}

	@Test
	void testDisableUser() {
		when(userRepository.existsById(Mockito.anyString())).thenReturn(true);
		when(userRepository.disableUser(Mockito.anyString())).thenReturn(1);
		Boolean userId = impl.disableUser("userId");
		assertEquals(true, userId);
	}

	@Test
	void testDisableUser_NotDisable() throws Exception {
		when(userRepository.existsById(Mockito.anyString())).thenReturn(true);
		when(userRepository.disableUser(Mockito.anyString())).thenReturn(0);
		Boolean userId = impl.disableUser("userId");
		assertEquals(false, userId);
	}

	@Test
	void testDisableUser_Exception() throws Exception {
		when(userRepository.existsById(Mockito.anyString())).thenReturn(false);
		try {
			impl.disableUser("userId");
		} catch (EntityDetailsNotFoundException e) {
			assertEquals("User registration detail not found", e.getMessage());
		}

	}

	@Test
	void testGetAllUsers() {
		when(userRepository.findAll()).thenReturn(Arrays.asList(userDetail, userDetailSecond));
		List<UserRegistrationDetailResponse> listOfUsers = impl.getAllUsers();
		assertEquals(2, listOfUsers.size());
	}

	@Test
	void testCheckForExistingEmail_EmptyEmailStringAsInput() throws Exception {
		UserRegistrationDetail result = impl.checkForExistingEmail("");
		assertEquals(null, result);

	}

	@Test
	void testCheckForExistingEmail() throws Exception {

		when(userRepository.checkForExistingEmail(Mockito.anyString())).thenReturn(userDetail);
		UserRegistrationDetail user = impl.checkForExistingEmail("user@email.com");
		assertEquals("user@email.com", user.getUserCredentials().getEmail());
	}

	@Test
	void testCheckForExistingEmail_Exception() throws Exception {

		when(userRepository.checkForExistingEmail(Mockito.anyString())).thenReturn(null);
		try {
			impl.checkForExistingEmail("user@email.com");
		} catch (EntityDetailsNotFoundException e) {
			assertEquals("User registration details not found", e.getMessage());
		}

	}

	@Test
	void testAfterFirstAuthParamterChange_EmptyUserIdAsInput() throws Exception {

		Boolean result = impl.afterFirstAuthParamterChange(true, false, "");
		assertEquals(false, result);

	}

	@Test
	void testAfterFirstAuthParamterChange() throws Exception {
		when(userRepository.afterFirstAuthParamterChange(Mockito.anyBoolean(), Mockito.anyBoolean(),
				Mockito.anyString())).thenReturn(1);
		Boolean result = impl.afterFirstAuthParamterChange(true, false, "user@email.com");
		assertEquals(true, result);

	}

	@Test
	void testAfterFirstAuthParamterChange_Exception() throws Exception {
		when(userRepository.afterFirstAuthParamterChange(Mockito.anyBoolean(), Mockito.anyBoolean(),
				Mockito.anyString())).thenReturn(0);
		try {
			impl.afterFirstAuthParamterChange(true, false, "user@email.com");
		} catch (EntityDetailsNotFoundException e) {
			assertEquals("User details not found", e.getMessage());
		}

	}

	@Test
	void testGetAllUsersByRole_InvaliEnumValueAsInput() throws Exception {
		List<UserRegistrationDetailResponse> response = impl.getAllUsersByRole("any role rather than enum value");
		assertEquals(null, response);
	}

	@Test
	void testGetAllUsersByRole() throws Exception {
		when(userRepository.findByrole(Role.valueOf("Admin"))).thenReturn(Arrays.asList(userDetail, userDetailSecond));
		List<UserRegistrationDetailResponse> response = impl.getAllUsersByRole("Admin");
		assertEquals(2, response.size());

	}

	@Test
	void testGetAllUsersByRole_Null() throws Exception {
		when(userRepository.findByrole(Role.valueOf("Admin"))).thenReturn(Arrays.asList());
		List<UserRegistrationDetailResponse> response = impl.getAllUsersByRole("Admin");
		assertEquals(null, response);

	}

}
