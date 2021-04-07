package com.citiustech.pms.model;

import org.springframework.stereotype.Component;

import lombok.Data;
@Component
@Data
public class Diagnosis {
private String diagnosis;
private String diagnosisDesc;
private String diagnosisId;

}
