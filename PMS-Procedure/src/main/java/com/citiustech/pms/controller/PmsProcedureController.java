package com.citiustech.pms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.pms.model.ProcedureDetail;
import com.citiustech.pms.service.ProcedureDetailServiceImpl;

@RestController
@RequestMapping("/healthcare/procedure")
public class PmsProcedureController {

	@Autowired
	private ProcedureDetailServiceImpl procedureDetailServiceImpl;
	
	@PostMapping(value = "/addprocedure")
	public ProcedureDetail addProcedure(@RequestBody ProcedureDetail procedureDetail) {
		
		return procedureDetailServiceImpl.addProcedure(procedureDetail);
		
	}

}
