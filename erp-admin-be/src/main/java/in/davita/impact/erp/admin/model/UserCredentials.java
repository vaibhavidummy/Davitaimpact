package in.davita.impact.erp.admin.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import in.davita.impact.erp.admin.util.Auditable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "userRegistrationDetail" })
public class UserCredentials extends Auditable<String> {
	@Id
	@GenericGenerator(name = "seq_user_id", strategy = "in.davita.impact.erp.admin.util.StringPrefixedSequenceIdGenerator")
	@GeneratedValue(generator = "seq_user_id")
	private String credentialId;

	@NotBlank(message = "UserName field is required")
	@Length(max = 20, message = "UserName field allow max 20 characters")
	@Column(nullable = false, unique = true, length = 20)
	private String userName;

	@NotBlank(message = "Password field is required")
	// @Pattern(regexp =
	// "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",
	// message = "Password")
	@Length(min = 8, max = 20, message = "password must contain atleast 8 characters and max 20")
	@Column(nullable = false, length = 20)
	private String password;

	private int wrongPasswordCount;
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "userCredentials")
	private UserRegistrationDetail userRegistrationDetail;

	

}
