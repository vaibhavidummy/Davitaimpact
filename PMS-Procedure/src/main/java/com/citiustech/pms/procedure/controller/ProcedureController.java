package com.citiustech.pms.procedure.controller;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.pms.procedure.model.ProcedureDetail;
import com.citiustech.pms.procedure.model.ProcedureMain;
import com.citiustech.pms.procedure.model.ProcedureMaster;
import com.citiustech.pms.procedure.service.ProcedureDetailService;




@RestController
@RequestMapping("/healthcare/procedure")
public class ProcedureController {

	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ProcedureController.class);
	
	@Autowired
	private ProcedureDetailService procedureDetailService;
	
	@PostMapping
	public ResponseEntity<ProcedureMain> addProcedure(@RequestBody ProcedureMain procedureDetail) {
		
		LOGGER.info("inside addProcedure");
		ProcedureMain addProcedureDetail = null;
		addProcedureDetail = procedureDetailService.addProcedure(procedureDetail);
		return new ResponseEntity<ProcedureMain>(addProcedureDetail, HttpStatus.CREATED);
	}
	
	@GetMapping("/getProcedureByVisitId")
    public ResponseEntity<ProcedureMain> getProcedureByVisitId(@RequestBody ProcedureMain procedureDetail) 
    { 
		ProcedureMain procedureByVisitId =null;
		procedureByVisitId = procedureDetailService.getProcedureByVisitId(procedureDetail);
         return new ResponseEntity<ProcedureMain>(procedureByVisitId , HttpStatus.OK);
    }
	
	@GetMapping("/getallProcedure")
    public List<ProcedureMaster> getAllProcedure() {
         return procedureDetailService.getAllProcedure();
    }
	
	@PostMapping("/procedureDetailDesc")
	public ResponseEntity<ProcedureMain> getProcedureDetailDesc(@RequestBody ProcedureDetail procedureDetail) 
    { 
		LOGGER.info("inside getProcedureDetailDesc: "+procedureDetail);
		
		procedureDetailService.getProcedureDescription(procedureDetail);
		return new ResponseEntity<ProcedureMain>(HttpStatus.CREATED);
    }	
}