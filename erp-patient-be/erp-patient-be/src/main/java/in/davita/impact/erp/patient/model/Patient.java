package in.davita.impact.erp.patient.model;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Data
@Entity
public class Patient {

	@Id
	private String id;
	private String firstName;
	private String lastName;
	private String emailId;
	private Date dateOfBirth;
	private long contactNo;
	private float age;
	private enum gender {male,female,TranceJender};
	private String race;
	private String ethnicity;
	
	private String home_address;
	private String emergency_first_name;
	private String emergency_last_name;
	private String emergency_address;
	private String emergency_relation_ship;
	private long emergency_contact_number;
	private boolean is_access_approved;
	private String emergencyContactEmailId;
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy = "patient")
	private List<LanguageKnown>languageKnow =new ArrayList<>();
	
	/*
	 * created_by created_on modified_by modified_on meta_status
	 */
	
	
	

	
}
