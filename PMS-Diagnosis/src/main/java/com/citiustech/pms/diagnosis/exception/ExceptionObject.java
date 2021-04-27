package com.citiustech.pms.diagnosis.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionObject {

	private  LocalDateTime timeStamp;
    private  String message;
    private  String error;
    private  StackTraceElement stacktrace;
    private boolean successFlag;
}