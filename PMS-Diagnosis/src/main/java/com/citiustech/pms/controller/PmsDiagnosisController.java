package com.citiustech.pms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.pms.model.Diagnosis;

@RestController
public class PmsDiagnosisController {
@Autowired
private Diagnosis diagnosis;

@GetMapping("/hello")
public String hello()
{
	return "hello";
}


}
