package com.citiustech.pms.procedure.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionObject {

		private  LocalDateTime timeStamp;
	    private  String message;
	    private  String error;
	    private  StackTraceElement stacktrace;
	    private boolean successFlag;
}