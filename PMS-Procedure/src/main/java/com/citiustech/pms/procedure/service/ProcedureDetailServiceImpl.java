package com.citiustech.pms.procedure.service;

import java.util.ArrayList;
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
	
//	  @Autowired
//	  private ProcedureMain procedureMain;
	  

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
    @Transactional(rollbackFor = Exception.class)
	public ProcedureMain getProcedureByVisitId(ProcedureMain procedureDetail)
	{
		ProcedureMain procedureGetById = prodecureDetailRepository.save(procedureDetail);
	        if (Objects.isNull(procedureGetById.getProcedure_id()))
	            throw new IllegalArgumentException();
	        else if(Objects.isNull(procedureGetById.getName()))
	            throw new IllegalArgumentException();
	        return procedureGetById;
	}
	
	@Override
    public List<ProcedureMaster> getAllProcedure()
    {
        return procedureMasterRepo.findAll();
    }

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String getProcedureDescription(ProcedureDetail procedureDetailDesc) {
		
		LOGGER.info("procedureDetailDesc : "+procedureDetailDesc + "size: "+procedureDetailDesc.getProcedure_details().size());
		
		List<ProcedureMain> procedureMainList = new ArrayList<>();
		
			LOGGER.info("procedureMainList value : "+procedureMainList);
			
			for(int i =0; i < procedureDetailDesc.getProcedure_details().size(); i++)
			{
				ProcedureMain procedureMain = new ProcedureMain();
				
				procedureMain.setPatient_visit_id(procedureDetailDesc.getPatient_visit_id());
				procedureMain.setProcedure_id(procedureDetailDesc.getProcedure_details().get(i).getId());
				procedureMain.setName(procedureDetailDesc.getProcedure_details().get(i).getName());
				procedureMain.setDescription(procedureDetailDesc.getProcedure_details().get(i).getDescription());
				
				procedureMainList.add(procedureMain);
				
				LOGGER.info("procedure value in For Loop : "+ procedureMain);
			}
			for(int i =0; i < procedureMainList.size(); i++)
			{
				procedureMainList.stream().forEach(str -> prodecureDetailRepository.save(str));
			}
			return "Added Successfully";
	}
}