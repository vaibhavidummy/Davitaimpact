package com.davita.impact.erp.patient.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
public class LanguageKnown {

	@Id
	private int languageId;
	private String languageName;
	
	/*
	 * @ManyToOne private Patient patient;
	 */
	
}
