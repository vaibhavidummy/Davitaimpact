package com.citiustech.pms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.citiustech.pms.diagnosis.exception.DiagnosisException;
import com.citiustech.pms.diagnosis.model.Diagnosis;
import com.citiustech.pms.diagnosis.model.DiagnosisDetails;
import com.citiustech.pms.diagnosis.model.DiagnosisMaster;
import com.citiustech.pms.diagnosis.model.DiagnosisModel;
import com.citiustech.pms.diagnosis.model.DiagnosisSuccess;
import com.citiustech.pms.diagnosis.repository.DiagnosisMasterRepo;
import com.citiustech.pms.diagnosis.repository.DiagnosisRepo;
import com.citiustech.pms.diagnosis.service.PmsDiagnosisServiceImpl;


@ExtendWith(MockitoExtension.class)
public class PmsDiagnosisServiceImplTest {

	@InjectMocks
	private PmsDiagnosisServiceImpl pmsDiagnosisServiceImpl;
	
	@Mock
	private DiagnosisRepo diagnosisRepo;
	
	@Mock
	private DiagnosisMasterRepo diagnosisMasteRepo;
	
	@Mock
	private Diagnosis diagnosis;
	
	
	@Test
	public void retrieveAllItemsBasic() {
		DiagnosisMaster diagnosisMaster = new DiagnosisMaster();
		diagnosisMaster.setDiagnosisId("A1814");
		diagnosisMaster.setDiagnosisName("Tuberculosis of prostate");
		
		List<DiagnosisMaster> list = Arrays.asList(diagnosisMaster);
		doReturn(list).when(diagnosisMasteRepo).findAll();
		assertEquals(1,pmsDiagnosisServiceImpl.getAllDiagnosis().getDiagnosisMaster().size());
	}
	
	@Test
	public void getAllDiagnosisException() {
		DiagnosisMaster diagnosisMaster = new DiagnosisMaster();
		diagnosisMaster.setDiagnosisId("");
		
		List<DiagnosisMaster> list = new ArrayList<DiagnosisMaster>();
		list.add(diagnosisMaster);
		
		Assertions.assertThrows(DiagnosisException.class, () -> {
			pmsDiagnosisServiceImpl.getAllDiagnosis();
		  });
	}
	
	@Test
	public void getDiagnosisDescription() {
		
		DiagnosisModel diagnosisModel = new DiagnosisModel();
		diagnosisModel.setPatient_visit_id("P101");
		
		List<DiagnosisDetails> diagnosisDetailslist = new ArrayList<DiagnosisDetails>();
		DiagnosisDetails diagnosisDetails = new DiagnosisDetails();
		diagnosisDetails.setId("P101");
		diagnosisDetailslist.add(diagnosisDetails);
		diagnosisModel.setDiagnosis_details(diagnosisDetailslist);
		
		boolean actual = pmsDiagnosisServiceImpl.getDiagnosisDescription(diagnosisModel).isSuccessFlag();
		
		assertEquals(Boolean.TRUE, actual);
	}
	
	@Test
	public void getDiagnosisDescriptionPatientVisitID() {
		
		DiagnosisModel diagnosisModel = new DiagnosisModel();
		diagnosisModel.setPatient_visit_id("");
		
		Assertions.assertThrows(DiagnosisException.class, () -> {
			pmsDiagnosisServiceImpl.getDiagnosisDescription(diagnosisModel);
		  });
	}
	
	@Test
	public void getDiagnosisDescriptionException() {
		
		DiagnosisModel diagnosisModel = new DiagnosisModel();
		diagnosisModel.setPatient_visit_id("P101");
		List<DiagnosisDetails> diagnosisList = new ArrayList<DiagnosisDetails>();
		diagnosisModel.setDiagnosis_details(diagnosisList);
		
		Assertions.assertThrows(DiagnosisException.class, () -> {
			pmsDiagnosisServiceImpl.getDiagnosisDescription(diagnosisModel);
		  });
	}
	
