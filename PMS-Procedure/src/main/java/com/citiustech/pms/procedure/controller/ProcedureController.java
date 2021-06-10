package com.citiustech.pms.procedure.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.pms.procedure.model.ProcedureDetail;
import com.citiustech.pms.procedure.model.ProcedureMain;
import com.citiustech.pms.procedure.model.ProcedureSuccess;
import com.citiustech.pms.procedure.service.ProcedureDetailService;


@RestController
@RequestMapping("/healthcare/procedure")
public class ProcedureController {

	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ProcedureController.class);
	
	@Autowired
	private ProcedureDetailService procedureDetailService;
	
	@PostMapping("/addProcedure")
	public ResponseEntity<ProcedureMain> addProcedure(@RequestBody ProcedureMain procedureMain) {
		LOGGER.info("Reached addProcedure");
		return new ResponseEntity<>(procedureDetailService.addProcedure(procedureMain), HttpStatus.CREATED);
	}
	
	@GetMapping("/{patientVisitId}")
    public ResponseEntity<ProcedureSuccess> getProcedureByVisitId(@PathVariable("patientVisitId") String patientVisitId) 
    { 
		LOGGER.info("Reached getProcedureByVisitId: ");
         return new ResponseEntity<>(procedureDetailService.getProcedureByVisitId(patientVisitId) , HttpStatus.OK);
    }
	
	@GetMapping("/getallProcedure")
    public ResponseEntity<ProcedureSuccess> getAllProcedure() {
		LOGGER.info("Reached getAllProcedure: ");
		return new ResponseEntity<>(procedureDetailService.getAllProcedure(), HttpStatus.OK);
    }
	
	@PostMapping("/procedureDetailDesc")
	public ResponseEntity<ProcedureSuccess> getProcedureDetailDesc(@RequestBody ProcedureDetail procedureDetail) 
    { 
		LOGGER.info("Reached getProcedureDetailDesc: {}",procedureDetail);
		return new ResponseEntity<>(procedureDetailService.getProcedureDescription(procedureDetail), HttpStatus.CREATED);
    }	
}