package com.davita.impact.erp.patient.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import com.davita.impact.erp.patient.utilities.Auditable;
import com.davita.impact.erp.patient.utilities.Auditable_visit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@Entity
public class PatientVisit  extends Auditable_visit<String>{

	@Id
	@GenericGenerator(name = "patient_visit_id", strategy = "com.davita.impact.erp.patient.utilities.PatientVisitIdGenerator")
	@GeneratedValue(generator = "patient_visit_id")
	private String id;
	
	private String userIdfk;
	
	private String appointmentIdfk;
	
	private boolean appointmentStatus;
	
	
	/*
	 * @ManyToOne private PatientDetails patientDetailsForVisit;
	 */
	
	public PatientVisit(){
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * @OneToMany(mappedBy="patientvisitdetails", cascade = CascadeType.ALL,fetch =
	 * FetchType.LAZY) private List<Diagnosis> diagnosisid;
	 * 
	 * 
	 * 
	 * 
	 * @OneToMany(mappedBy="patientvisitdetails1", cascade = CascadeType.ALL,fetch =
	 * FetchType.LAZY) private List<Procedures> procedureid;
	 * 
	 * 
	 * @OneToMany(mappedBy="patientvisitdetails2", cascade = CascadeType.ALL,fetch =
	 * FetchType.LAZY) private List<Medication> medication;
	 * 
	 * 
	 * @OneToOne(mappedBy = "patientvisitdetails3",cascade = CascadeType.ALL,fetch =
	 * FetchType.LAZY) private VitalDetails vitalDetails;
	 */    
}
