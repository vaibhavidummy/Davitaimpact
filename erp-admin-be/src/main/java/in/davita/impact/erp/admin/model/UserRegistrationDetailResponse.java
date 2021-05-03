package in.davita.impact.erp.admin.model;

import java.time.LocalDate;

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
	
	private String userId;

	private Title title;

	private String firstName;

	private String lastName;

	private LocalDate dob;

	private long contactNumber;

	private Role role;
	
	String Metastatus;
	
	Boolean isPersonalDetailsRequired;

	Boolean isPasswordChangeRequired;
	
	String email;

}
