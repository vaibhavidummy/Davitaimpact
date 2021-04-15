package com.citiustech.pms.diagnosis.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@EnableJpaAuditing
@EntityListeners(AuditingEntityListener.class)
public class Diagnosis implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(updatable = false, nullable = false)
	private Long seq_id;

	@Column(name = "id")
	private String id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	// ManyToOne
	// @JoinColumn(name="patient_visit_id", nullable=false)
	// private Patient_Visit_Id patient_visit_id;
	@Column(name = "patient_visit_id")
	private String patient_visit_id;

	@CreatedBy
	@Column(nullable = false, updatable = false)
	private String created_by = "sweeta";

	@CreatedDate
	@JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, updatable = false)
	private Date creation_on;

	@LastModifiedBy
	@Column(nullable = false)

	private String last_modified_by = "sweeta";

	@LastModifiedDate
	@JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date last_modified_on;

}
