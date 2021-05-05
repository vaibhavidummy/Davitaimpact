package com.citiustech.pms.diagnosis.exception;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.citiustech.pms.diagnosis.constants.ErrorConstant;


@RestControllerAdvice
public class DiagnosisGlobalExceptionHandler {

	@ExceptionHandler(DiagnosisException.class)
	public ExceptionObject procedureExceptionHandler(DiagnosisException  ex)
	{
		return new ExceptionObject(LocalDateTime.now(), ex.getMessage(), ErrorConstant.ADMIN_CONTACT ,ex.getStackTrace()[0], false);
	}

	@ExceptionHandler(Exception.class)
	public ExceptionObject procedureExceptionHandler(Exception  ex)
	{
		return new ExceptionObject(LocalDateTime.now(), ex.getMessage(), ErrorConstant.ADMIN_CONTACT ,ex.getStackTrace()[0], false);
	}

	@ExceptionHandler(RuntimeException.class)
	public ExceptionObject procedureExceptionHandler(RuntimeException  ex)
	{
		return new ExceptionObject(LocalDateTime.now(), ex.getMessage(), ErrorConstant.ADMIN_CONTACT ,ex.getStackTrace()[0], false);
	}
}