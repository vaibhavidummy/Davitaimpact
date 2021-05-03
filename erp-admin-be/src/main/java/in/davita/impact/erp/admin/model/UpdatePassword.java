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
@Getter
@Setter
@ToString
public class UpdatePassword {

	@NotBlank(message = "Email field is required")
	@Email
	String email;
	
	@NotBlank(message = "OldPassword field is required")
	@Length(min = 8, max = 20, message = "password must contain atleast 8 characters")
	String oldPassword;
	
	@NotBlank(message = "NewPassword field is required")
	@Length(min = 8, max = 20, message = "password must contain atleast 8 characters")
	String newPassword;
}
