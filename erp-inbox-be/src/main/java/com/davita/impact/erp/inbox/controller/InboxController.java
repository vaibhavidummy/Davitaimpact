package com.davita.impact.erp.inbox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.davita.impact.erp.inbox.model.Inbox;
import com.davita.impact.erp.inbox.service.InboxService;

@RestController
@RequestMapping(value = "inbox")
public class InboxController {
	
	@Autowired
	InboxService inboxService;
	
	@GetMapping(value="/hello")
	public String Hello()
	{
		return "Welcome to Inbox Module";
	}

	@PostMapping()
	public ResponseEntity<String> createInbox(@RequestBody Inbox inbox)
	{
		Inbox inboxStatus = null;
		inboxStatus = inboxService.addInbox(inbox);
		return new ResponseEntity<String>(inboxStatus.getId(), HttpStatus.CREATED);
	}
}
