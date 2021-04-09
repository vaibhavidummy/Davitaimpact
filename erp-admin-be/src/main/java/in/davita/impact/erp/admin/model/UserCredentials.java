package in.davita.impact.erp.admin.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import in.davita.impact.erp.admin.util.Auditable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude ="userRegistrationDetail")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "userRegistrationDetail" })
public class UserCredentials extends Auditable<String> {
	@Id
	@GenericGenerator(name = "seq_user_id", strategy = "in.davita.impact.erp.admin.util.RandomIdGenerator")
	@GeneratedValue(generator = "seq_user_id")
	@Column(nullable = false, unique = true,updatable = false)
	private String credentialId;
	
	@NotBlank(message = "Email field is required")
	@Email
	@Column(nullable = false, unique = true,updatable = false)
	private String email;
	
	@NotBlank(message = "Password field is required")
	@Length(min = 8, max = 20, message = "password must contain atleast 8 characters")
	@Column(nullable = false, length = 20)
	private String password;

	private int wrongPasswordCount;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "userCredentials")
	private UserRegistrationDetail userRegistrationDetail;

	

}
