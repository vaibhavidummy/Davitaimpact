package com.davita.impact.erp.patient.utilities;

import java.io.Serializable;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class AppointmentIdGenerator implements IdentifierGenerator {


	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		
		
		return "APT"+ UUID.randomUUID().toString();
		
	}

	

}
