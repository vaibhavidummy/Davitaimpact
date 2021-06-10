package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.citiustech.pms.procedure.exception.ProcedureException;
import com.citiustech.pms.procedure.model.ProcedureDetail;
import com.citiustech.pms.procedure.model.ProcedureDetailsDescrpition;
import com.citiustech.pms.procedure.model.ProcedureMain;
import com.citiustech.pms.procedure.model.ProcedureMaster;
import com.citiustech.pms.procedure.model.ProcedureSuccess;
import com.citiustech.pms.procedure.repository.ProcedureMasterRepo;
import com.citiustech.pms.procedure.repository.ProdecureDetailRepository;
import com.citiustech.pms.procedure.service.ProcedureDetailServiceImpl;


@ExtendWith(MockitoExtension.class)
public class ProcedureDetailServiceImplTest {

	@InjectMocks
	private ProcedureDetailServiceImpl procedureDetailServiceImpl;

	@Mock
	private ProdecureDetailRepository prodecureDetailRepository;
	
	@Mock 
	private ProcedureMasterRepo procedureMasterRepo;
	
	@Mock
	private ProcedureMain procedureMain;
	
	@Test
	public void retrieveAllItems_basic() {
		//  Given 
		List<ProcedureMaster> list =  Arrays.asList(new ProcedureMaster("0016070" ,"Bypass Cerebral Ventricle to Nasopharynx with Autologous Tissue Substitute"));
		// when
		doReturn(list).when(procedureMasterRepo).findAll();
		// then
		assertEquals(1,procedureDetailServiceImpl.getAllProcedure().getProcedureMaster().size());
	}
	
	@Test
	public void retrieveAllItems_basicException() {
		//  Given 
//		List<ProcedureMaster> list =  Arrays.asList(new ProcedureMaster("",""));
		// when
	//	when(procedureMasterRepo.findAll()).thenThrow(ProcedureException.class);
		// then
		//assertThrows(ProcedureException.class, procedureDetailServiceImpl.getAllProcedure());
		
		Assertions.assertThrows(ProcedureException.class, () -> {
			procedureDetailServiceImpl.getAllProcedure();
		  });
	}
	
	@Test
	public void getProcedureDescription() {
	//Given
		ProcedureDetail procedureDetailDesc = new ProcedureDetail();
		procedureDetailDesc.setPatient_visit_id("101");
		
		List<ProcedureDetailsDescrpition> procedureMainList = new ArrayList<>();
		ProcedureDetailsDescrpition procedureDetailsDescrpition = new ProcedureDetailsDescrpition();
		procedureDetailsDescrpition.setId("101");
		procedureMainList.add(procedureDetailsDescrpition);
		procedureDetailDesc.setProcedure_details(procedureMainList);
		
		boolean actual = procedureDetailServiceImpl.getProcedureDescription(procedureDetailDesc).isSuccessFlag(); 
	//	verify(repository.saveAll("ABC"));
//		Mockito.verify(repository, Mockito.times(1));
	
		assertEquals(Boolean.TRUE, actual);
		
	}
	
	@Test
	public void getProcedureDescriptionException() {
	//Given
		ProcedureDetail procedureDetailDesc = new ProcedureDetail();
		procedureDetailDesc.setPatient_visit_id("101");
		
		List<ProcedureDetailsDescrpition> procedureMainList = new ArrayList<>();
		
		procedureDetailDesc.setProcedure_details(procedureMainList);
		
		//boolean actual = procedureDetailServiceImpl.getProcedureDescription(procedureDetailDesc).isSuccessFlag(); 

		Assertions.assertThrows(ProcedureException.class, () -> {
			procedureDetailServiceImpl.getProcedureDescription(procedureDetailDesc);
		  });
	}
	
	@Test
	public void getProcedureDescriptionExceptionPatient() {
		ProcedureDetail procedureDetail = new ProcedureDetail();
		procedureDetail.setPatient_visit_id("");
		
		Assertions.assertThrows(ProcedureException.class, () -> {
			procedureDetailServiceImpl.getProcedureDescription(procedureDetail);
		  });
	}
	
