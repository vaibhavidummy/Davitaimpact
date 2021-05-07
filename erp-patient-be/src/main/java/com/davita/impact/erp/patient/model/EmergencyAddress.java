package com.davita.impact.erp.patient.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

import com.davita.impact.erp.patient.utilities.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Entity
public class EmergencyAddress  {

	@Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
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
	
	
	/*
	 * @ManyToOne
	 * 
	 * @JsonIgnore PatientDetails patientDetailsEmergencyAddress;
	 */	
	
}
