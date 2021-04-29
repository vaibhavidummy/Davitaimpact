package in.davita.impact.erp.appointment.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.davita.impact.erp.appointment.model.Appointment;
import in.davita.impact.erp.appointment.repository.AppointmentRepository;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService{
	
	@Autowired
	AppointmentRepository appointmentRepository;

	@Override
	public Appointment addAppointment(Appointment appointment) {
		return appointmentRepository.save(appointment);
	}

	@Override
	public Appointment UpdateAppointment(Appointment appointmentDtls) {
		// TODO Auto-generated method stub
		return appointmentRepository.save(appointmentDtls);
	}

	@Override
	public Optional<Appointment> getAppointment(String id) {
		return appointmentRepository.findById(id);
	}

	@Override
	public List<Appointment> getAllAppointments() {
		return appointmentRepository.findAll();
	}

}
