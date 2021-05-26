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
		LOGGER.info("Get in addProcedure ");
		
		if (Objects.isNull(procedureDetail.getProcedureId()) || procedureDetail.getProcedureId().isEmpty() ) {
			throw new ProcedureException("Procedure Id cannot be Empty or Null");
		} else if (Objects.isNull(procedureDetail.getName()) || procedureDetail.getName().isEmpty()) {
			throw new ProcedureException(" Procedure Name cannot be Empty or Null");
		} else if (Objects.isNull(procedureDetail.getDescription()) || procedureDetail.getDescription().isEmpty()) {
			throw new ProcedureException("Procedure Description cannot be Empty or Null ");
		}

		procedureMain = prodecureDetailRepository.save(procedureDetail);
		LOGGER.info("Procedure Added Successfully");

		return procedureMain;
	}

	@Override
	public ProcedureSuccess getProcedureByVisitId(String patientVisitId )
	{
		List<ProcedureMain> getPatientVisitId = new ArrayList<>();

		if (!patientVisitId.isEmpty()) {
			getPatientVisitId = prodecureDetailRepository.checkForExistingPatientVisitId(patientVisitId);
			LOGGER.info("List of Patient Visit Id : {} ",getPatientVisitId);
			
			if (getPatientVisitId.isEmpty()) {
				throw new ProcedureException("Patient Visit Id Not Found");
			}
		}
		return new ProcedureSuccess.ProcedureDto().setProcedureMain(getPatientVisitId).setSuccessFlag(Boolean.TRUE).build();
	}
	
	@Override
    public ProcedureSuccess getAllProcedure()
	{
		List<ProcedureMaster> procedureMaster = procedureMasterRepo.findAll();
		LOGGER.info("Get Master Data: {} ",procedureMaster);
		
		if (procedureMaster.isEmpty()) {
			throw new ProcedureException("No Data available");
		}
		return new ProcedureSuccess.ProcedureDto().setProcedureMaster(procedureMaster).setSuccessFlag(Boolean.TRUE).build();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ProcedureSuccess getProcedureDescription(ProcedureDetail procedureDetailDesc) {
		LOGGER.info("procedureDetailDesc : {} ",procedureDetailDesc);

		List<ProcedureMain> procedureMainList = new ArrayList<>();

		if (procedureDetailDesc.getPatient_visit_id().isEmpty()) {
			throw new ProcedureException("Patient Visit Id cannot be Empty");
		} else if (procedureDetailDesc.getProcedure_details().isEmpty()) {
			throw new ProcedureException("Procedure Description cannot be Empty");
		}
		LOGGER.info("Size : {} ",procedureDetailDesc.getProcedure_details().size());
		
		for (int i = 0; i < procedureDetailDesc.getProcedure_details().size(); i++) {
			ProcedureMain procedureDesc = new ProcedureMain();

			procedureDesc.setPatientVisitId(procedureDetailDesc.getPatient_visit_id());
			procedureDesc.setProcedureId(procedureDetailDesc.getProcedure_details().get(i).getId());
			procedureDesc.setName(procedureDetailDesc.getProcedure_details().get(i).getName());
			procedureDesc.setDescription(procedureDetailDesc.getProcedure_details().get(i).getDescription());

			procedureMainList.add(procedureDesc);
		}
		for (int i = 0; i < procedureMainList.size(); i++) {
			procedureMainList.stream().forEach(str -> prodecureDetailRepository.save(str));
		}
		LOGGER.info("Data Save Successfully");
		
		return new ProcedureSuccess.ProcedureDto().setMessage("Added Successfully").setSuccessFlag(Boolean.TRUE).build();
	}
}
