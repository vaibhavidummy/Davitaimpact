package com.inbox.serialisers;



import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inbox.model.KafkaKey;

public class KafkaKeySerialiser implements Serializer<KafkaKey>{

	
	 public byte[] serialize(String topic, KafkaKey key) {
	        byte[] serializedValue = null;
	        ObjectMapper om = new ObjectMapper();
	        if(key != null) {
	            try {
	                serializedValue = om.writeValueAsString(key).getBytes();
	            } catch (JsonProcessingException e) {
	            }
	        }
	        return serializedValue;
	    }
	 
	 
}
