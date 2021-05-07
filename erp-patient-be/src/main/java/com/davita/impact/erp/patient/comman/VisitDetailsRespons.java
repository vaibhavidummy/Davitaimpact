package com.davita.impact.erp.patient.comman;

import java.util.ArrayList;
import java.util.List;

import com.davita.impact.erp.patient.model.PatientVisit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitDetailsRespons {

	private PatientVisit patientvisit ;
	private  List<Diagnosis> diagnosis =new ArrayList<Diagnosis>();
	
}
