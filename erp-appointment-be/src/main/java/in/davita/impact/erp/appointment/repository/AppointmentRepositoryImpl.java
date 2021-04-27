package in.davita.impact.erp.appointment.repository;

import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.repository.query.CassandraEntityInformation;
import org.springframework.data.cassandra.repository.support.SimpleCassandraRepository;

import in.davita.impact.erp.appointment.model.Appointment;

public class AppointmentRepositoryImpl extends SimpleCassandraRepository<Appointment, String> implements AppointmentRepository{

	public AppointmentRepositoryImpl(CassandraEntityInformation<Appointment, String> metadata,
			CassandraOperations operations) {
		super(metadata, operations);
		// TODO Auto-generated constructor stub
	}

}
