package com.davita.impact.erp.patient.comman;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Diagnosis {

	private Long seq_id;
	private String diagonosisId;
	private String description;
	private String patient_visit_id;
	private String name;

		
	private String created_by ;

	@CreatedDate
	@JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creation_on;

	
	private String last_modified_by;

	@CreatedDate
	@JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date last_modified_on;

	
	
}
