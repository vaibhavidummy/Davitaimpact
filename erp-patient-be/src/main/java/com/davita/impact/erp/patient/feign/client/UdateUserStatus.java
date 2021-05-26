package com.davita.impact.erp.patient.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name="admin-service")
public interface UdateUserStatus {
	@RequestMapping(method = RequestMethod.PUT, value = "/registration/afterfirstauth")
	public Boolean afterFirstAuthParamterChange (@RequestParam boolean isPasswordChangeReq, @RequestParam boolean isPersonalDeatilRequired, @RequestParam String userId);
}
