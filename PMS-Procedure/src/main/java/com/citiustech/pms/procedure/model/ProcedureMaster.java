package com.citiustech.pms.procedure.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@Entity 
@AllArgsConstructor
@NoArgsConstructor
public class ProcedureMaster  {

	@Id
    @Column(name="Procedure_Code")
    private String procedureId;
    
    @Column(name="Procedure_Description")
    private String procedureName;
}
