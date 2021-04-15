package com.citiustech.pms.procedure.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.citiustech.pms.procedure.model.ProcedureDetail;
import com.citiustech.pms.procedure.model.ProcedureMain;
import com.citiustech.pms.procedure.model.ProcedureMaster;

@Service
public interface ProcedureDetailService {

	 ProcedureMain addProcedure(ProcedureMain procedureDetail);
	 
	  List<ProcedureMaster> getAllProcedure();
	  
	  ProcedureMain getProcedureByVisitId(ProcedureMain procedureDetail);
	  
	  ProcedureMain getProcedureDescription(ProcedureDetail procedureDetailDesc);

}