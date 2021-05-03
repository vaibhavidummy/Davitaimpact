package in.davita.impact.erp.davitaauthserver.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
/**
 * @Author : Prashant Wankhede on 30-04-2021
 */
public class CustomUserRegistrationDetail extends UserRegistrationDetail implements UserDetails{

	
	private static final long serialVersionUID = 1L;

	UserRegistrationDetail detail;
	public CustomUserRegistrationDetail(final UserRegistrationDetail userRegistration) {
		super(userRegistration);
		detail=userRegistration;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		 Role role=detail.getRole();
		 SimpleGrantedAuthority grant=new SimpleGrantedAuthority("ROLE_"+role.toString().toUpperCase());
		List<SimpleGrantedAuthority> list=new ArrayList<SimpleGrantedAuthority>();
				list.add(grant);
		return list ;
		
		
	}
 
	@Override
	public String getPassword() {
		return detail.getUserCredentials().getPassword();
	}

	@Override
	public String getUsername() {
		
		return detail.getUserCredentials().getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return detail.getMetastatus().equals("A")?true:false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
