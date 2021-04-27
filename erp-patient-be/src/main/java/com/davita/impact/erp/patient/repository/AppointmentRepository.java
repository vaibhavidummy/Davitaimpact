package com.davita.impact.erp.patient.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.davita.impact.erp.patient.model.Appointment;
import com.davita.impact.erp.patient.model.AppointmentStatistics;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, String>{
	
	@Query("FROM Appointment a WHERE (a.physicianId= :userId OR a.patientId= :userId) and a.date= :date and a.status IN ('ACCEPTED','PENDING') " )
	public List<Appointment> findAllByUserIdByDate(String userId, LocalDate date);

	@Query("SELECT new com.davita.impact.erp.patient.model.AppointmentStatistics(a.date,COUNT(a)) FROM Appointment a WHERE (a.physicianId= :userId OR a.patientId= :userId) and a.date BETWEEN :startDate and :endDate and a.status IN ('ACCEPTED','PENDING') "
			+ "GROUP BY a.date ORDER BY a.date" )
	public List<AppointmentStatistics> findAllByUserIdBetweenDate(String userId, LocalDate startDate, LocalDate endDate);
	
	@Query("FROM Appointment a WHERE a.physicianId= :physicianId and a.date= :date and a.startTime=:startTime and a.endTime=:endTime and a.status IN ('ACCEPTED','PENDING') " )
	public Appointment checkForExistingAppointment(String physicianId, LocalDate date, LocalTime startTime, LocalTime endTime );
	

	/*
	 * @Query("SELECT a.startTime FROM Appointment a WHERE a.physicianId= :physicianId and a.date= :date and a.status IN ('ACCEPTED','PENDING') "
	 * ) public List<Date> findAllByPhysicianIdByDate(String physicianId, LocalDate
	 * date);
	 */
	

}
