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
import com.citiustech.pms.procedure.model.ProcedureMaster;
import com.citiustech.pms.procedure.service.ProcedureDetailService;


@RestController
@RequestMapping("/healthcare/procedure")
public class ProcedureController {

	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ProcedureController.class);
	
	@Autowired
	private ProcedureDetailService procedureDetailService;
	
	@PostMapping
	public ResponseEntity<ProcedureDetail> addProcedure(@RequestBody ProcedureDetail procedureDetail) {
		
		LOGGER.info("inside addProcedure");
		ProcedureDetail addProcedureDetail = null;
		addProcedureDetail = procedureDetailService.addProcedure(procedureDetail);
		return new ResponseEntity<ProcedureDetail>(addProcedureDetail, HttpStatus.CREATED);
	}
	
	@GetMapping("/getProcedureByVisitId")
    public ResponseEntity<ProcedureDetail> getProcedureByVisitId(@RequestBody ProcedureDetail procedureDetail) 
    { 
		ProcedureDetail procedureByVisitId =null;
		procedureByVisitId = procedureDetailService.getProcedureByVisitId(procedureDetail);
         return new ResponseEntity<ProcedureDetail>(procedureByVisitId , HttpStatus.OK);
    }
	
	@GetMapping("/getallProcedure")
    public List<ProcedureMaster> getAllProcedure() {
         System.out.println("the all getAllProcedure is"+ procedureDetailService.getAllProcedure());
         return procedureDetailService.getAllProcedure();
    }
}