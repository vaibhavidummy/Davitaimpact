package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.citiustech.pms.procedure.controller.ProcedureController;
import com.citiustech.pms.procedure.model.ProcedureDetail;
import com.citiustech.pms.procedure.model.ProcedureMain;
import com.citiustech.pms.procedure.model.ProcedureSuccess;
import com.citiustech.pms.procedure.service.ProcedureDetailServiceImpl;


@ExtendWith(MockitoExtension.class)
public class ProcedureControllerTest {

	@InjectMocks
	private ProcedureController procedureController;
	
	@Mock
	private ProcedureDetailServiceImpl procedureDetailServiceImpl;
	
	@Mock
	private ProcedureDetail procedureDetail;
	
	@Mock
	private ProcedureMain procedureMain ;
	
	@Test
	public void GetAllProcedure()
	 {
		ProcedureSuccess ProcedureSuccessExp = new ProcedureSuccess("abc" , true,null , null);
//		doReturn(ProcedureSuccessExp).when(procedureDetailServiceImpl).getAllProcedure();		
		when(procedureDetailServiceImpl.getAllProcedure()).thenReturn(ProcedureSuccessExp);
		ProcedureSuccess ProcedureSuccessActual =  procedureController.getAllProcedure().getBody();
		assertEquals(ProcedureSuccessExp.getMessage(), ProcedureSuccessActual.getMessage());
	 }
	
	
	@Test
	public void getProcedureDetailDesc() { 
//	 ProcedureDetail procedureDetail = new ProcedureDetail();
//		procedureDetail.setPatient_visit_id("101"); 
//		procedureDetailsDescrpition = new ProcedureDetailsDescrpition(); 
//		procedureDetailsDescrpition.setId("0016070"); 
//		procedureDetailsDescrpition.setName("Bypass"); 
//		procedureDetailsDescrpition.setDescription("Bypass"); 
//		List<ProcedureDetailsDescrpition> list = new ArrayList<ProcedureDetailsDescrpition>(); 
//		list.add(procedureDetailsDescrpition); 
//		procedureDetail.setProcedure_details(list);

		ProcedureSuccess procedureSuccess = new ProcedureSuccess();
		procedureSuccess.setMessage("Added Successfully");

		when(procedureDetailServiceImpl.getProcedureDescription(procedureDetail)).thenReturn(procedureSuccess);

		ProcedureSuccess procedureDetailActual = procedureController.getProcedureDetailDesc(procedureDetail).getBody();

		assertEquals("Added Successfully", procedureDetailActual.getMessage());
	}

	@Test
	public void addProcedure()
	 {
		ProcedureMain ProcedureMainExp = new ProcedureMain();
		ProcedureMainExp.setName("A");
		ProcedureMainExp.setProcedureId("101");
		ProcedureMainExp.setDescription("A");
		ProcedureMainExp.setSeqId(101L);
		
		when(procedureDetailServiceImpl.addProcedure(procedureMain)).thenReturn(ProcedureMainExp);
		
		ProcedureMain procedureMainActual = procedureController.addProcedure(procedureMain).getBody();
		
		assertEquals(ProcedureMainExp, procedureMainActual);
	 }
	
	@Test
	public void getProcedureByVisitId()
	 {
		String str ="P101";
		
		ProcedureSuccess ProcedureExp = new ProcedureSuccess();
		
		 when(procedureDetailServiceImpl.getProcedureByVisitId(str)).thenReturn(ProcedureExp);
		 
		 ProcedureSuccess ProcedureActual = procedureController.getProcedureByVisitId(str).getBody();
		 
		 assertEquals(ProcedureExp, ProcedureActual);
	 }
	
}