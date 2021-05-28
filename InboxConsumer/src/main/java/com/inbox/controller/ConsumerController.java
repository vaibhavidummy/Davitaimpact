package com.inbox.controller;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inbox.model.DisplayMessage;
import com.inbox.model.KafkaKey;
import com.inbox.model.KafkaValue;
import com.inbox.service.ConsumerService;


@RestController
@RequestMapping("/records")
public class ConsumerController {

	@Autowired
	private ConsumerService consumerService;

	@KafkaListener(topics = "inbox")
	public void inboxListener(ConsumerRecord<KafkaKey, KafkaValue> consumerRecord) {
		consumerService.inboxListener(consumerRecord);
	}

	@PostMapping("/get")
	public List<DisplayMessage> getRecordList(@RequestBody KafkaKey kafkaKey) {
		return consumerService.getRecordList(kafkaKey);
}
}