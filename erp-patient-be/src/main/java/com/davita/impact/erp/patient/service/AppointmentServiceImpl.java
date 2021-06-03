package com.davita.impact.erp.patient.service;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.management.ServiceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.davita.impact.erp.patient.exception.EntityDetailsFoundException;
import com.davita.impact.erp.patient.exception.EntityDetailsNotFoundException;
import com.davita.impact.erp.patient.feign.client.InboxServiceClient;
import com.davita.impact.erp.patient.model.Appointment;
import com.davita.impact.erp.patient.model.AppointmentStatistics;
import com.davita.impact.erp.patient.model.Inbox;
import com.davita.impact.erp.patient.model.Status;
import com.davita.impact.erp.patient.repository.AppointmentRepository;

@Service
@Transactional(rollbackFor = Exception.class, noRollbackFor = FileNotFoundException.class)
public class AppointmentServiceImpl implements AppointmentService{
	
	@Autowired
	AppointmentRepository appointmentRepository;
	
	@Autowired
	InboxServiceClient inboxServiceClient;

	@Override
	public Appointment addAppointment(Appointment appointment){
		Appointment appointmentStatus=null;
		if(checkForExistingAppointment(appointment) == false)
		{	
		appointmentStatus = appointmentRepository.save(appointment);
		}
		return appointmentStatus;
	}
	

	@Override
	public Appointment updateAppointment(Appointment appointment,String appointmentId) {
		
		Appointment appointmentStatus=null;
		Optional<Appointment> oldAppointment = appointmentRepository.findById(appointmentId);
        if(oldAppointment.isPresent()==false){
        	throw new EntityDetailsNotFoundException("Appointment not present for given appointment Id",
					new Object[]{appointmentId });
		}
		 appointmentStatus=appointmentRepository.save(appointment);
        
		return appointmentStatus;
	}

	@Override
	public Optional<Appointment> getAppointment(String appointmentId) {
		Optional<Appointment> result = null;
		if(!appointmentId.isEmpty()) {
			result=appointmentRepository.findById(appointmentId);
			if (!result.isPresent()) {
				throw new EntityDetailsNotFoundException("Appointment not found",
						new Object[]{ appointmentId });
			}
			
		}
		return result;
	}
	

	@Override
	public List<Appointment> getUserAppointmentforDate(String userId, LocalDate date) {
		List<Appointment> result=null;
		result=appointmentRepository.findAllByUserIdByDate(userId,date);
		if(result.isEmpty()) {
			throw new EntityDetailsNotFoundException("Appointment not present for given userid and date",
					new Object[]{ userId, date});
		}
			
		return result;
	}

	
	@Override
	public List<AppointmentStatistics> getUserAppointmentBetweenDate(String userId, LocalDate startDate, LocalDate endDate) {
		List<AppointmentStatistics> result=null;
		result=appointmentRepository.findAllByUserIdBetweenDate(userId,startDate,endDate);
		if(result.isEmpty()) {
			throw new EntityDetailsNotFoundException("Appointment not present for given userid and between given date",
					new Object[]{ userId, startDate,endDate});
		}
		return result;
	}
	
	
	public String updateAppointmentStatus(String appointmentId,Status status) {
		Appointment result=null;
		Optional<Appointment> appointmentStatus = appointmentRepository.findById(appointmentId);
            if(appointmentStatus.isPresent()==false){
            	throw new EntityDetailsNotFoundException("Appointment not present for given appointment Id",
    					new Object[]{appointmentId });
    		}
           appointmentStatus.get().setStatus(status);
           result= appointmentRepository.save(appointmentStatus.get());
           return result.getAppointmentId();
        }
	  
	@Override
	public Boolean checkForExistingAppointment(Appointment appointment) {
		Appointment result=null;
		if(appointment!=null)
		{
			result=appointmentRepository.checkForExistingAppointment(appointment.getPhysicianId(), appointment.getDate(), appointment.getStartTime(), appointment.getEndTime());

			if (result!=null) {
				throw new EntityDetailsFoundException("Appointment already booked for physician for given date and time",
    					new Object[]{result });
			}
		}
		return false;
	}
	
	
}
