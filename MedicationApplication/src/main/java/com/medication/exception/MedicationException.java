package com.medication.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicationException extends RuntimeException {

	public MedicationException(String message) {
		this.message = message;
		this.stacktrace = super.getStackTrace()[0];
	}

	private static final long serialVersionUID = 1L;

	private String message;
	private StackTraceElement stacktrace;

}
