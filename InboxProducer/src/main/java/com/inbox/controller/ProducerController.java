package com.inbox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inbox.model.InputMessage;
import com.inbox.service.ProducerService;

@RestController
@RequestMapping("/inboxProducer")
public class ProducerController {

	@Autowired
	private ProducerService producerService;

	@PostMapping("/sendRequest")
	public void sendInboxMessage(@RequestBody InputMessage input) {
		producerService.sendInboxMessage(input);
	}

}
