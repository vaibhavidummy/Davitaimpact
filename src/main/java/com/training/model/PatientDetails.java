package com.training.model;

import java.sql.Date;
import java.util.List;

public class PatientDetails {
	
	private String firstName;
	private String lastName;
	private Date dateofBirth;
	private String contactNo;
	private String age;
	enum gender{
		MALE,
		FEMALE,
		OTHERS;
	};
	private String race;
	private String ethnicity;
	 enum languages_Known{
		 English,		 Hindi,
		 Marathi, spanish;
	 };
	 private String  email;
	 private Address  homeAddress; 
	 private EmergencyContact emergencyContact;
	 private boolean status;
	 private List<AllergyInfo> allergyies;
}