package in.davita.impact.erp.appointment.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import in.davita.impact.erp.appointment.model.Appointment;

@Service
public interface AppointmentService {
	
	Appointment addAppointment(Appointment appointment);
	
	Appointment UpdateAppointment(Appointment appointmentDtls);
	
	Optional<Appointment> getAppointment(String appointmentId);
	
	List<Appointment> getAllAppointments();

}
