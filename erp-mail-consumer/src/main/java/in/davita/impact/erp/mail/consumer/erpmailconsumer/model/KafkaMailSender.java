package in.davita.impact.erp.mail.consumer.erpmailconsumer.model;






public class KafkaMailSender {
	
	
	String recipientEmail;
	
	String genPassword;

	public KafkaMailSender() {
		super();
	}

	public KafkaMailSender(String recipientEmail, String genPassword) {
		super();
		this.recipientEmail = recipientEmail;
		this.genPassword = genPassword;
	}

	public String getRecipientEmail() {
		return recipientEmail;
	}

	public void setRecipientEmail(String recipientEmail) {
		this.recipientEmail = recipientEmail;
	}

	public String getGenPassword() {
		return genPassword;
	}

	public void setGenPassword(String genPassword) {
		this.genPassword = genPassword;
	}

	@Override
	public String toString() {
		return "KafkaMailSender [recipientEmail=" + recipientEmail + ", genPassword=" + genPassword + "]";
	}
	
	
	


}
