package in.davita.impact.erp.mail.consumer.erpmailconsumer.events;

import org.springframework.kafka.support.serializer.JsonDeserializer;

import in.davita.impact.erp.mail.consumer.erpmailconsumer.model.KafkaMailSender;

public class KafkaMailSenderDeserializer  extends JsonDeserializer<KafkaMailSender>{
	
	public KafkaMailSenderDeserializer() {
		super(KafkaMailSender.class);
	}

}
