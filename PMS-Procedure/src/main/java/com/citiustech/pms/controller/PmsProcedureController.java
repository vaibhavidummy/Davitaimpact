package com.citiustech.pms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.pms.model.Procedure;

@RestController
public class PmsProcedureController {

	@Autowired
	private Procedure procedure;
	
	@GetMapping("/hello")
	public String get()
	{
		return "hello";
	}

}
