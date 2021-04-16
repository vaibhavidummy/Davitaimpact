package in.davita.impact.erp.patient.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@Entity
public class LanguageKnown {

	
	  @Id  
	  @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;

	
	@ManyToMany(targetEntity = PatientDetails.class, mappedBy ="languageKnown",fetch = FetchType.LAZY)
	  @JsonIgnore 
	List<PatientDetails> patientdetails;
	 
	
	
	  public LanguageKnown() {

		}

		
		
}
