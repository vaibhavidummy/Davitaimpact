package com.inbox.serialisers;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inbox.model.KafkaValue;

public class KafkaValueSerialiser implements Serializer<KafkaValue> {

	public byte[] serialize(String topic, KafkaValue value) {
		byte[] serializedValue = null;
		ObjectMapper om = new ObjectMapper();
		if (value != null) {
			try {
				serializedValue = om.writeValueAsString(value).getBytes();
			} catch (JsonProcessingException e) {
			}
		}
		return serializedValue;
	}

}
