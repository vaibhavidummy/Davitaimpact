package in.davita.impact.erp.admin.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Login {


	@NotBlank(message = "Email field is required")
	@Email
	private String email;
	
	@NotBlank(message = "Password field is required")
	@Length(min = 8, max = 20, message = "password must contain atleast 8 characters and max 20")
	private String password;

}
