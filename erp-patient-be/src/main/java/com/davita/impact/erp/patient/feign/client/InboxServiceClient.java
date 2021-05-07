package com.davita.impact.erp.patient.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.davita.impact.erp.patient.model.Inbox;

//@FeignClient("inbox-service")
public interface InboxServiceClient {
	
	//@RequestMapping(method = RequestMethod.POST, value = "/inbox")
    public String createInbox(@RequestBody Inbox inbox);

}
