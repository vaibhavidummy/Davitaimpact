package com.inbox.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KafkaKey {

	public String fromId;
	public String fromName;
	public String toId;
	public String toName;
}
