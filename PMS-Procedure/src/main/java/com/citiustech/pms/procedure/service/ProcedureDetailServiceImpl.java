package com.citiustech.pms.procedure.service;

import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citiustech.pms.procedure.model.ProcedureDetail;
import com.citiustech.pms.procedure.model.ProcedureMaster;
import com.citiustech.pms.procedure.repository.ProcedureMasterRepo;
import com.citiustech.pms.procedure.repository.ProdecureDetailRepository;

@Service
public class ProcedureDetailServiceImpl implements ProcedureDetailService {

	@Autowired
	private ProdecureDetailRepository prodecureDetailRepository;
	
	@Autowired
	private ProcedureMasterRepo procedureMasterRepo;
	
	@Override
	@Transactional
	public ProcedureDetail addProcedure(ProcedureDetail procedureDetail) {
		
		 prodecureDetailRepository.save(procedureDetail);
		 if(procedureDetail.getName().equals("A")) {
			 throw new IllegalArgumentException();
		 }
		 return procedureDetail;
	}

	@Override
    @Transactional
    public List<ProcedureMaster> getAllProcedure()
    {
        return procedureMasterRepo.findAll();
    }
		
	@Override
    @Transactional
	public ProcedureDetail getProcedureByVisitId(ProcedureDetail procedureDetail)
	{
		ProcedureDetail procedureGetById = prodecureDetailRepository.save(procedureDetail);
	        if (Objects.isNull(procedureGetById.getId()))
	            throw new IllegalArgumentException();
	        else if(Objects.isNull(procedureGetById.getName()))
	            throw new IllegalArgumentException();
	        return procedureGetById;
	}
}
