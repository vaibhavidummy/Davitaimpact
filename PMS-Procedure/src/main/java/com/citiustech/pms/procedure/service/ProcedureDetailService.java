package com.citiustech.pms.procedure.service;

import org.springframework.stereotype.Service;

import com.citiustech.pms.procedure.model.ProcedureDetail;

@Service
public interface ProcedureDetailService {

	  ProcedureDetail addProcedure(ProcedureDetail procedureDetail);
}
