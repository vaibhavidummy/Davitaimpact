package com.medication.exception;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.datastax.oss.driver.api.core.NoNodeAvailableException;
import com.medication.constants.ErrorConstant;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ MedicationException.class })
	public ExceptionObject catchMedicationException(MedicationException ex) {
		return new ExceptionObject(LocalDateTime.now(), ex.getMessage(), ErrorConstant.ADMIN_CONTACT,
				ex.getStacktrace());
	}

	@ExceptionHandler({ NoNodeAvailableException.class })
	public ExceptionObject catchCassandraUnavailableException(NoNodeAvailableException ex) {
		return new ExceptionObject(LocalDateTime.now(), ex.getMessage(), ErrorConstant.CASSANDRA_DOWN,
				ex.getStackTrace()[0]);
	}

	@ExceptionHandler({ Exception.class })
	public ExceptionObject catchAllException(Exception ex) {
		return new ExceptionObject(LocalDateTime.now(), ex.getMessage(), ErrorConstant.ADMIN_CONTACT,
				ex.getStackTrace()[0]);
	}

	@ExceptionHandler({ RuntimeException.class })
	public ExceptionObject catchAllRuntimeException(RuntimeException ex) {
		return new ExceptionObject(LocalDateTime.now(), ex.getMessage(), ErrorConstant.ADMIN_CONTACT,
				ex.getStackTrace()[0]);
	}
}
