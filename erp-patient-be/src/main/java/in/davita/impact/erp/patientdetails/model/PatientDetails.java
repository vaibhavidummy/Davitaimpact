package in.davita.impact.erp.patientdetails.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Data
@Entity
public class PatientDetails {

	@Id
	@GenericGenerator(name = "patient_sequence_id", strategy = "in.davita.impact.erp.patientdetails.utilities.PatientDetailsIdGenerator")
	@GeneratedValue(generator = "patient_sequence_id")
	private String id;
	
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
	@Column(nullable = false, unique = true)
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
	@Column(nullable = false, length = 13)
	private float age;

	@NotNull(message = "Role field is required")
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

	@NotBlank(message = "HomeAddress field is required")
	@Length(max = 200, message = "Race field allow only max 200 characters")
	@Column(nullable = false, length = 200)
	private String home_address;
	
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
	
	@NotBlank(message = "Emergency Address field is required")
	@Length(max = 200, message = "Emergency Addres field allow only max 200 characters")
	@Column(nullable = false, length = 200)
	private String emergency_address;
	
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
	
	 @NotNull
	 @Pattern(regexp = "^true$|^false$", message = "Allowed input: true or false")
	private boolean is_access_approved;
	
	
	@NotBlank(message = "Email field is required")
	@Email
	@Column(nullable = false, unique = true)
	private String emergencyContactEmailId;


	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "patientdetails_languageknown", joinColumns = @JoinColumn(name = "patientdetails_id"), 
	inverseJoinColumns = @JoinColumn(name = "languageknown_id"))
	List<LanguageKnown> languageKnown;

	public PatientDetails() {
		
	}

	
	/*
	 * created_by created_on modified_by modified_on meta_status
	 */

}
