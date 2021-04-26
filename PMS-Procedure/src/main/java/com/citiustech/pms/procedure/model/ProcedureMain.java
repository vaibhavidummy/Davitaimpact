package com.citiustech.pms.procedure.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
@Component
@EntityListeners(AuditingEntityListener.class)
@EnableJpaAuditing
public class ProcedureMain implements Serializable{

	private static final long serialVersionUID = -3661065571206334359L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(updatable = false, nullable = false)
	private Long seq_id;

	@Column
	private String procedure_id;
	
	@Column
	private String name;

	@Column
	private String description;
	
	@CreatedBy
    @Column(nullable = true , updatable = false)
    private String created_by="admin";

    @CreatedDate
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
   // @Temporal(TemporalType.TIMESTAMP)
    @Column(name="creation_on" , updatable = false)
    private Date creation_on;

    @LastModifiedBy
    @Column(nullable = false)
    private String last_modified_by="admin";

    @LastModifiedDate
   @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
   // @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date last_modified_on;
    
    @Column(nullable = false, length = 1)
	String status = "I";
    
    @Column(nullable = false)
    private String patient_visit_id;
    
    
 // ManyToOne
 	// @JoinColumn(name="patient_visit_id", nullable=false)  check and remove the join col if not needed
 	// private Patient_Visit_Id patient_visit_id;
    
}