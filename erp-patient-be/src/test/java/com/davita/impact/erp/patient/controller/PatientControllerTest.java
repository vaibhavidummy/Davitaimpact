package com.davita.impact.erp.patient.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.davita.impact.erp.patient.comman.ResponseOnOk;
import com.davita.impact.erp.patient.model.Address;
import com.davita.impact.erp.patient.model.AddressType;
import com.davita.impact.erp.patient.model.Allergies;
import com.davita.impact.erp.patient.model.BasicDetails;
import com.davita.impact.erp.patient.model.EmergencyDetails;
import com.davita.impact.erp.patient.model.Gender;
import com.davita.impact.erp.patient.model.LanguageKnown;
import com.davita.impact.erp.patient.model.PatientDetails;
import com.davita.impact.erp.patient.model.Type;
import com.davita.impact.erp.patient.service.LanguageServices;
import com.davita.impact.erp.patient.service.PatientServices;
import com.davita.impact.erp.patient.service.allergiesServices;

@ExtendWith(MockitoExtension.class)
class PatientControllerTest {

	@InjectMocks
	private PatientController pataintController;
	
	@Mock
	private LanguageServices languageServices;
	
	@Mock
	private allergiesServices allergiesServices;


	@Mock
	private PatientServices patientServices;

	@Autowired(required = true)
	MockMvc mockMvc;

	@MockBean
	private PatientServices patientServices1;

	/* @BeforeEach public void setup() { MockitoAnnotations.initMocks(this); } */

	/* patientDetails object For all */
	Set<LanguageKnown> lang = new HashSet<LanguageKnown>();
	Set<Allergies> alergy = new HashSet<Allergies>();

	List<Integer> languageKnown = new ArrayList<Integer>();
	List<Integer> allergies = new ArrayList<Integer>();

	LanguageKnown language1 = new LanguageKnown(1, "Marathi");
	LanguageKnown language2 = new LanguageKnown(2, "English");

	Allergies allergies1 = new Allergies(1, Type.DUST, false);
	Allergies allergies2 = new Allergies(2, Type.FOOD, false);

	BasicDetails basicDetails = new BasicDetails("Sachin", "Tendulkar", "m16@gmail.com", new Date(1985 - 01 - 30),
			new Long("9090909090"), new Float("36.0"), Gender.MALE, "Ashian", "Test");

	EmergencyDetails emergencyDetails = new EmergencyDetails("Amar", "Shinde", "Friend", new Long("9898989898"), true,
			"test16@gmail.com", true);

	Address address = new Address("24/b Tower 4", "Sadarbazar Peth", "Satara", "Maharashtra", "India", 415010,
			AddressType.HOME_ADDRESS);

	PatientDetails patientDetails1 = new PatientDetails("CT26834216-c55e-48fe-9151-a4b9b47bc25d",
			"APT50e2a882-abc4-4df4-a1f1-480f3731a635", basicDetails, emergencyDetails, address, lang, alergy);

	PatientDetails patientDetails2 = new PatientDetails("CT26834216-c55e-48fe-9151-a4b9b47bc25d",
			"APT50e2a882-abc4-4df4-a1f1-480f3731a635", basicDetails, emergencyDetails, address, lang, alergy);

	PatientDetails patientDetailsForPost = new PatientDetails("APT50e2a882-abc4-4df4-a1f1-480f3731a635", basicDetails,
			emergencyDetails, languageKnown, allergies, address);

	ResponseEntity<PatientDetails> patientDetails3 = new ResponseEntity<PatientDetails>(patientDetails1, HttpStatus.OK);

	PatientDetails patientDetailsForPut = new PatientDetails("CT26834216-c55e-48fe-9151-a4b9b47bc25d",
			"APT50e2a882-abc4-4df4-a1f1-480f3731a635", basicDetails, emergencyDetails, languageKnown, allergies,
			address);

