package in.davita.impact.erp.patient.service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.davita.impact.erp.patient.model.Appointment;
import in.davita.impact.erp.patient.repository.AppointmentRepository;

@Service
@Transactional(rollbackFor = Exception.class, noRollbackFor = FileNotFoundException.class)
public class AppointmentServiceImpl implements AppointmentService{
	
	@Autowired
	AppointmentRepository appointmentRepository;

	@Override
	public Appointment addAppointment(Appointment appointment){
		return appointmentRepository.save(appointment);
	}

	@Override
	public Appointment UpdateAppointment(Appointment appointment) {
		return appointmentRepository.save(appointment);
	}

	@Override
	public Optional<Appointment> getAppointment(String appointmentId) {
		return appointmentRepository.findById(appointmentId);
	}

	@Override
	public List<Appointment> getAllAppointments() {
		return appointmentRepository.findAll();	}

}
