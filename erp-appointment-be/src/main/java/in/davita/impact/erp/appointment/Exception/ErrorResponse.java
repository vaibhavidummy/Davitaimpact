package in.davita.impact.erp.appointment.Exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ErrorResponse {
	
	private String code;

	private Date timestamp;

	private String correlationId;

	private String message;

	private String detail;

}
