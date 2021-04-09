package in.davita.impact.erp.admin.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

import in.davita.impact.erp.admin.util.Auditable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserRegistrationDetail extends Auditable<String> {

	@Id
	@GenericGenerator(name = "seq_user_id", strategy = "in.davita.impact.erp.admin.util.RandomIdGenerator")
	@GeneratedValue(generator = "seq_user_id")
	@Column(nullable = false, unique = true,updatable = false)
	private String user_Id;

	@NotNull(message = "Title field is required")
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 5)
	private Title title;

	@Pattern(regexp = "^[a-zA-Z]+$", message = "FirstName field must contain characters only")
	@NotBlank(message = "FirstName field is required")
	@Length(max = 15, message = "FirstName field allow only max 15 characters")
	@Column(nullable = false, length = 15)
	private String firstName;

	@Pattern(regexp = "^[a-zA-Z]+$", message = "LastName field must contain characters only")
	@NotBlank(message = "LastName field is required")
	@Length(max = 15, message = "LastName field allow only max 15 characters")
	@Column(nullable = false, length = 15)
	private String lastName;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Dob field is required")
	@Column(nullable = false)
	@PastOrPresent(message = "Dob must be past or present date")
	private Date dob;

	@NotNull(message = "ContactNumber field is required")
	@Digits(integer = 10, fraction = 0, message = "provide proper ContactNumber")
	@Positive(message = "Please provide proper ContactNumber")
	@Column(nullable = false, length = 10)
	private Long contactNumber;

	@NotNull(message = "Role field is required")
	@Enumerated(EnumType.STRING)
	private Role role;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "credential_id_fk",nullable = false,unique = true,updatable = false)
	UserCredentials userCredentials;
	
	@Column(nullable = false, length = 1)
	String Metastatus = "A";
	
	@Column(nullable = false, length = 1)
	Boolean isPersonalDetailsRequired;
	
	@Column(nullable = false, length = 1)
	Boolean isPasswordChangeRequired;
	
}
