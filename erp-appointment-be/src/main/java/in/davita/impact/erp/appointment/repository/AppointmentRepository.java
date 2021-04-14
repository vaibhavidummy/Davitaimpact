package in.davita.impact.erp.appointment.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import in.davita.impact.erp.appointment.model.Appointment;

@Repository
public interface AppointmentRepository extends CassandraRepository<Appointment, String>{

}
