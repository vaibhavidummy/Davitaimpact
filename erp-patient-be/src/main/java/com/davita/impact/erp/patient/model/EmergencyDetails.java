package com.davita.impact.erp.patient.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

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
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler","patientDetails1"})
public class EmergencyDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "emergencyDetailsid")
	private int id;
	
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Emergency Contact Personfield must contain characters only")
	@NotBlank(message = "Emergency Contact Person FristName field is required")
	@Length(max = 15, message = "Emergency Contact Person FirstName field allow only max 15 characters")
	@Column(nullable = false, length = 15)
	private String emergency_first_name;

	@Pattern(regexp = "^[a-zA-Z]+$", message = "Emergency Contact Persone Last Name field must contain characters only")
	@NotBlank(message = "Emergency Contact Person LastName field is required")
	@Length(max = 15, message = "FirstName field allow only max 15 characters")
	@Column(nullable = false, length = 15)
	private String emergency_last_name;

	@Pattern(regexp = "^[a-zA-Z]+$", message = "Relationship With Patient field must contain characters only")
	@NotBlank(message = "Relationship With Patient field is required")
	@Length(max = 15, message = "Relationship With Patient field allow only max 15 characters")
	@Column(nullable = false, length = 15)
	private String emergency_relation_ship;

	@NotNull(message = "Emergency ContactNumber field is required")
	@Digits(integer = 10, fraction = 0, message = "provide proper Emergency ContactNumber")
	@Positive(message = " Please provide proper Emergency ContactNumber")
	@Column(nullable = false, length = 10)
	private long emergency_contact_number;

	/*
	 * @Pattern(regexp = "^true$|^false$", message = "Allowed input: true or false")
	 */
	@NotNull
	private boolean is_access_approved;
	

	@NotBlank(message = "Email field is required")
	@Email
	@Column(name="emergency_contact_email_id",nullable = false, unique = true)
	private String mailId;

	@NotNull
	private boolean is_same_address;

	@OneToOne(fetch = FetchType.LAZY, mappedBy ="emergencyDetails") 
	  private PatientDetails patientDetails1;
	
	public EmergencyDetails() {
		
	}

	public EmergencyDetails(
			@Pattern(regexp = "^[a-zA-Z]+$", message = "Emergency Contact Personfield must contain characters only") @NotBlank(message = "Emergency Contact Person FristName field is required") @Length(max = 15, message = "Emergency Contact Person FirstName field allow only max 15 characters") String emergency_first_name,
			@Pattern(regexp = "^[a-zA-Z]+$", message = "Emergency Contact Persone Last Name field must contain characters only") @NotBlank(message = "Emergency Contact Person LastName field is required") @Length(max = 15, message = "FirstName field allow only max 15 characters") String emergency_last_name,
			@Pattern(regexp = "^[a-zA-Z]+$", message = "Relationship With Patient field must contain characters only") @NotBlank(message = "Relationship With Patient field is required") @Length(max = 15, message = "Relationship With Patient field allow only max 15 characters") String emergency_relation_ship,
			@NotNull(message = "Emergency ContactNumber field is required") @Digits(integer = 10, fraction = 0, message = "provide proper Emergency ContactNumber") @Positive(message = " Please provide proper Emergency ContactNumber") long emergency_contact_number,
			@NotNull boolean is_access_approved, @NotBlank(message = "Email field is required") @Email String mailId,
			@NotNull boolean is_same_address) {
		super();
		this.emergency_first_name = emergency_first_name;
		this.emergency_last_name = emergency_last_name;
		this.emergency_relation_ship = emergency_relation_ship;
		this.emergency_contact_number = emergency_contact_number;
		this.is_access_approved = is_access_approved;
		this.mailId = mailId;
		this.is_same_address = is_same_address;
	}
	
	
	
}
