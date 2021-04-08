package com.citiustech.pms.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import lombok.Data;
@Component
@Data
@Entity
//@Table(name = "diagnosis", schema = "Davita")
public class Diagnosis {

@Id
@Column(name = "diagnosis_id")
@GeneratedValue(strategy =GenerationType.AUTO ,generator="system-uuid")
@GenericGenerator(name="system-uuid", strategy = "uuid")
//@GeneratedValue(strategy = GenerationType.AUTO, generator = "DiagnosisSeqGenerator")
//@SequenceGenerator(name = "DiagnosisSeqGenerator", sequenceName = "Davita.SQ_DAVITA_DIAGNOSIS_ID", allocationSize = 1)
private String diagnosis_id;

@Column(name = "diagnosis")
private String diagnosis;

@Column(name = "diagnosis_description")
private String diagnosis_description;

@Column(name = "created_by")
private String created_by;




}
