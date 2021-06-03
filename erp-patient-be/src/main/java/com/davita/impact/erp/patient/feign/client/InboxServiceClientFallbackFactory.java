package com.davita.impact.erp.patient.feign.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import com.davita.impact.erp.patient.controller.AppointmentController;
import com.davita.impact.erp.patient.exception.ServiceNotFoundException;
import com.davita.impact.erp.patient.model.Inbox;

@Component
public class InboxServiceClientFallbackFactory implements FallbackFactory<InboxServiceClient> {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentController.class);
	
	 @Override
	    public InboxServiceClient create(Throwable throwable) {
		 
	        return new InboxServiceClient()
	        		{
						@Override
						public String createInbox(Inbox inbox) {
							LOGGER.info("Service is disabled",throwable.getCause());
							System.out.println("Inside fallback");
							return "1234";
						}
	        	
	        		};
	    }
}
