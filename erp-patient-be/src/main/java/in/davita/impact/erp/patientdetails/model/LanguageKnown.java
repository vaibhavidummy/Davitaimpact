package in.davita.impact.erp.patientdetails.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

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
	private int id;
	private String name;

	public LanguageKnown() {

	}

	@ManyToMany(mappedBy = "languageKnown",fetch = FetchType.LAZY)
	List<PatientDetails> patientdetails;

}
