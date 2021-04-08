package com.citiustech.pms.service;

import org.springframework.stereotype.Service;

import com.citiustech.pms.model.ProcedureDetail;

@Service
public interface ProcedureDetailService {

	  ProcedureDetail addProcedure(ProcedureDetail procedureDetail);
}
