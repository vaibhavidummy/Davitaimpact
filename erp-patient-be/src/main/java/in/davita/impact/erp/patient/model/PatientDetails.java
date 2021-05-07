package in.davita.impact.erp.patient.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.springframework.core.annotation.AliasFor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import in.davita.impact.erp.patient.utilities.Auditable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Data
@Entity
public class PatientDetails extends Auditable<String> {

	@Id
	@GenericGenerator(name = "patient_sequence_id", strategy = "in.davita.impact.erp.patient.utilities.PatientDetailsIdGenerator")
	@GeneratedValue(generator = "patient_sequence_id")
	private String id;

	@NotBlank(message = "user id should not be blank")
	private String user_id_fk;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "basic_details_id")
	private BasicDetails basicDetails;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "emergency_details_id")
	private EmergencyDetails emergencyDetails;

	@Transient
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<Integer> languageKnown;

	@Transient
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<Integer> allergies;

	/*
	 * @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	 * 
	 * @JoinColumn(name = "patient_details_address_id")
	 * 
	 * @JsonAlias("emergencyAddress") private List<Address> address;
	 */

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address address;

	@ManyToMany(targetEntity = LanguageKnown.class, fetch = FetchType.LAZY/* , cascade = { CascadeType.MERGE } */)
	@JoinTable(name = "patientdetails_languageknown", joinColumns = @JoinColumn(name = "patientdetails_id"), inverseJoinColumns = @JoinColumn(name = "languageknown_id"))
	Set<LanguageKnown> languageKnownObject;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE) // { CascadeType.PERSIST, CascadeType.MERGE }
	@JoinTable(name = "patientdetails_allergies", joinColumns = @JoinColumn(name = "patientdetails_id"), inverseJoinColumns = @JoinColumn(name = "allergies_id"))
	Set<Allergies> allergiesObject;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "patientDetailsForVisit")
	// @JoinColumn(name = "visit_id")
	private List<PatientVisit> patientVisit;

	public PatientDetails() {

	}

	public PatientDetails(String id, @NotBlank(message = "user id should not be blank") String user_id_fk,
			BasicDetails basicDetails, EmergencyDetails emergencyDetails, Address address,
			Set<LanguageKnown> languageKnown, Set<Allergies> allergies) {
		super();
		this.id = id;
		this.user_id_fk = user_id_fk;
		this.basicDetails = basicDetails;
		this.emergencyDetails = emergencyDetails;
		this.address = address;
		this.languageKnownObject = languageKnown;
		this.allergiesObject = allergies;
	}

	public PatientDetails(@NotBlank(message = "user id should not be blank") String user_id_fk,
			BasicDetails basicDetails, EmergencyDetails emergencyDetails, List<Integer> languageKnown,
			List<Integer> allergies, Address address) {
		super();
		this.user_id_fk = user_id_fk;
		this.basicDetails = basicDetails;
		this.emergencyDetails = emergencyDetails;
		this.languageKnown = languageKnown;
		this.allergies = allergies;
		this.address = address;
	}

	public PatientDetails(String id, @NotBlank(message = "user id should not be blank") String user_id_fk,
			BasicDetails basicDetails, EmergencyDetails emergencyDetails, List<Integer> languageKnown,
			List<Integer> allergies, Address address) {
		super();
		this.id = id;
		this.user_id_fk = user_id_fk;
		this.basicDetails = basicDetails;
		this.emergencyDetails = emergencyDetails;
		this.languageKnown = languageKnown;
		this.allergies = allergies;
		this.address = address;
	}

}
