package com.inbox.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class DisplayMessage {

	private String fromName;
	private String message;
	private LocalDateTime date;
	private String appointmentId;
	
}
