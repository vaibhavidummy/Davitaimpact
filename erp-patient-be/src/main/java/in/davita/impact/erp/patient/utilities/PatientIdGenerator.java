package in.davita.impact.erp.patient.utilities;

import java.io.Serializable;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.util.IdGenerator;

public class PatientIdGenerator implements IdentifierGenerator {


	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		
		
		return "IMEI"+ UUID.randomUUID().toString();
		
	}

	

}
