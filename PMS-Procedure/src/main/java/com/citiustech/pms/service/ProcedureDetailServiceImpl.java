package com.citiustech.pms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citiustech.pms.model.ProcedureDetail;
import com.citiustech.pms.repository.ProdecureDetailRepository;

@Service
public class ProcedureDetailServiceImpl implements ProcedureDetailService {

	@Autowired
	private ProdecureDetailRepository prodecureDetailRepository;
	
	@Override
	public ProcedureDetail addProcedure(ProcedureDetail procedureDetail) {
		
		 prodecureDetailRepository.save(procedureDetail);
		 return procedureDetail;
	}
	
}
