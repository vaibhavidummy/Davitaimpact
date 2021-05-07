package com.davita.impact.erp.inbox.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davita.impact.erp.inbox.model.Inbox;
import com.davita.impact.erp.inbox.repository.InboxRepository;

@Service
public class InboxServiceImpl implements InboxService {

	@Autowired
	InboxRepository inboxRepository;
	
	@Override
	public Inbox addInbox(Inbox inbox) {
		return inboxRepository.save(inbox);
	}

	@Override
	public Optional<Inbox> getInbox(String id) {
		return inboxRepository.findById(id);
	}

}
