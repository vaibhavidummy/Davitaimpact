package com.inbox.controller;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.inbox.model.InputMessage;
import com.inbox.service.ProducerService;
@ExtendWith(MockitoExtension.class)
class ProducerControllerTest {

	@InjectMocks
	private ProducerController producerController;

	@Mock
	private ProducerService producerService;

	@Mock
	private InputMessage input;

	@Test
	void sendInboxMessageTest() {
		producerController.sendInboxMessage(input);
		verify(producerService).sendInboxMessage(input);
	}

}
