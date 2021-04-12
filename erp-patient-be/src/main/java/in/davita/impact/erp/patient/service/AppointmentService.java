package in.davita.impact.erp.patient.service;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;



import in.davita.impact.erp.patient.model.Appointment;


public interface AppointmentService {
	
	Appointment addAppointment(Appointment Appointment);
	
	Appointment UpdateAppointment(Appointment appointmentDtls);
	
	Optional<Appointment> getAppointment(String AppointmentId);
	List<Appointment> getAllAppointments();


	
}
