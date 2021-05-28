package com.inbox.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.Embedded;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(Include.NON_NULL)
@Table("records")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KafkaKey {

	@PrimaryKey(value = "record_id")
	private String recordId = UUID.randomUUID().toString();
	@Column("from_id")
	private String fromId;
	@Column("from_name")
	private String fromName;
	@Column("to_id")
	private String toId;
	@Column("to_name")
	private String toName;
	private LocalDateTime date;
	@Embedded.Nullable
	private KafkaValue value;
	@Transient
	private String role;

	public void setLocalDateTime() {
		this.date = LocalDateTime.now();
	}
}
