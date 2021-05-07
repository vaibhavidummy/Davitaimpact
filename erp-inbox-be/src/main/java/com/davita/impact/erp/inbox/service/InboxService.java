package com.davita.impact.erp.inbox.service;

import java.util.Optional;

import com.davita.impact.erp.inbox.model.Inbox;
public interface InboxService {

	Inbox addInbox(Inbox inbox);
	
	Optional<Inbox> getInbox(String id);
}
