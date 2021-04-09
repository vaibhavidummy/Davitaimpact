package com.citiustech.pms.diagnosis.service;

import java.util.Objects;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citiustech.pms.diagnosis.model.Diagnosis;
import com.citiustech.pms.diagnosis.repository.DiagnosisRepo;

@Service
public class PmsDiagnosisServiceImpl implements PmsDiagnosisServiceInterface {
	@Autowired
	DiagnosisRepo diagnosisRepo;

	@Override
	@Transactional
	public Diagnosis addUser(Diagnosis diagnosis) {
		Diagnosis diagnosisAdd = diagnosisRepo.save(diagnosis);

		if (Objects.isNull(diagnosis.getId()))
			throw new IllegalArgumentException();
		else if (Objects.isNull(diagnosis.getName()) || Objects.isNull(diagnosis.getDescription()))
			throw new IllegalArgumentException();

		return diagnosisAdd;

	}
}
