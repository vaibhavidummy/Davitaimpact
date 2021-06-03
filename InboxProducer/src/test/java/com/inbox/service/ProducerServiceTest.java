package com.inbox.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import com.inbox.model.InputMessage;
import com.inbox.model.KafkaKey;
import com.inbox.model.KafkaValue;
import com.medication.exception.ProducerException;

@ExtendWith(MockitoExtension.class)
class ProducerServiceTest {

	@Mock
	private KafkaTemplate<KafkaKey, KafkaValue> kafkaTemplate;
	
	@Spy
	private InputMessage input;
	
	@Spy
	KafkaKey key;
	
	@Mock
	KafkaValue value;
	
	@InjectMocks
	private ProducerService producerService;
	
	@Test
	void sendInboxMessageTest() {
		//given
		key.setToId("5");
		input.setKey(key);
		input.setValue(value);
		producerService.sendInboxMessage(input);
		verify(kafkaTemplate).send("inbox", key, value);
		
	}

	@Test
	void sendInboxMessage_toIdNotPresentTest() {
		//given
		key.setToId("");
		input.setKey(key);
		input.setValue(value);
		
		assertThrows(ProducerException.class, () -> {
			producerService.sendInboxMessage(input);
		});
	}
}
