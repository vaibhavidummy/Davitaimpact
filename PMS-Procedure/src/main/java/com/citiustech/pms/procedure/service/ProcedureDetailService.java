package com.citiustech.pms.procedure.service;

import org.springframework.stereotype.Service;

import com.citiustech.pms.procedure.model.ProcedureDetail;
import com.citiustech.pms.procedure.model.ProcedureMain;
import com.citiustech.pms.procedure.model.ProcedureSuccess;

@Service
public interface ProcedureDetailService {

	 ProcedureMain addProcedure(ProcedureMain procedureDetail);
	 
	 ProcedureSuccess getProcedureByVisitId(String patientVisitId);
	  
	  ProcedureSuccess getAllProcedure();
	  
	  ProcedureSuccess getProcedureDescription(ProcedureDetail procedureDetailDesc);

}