	@Test
	public void putPatientTest() throws Exception {

		languageKnown.add(1);
		languageKnown.add(2);

		allergies.add(1);
		allergies.add(2);

		ResponseOnOk responseOnOk = new ResponseOnOk();
		responseOnOk.setId(patientDetails1.getId());
		responseOnOk.setMessage("Patient Details Added Successfully");
		responseOnOk.setStatus(200);

		ResponseEntity<ResponseOnOk> putexpectedPatientDetails = new ResponseEntity<ResponseOnOk>(responseOnOk,
				HttpStatus.OK);
		// when(patientServices.getPatientById("CT26834216-c55e-48fe-9151-a4b9b47bc25d")).thenReturn(patientDetails1);
		when(patientServices.updatePatient(patientDetailsForPut)).thenReturn(patientDetails1);
		// doReturn(expectedList).when(languageServices).getAllLangwages();

		System.out.println("Inside Update :------    " + patientDetailsForPut.getId());

		ResponseEntity<ResponseOnOk> patientDetailsActual = pataintController
				.updatePatientDetails(patientDetailsForPut);

		assertEquals(putexpectedPatientDetails.getBody().getId(), patientDetailsActual.getBody().getId());

	}

	@Test
	public void postPatientTest() {

		languageKnown.add(1);
		languageKnown.add(2);

		allergies.add(1);
		allergies.add(2);

		ResponseOnOk responseOnOk = new ResponseOnOk();
		responseOnOk.setId(patientDetails1.getId());
		responseOnOk.setMessage("Patient Details Added Successfully");
		responseOnOk.setStatus(201);

		ResponseEntity<ResponseOnOk> postexpectedPatientDetails = new ResponseEntity<ResponseOnOk>(responseOnOk,
				HttpStatus.CREATED);
		// (PatientDetails) Arrays.asList(patientDetails1);
		when(patientServices.addNewPatient(patientDetailsForPost)).thenReturn(patientDetails1);
		// doReturn(expectedList).when(languageServices).getAllLangwages();

		// System.out.println("Inside SAVE :------ "+patientDetailsForPost);

		ResponseEntity<ResponseOnOk> patientDetailsActual = pataintController.createUser(patientDetailsForPost);

		assertEquals(postexpectedPatientDetails.getBody().getId(), patientDetailsActual.getBody().getId());
	}

	@Test
	public void getSpecificPatientTest() {
		String id = "CT26834216-c55e-48fe-9151-a4b9b47bc25d";
		lang.add(new LanguageKnown(3, "English"));
		alergy.add(new Allergies(1, Type.DUST, false));
		alergy.add(new Allergies(2, Type.FOOD, false));
		ResponseEntity<PatientDetails> expectedPatientDetails = patientDetails3;
		// (PatientDetails) Arrays.asList(patientDetails1);
		when(patientServices.getPatientById(id)).thenReturn(patientDetails1);
		// doReturn(expectedList).when(languageServices).getAllLangwages();
		ResponseEntity<PatientDetails> patientDetailsActual = pataintController.getPatientDetailsByID(id);
		assertEquals(expectedPatientDetails, patientDetailsActual);
	}

	@Test
	public void getAllPatientTest() {

		lang.add(new LanguageKnown(3, "English"));
		alergy.add(new Allergies(1, Type.DUST, false));
		alergy.add(new Allergies(2, Type.FOOD, false));
		List<PatientDetails> expectedListPatient = Arrays.asList(patientDetails1, patientDetails2);
		when(patientServices.getAllPatient()).thenReturn(expectedListPatient);
		// doReturn(expectedList).when(languageServices).getAllLangwages();
		List<PatientDetails> allPatientDetailsActuals = pataintController.getAllPatientDetails();
		assertEquals(expectedListPatient, allPatientDetailsActuals);
	}

