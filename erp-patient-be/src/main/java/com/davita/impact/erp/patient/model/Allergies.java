package com.davita.impact.erp.patient.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Data
@Entity
public class Allergies {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@NotNull(message = "Allergis Type field is required")
	@Enumerated(EnumType.STRING)
	private Type type;

	@NotNull
	private boolean isFatal;

	@ManyToMany(mappedBy = "allergiesObject", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	List<PatientDetails> patientdetails;
	/* Set<PatientDetails> patientdetails=new HashSet<>(); */

	public Allergies() {

	}
	public Allergies(int id, @NotNull(message = "Allergis Type field is required") Type type,
			@NotNull boolean isFatal) {
		super();
		this.id = id;
		this.type = type;
		this.isFatal = isFatal;
	}
	
	
	
	
}