	@Test
	public void getProcedureByVisitId() {

		String str = "P101";

		List<ProcedureMain> getPatientVisitId = new ArrayList<>();

		ProcedureMain procedureMain = new ProcedureMain();
		procedureMain.setProcedureId("1");
		procedureMain.setName("ABC");
		getPatientVisitId.add(procedureMain);

		ProcedureSuccess procedureExp = new ProcedureSuccess();
		procedureExp.setSuccessFlag(Boolean.TRUE);
		procedureExp.setProcedureMain(getPatientVisitId);

		when(prodecureDetailRepository.findByPatientVisitId(str)).thenReturn(getPatientVisitId);

		ProcedureSuccess ProcedureActual = procedureDetailServiceImpl.getProcedureByVisitId(str);

		assertEquals(procedureExp, ProcedureActual);
	}
	 
	
	@Test
	public void getProcedureByVisitIdCheckIsEmpty() {
		
		String str = "";

		boolean ProcedureActual = procedureDetailServiceImpl.getProcedureByVisitId(str).isSuccessFlag();

		assertEquals(Boolean.TRUE, ProcedureActual);
	}
	
	@Test
	public void getProcedureByVisitIdException() {
		
		String str = "P101";
		List<ProcedureMain> getPatientVisitId = new ArrayList<>();
		
		ProcedureMain procedureMain = new ProcedureMain();
		procedureMain.setProcedureId("");
		getPatientVisitId.add(procedureMain);
		
		Assertions.assertThrows(ProcedureException.class, () -> {
			procedureDetailServiceImpl.getProcedureByVisitId(str);
		  });
	}
	
	@Test
	public void addProcedureImpl() {
		
		ProcedureMain procedureMainExp = new ProcedureMain();
		procedureMainExp.setName("A");
		procedureMainExp.setProcedureId("101");
		procedureMainExp.setDescription("A");
		procedureMainExp.setCreatedBy("admin");
		procedureMainExp.setPatientVisitId("P102");
		
		when(prodecureDetailRepository.save(procedureMainExp)).thenReturn(procedureMainExp);
		
	 	ProcedureMain procedureMainActual = procedureDetailServiceImpl.addProcedure(procedureMainExp);
	 
		assertEquals(procedureMainExp , procedureMainActual);
	}
	
	@Test
	public void checkAddProcedureProcedureId() {
		
		ProcedureMain procedureMainExp = new ProcedureMain();
		procedureMainExp.setProcedureId(null);
		
		Assertions.assertThrows(ProcedureException.class, () -> {
			procedureDetailServiceImpl.addProcedure(procedureMainExp);
		  });	
	 
	}
	
	@Test
	public void checkAddProcedureProcedureIdempty() {
		
		ProcedureMain procedureMainExp = new ProcedureMain();
		procedureMainExp.setProcedureId("");
		
		Assertions.assertThrows(ProcedureException.class, () -> {
			procedureDetailServiceImpl.addProcedure(procedureMainExp);
		  });	
	 
	}
	
	@Test
	public void checkAddProcedureName() {

		ProcedureMain procedureMainExp = new ProcedureMain();
		procedureMainExp.setProcedureId("P101");
		procedureMainExp.setName(null);

		Assertions.assertThrows(ProcedureException.class, () -> {
			procedureDetailServiceImpl.addProcedure(procedureMainExp);
		});

	}
	
	@Test
	public void checkAddProcedureNameEmpty() {

		ProcedureMain procedureMainExp = new ProcedureMain();
		procedureMainExp.setProcedureId("P101");
		procedureMainExp.setName("");

		Assertions.assertThrows(ProcedureException.class, () -> {
			procedureDetailServiceImpl.addProcedure(procedureMainExp);
		});

	}

	
	@Test
	public void checkAddProcedureDescription() {
		
		ProcedureMain procedureMainExp = new ProcedureMain();
		procedureMainExp.setProcedureId("P101");
		procedureMainExp.setName("A");
		procedureMainExp.setDescription(null);
		
		Assertions.assertThrows(ProcedureException.class, () -> {
			procedureDetailServiceImpl.addProcedure(procedureMainExp);
		  });	
	}
	
	@Test
	public void checkAddProcedureDescriptionEmpty() {
		
		ProcedureMain procedureMainExp = new ProcedureMain();
		procedureMainExp.setProcedureId("P101");
		procedureMainExp.setName("A");
		procedureMainExp.setDescription("");
		
		Assertions.assertThrows(ProcedureException.class, () -> {
			procedureDetailServiceImpl.addProcedure(procedureMainExp);
		  });	
	}

}