package com.davita.impact.erp.inbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.davita.impact.erp.inbox.model.Inbox;

@Repository
public interface InboxRepository extends JpaRepository<Inbox, String> {

}
