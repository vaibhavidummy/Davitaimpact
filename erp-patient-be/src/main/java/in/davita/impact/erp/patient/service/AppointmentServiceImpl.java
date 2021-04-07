package in.davita.impact.erp.patient.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.davita.impact.erp.patient.model.Appointment;
import in.davita.impact.erp.patient.repository.AppointmentRepository;

@Service
public class AppointmentServiceImpl implements AppointmentService{
	
	@Autowired
	AppointmentRepository appointmentRepository;

	@Override
	public Appointment addAppointment(Appointment Appointment) {
		return appointmentRepository.save(Appointment);
	}

	@Override
	public Appointment UpdateAppointment(Appointment appointment) {
		return appointmentRepository.save(appointment);
	}

	@Override
	public Optional<Appointment> getAppointment(String AppointmentId) {
		return appointmentRepository.findById(AppointmentId);
	}

	@Override
	public List<Appointment> getAllAppointments() {
		return appointmentRepository.findAll();	}

}
