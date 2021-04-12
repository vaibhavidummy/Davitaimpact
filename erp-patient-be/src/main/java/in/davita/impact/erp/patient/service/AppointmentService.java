package in.davita.impact.erp.patient.service;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;



import in.davita.impact.erp.patient.model.Appointment;


public interface AppointmentService {
	
	Appointment addAppointment(Appointment appointment);
	
	Appointment UpdateAppointment(Appointment appointmentDtls);
	
	Optional<Appointment> getAppointment(String appointmentId);
	List<Appointment> getAllAppointments();


	
}
