package in.davita.impact.erp.davitaauthserver.security;

import in.davita.impact.erp.davitaauthserver.model.UserRegistrationDetail;
import in.davita.impact.erp.davitaauthserver.repository.UserRegistrationDetailRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;

/**
 * @Author :Prashant Wankhede
 */
@Slf4j
@Component
public class UserConfigMetaData implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
    private UserRegistrationDetailRepository userService;


    public Map<String, Object> getUserRelatedInformation(String userName) {
        Map<String, Object> maps = new HashMap<>();
        UserRegistrationDetail userEntity = userService.checkForExistingEmail(userName);
        maps.put("user_role", userEntity.getRole());
        maps.put("fullName", userEntity.getFirstName().concat(" ").concat(userEntity.getLastName()));
        maps.put("email", userEntity.getUserCredentials().getEmail());
        maps.put("userId", userEntity.getUserId());
        maps.put("isPasswordChangeRequired",userEntity.getIsPasswordChangeRequired());
        maps.put("isPersonalDetailsRequired", userEntity.getIsPersonalDetailsRequired());
        return maps;
    }
}