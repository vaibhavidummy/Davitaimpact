package com.inbox.deserialisers;

import java.io.IOException;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inbox.model.KafkaKey;

public class KafkaKeyDeSerialiser implements Deserializer<KafkaKey> {

	public KafkaKey deserialize(String topic, byte[] data) {

		KafkaKey message = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			message = mapper.readValue(data, KafkaKey.class);
		} catch (IOException e) {
		}
		return message;
	}

}
