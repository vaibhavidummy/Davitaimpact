package com.medication.configuration;

import org.springframework.context.annotation.Bean;

import com.datastax.oss.driver.api.core.CqlSession;


public class CassandraConfiguration {

	 @Bean 
	 public CqlSession session() {
	    return CqlSession.builder().withKeyspace("medicationlist").build();
	  }
	}
	

