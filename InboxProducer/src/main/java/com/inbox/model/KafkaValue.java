package com.inbox.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KafkaValue {

	private String message;
	private Boolean isNurse;
	private String appointmentId;
}
