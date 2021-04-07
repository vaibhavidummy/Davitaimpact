package in.davita.impact.erp.admin.repository;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import in.davita.impact.erp.admin.model.UserRegistrationDetail;

@Repository
public interface UserRegistrationDetailRepository extends JpaRepository<UserRegistrationDetail, String> {


	@Modifying
	@Transactional
	@Query(value="UPDATE user_registration_detail SET status ='I' WHERE user_id=:userId", nativeQuery = true )
	int DisableUser(@Param("userId") String userId);
	
	@Query(value="SELECT u.email FROM user_registration_detail u  WHERE u.email=:email", nativeQuery = true )
	String checkForExistingEmail(@Param("email") String email);
	
	@Query(value="SELECT c.user_name FROM user_registration_detail u join user_credentials c on u.credential_id_fk=c.credential_id WHERE c.user_name=:userName", nativeQuery = true )
	String checkForExistingUsername(@Param("userName")String userName);



}
