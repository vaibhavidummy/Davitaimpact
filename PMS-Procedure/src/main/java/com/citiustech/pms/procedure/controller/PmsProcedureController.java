package com.citiustech.pms.procedure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.pms.procedure.model.ProcedureDetail;
import com.citiustech.pms.procedure.service.ProcedureDetailService;
import com.citiustech.pms.procedure.service.ProcedureDetailServiceImpl;

@RestController
@RequestMapping("/healthcare/procedure")
public class PmsProcedureController {

	@Autowired
	private ProcedureDetailService procedureDetailService;
	
	@PostMapping
	public ProcedureDetail addProcedure(@RequestBody ProcedureDetail procedureDetail) {
		
		return procedureDetailService.addProcedure(procedureDetail);
		
	}

}
