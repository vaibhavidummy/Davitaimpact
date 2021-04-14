package com.citiustech.pms.procedure.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@Component
@Entity
@EntityListeners(AuditingEntityListener.class)
@EnableJpaAuditing
public class ProcedureDetail implements Serializable {

	private static final long serialVersionUID = -3025916076728582932L;

	@Id
	@Column
	@GeneratedValue
	private long id;

	@Column
	private String name;

	@Column
	private String description;
	
	@CreatedBy
    @Column(name="created_by", nullable = true)
    private String created_by="admin";

    @CreatedDate
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.DATE)
    @Column(name="creation_on")
    private Date creation_on;

    @LastModifiedBy
    @Column(name="last_modified_by", nullable = true)
    private String last_modified_by="admin";

    @LastModifiedDate
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.DATE)
    @Column(name ="last_modified_on")
    private Date last_modified_on;
    
    @Column(nullable = false, length = 1)
	String status = "I";
    
}