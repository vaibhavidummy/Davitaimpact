package com.inbox.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;

import com.inbox.model.KafkaKey;

public interface InboxRepository extends CassandraRepository<KafkaKey,String>{

	@Query("SELECT * FROM records WHERE is_nurse=:isNurse")
	public List<KafkaKey> getAllRecordsForNurse(@Param("isNurse") boolean isNurse);
	
	@Query("SELECT * FROM records WHERE to_id=:toId")
	public List<KafkaKey> getAllRecords(@Param("toId") String toId);
}
