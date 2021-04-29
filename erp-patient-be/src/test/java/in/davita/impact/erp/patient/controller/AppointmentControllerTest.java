package in.davita.impact.erp.patient.controller;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.mockito.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.davita.impact.erp.patient.controller.AppointmentController;
import com.davita.impact.erp.patient.model.Appointment;
import com.davita.impact.erp.patient.model.Status;
import com.davita.impact.erp.patient.service.AppointmentService;
import com.davita.impact.erp.patient.service.AppointmentServiceImpl;


@WebMvcTest(AppointmentController.class)
class AppointmentControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AppointmentServiceImpl appointmentService;	
	
	  
	  @Test 
	  public void getAppointment() throws Exception 
	  {
			 Optional<Appointment> mockAppointment=Optional.of(new Appointment("1", "10","john", "140","Smith",
						 		LocalDate.of(2021, 12, 12), Date.valueOf("9:00"), Date.valueOf("9:30"),"Meeting title", Status.PENDING, "New Appointment", null, null));
		  
			 String appointmentId=mockAppointment.get().getAppointmentId();
			 Mockito.when(appointmentService.getAppointment(appointmentId)).thenReturn(mockAppointment);
		  
			 RequestBuilder request = MockMvcRequestBuilders
					 				 .get("/appointment/appointmentId") .accept(MediaType.APPLICATION_JSON);
		  
			 MvcResult result = mockMvc.perform(request) 
					  	.andExpect(status().isCreated())
						.andExpect(content().
						  json("{\"appointmentId\":\"1\" ,\"physicianId\":\"10\",\"physicianName\": \"John\",\"patientId\":140,\"patientName\": \"Smith\","
						  + "\"date\":\"2021-12-12\"\"startTime\": \"9:00:00\"," +
						  "\"endTime\": \"9:30:00\"," + "\"status\": \"PENDING\"," +
						  "\"description\": \"New appointment\"," + "\"reasonForChange\": null," +
						  "\"patientVisitDetailId\": null}")) 
						.andReturn();
	  
	  }
	 
}
