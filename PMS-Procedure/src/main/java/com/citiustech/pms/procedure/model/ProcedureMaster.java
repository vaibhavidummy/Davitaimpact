package com.citiustech.pms.procedure.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@Entity 
public class ProcedureMaster  {

	@Id
    @Column(name="id")
    private String procedureId;
    
    @Column(name="name")
    private String procedureName;
}
