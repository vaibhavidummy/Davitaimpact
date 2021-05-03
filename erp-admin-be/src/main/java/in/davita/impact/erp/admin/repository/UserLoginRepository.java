package in.davita.impact.erp.admin.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import in.davita.impact.erp.admin.model.UserRegistrationDetail;
@Repository
public interface UserLoginRepository extends JpaRepository<UserRegistrationDetail, String> {

	@Query(value = "SELECT * FROM user_registration_detail u join user_credentials c on u.credential_id_fk=c.credential_id WHERE c.email=:email AND c.password=:pass", nativeQuery = true)
	UserRegistrationDetail userLogin(@Param("email") String email, @Param("pass") String pass);

	@Modifying
	@Query(value = "UPDATE user_credentials SET password =:newPassword "
			+ "WHERE email=:email", nativeQuery = true)
	int updatePassword(@Param("email") String email,
			@Param("newPassword") String newPassword);

}
