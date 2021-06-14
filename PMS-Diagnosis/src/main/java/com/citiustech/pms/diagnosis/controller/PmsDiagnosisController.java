package com.citiustech.pms.diagnosis.controller;


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

import com.citiustech.pms.diagnosis.constants.ErrorConstant;
import com.citiustech.pms.diagnosis.model.Diagnosis;
import com.citiustech.pms.diagnosis.model.DiagnosisModel;
import com.citiustech.pms.diagnosis.model.DiagnosisSuccess;
import com.citiustech.pms.diagnosis.service.PmsDiagnosisServiceInterface;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/healthcare/diagnosis")
public class PmsDiagnosisController {

	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(PmsDiagnosisController.class);

	@Autowired
	private PmsDiagnosisServiceInterface pmsDiagnosisService;

	@ApiOperation(value = "Method for adding the new Diagnosis")
	@ApiResponses(value = { @ApiResponse(code = 201, message = ErrorConstant.CREATED, response = Diagnosis.class),
			@ApiResponse(code = 401, message = ErrorConstant.UNAUTHORIZED),
			@ApiResponse(code = 403, message = ErrorConstant.FORBIDDEN),
			@ApiResponse(code = 404, message = ErrorConstant.NOT_FOUND),
			@ApiResponse(code = 500, message = ErrorConstant.FAILURE) })
	@PostMapping("/addDiagnosis")
	public ResponseEntity<Diagnosis> addDiagnosis(@RequestBody Diagnosis diagnosisDetail) {
		LOGGER.info("Reached addDiagnosis");
		return new ResponseEntity<>(pmsDiagnosisService.addDiagnosis(diagnosisDetail), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Get Diagnosis List")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = ErrorConstant.SUCCESS, response = DiagnosisSuccess.class),
			@ApiResponse(code = 401, message = ErrorConstant.UNAUTHORIZED),
			@ApiResponse(code = 403, message = ErrorConstant.FORBIDDEN),
			@ApiResponse(code = 404, message = ErrorConstant.NOT_FOUND),
			@ApiResponse(code = 500, message = ErrorConstant.FAILURE) })
	@GetMapping("/getalldiagnosis")
	public ResponseEntity<DiagnosisSuccess> getAllDiagnosis() {
		LOGGER.info("Reached getAllDiagnosis ");
		return new ResponseEntity<>(pmsDiagnosisService.getAllDiagnosis(), HttpStatus.OK);
	}

	@ApiOperation(value = "Method for showing Patient visit details")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = ErrorConstant.SUCCESS, response = DiagnosisSuccess.class),
			@ApiResponse(code = 401, message = ErrorConstant.UNAUTHORIZED),
			@ApiResponse(code = 403, message = ErrorConstant.FORBIDDEN),
			@ApiResponse(code = 404, message = ErrorConstant.NOT_FOUND),
			@ApiResponse(code = 500, message = ErrorConstant.FAILURE) })
	@GetMapping("/{patientVisitId}")
	public ResponseEntity<DiagnosisSuccess> getDiagnosisByVisitId(
			@ApiParam(value = ErrorConstant.PATIENT_VISIT_ID, required = true) @PathVariable("patientVisitId") String patientVisitId) {
		LOGGER.info("Reached getDiagnosisByVisitId ");
		return new ResponseEntity<>(pmsDiagnosisService.getProcedureByVisitId(patientVisitId), HttpStatus.OK);
	}

	@ApiOperation(value = "Method for adding the patient diagnosis description")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = ErrorConstant.CREATED, response = DiagnosisSuccess.class),
			@ApiResponse(code = 401, message = ErrorConstant.UNAUTHORIZED),
			@ApiResponse(code = 403, message = ErrorConstant.FORBIDDEN),
			@ApiResponse(code = 404, message = ErrorConstant.NOT_FOUND),
			@ApiResponse(code = 500, message = ErrorConstant.FAILURE) })
	@PostMapping("/diagnosisDetailDesc")
	public ResponseEntity<DiagnosisSuccess> getDiagnosisDetailDesc(@RequestBody DiagnosisModel diagnosisModel) {
		LOGGER.info("Reached getDiagnosisDetailDesc: {} ", diagnosisModel);
		return new ResponseEntity<>(pmsDiagnosisService.getDiagnosisDescription(diagnosisModel), HttpStatus.CREATED);
	}

}