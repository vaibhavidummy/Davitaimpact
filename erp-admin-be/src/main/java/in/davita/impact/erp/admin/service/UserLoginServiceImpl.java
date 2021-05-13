package in.davita.impact.erp.admin.service;

import java.nio.file.spi.FileSystemProvider;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.RandomStringUtils;
import org.aspectj.weaver.NewConstructorTypeMunger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.davita.impact.erp.admin.exception.EntityDetailsNotFoundException;
import in.davita.impact.erp.admin.model.KafkaMailSender;
import in.davita.impact.erp.admin.model.UpdatePassword;
import in.davita.impact.erp.admin.model.UserRegistrationDetail;
import in.davita.impact.erp.admin.model.UserRegistrationDetailResponse;
import in.davita.impact.erp.admin.repository.UserLoginRepository;
import in.davita.impact.erp.admin.util.Utility;
/**
 * 'User login service interface implementation' 
 * @version 1.0 08-04-2021
 * @author PrashantW3
 * */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserLoginServiceImpl implements UserLoginService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserLoginServiceImpl.class);
	
	@Autowired
	UserRegistrationDetailService userRegistrationDetailService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserLoginRepository userLoginRepository;
	
	@Autowired
	KafkaTemplate<String, KafkaMailSender> producer;
	
	@Override
	public UserRegistrationDetailResponse userLogin(String email, String pass) {
		LOGGER.info("inside userLogin method of UserLoginServiceImpl");
		UserRegistrationDetailResponse responseToUi=null;
		if(!(email.isEmpty() && pass.isEmpty())) {
			UserRegistrationDetail result=null;
			result= userRegistrationDetailService.checkForExistingEmail(email);
			if(null != result) {
				if(passwordEncoder.matches(pass, result.getUserCredentials().getPassword()))
				{
				 responseToUi = new Utility().bindDataToResponse(result);
				}else {
					throw new EntityDetailsNotFoundException("Sorry bad credentials", 
							new Object[] {email});
				}
			}
		}
		return responseToUi;
	}

	@Override
	public String generateCommonLangPassword() {
		LOGGER.info("inside generateCommonLangPassword method of UserLoginServiceImpl");
		String upperCaseLetters = RandomStringUtils.random(2, 65, 90, true, true);
		String lowerCaseLetters = RandomStringUtils.random(2, 97, 122, true, true);
		String numbers = RandomStringUtils.randomNumeric(2);
		String specialChar = RandomStringUtils.random(1, 35, 38, false, false);
		String totalChars = RandomStringUtils.randomAlphanumeric(1);
		String combinedChars = upperCaseLetters.concat(lowerCaseLetters).concat(numbers).concat(specialChar)
				.concat(totalChars);
		List<Character> pwdChars = combinedChars.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
		Collections.shuffle(pwdChars);
		String password = pwdChars.stream().collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
				.toString();
		return password;
	}
	
	@Override
	public boolean sendEmail(String recipientEmail, String genPassword) {
		System.out.println("insideimpl");
		if(!recipientEmail.isEmpty())
		{
		KafkaMailSender mailsendproducer= new KafkaMailSender(recipientEmail, genPassword);
		
		producer.send("mail-send", recipientEmail + "created",mailsendproducer );
		}
		return true;
		
	}

	@Override
	public Boolean updatePassword(UpdatePassword updatePassword) {
		LOGGER.info("inside updatePassword method of UserLoginServiceImpl");
		int result = 0;
		UserRegistrationDetail userRegistrationDetail=userRegistrationDetailService.checkForExistingEmail(updatePassword.getEmail());
		if (passwordEncoder.matches(updatePassword.getOldPassword(),userRegistrationDetail.getUserCredentials().getPassword()))
		{
			result=userLoginRepository.updatePassword(updatePassword.getEmail(),
					passwordEncoder.encode(updatePassword.getNewPassword()));
			if(result > 0) {
				return true;
			}
		}else {
			throw new EntityDetailsNotFoundException("Sorry password not updated", 
					new Object[] {updatePassword.getEmail()});
		}
		return false;
	}

	
	
}
