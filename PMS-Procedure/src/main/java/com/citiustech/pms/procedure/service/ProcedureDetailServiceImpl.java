package com.citiustech.pms.procedure.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.citiustech.pms.procedure.exception.ProcedureException;
import com.citiustech.pms.procedure.model.ProcedureDetail;
import com.citiustech.pms.procedure.model.ProcedureMain;
import com.citiustech.pms.procedure.model.ProcedureMaster;
import com.citiustech.pms.procedure.model.ProcedureSuccess;
import com.citiustech.pms.procedure.repository.ProcedureMasterRepo;
import com.citiustech.pms.procedure.repository.ProdecureDetailRepository;


@Service
public class ProcedureDetailServiceImpl implements ProcedureDetailService {
	
	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ProcedureDetailServiceImpl.class);
	
	@Autowired
	private ProdecureDetailRepository prodecureDetailRepository;
	
	@Autowired
	private ProcedureMasterRepo procedureMasterRepo;
	
	@Autowired
	private ProcedureMain procedureMain;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public ProcedureMain addProcedure(ProcedureMain procedureDetail) {

		if (Objects.isNull(procedureDetail.getProcedureId())) {
			throw new ProcedureException("Procedure Id cannot be Null");
		} else if (Objects.isNull(procedureDetail.getName())) {
			throw new ProcedureException(" Procedure Name cannot be Null");
		} else if (Objects.isNull(procedureDetail.getDescription())) {
			throw new ProcedureException("Procedure Description cannot be Null ");
		}

		procedureMain = prodecureDetailRepository.save(procedureDetail);

		return procedureMain;
	}

	@Override
	public ProcedureSuccess getProcedureByVisitId(String patientVisitId )
	{
		List<ProcedureMain> getPatientVisitId = new ArrayList<>();
		
		if(!patientVisitId.isEmpty())
		{
			 getPatientVisitId = prodecureDetailRepository.checkForExistingPatientVisitId(patientVisitId);
			 
			if (getPatientVisitId.isEmpty()) {
				throw new ProcedureException("Patient Visit Id cannot be blank");
			}
		}
		
		 ProcedureSuccess procedureSuccess = new ProcedureSuccess();
		 procedureSuccess.setSuccessFlag(Boolean.TRUE);
		 procedureSuccess.setProcedureMain(getPatientVisitId);
		 return procedureSuccess;
	}
	
	@Override
    public ProcedureSuccess getAllProcedure()
    {
		List<ProcedureMaster> procedureMaster = procedureMasterRepo.findAll();
				
		if(procedureMaster.isEmpty())
		{
			throw new ProcedureException("Procedure Master data is not present");
		}
		ProcedureSuccess procedureSuccess = new ProcedureSuccess();
		 procedureSuccess.setSuccessFlag(Boolean.TRUE);
		 procedureSuccess.setProcedureMaster(procedureMaster);
        return procedureSuccess;
    }

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ProcedureSuccess getProcedureDescription(ProcedureDetail procedureDetailDesc) {
		
		LOGGER.info("procedureDetailDesc : "+procedureDetailDesc);

		List<ProcedureMain> procedureMainList = new ArrayList<>();
		
		LOGGER.info("procedureMainList value : "+procedureMainList);
		
		if(procedureDetailDesc.getPatient_visit_id().isEmpty())
		{
			throw new ProcedureException("Patient Visit Id cannot be empty");
		}
		else if(procedureDetailDesc.getProcedure_details().isEmpty())
		{
			throw new ProcedureException("Procedure Description cannot be empty");
		}

		LOGGER.info("Size : "+procedureDetailDesc.getProcedure_details().size());
		
			for(int i =0; i < procedureDetailDesc.getProcedure_details().size(); i++)
			{
				ProcedureMain procedureMain = new ProcedureMain();

				procedureMain.setPatientVisitId(procedureDetailDesc.getPatient_visit_id());
				procedureMain.setProcedureId(procedureDetailDesc.getProcedure_details().get(i).getId());
				procedureMain.setName(procedureDetailDesc.getProcedure_details().get(i).getName());
				procedureMain.setDescription(procedureDetailDesc.getProcedure_details().get(i).getDescription());
				
				procedureMainList.add(procedureMain);
			}
			for(int i =0; i < procedureMainList.size(); i++)
			{
				procedureMainList.stream().forEach(str -> prodecureDetailRepository.save(str));
			}
			
			 ProcedureSuccess procedureSuccess = new ProcedureSuccess();
			 procedureSuccess.setMessage("Added Successfully");
			 procedureSuccess.setSuccessFlag(Boolean.TRUE);
			 
			 return procedureSuccess;
	}
}