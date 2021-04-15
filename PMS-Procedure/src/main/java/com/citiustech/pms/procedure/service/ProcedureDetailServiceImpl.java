package com.citiustech.pms.procedure.service;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.citiustech.pms.procedure.model.ProcedureDetail;
import com.citiustech.pms.procedure.model.ProcedureMain;
import com.citiustech.pms.procedure.model.ProcedureMaster;
import com.citiustech.pms.procedure.repository.ProcedureMasterRepo;
import com.citiustech.pms.procedure.repository.ProdecureDetailRepository;

@Service
public class ProcedureDetailServiceImpl implements ProcedureDetailService {
	
	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ProcedureDetailServiceImpl.class);
	
	@Autowired
	private ProdecureDetailRepository prodecureDetailRepository;
	
	@Autowired
	private ProcedureMasterRepo procedureMasterRepo;
	
	@Autowired(required = false)
	private ProcedureMain procedureMain;

	@Override
	@Transactional(rollbackFor = Exception.class , noRollbackFor = IllegalArgumentException.class)
	public ProcedureMain addProcedure(ProcedureMain procedureDetail) {
		
		 prodecureDetailRepository.save(procedureDetail);
		 if(procedureDetail.getName().equals("A")) {
			 throw new IllegalArgumentException();
		 }
		 return procedureDetail;
	}

	@Override
    public List<ProcedureMaster> getAllProcedure()
    {
        return procedureMasterRepo.findAll();
    }
		
	@Override
    @Transactional
	public ProcedureMain getProcedureByVisitId(ProcedureMain procedureDetail)
	{
		ProcedureMain procedureGetById = prodecureDetailRepository.save(procedureDetail);
	        if (Objects.isNull(procedureGetById.getId()))
	            throw new IllegalArgumentException();
	        else if(Objects.isNull(procedureGetById.getName()))
	            throw new IllegalArgumentException();
	        return procedureGetById;
	}

	@Override
	@Transactional
	public ProcedureMain getProcedureDescription(ProcedureDetail procedureDetailDesc) {
		
		LOGGER.info("procedureDetailDesc : "+procedureDetailDesc);
		
		for(int i =0; i < procedureDetailDesc.getProcedure_details().size(); i++)
		{
			procedureMain.setPatient_visit_id(procedureDetailDesc.getPatient_visit_id());
			procedureMain.setName(procedureDetailDesc.getProcedure_details().get(i).getName());
			procedureMain.setName(procedureDetailDesc.getProcedure_details().get(i).getId());
			procedureMain.setDescription(procedureDetailDesc.getProcedure_details().get(i).getDescription());
			
			prodecureDetailRepository.save(procedureMain);
			
			LOGGER.info("procedure value : "+procedureMain + "\n procedure save() : "+prodecureDetailRepository.save(procedureMain));
		}
		return procedureMain;
	}
	
}