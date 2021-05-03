package in.davita.impact.erp.davitaauthserver.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/**
 * @Author : Prashant Wankhede on 30-04-2021
 */

@Component
@Entity()
@Table(name = "user_registration_detail")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserRegistrationDetail implements Serializable{

	
	public UserRegistrationDetail(UserRegistrationDetail userRegistration) {
		
	}

 @Id
	//@GenericGenerator(name = "seq_user_id", strategy = "in.davita.impact.erp.admin.util.RandomIdGenerator")
	//@GeneratedValue(generator = "seq_user_id")
	@Column(nullable = false, unique = true,updatable = false)
	private String userId;

	@NotNull(message = "Title field is required")
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 10)
	private Title title;

	@Pattern(regexp = "^[a-zA-Z]+$", message = "FirstName field must contain characters only")
	@NotBlank(message = "FirstName field is required")
	@Length(max = 20, message = "FirstName field allow only max 20 characters")
	@Column(nullable = false, length = 20)
	private String firstName;

	@Pattern(regexp = "^[a-zA-Z]+$", message = "LastName field must contain characters only")
	@NotBlank(message = "LastName field is required")
	@Length(max = 20, message = "LastName field allow only max 20 characters")
	@Column(nullable = false, length = 20)
	private String lastName;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Dob field is required")
	@Column(nullable = false)
	@PastOrPresent(message = "Dob must be past or present date")
	private LocalDate dob;

	@NotNull(message = "ContactNumber field is required")
	@Digits(integer = 10, fraction = 0, message = "provide proper ContactNumber")
	@Positive(message = "Please provide proper ContactNumber")
	@Column(nullable = false, length = 10)
	private Long contactNumber;

	@NotNull(message = "Role field is required")
	@Enumerated(EnumType.STRING)
	private Role role;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "credential_id_fk",nullable = false,unique = true,updatable = false)
	@Valid
	@NotNull
	private UserCredentials userCredentials;
	
	@Column(nullable = false, length = 1)
	private String Metastatus = "A";
	
	@Column(nullable = false, length = 1)
	private Boolean isPersonalDetailsRequired;
	
	@Column(nullable = false, length = 1)
	private Boolean isPasswordChangeRequired;
	
}
