package com.citiustech.pms.procedure.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@Entity 
@Table(name="procedure_master", schema = "davita")
public class ProcedureMaster implements Serializable {

	@Id
    @Column(name="id")
    private String procedureId;
    
    @Column(name="name")
    private String procedureName;
}
