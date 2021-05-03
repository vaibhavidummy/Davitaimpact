package in.davita.impact.erp.admin.util;

import in.davita.impact.erp.admin.model.UserRegistrationDetail;
import in.davita.impact.erp.admin.model.UserRegistrationDetailResponse;

public class Utility {
	
	public UserRegistrationDetailResponse bindDataToResponse(UserRegistrationDetail userRegistrationDetail) {
		return new UserRegistrationDetailResponse(
				userRegistrationDetail.getUserId(),
				userRegistrationDetail.getTitle(),
				userRegistrationDetail.getFirstName(),
				userRegistrationDetail.getLastName(),
				userRegistrationDetail.getDob(),
				userRegistrationDetail.getContactNumber(),
				userRegistrationDetail.getRole(),
				userRegistrationDetail.getMetastatus(),
				userRegistrationDetail.getIsPersonalDetailsRequired(),
				userRegistrationDetail.getIsPasswordChangeRequired(),
				userRegistrationDetail.getUserCredentials().getEmail());
	}

}
