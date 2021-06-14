package com.citiustech.pms.procedure.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.citiustech.pms.procedure.constants.ErrorConstant;
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

		String procedureId = Optional.ofNullable(procedureDetail.getProcedureId()).filter(s -> !s.isEmpty())
				.orElseThrow(() -> new ProcedureException(ErrorConstant.PROCEDURE_ID));
		LOGGER.info("procedure Id : {} ", procedureId);

		String procedureName = Optional.ofNullable(procedureDetail.getName()).filter(s -> !s.isEmpty())
				.orElseThrow(() -> new ProcedureException(ErrorConstant.PROCEDURE_NAME));
		LOGGER.info("procedure Name : {} ", procedureName);

		String procedureDescription = Optional.ofNullable(procedureDetail.getDescription()).filter(s -> !s.isEmpty())
				.orElseThrow(() -> new ProcedureException(ErrorConstant.PROCEDURE_DESCRIPTION));
		LOGGER.info("procedure Description : {} ", procedureDescription);

		procedureMain = prodecureDetailRepository.save(procedureDetail);
		LOGGER.info("Procedure Added Successfully");

		return procedureMain;
	}

	@Override
	public ProcedureSuccess getProcedureByVisitId(String patientVisitId )
	{
		List<ProcedureMain> getPatientVisitId = new ArrayList<>();

		if (!patientVisitId.isEmpty()) {
			getPatientVisitId = prodecureDetailRepository.findByPatientVisitId(patientVisitId);
			LOGGER.info("List of Patient Visit Id : {} ",getPatientVisitId);
			
			if (getPatientVisitId.isEmpty()) {
				LOGGER.error("Patient Visit Id Not Found in GetPatientVisitId ");
				throw new ProcedureException(ErrorConstant.PROCEDURE_PATIENT_VISIT_ID);
			}
		}
		return new ProcedureSuccess.ProcedureDto().setProcedureMain(getPatientVisitId).setSuccessFlag(Boolean.TRUE).build();
	}
	
	@Override
    public ProcedureSuccess getAllProcedure()
	{
		List<ProcedureMaster> procedureMaster = procedureMasterRepo.findAll();
		LOGGER.info("Get Master Data size: {} ",procedureMaster.size());
		
		if (procedureMaster.isEmpty()) {
			LOGGER.error("No Data available in ProcedureMaster");
			throw new ProcedureException(ErrorConstant.PROCEDURE_GETDATA);
		}
		return new ProcedureSuccess.ProcedureDto().setProcedureMaster(procedureMaster).setSuccessFlag(Boolean.TRUE).build();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ProcedureSuccess getProcedureDescription(ProcedureDetail procedureDetailDesc) {
		LOGGER.info("procedureDetailDesc : {} ", procedureDetailDesc);

		List<ProcedureMain> procedureMainList = new ArrayList<>();

		if (procedureDetailDesc.getPatient_visit_id().isEmpty()) {
			LOGGER.error("Patient Visit Id cannot be Empty");
			throw new ProcedureException(ErrorConstant.PROCEDURE_DETAIL_ID);
		} else if (procedureDetailDesc.getProcedure_details().isEmpty()) {
			LOGGER.error("Procedure Description cannot be Empty");
			throw new ProcedureException(ErrorConstant.PROCEDURE_DETAIL_DESC);
		}
		LOGGER.info("Size : {} ", procedureDetailDesc.getProcedure_details().size());

		procedureDetailDesc.getProcedure_details().stream().forEach(element -> {
			ProcedureMain procedureDesc = new ProcedureMain();
			procedureDesc.setPatientVisitId(procedureDetailDesc.getPatient_visit_id());
			procedureDesc.setProcedureId(element.getId());
			procedureDesc.setName(element.getName());
			procedureDesc.setDescription(element.getDescription());

			procedureMainList.add(procedureDesc);
		});

		prodecureDetailRepository.saveAll(procedureMainList);
		LOGGER.info("Data Save Successfully : {} ",procedureMainList);

		return new ProcedureSuccess.ProcedureDto().setMessage("Added Successfully").setSuccessFlag(Boolean.TRUE)
				.build();
	}
}