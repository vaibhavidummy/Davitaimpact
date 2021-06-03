package com.inbox.deserialisers;

import java.io.IOException;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inbox.model.KafkaValue;

public class KafkaValueDeSerialiser implements Deserializer<KafkaValue> {

	 public KafkaValue deserialize(String topic, byte[] data) {

		 KafkaValue message = null;
	        try {
	            ObjectMapper mapper = new ObjectMapper();
	            message = mapper.readValue(data, KafkaValue.class);
	        } catch (IOException e) {
	        }
	        return message;
	    }

}
