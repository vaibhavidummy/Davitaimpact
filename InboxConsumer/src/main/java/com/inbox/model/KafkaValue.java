package com.inbox.model;

import org.springframework.data.cassandra.core.mapping.Column;

import lombok.Data;

@Data
public class KafkaValue {

	private String message;
	@Column("is_nurse")
	private Boolean isNurse;
	@Column("appointment_id")
	private String appointmentId;
}
