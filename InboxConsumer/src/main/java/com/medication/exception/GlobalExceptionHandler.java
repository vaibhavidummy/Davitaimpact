package com.medication.exception;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.medication.constants.ErrorConstant;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ ConsumerException.class })
	public ExceptionObject catchMedicationException(ConsumerException ex) {
		return new ExceptionObject(LocalDateTime.now(), ex.getMessage(),ErrorConstant.PROVIDE_VALID_INFO ,
				ex.getStacktrace(),Boolean.FALSE);
	}

	@ExceptionHandler({ Exception.class })
	public ExceptionObject catchAllException(Exception ex) {
		return new ExceptionObject(LocalDateTime.now(), ex.getMessage(), ErrorConstant.ADMIN_CONTACT,
				ex.getStackTrace()[0],Boolean.FALSE);
	}

	@ExceptionHandler({ RuntimeException.class })
	public ExceptionObject catchAllRuntimeException(RuntimeException ex) {
		return new ExceptionObject(LocalDateTime.now(), ex.getMessage(), ErrorConstant.ADMIN_CONTACT,
				ex.getStackTrace()[0],Boolean.FALSE);
	}
}
