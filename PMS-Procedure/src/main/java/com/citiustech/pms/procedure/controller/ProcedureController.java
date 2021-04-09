package com.citiustech.pms.procedure.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.pms.procedure.model.ProcedureDetail;
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

}