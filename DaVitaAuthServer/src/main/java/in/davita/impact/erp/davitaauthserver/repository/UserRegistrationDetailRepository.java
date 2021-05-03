package in.davita.impact.erp.davitaauthserver.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import in.davita.impact.erp.davitaauthserver.model.UserRegistrationDetail;
/**
 * @Author : Prashant Wankhede on 30-04-2021
 */

@Repository
public interface UserRegistrationDetailRepository extends JpaRepository<UserRegistrationDetail, String> {


	@Query(value="SELECT * FROM user_registration_detail u join user_credentials c on u.credential_id_fk=c.credential_id WHERE email=:email", nativeQuery = true )
	UserRegistrationDetail checkForExistingEmail(@Param("email") String email);
	
}
