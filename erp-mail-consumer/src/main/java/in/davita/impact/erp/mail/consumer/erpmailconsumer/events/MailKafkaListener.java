package in.davita.impact.erp.mail.consumer.erpmailconsumer.events;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import in.davita.impact.erp.mail.consumer.erpmailconsumer.model.KafkaMailSender;
@Component
public class MailKafkaListener {
	
	private final Logger LOGGER = LoggerFactory.getLogger(MailKafkaListener.class);

	@KafkaListener(topics ="mail-send")
	public void mailsend(KafkaMailSender consumerSender) {
		LOGGER.info("inside sendEmail method of UserLoginServiceImpl");
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername("davitamailsender@gmail.com");
		mailSender.setPassword("jbpxcayokvksnysu");
		Properties javaMailProperties = new Properties();
		javaMailProperties.setProperty("mail.debug", "false");
		javaMailProperties.setProperty("mail.smtp.auth", "true");
		javaMailProperties.setProperty("mail.smtp.starttls.enable", "true");
		mailSender.setJavaMailProperties(javaMailProperties);
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			 helper.setFrom("davitamailsender@gmail.com", "davita");
			 helper.setTo(consumerSender.getRecipientEmail());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		String subject = "Here's your default password";
	     
	    String content =
	    		"<html>\n" +
                "<body>\n" +
                "<p>Hello,</p>"+
                "<p>You have requested to forgot/reset your password.</p>\n"+
	            "<p>use the below password to login:</p>"+
                "\n" +
                "<p>"+consumerSender.getGenPassword()+"</p>\n"+
                "\n" +
                "<p>Ignore this email if you do remember your password,\n"+
	             "or you have not made the request.</p>\n"+
                "</body>\n" +
                "</html>";
		try {
			helper.setSubject(subject);
			helper.setText(content, true);
			mailSender.send(message);
		} catch (MessagingException e) {
			
			LOGGER.error(e.getMessage());
		}

	}

}
