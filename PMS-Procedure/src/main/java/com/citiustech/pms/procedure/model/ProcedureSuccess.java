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

	private String message;
	private boolean successFlag;
	private List<ProcedureMaster> procedureMaster;
	private List<ProcedureMain> procedureMain ;

	public static class ProcedureDto{
		
		private String message;
		private boolean successFlag;
		private List<ProcedureMaster> procedureMaster;
		private List<ProcedureMain> procedureMain ;
		
		public String getMessage() {
			return message;
		}
		public ProcedureDto setMessage(String message) {
			this.message = message;
			return this;
		}
		public boolean isSuccessFlag() {
			return successFlag;
		}
		public ProcedureDto setSuccessFlag(boolean successFlag) {
			this.successFlag = successFlag;
			return this;
		}
		public List<ProcedureMaster> getProcedureMaster() {
			return procedureMaster;
		}
		public ProcedureDto setProcedureMaster(List<ProcedureMaster> procedureMaster) {
			this.procedureMaster = procedureMaster;
			return this;
		}
		public List<ProcedureMain> getProcedureMain() {
			return procedureMain;
		}
		public ProcedureDto setProcedureMain(List<ProcedureMain> procedureMain) {
			this.procedureMain = procedureMain;
			return this;
		}
		
		public ProcedureSuccess build()
		{
			return new ProcedureSuccess(this.getMessage(), this.isSuccessFlag(),  this.getProcedureMaster() , this.getProcedureMain());
		}
	}
}
