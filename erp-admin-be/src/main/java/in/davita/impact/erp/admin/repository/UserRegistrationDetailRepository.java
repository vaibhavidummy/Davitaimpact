package in.davita.impact.erp.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import in.davita.impact.erp.admin.model.Role;
import in.davita.impact.erp.admin.model.UserRegistrationDetail;

@Repository
public interface UserRegistrationDetailRepository extends JpaRepository<UserRegistrationDetail, String> {


	@Modifying
	@Query(value="UPDATE user_registration_detail SET metastatus ='I' WHERE user_id=:userId", nativeQuery = true )
	int disableUser(@Param("userId") String userId);
	
	@Query(value="SELECT * FROM user_registration_detail u join user_credentials c on u.credential_id_fk=c.credential_id WHERE email=:email", nativeQuery = true )
	UserRegistrationDetail checkForExistingEmail(@Param("email") String email);
	
	@Modifying
	@Query(value="UPDATE user_registration_detail SET is_password_change_required =:isPasswordChangeReq,"
			+ "is_personal_details_required=:isPersonalDeatilRequired WHERE user_id=:userId", nativeQuery = true )
	int afterFirstAuthParamterChange(@Param("isPasswordChangeReq") boolean isPasswordChangeReq, 
			@Param("isPersonalDeatilRequired") boolean isPersonalDeatilRequired, String userId);

	//Use of finder Method ex
	List<UserRegistrationDetail> findByrole(Role role);

}
