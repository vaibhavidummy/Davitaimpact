package com.citiustech.pms.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.citiustech.pms.diagnosis.controller.PmsDiagnosisController;
import com.citiustech.pms.diagnosis.model.Diagnosis;
import com.citiustech.pms.diagnosis.model.DiagnosisModel;
import com.citiustech.pms.diagnosis.model.DiagnosisSuccess;
import com.citiustech.pms.diagnosis.service.PmsDiagnosisServiceImpl;


@ExtendWith(MockitoExtension.class)
public class PmsDiagnosisControllerTest {

	@InjectMocks
	private PmsDiagnosisController pmsDiagnosisController;
	
	@Mock
	private Diagnosis diagnosis;
	
	@Mock
	private PmsDiagnosisServiceImpl pmsDiagnosisServiceImpl;
	
	@Mock
	private DiagnosisModel diagnosisModel;
	
	
	@Test
	public void GetAllDiagnosis()
	 {
		DiagnosisSuccess DiagnosisSuccessExp = new DiagnosisSuccess("abc" , true,null , null);
		when(pmsDiagnosisServiceImpl.getAllDiagnosis()).thenReturn(DiagnosisSuccessExp);
		DiagnosisSuccess diagnosisSuccessActual =  pmsDiagnosisController.getAllDiagnosis().getBody();
		assertEquals(diagnosisSuccessActual.getMessage(), DiagnosisSuccessExp.getMessage());
	 }
	
	@Test
	public void getDiagnosisDetailDesc()
	 {
		DiagnosisSuccess DiagnosisSuccess = new DiagnosisSuccess();
		DiagnosisSuccess.setMessage("Added Successfully");
		
		when(pmsDiagnosisServiceImpl.getDiagnosisDescription(diagnosisModel)).thenReturn(DiagnosisSuccess);
		DiagnosisSuccess diagnosisSuccessActual =  pmsDiagnosisController.getDiagnosisDetailDesc(diagnosisModel).getBody();
		assertEquals("Added Successfully", diagnosisSuccessActual.getMessage());
	 }
	
	@Test
	public void getDiagnosisByVisitId()
	 {
		String str ="A001";
		DiagnosisSuccess DiagnosisSuccessExp = new DiagnosisSuccess();
		
		when(pmsDiagnosisServiceImpl.getProcedureByVisitId(str)).thenReturn(DiagnosisSuccessExp);
		DiagnosisSuccess diagnosisSuccessActual =  pmsDiagnosisController.getDiagnosisByVisitId(str).getBody();
		assertEquals(DiagnosisSuccessExp, diagnosisSuccessActual);
	 }
	
	@Test
	public void addDiagnosis()
	 {
		Diagnosis diagnosisExp = new Diagnosis();
		diagnosisExp.setName("A");
		diagnosisExp.setDiagonosisId("101");
		diagnosisExp.setDescription("A");
		diagnosisExp.setSeqId(101L);
		
		when(pmsDiagnosisServiceImpl.addDiagnosis(diagnosis)).thenReturn(diagnosisExp);
		
		Diagnosis diagnosisActual = pmsDiagnosisController.addDiagnosis(diagnosis).getBody();
		
		assertEquals(diagnosisExp, diagnosisActual);
	 }
}
