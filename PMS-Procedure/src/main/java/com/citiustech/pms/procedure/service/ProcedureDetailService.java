package com.citiustech.pms.procedure.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.citiustech.pms.procedure.model.ProcedureDetail;
import com.citiustech.pms.procedure.model.ProcedureMaster;

@Service
public interface ProcedureDetailService {

	 ProcedureDetail addProcedure(ProcedureDetail procedureDetail);
	 
	  List<ProcedureMaster> getAllProcedure();
	  
	  ProcedureDetail getProcedureByVisitId(ProcedureDetail procedureDetail);
}