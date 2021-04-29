package com.medication.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class MedicationDto {

	private Medication medication;
	
	private List<Medication> medicationList;
	
	private Boolean successFlag;
	
	private MedicationOnVisit medicationOnVisit;
	
	private List<MedicationOnVisit> medicationOnVisitList;
	
	public static class MedicationDtoBuilder{
		private Medication medication;
		
		private List<Medication> medicationList;
		
		private Boolean successFlag;
		
		private MedicationOnVisit medicationOnVisit;
		
		private List<MedicationOnVisit> medicationOnVisitList;
		
		

		public List<Medication> getMedicationList() {
			return medicationList;
		}

		public MedicationDtoBuilder setMedicationList(List<Medication> medicationList) {
			this.medicationList = medicationList;
			return this;
		}

		public Boolean getSuccessFlag() {
			return successFlag;
		}

		public MedicationDtoBuilder setSuccessFlag(Boolean successFlag) {
			this.successFlag = successFlag;
			return this;
		}

		public MedicationOnVisit getMedicationOnVisit() {
			return medicationOnVisit;
		}

		public MedicationDtoBuilder setMedicationOnVisit(MedicationOnVisit medicationOnVisit) {
			this.medicationOnVisit = medicationOnVisit;
			return this;
		}

		public List<MedicationOnVisit> getMedicationOnVisitList() {
			return medicationOnVisitList;
		}

		public MedicationDtoBuilder setMedicationOnVisitList(List<MedicationOnVisit> medicationOnVisitList) {
			this.medicationOnVisitList = medicationOnVisitList;
			return this;
		}
		
		
		public Medication getMedication() {
			return medication;
		}

		public MedicationDtoBuilder setMedication(Medication medication) {
			this.medication = medication;
			return this;
		}

		public MedicationDto build() {
			return new MedicationDto(this.getMedication(),this.getMedicationList(),this.getSuccessFlag(),this.getMedicationOnVisit(),this.getMedicationOnVisitList());
		}
		
	}
}