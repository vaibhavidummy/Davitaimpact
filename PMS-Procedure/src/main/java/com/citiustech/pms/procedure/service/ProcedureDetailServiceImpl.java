package com.citiustech.pms.procedure.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citiustech.pms.procedure.model.ProcedureDetail;
import com.citiustech.pms.procedure.repository.ProdecureDetailRepository;

@Service
public class ProcedureDetailServiceImpl implements ProcedureDetailService {

	@Autowired
	private ProdecureDetailRepository prodecureDetailRepository;
	
	@Override
	@Transactional
	public ProcedureDetail addProcedure(ProcedureDetail procedureDetail) {
		
		 prodecureDetailRepository.save(procedureDetail);
		 if(procedureDetail.getName().equals("A")) {
			 throw new IllegalArgumentException();
		 }
		 return procedureDetail;
	}

}
