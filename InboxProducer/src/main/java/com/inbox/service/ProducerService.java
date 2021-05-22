package com.inbox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.inbox.model.InputMessage;
import com.inbox.model.KafkaKey;
import com.inbox.model.KafkaValue;
import com.medication.exception.ProducerException;

@Service
public class ProducerService {
	@Autowired
	private KafkaTemplate<KafkaKey, KafkaValue> kafkaTemplate;

	public void sendInboxMessage(InputMessage input) {
		if (input.getKey().getToId().isEmpty()) {
			throw new ProducerException("Please provide receivers Id");
		}
		KafkaKey key = input.getKey();
		KafkaValue value = input.getValue();
		kafkaTemplate.send("inbox", key, value);

	}
}
