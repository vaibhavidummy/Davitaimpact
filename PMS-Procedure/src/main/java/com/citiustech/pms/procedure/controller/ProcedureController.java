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

import com.citiustech.pms.procedure.constants.ErrorConstant;
import com.citiustech.pms.procedure.model.ProcedureDetail;
import com.citiustech.pms.procedure.model.ProcedureMain;
import com.citiustech.pms.procedure.model.ProcedureSuccess;
import com.citiustech.pms.procedure.service.ProcedureDetailService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/healthcare/procedure")
public class ProcedureController {

	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ProcedureController.class);

	@Autowired
	private ProcedureDetailService procedureDetailService;

	@ApiOperation(value = "Method for adding the new Procedure")
	@ApiResponses(value = { @ApiResponse(code = 201, message = ErrorConstant.CREATED, response = ProcedureMain.class),
			@ApiResponse(code = 401, message = ErrorConstant.UNAUTHORIZED),
			@ApiResponse(code = 403, message = ErrorConstant.FORBIDDEN),
			@ApiResponse(code = 404, message = ErrorConstant.NOT_FOUND),
			@ApiResponse(code = 500, message = ErrorConstant.FAILURE) })
	@PostMapping("/addProcedure")
	public ResponseEntity<ProcedureMain> addProcedure(@RequestBody ProcedureMain procedureMain) {
		LOGGER.info("Reached addProcedure");
		return new ResponseEntity<>(procedureDetailService.addProcedure(procedureMain), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Method for showing Patient visit details")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = ErrorConstant.SUCCESS, response = ProcedureSuccess.class),
			@ApiResponse(code = 401, message = ErrorConstant.UNAUTHORIZED),
			@ApiResponse(code = 403, message = ErrorConstant.FORBIDDEN),
			@ApiResponse(code = 404, message = ErrorConstant.NOT_FOUND),
			@ApiResponse(code = 500, message = ErrorConstant.FAILURE) })
	@GetMapping("/{patientVisitId}")
	public ResponseEntity<ProcedureSuccess> getProcedureByVisitId(
			@ApiParam(value = ErrorConstant.PATIENT_VISIT_ID, required = true) @PathVariable("patientVisitId") String patientVisitId) {
		LOGGER.info("Reached getProcedureByVisitId: ");
		return new ResponseEntity<>(procedureDetailService.getProcedureByVisitId(patientVisitId), HttpStatus.OK);
	}

	@ApiOperation(value = "Get Procedure List")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = ErrorConstant.SUCCESS, response = ProcedureSuccess.class),
			@ApiResponse(code = 401, message = ErrorConstant.UNAUTHORIZED),
			@ApiResponse(code = 403, message = ErrorConstant.FORBIDDEN),
			@ApiResponse(code = 404, message = ErrorConstant.NOT_FOUND),
			@ApiResponse(code = 500, message = ErrorConstant.FAILURE) })
	@GetMapping("/getallProcedure")
	public ResponseEntity<ProcedureSuccess> getAllProcedure() {
		LOGGER.info("Reached getAllProcedure: ");
		return new ResponseEntity<>(procedureDetailService.getAllProcedure(), HttpStatus.OK);
	}

	@ApiOperation(value = "Method for adding the patient procedure description")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = ErrorConstant.CREATED, response = ProcedureSuccess.class),
			@ApiResponse(code = 401, message = ErrorConstant.UNAUTHORIZED),
			@ApiResponse(code = 403, message = ErrorConstant.FORBIDDEN),
			@ApiResponse(code = 404, message = ErrorConstant.NOT_FOUND),
			@ApiResponse(code = 500, message = ErrorConstant.FAILURE) })
	@PostMapping("/procedureDetailDesc")
	public ResponseEntity<ProcedureSuccess> getProcedureDetailDesc(@RequestBody ProcedureDetail procedureDetail) {
		LOGGER.info("Reached getProcedureDetailDesc: {}", procedureDetail);
		return new ResponseEntity<>(procedureDetailService.getProcedureDescription(procedureDetail),
				HttpStatus.CREATED);
	}
}