	@Test
	public void getAllLanguagesTest() {
		List<LanguageKnown> expectedList = Arrays.asList(language1, language2);
		when(languageServices.getAllLangwages()).thenReturn(expectedList);
//doReturn(expectedList).when(languageServices).getAllLangwages();
		List<LanguageKnown> allLanguageKnownActuals = pataintController.getAllLangugeDetails();
		assertEquals(expectedList, allLanguageKnownActuals);
	}

	@Test
	public void getAllAllergiesTest() {
		List<Allergies> expectedList = Arrays.asList(allergies1, allergies2);
		when(allergiesServices.getAllAllergies()).thenReturn(expectedList);
//doReturn(expectedList).when(languageServices).getAllLangwages();
		List<Allergies> allergiesActuals = pataintController.getAllAllergiesDetails();
		assertEquals(expectedList, allergiesActuals);
	}

	/*
	 * @Disabled public void dumyMethodForGetAllPateintDetails() throws Exception {
	 * 
	 * List<Integer> lang;lang.add(1);lang.add(2); List<Integer>
	 * alergy;lang.add(1);lang.add(2);
	 * 
	 * Set<LanguageKnown> lang = new HashSet<LanguageKnown>(); lang.add(new
	 * LanguageKnown(3, "English"));
	 * 
	 * Set<Allergies> alergy = new HashSet<Allergies>(); alergy.add(new Allergies(1,
	 * Type.DUST, false)); alergy.add(new Allergies(2, Type.FOOD, false));
	 * 
	 * when(patientServiceImpl.getAllPatient()) .thenReturn((List<PatientDetails>)
	 * new PatientDetails("CT26834216-c55e-48fe-9151-a4b9b47bc25d",
	 * "APT50e2a882-abc4-4df4-a1f1-480f3731a635",
	 * 
	 * new BasicDetails("Sachin", "Tendulkar", "m16@gmail.com", new
	 * Date("1985-01-30"), new Long("9090909090"), new Float("36.0"), Gender.MALE,
	 * "Ashian", "Test"), new EmergencyDetails("Amar", "Shinde", "Friend", new
	 * Long("9898989898"), true, "test16@gmail.com", true), new
	 * Address("24/b Tower 4", "Sadarbazar Peth", "Satara", "Maharashtra", "India",
	 * 415010, AddressType.HOME_ADDRESS), lang, alergy
	 * 
	 * ));
	 * 
	 * RequestBuilder request =
	 * MockMvcRequestBuilders.get("/healthcare/patient").accept(MediaType.
	 * APPLICATION_JSON);
	 * 
	 * MvcResult result =
	 * mockMvc.perform(request).andExpect(status().isOk()).andExpect(content()
	 * .json(
	 * "[{id:CT26834216-c55e-48fe-9151-a4b9b47bc25d,user_id_fk:APT50e2a882-abc4-4df4-a1f1-480f3731a635"
	 * + "basicDetails:{" +
	 * "id:305,firstName:SachinRamesh,lastName:Tendulkar,emailId:m16@gmail.com,dateOfBirth:1985-01-30,contactNo:9090909090,age:36.0,gender:male,race:Ashian,ethnicity:Test"
	 * + "}," + "emergencyDetails:{" +
	 * "id:307,emergency_first_name:Amar,emergency_last_name:Shinde,emergency_relation_ship:Friend,emergency_contact_number:9898989898,mailId:test14@gmail.com,_access_approved:true,_same_address=true"
	 * + "}," + "address:{" +
	 * "id:299,addressLine:24/b Tower 4,landmarkArea:Sadarbazar,city:satara,state:Maharashtra,country:India,pin:415001,addressType:HOME_ADDRESS"
	 * + "}," + "languageKnown:[" + "{id:3,name:English}," + "{id:1,name:Marathi}" +
	 * "]," + "allergies: [" + "{id:1,type:DUST,fatal:false}," +
	 * "{id:2,type:FOOD,fatal:false}" + "]" + "}" + "]")) .andReturn(); }
	 * 
	 */ /* test for localhost:8080/healthcare/allergies */


}
