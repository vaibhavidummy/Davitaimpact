package com.davita.impact.erp.patient.model;

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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "patientDetailsAddress" })
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@NotBlank(message = "Address Line should not Blank")
	@Length(max = 200, message = "Address Line allow only max 200 characters")
	@Column(nullable = false, length = 200)
	private String addressLine;

	@NotBlank(message = "Landmark Area should not Blank")
	@Length(max = 200, message = "Landmark Area allow only max 200 characters")
	@Column(nullable = false, length = 200)
	private String landmarkArea;

	@NotBlank(message = "city should not Blank")
	@Length(max = 200, message = "city allow only max 200 characters")
	@Column(nullable = false, length = 200)
	private String city;

	@NotBlank(message = "State should not Blank")
	@Length(max = 200, message = "State allow only max 200 characters")
	@Column(nullable = false, length = 200)
	private String state;

	@NotBlank(message = "Country should not Blank")
	@Length(max = 200, message = "Country allow only max 100 characters")
	@Column(nullable = false, length = 100)
	private String country;

	@NotNull(message = "PinCode field is required")
	@Digits(integer = 6, fraction = 0, message = "provide proper Pin Code No")
	@Positive(message = "Please provide proper PinCode No")
	@Column(nullable = false, length = 10)
	private int pin;

	@NotNull(message = "Gender field is required")
	@Enumerated(EnumType.STRING)
	private AddressType addressType;

	
	
	  
	/*
	 * @ManyToOne(cascade = CascadeType.ALL)
	 * 
	 * @JsonIgnore PatientDetails patientDetailsAddress;
	 */
	 

	
	@OneToOne(fetch = FetchType.LAZY,  mappedBy ="address") 
	  private PatientDetails patientDetailsAddress;




	public Address(
			@NotBlank(message = "Address Line should not Blank") @Length(max = 200, message = "Address Line allow only max 200 characters") String addressLine,
			@NotBlank(message = "Landmark Area should not Blank") @Length(max = 200, message = "Landmark Area allow only max 200 characters") String landmarkArea,
			@NotBlank(message = "city should not Blank") @Length(max = 200, message = "city allow only max 200 characters") String city,
			@NotBlank(message = "State should not Blank") @Length(max = 200, message = "State allow only max 200 characters") String state,
			@NotBlank(message = "Country should not Blank") @Length(max = 200, message = "Country allow only max 100 characters") String country,
			@NotNull(message = "PinCode field is required") @Digits(integer = 6, fraction = 0, message = "provide proper Pin Code No") @Positive(message = "Please provide proper PinCode No") int pin,
			@NotNull(message = "Gender field is required") AddressType addressType) {
		super();
		this.addressLine = addressLine;
		this.landmarkArea = landmarkArea;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pin = pin;
		this.addressType = addressType;
	}
	 
	
	public Address() {
		
	}
	
	
	
	
}
