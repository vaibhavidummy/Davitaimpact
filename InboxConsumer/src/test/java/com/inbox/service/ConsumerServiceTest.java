package com.inbox.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.inbox.model.DisplayMessage;
import com.inbox.model.KafkaKey;
import com.inbox.model.KafkaValue;
import com.inbox.repository.InboxRepository;
import com.medication.exception.ConsumerException;

@ExtendWith(MockitoExtension.class)
class ConsumerServiceTest {

	@Mock
	private InboxRepository inboxRepository;

	@Spy
	private KafkaKey kafkaKey;

	@Spy
	private KafkaValue kafkaValue;

	@InjectMocks
	private ConsumerService consumerService;

	@Test
	void inboxListenerTest() {
		// given
		kafkaKey.setToId("5");
		ConsumerRecord<KafkaKey, KafkaValue> consumerRecord = new ConsumerRecord<KafkaKey, KafkaValue>("inbox", 3, 2L,
				kafkaKey, kafkaValue);
		// when
		consumerService.inboxListener(consumerRecord);
		// then
		verify(inboxRepository).save(kafkaKey);

	}

	@Test
	void inboxListenerTest_withToIdEmpty() {
		// given
		kafkaKey.setToId("");
		ConsumerRecord<KafkaKey, KafkaValue> consumerRecord = new ConsumerRecord<KafkaKey, KafkaValue>("inbox", 3, 2L,
				kafkaKey, kafkaValue);
		// when
		assertThrows(ConsumerException.class, () -> {
			consumerService.inboxListener(consumerRecord);
		});

	}

	@Test
	void getRecordList_forNurse() {
		//given
		List<KafkaKey> recordList=new ArrayList<KafkaKey>();
		kafkaKey.setRole("NURSE");
		kafkaKey.setFromName("doctor");
		kafkaKey.setDate(LocalDateTime.now());
		kafkaValue.setAppointmentId("5");
		kafkaValue.setMessage("REQUEST");
		kafkaKey.setValue(kafkaValue);
		recordList.add(kafkaKey);
		//when
		doReturn(recordList).when(inboxRepository).getAllRecordsForNurse(Boolean.TRUE);
		//then
		List<DisplayMessage> actualList=consumerService.getRecordList(kafkaKey);
		assertEquals(recordList.get(0).getValue().getAppointmentId(), actualList.get(0).getAppointmentId());
		
	}
	
	@Test
	void getRecordList_forOtherThanNurse() {
		//given
		List<KafkaKey> recordList=new ArrayList<KafkaKey>();
		kafkaKey.setToId("5");
		kafkaKey.setRole("Doctor");
		kafkaKey.setFromName("doctor");
		kafkaKey.setDate(LocalDateTime.now());
		kafkaValue.setAppointmentId("5");
		kafkaValue.setMessage("REQUEST");
		kafkaKey.setValue(kafkaValue);
		recordList.add(kafkaKey);
		//when
		doReturn(recordList).when(inboxRepository).getAllRecords(Mockito.anyString());
		//then
		List<DisplayMessage> actualList=consumerService.getRecordList(kafkaKey);
		assertEquals(recordList.get(0).getValue().getAppointmentId(), actualList.get(0).getAppointmentId());
	}
	
	@Test
	void getRecordList_noRecordPresent() {
		//given
		kafkaKey.setRole("Doctor");
		//when
		//then
		assertThrows(ConsumerException.class, () -> {
			consumerService.getRecordList(kafkaKey);
		});
		
	}
	
}
