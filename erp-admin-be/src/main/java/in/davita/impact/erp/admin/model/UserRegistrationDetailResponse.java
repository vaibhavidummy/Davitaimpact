package in.davita.impact.erp.admin.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserRegistrationDetailResponse {
	
	private String user_Id;

	private Title title;

	private String firstName;

	private String lastName;

	private Date dob;

	private long contactNumber;

	private Role role;
	
	String Metastatus;
	
	Boolean isPersonalDetailsRequired;

	Boolean isPasswordChangeRequired;
	
	String email;

}
