package com.inbox.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.inbox.model.DisplayMessage;
import com.inbox.model.KafkaKey;
import com.inbox.model.KafkaValue;
import com.inbox.service.ConsumerService;

@ExtendWith(MockitoExtension.class)
class ConsumerControllerTest {

	@Mock
	private ConsumerService consumerService;

	@Mock
	private KafkaKey kafkaKey;

	@Mock
	private ConsumerRecord<KafkaKey, KafkaValue> consumerRecord;

	@InjectMocks
	private ConsumerController consumerController;

	@Test
	void inboxListenerTest() {

		consumerController.inboxListener(consumerRecord);
		verify(consumerService).inboxListener(consumerRecord);

	}

	@Test
	void getRecordListForUserTest() {
		// given
		List<DisplayMessage> displayMessageList = new ArrayList<DisplayMessage>();
		DisplayMessage displayMessage = new DisplayMessage();
		displayMessage.setAppointmentId("1");
		displayMessage.setMessage("Request");
		displayMessageList.add(displayMessage);

		// when
		when(consumerService.getRecordList(kafkaKey)).thenReturn(displayMessageList);
		// then
		List<DisplayMessage> actualList = consumerController.getRecordList(kafkaKey);
		assertEquals(displayMessageList.get(0).getAppointmentId(), actualList.get(0).getAppointmentId());
	}

}
