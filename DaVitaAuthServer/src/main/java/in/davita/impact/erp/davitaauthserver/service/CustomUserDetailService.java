package in.davita.impact.erp.davitaauthserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.davita.impact.erp.davitaauthserver.model.CustomUserRegistrationDetail;
import in.davita.impact.erp.davitaauthserver.model.UserRegistrationDetail;
import in.davita.impact.erp.davitaauthserver.repository.UserRegistrationDetailRepository;
/**
 * @Author : Prashant Wankhede on 30-04-2021
 */
@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	UserRegistrationDetailRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserRegistrationDetail userDetail= checkForExistingEmail(username);
		System.out.println("inside service::"+userDetail);
		if(null==userDetail)
		{
			throw new UsernameNotFoundException(username);
		}
		CustomUserRegistrationDetail customDetail=new CustomUserRegistrationDetail(userDetail);
		return customDetail;
	}
	
	
	public UserRegistrationDetail checkForExistingEmail(String email) {
		UserRegistrationDetail result=null;
		if(!email.isEmpty())
		{
			result=repository.checkForExistingEmail(email);
			
		}
		return result;
	}

}
