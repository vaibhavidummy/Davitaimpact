package com.inbox.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputMessage {

	private KafkaKey key;
	private KafkaValue value;
}
