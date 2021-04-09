package in.davita.impact.erp.admin.exception;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@PropertySource("classpath:/errormap.properties")
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@Autowired
	private Environment env;

	@Autowired
	private MessageSource messageSource;

	private Date getTimestamp() {
		return Calendar.getInstance().getTime();
	}

/********************************************************************************************/

@ExceptionHandler(HttpClientErrorException.class)
public final ErrorResponse handleForbiddenException(HttpClientErrorException ex, WebRequest request) {
	LOGGER.error(ex.getMessage());
	return new ErrorResponse(ex.getStatusCode().toString(), getTimestamp(), "Client Error", ex.getStatusText(),
			ex.getMessage());
}
/********************************************************************************************************/
@ExceptionHandler(HttpServerErrorException.class)
public final ErrorResponse handleAllExceptions(HttpServerErrorException ex, WebRequest request) {
	LOGGER.error(ex.getMessage());
	return new ErrorResponse(ex.getStatusCode().toString(), getTimestamp(), "Server Error", ex.getStatusText(),
			ex.getMessage());
}
/**************************************************************************************************/
@ExceptionHandler(SQLException.class)
public final ErrorResponse handleSQLExceptions(SQLException ex, WebRequest request) {
	ErrorResponse error = new ErrorResponse(HttpStatus.NO_CONTENT.toString(), getTimestamp(), ex.getMessage(), "",
			ex.getMessage());
	LOGGER.error(ex.getMessage());
	return error;
}
/***********************************************************************************************************/
@Override
protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
		HttpStatus status, WebRequest request) {
	Map<String, String> fieldErrorMap = new HashMap<String, String>();

	for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
		fieldErrorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
	}
	String defaultMessage = "Validation Failed for " + ex.getParameter().getParameterType();
	String errorMessage;
	String errorCode = env
			.getProperty(ex.getParameter().getParameterType().getName() + ExceptionConstantsMap.VALIDATION_ERROR);
	if (errorCode != null) {
		errorMessage = messageSource.getMessage(errorCode, null, LocaleContextHolder.getLocale());
	} else {
		errorMessage = defaultMessage;
		errorCode = defaultMessage;
	}
	LOGGER.error(fieldErrorMap.toString());
	ErrorResponse errorResponse = new ErrorResponse(errorCode, getTimestamp(), "", errorMessage,
			fieldErrorMap.toString());
	return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
}

/****************************************************************************************************/
@ExceptionHandler(UserRegistrationDetailNotFoundException.class)
public final ResponseEntity<ErrorResponse> handleCustomException(UserRegistrationDetailNotFoundException ex,
		WebRequest request) throws Exception {
	String errorCode = env.getProperty(ExceptionConstantsMap.USER_REIGISTRATION_DETAIL_NOT_FOUND);
	String errorMessage = messageSource.getMessage(errorCode, ex.getArgs(), LocaleContextHolder.getLocale());
	ErrorResponse errorResponse = new ErrorResponse(errorCode, getTimestamp(), "", errorMessage, ex.getMessage());
	LOGGER.error(errorResponse.getMessage());
	return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
}

/************************************************************************************************/
@ExceptionHandler(MessagingException.class)
public final ResponseEntity<ErrorResponse> handleEmailException(MessagingException ex, WebRequest request) {
	LOGGER.error(ex.getMessage());
	ErrorResponse errorResponse = new ErrorResponse(HttpStatus.REQUEST_TIMEOUT.toString(), getTimestamp(),
			ex.getMessage(), "Exception occure while sending mail", ex.getMessage());
	return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.REQUEST_TIMEOUT);
}
	
}