	@Test
	public void getDiagnosisByVisitId() {
		
		String str ="P101";
		List<Diagnosis> getPatientVisitId = new ArrayList<>();
		Diagnosis diagnosis = new Diagnosis();
		diagnosis.setPatientVisitId("P101");
		diagnosis.setName("ABC");
		getPatientVisitId.add(diagnosis);
		
		DiagnosisSuccess diagnosisExp = new DiagnosisSuccess();
		diagnosisExp.setSuccessFlag(Boolean.TRUE);
		diagnosisExp.setDiagnosis(getPatientVisitId);
		
		when(diagnosisRepo.findByPatientVisitId(str)).thenReturn(getPatientVisitId);

		DiagnosisSuccess ProcedureActual = pmsDiagnosisServiceImpl.getProcedureByVisitId(str);

		assertEquals(diagnosisExp, ProcedureActual);
	}
	
	@Test
	public void getDiagnosisByVisitIdCheckIsEmpty() {
		
		String str = "";
		
		pmsDiagnosisServiceImpl.getProcedureByVisitId(str);
	}
	
	@Test
	public void getDiagnosisByVisitIdException() {
		
		String str = "P101";
		List<Diagnosis> getPatientVisitId = new ArrayList<>();
		Diagnosis diagnosis = new Diagnosis();
		diagnosis.setPatientVisitId(null);
		getPatientVisitId.add(diagnosis);
		
		Assertions.assertThrows(DiagnosisException.class, () -> {
			pmsDiagnosisServiceImpl.getProcedureByVisitId(str);
		  });
	}
	
	
	@Test
	public void addDiagnsisImpl() {
		
		Diagnosis diagnosisExp = new Diagnosis();
		diagnosisExp.setName("A");
		diagnosisExp.setDiagonosisId("101");
		diagnosisExp.setDescription("A");
		diagnosisExp.setCreatedBy("admin");
		diagnosisExp.setPatientVisitId("P102");
		
		when(diagnosisRepo.save(diagnosisExp)).thenReturn(diagnosisExp);
		
	 	Diagnosis diagnosisActual = pmsDiagnosisServiceImpl.addDiagnosis(diagnosisExp);
	 
		assertEquals(diagnosisExp , diagnosisActual);
	}
	
	@Test
	public void checkAddDiagnosisId() {
		
		Diagnosis procedureMainExp = new Diagnosis();
		procedureMainExp.setDiagonosisId(null);
		
		Assertions.assertThrows(DiagnosisException.class, () -> {
			pmsDiagnosisServiceImpl.addDiagnosis(procedureMainExp);
		  });	
	 
	}
	
	@Test
	public void checkAddDiagnosisIdEmpty() {
		
		Diagnosis procedureMainExp = new Diagnosis();
		procedureMainExp.setDiagonosisId(null);
		procedureMainExp.setDiagonosisId("");
		
		Assertions.assertThrows(DiagnosisException.class, () -> {
			pmsDiagnosisServiceImpl.addDiagnosis(procedureMainExp);
		  });	
	 
	}

	
	@Test
	public void checkAddDiagnosisName() {

		Diagnosis procedureMainExp = new Diagnosis();
		procedureMainExp.setDiagonosisId("P101");
		procedureMainExp.setName(null);

		Assertions.assertThrows(DiagnosisException.class, () -> {
			pmsDiagnosisServiceImpl.addDiagnosis(procedureMainExp);
		  });	

	}
	
	@Test
	public void checkAddDiagnosisNameEmpty() {

		Diagnosis procedureMainExp = new Diagnosis();
		procedureMainExp.setDiagonosisId("P101");
		procedureMainExp.setName("");

		Assertions.assertThrows(DiagnosisException.class, () -> {
			pmsDiagnosisServiceImpl.addDiagnosis(procedureMainExp);
		  });	

	}
	
	@Test
	public void checkAddDiagnosisDescription() {
		
		Diagnosis procedureMainExp = new Diagnosis();
		procedureMainExp.setDiagonosisId("P101");
		procedureMainExp.setName("A");
		procedureMainExp.setDescription(null);
		
		Assertions.assertThrows(DiagnosisException.class, () -> {
			pmsDiagnosisServiceImpl.addDiagnosis(procedureMainExp);
		  });		
	}
	
	@Test
	public void checkAddDiagnosisDescriptionEmpty() {
		
		Diagnosis diagnosisMainExp = new Diagnosis();
		diagnosisMainExp.setDiagonosisId("P101");
		diagnosisMainExp.setName("A");
		diagnosisMainExp.setDescription("");
		
		Assertions.assertThrows(DiagnosisException.class, () -> {
			pmsDiagnosisServiceImpl.addDiagnosis(diagnosisMainExp);
		  });		
	}
		
}
