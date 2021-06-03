package com.davita.impact.erp.patient.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Data
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler","patientDetails"})
public class BasicDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Pattern(regexp = "^[a-zA-Z]+$", message = "FirstName field must contain characters only")
	@NotBlank(message = "FirstName field is required")
	@Length(max = 15, message = "FirstName field allow only max 15 characters")
	@Column(nullable = false, length = 15)
	private String firstName;

	@Pattern(regexp = "^[a-zA-Z]+$", message = "FirstName field must contain characters only")
	@NotBlank(message = "FirstName field is required")
	@Length(max = 15, message = "LastName field allow only max 15 characters")
	@Column(nullable = false, length = 15)
	private String lastName;

	@NotBlank(message = "Email field is required")
	@Email
	@Column(name="email_id",nullable = false, unique = true)
	private String emailId;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Dob field is required")
	@Column(nullable = false)
	@PastOrPresent(message = "Dob must be past or present date")
	private Date dateOfBirth;

	@NotNull(message = "ContactNumber field is required")
	@Digits(integer = 10, fraction = 0, message = "provide proper ContactNumber")
	@Positive(message = "Please provide proper ContactNumber")
	@Column(nullable = false, length = 10)
	private long contactNo;

	@NotNull(message = "Age field is required")
	@Digits(integer = 3, fraction = 2, message = "Age is not Proper")
	@Positive(message = "Please provide proper Age")
	@Column(nullable = false, length = 3)
	private float age;

	@NotNull(message = "Gender field is required")
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Pattern(regexp = "^[a-zA-Z]+$", message = "Race field must contain characters only")
	@NotBlank(message = "Race field is required")
	@Length(max = 15, message = "Race field allow only max 15 characters")
	@Column(nullable = false, length = 15)
	private String race;

	@Pattern(regexp = "^[a-zA-Z]+$", message = "Ethnicity field must contain characters only")
	@NotBlank(message = "Ethnicity field is required")
	@Length(max = 15, message = "Ethnicity field allow only max 15 characters")
	@Column(nullable = false, length = 15)
	private String ethnicity;

	
	
	@OneToOne(fetch = FetchType.LAZY,  mappedBy ="basicDetails") 
	  private PatientDetails patientDetails;
	 
	/*
	 * @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	 * 
	 * @JoinColumn(name = "patient_details_id") //@JsonIgnore private PatientDetails
	 * patientDetails;
	 */
	
	
	public BasicDetails() {
	
	}
	
	public BasicDetails(
			@Pattern(regexp = "^[a-zA-Z]+$", message = "FirstName field must contain characters only") @NotBlank(message = "FirstName field is required") @Length(max = 15, message = "FirstName field allow only max 15 characters") String firstName,
			@Pattern(regexp = "^[a-zA-Z]+$", message = "FirstName field must contain characters only") @NotBlank(message = "FirstName field is required") @Length(max = 15, message = "LastName field allow only max 15 characters") String lastName,
			@NotBlank(message = "Email field is required") @Email String emailId,
			@NotNull(message = "Dob field is required") @PastOrPresent(message = "Dob must be past or present date") Date dateOfBirth,
			@NotNull(message = "ContactNumber field is required") @Digits(integer = 10, fraction = 0, message = "provide proper ContactNumber") @Positive(message = "Please provide proper ContactNumber") long contactNo,
			@NotNull(message = "Age field is required") @Digits(integer = 3, fraction = 2, message = "Age is not Proper") @Positive(message = "Please provide proper Age") float age,
			@NotNull(message = "Gender field is required") Gender gender,
			@Pattern(regexp = "^[a-zA-Z]+$", message = "Race field must contain characters only") @NotBlank(message = "Race field is required") @Length(max = 15, message = "Race field allow only max 15 characters") String race,
			@Pattern(regexp = "^[a-zA-Z]+$", message = "Ethnicity field must contain characters only") @NotBlank(message = "Ethnicity field is required") @Length(max = 15, message = "Ethnicity field allow only max 15 characters") String ethnicity
			) {
		super();
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.dateOfBirth = dateOfBirth;
		this.contactNo = contactNo;
		this.age = age;
		this.gender = gender;
		this.race = race;
		this.ethnicity = ethnicity;
		
	}
	
}
