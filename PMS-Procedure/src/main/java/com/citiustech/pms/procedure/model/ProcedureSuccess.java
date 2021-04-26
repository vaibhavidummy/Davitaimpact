package com.citiustech.pms.procedure.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProcedureSuccess {

	private String msg;
	private boolean successFlag;
	private List<ProcedureMaster> procedureMaster;
	private List<ProcedureMain> procedureMain ;
}
