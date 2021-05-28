package com.inbox.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inbox.model.DisplayMessage;
import com.inbox.model.KafkaKey;
import com.inbox.model.KafkaValue;
import com.inbox.repository.InboxRepository;
import com.medication.exception.ConsumerException;

@Service
public class ConsumerService {

	@Autowired
	private InboxRepository inboxRepository;

	
	public void inboxListener(ConsumerRecord<KafkaKey, KafkaValue> consumerRecord) {
		KafkaKey kafkaKey = consumerRecord.key();
		KafkaValue kafkaValue = consumerRecord.value();
		if(kafkaKey.getToId().isEmpty()) {
			throw new  ConsumerException("Please provide receivers Id");
		}
		kafkaKey.setValue(kafkaValue);
		kafkaKey.setLocalDateTime();
		inboxRepository.save(kafkaKey);
	}
	
	public List<DisplayMessage> getRecordList(KafkaKey kafkaKey) {
		List<KafkaKey> recordList = null;
		List<DisplayMessage> displayRecordList = new ArrayList<>();
		if (kafkaKey.getRole().equalsIgnoreCase("NURSE")) {
			recordList = inboxRepository.getAllRecordsForNurse(Boolean.TRUE);
		}
		else {
			recordList=inboxRepository.getAllRecords(kafkaKey.getToId());
		}
		if(recordList.isEmpty()) {
			throw new ConsumerException("Record is not available");
		}
		recordList.stream().forEach(record -> {
			DisplayMessage message = new DisplayMessage();
			message.setFromName(record.getFromName());
			message.setDate(record.getDate());
			message.setAppointmentId(record.getValue().getAppointmentId());
			message.setMessage(record.getValue().getMessage());
			displayRecordList.add(message);
		});
		return displayRecordList;
	}

}
