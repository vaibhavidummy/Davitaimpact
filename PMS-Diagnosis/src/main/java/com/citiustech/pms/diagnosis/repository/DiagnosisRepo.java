package com.citiustech.pms.diagnosis.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.citiustech.pms.diagnosis.model.Diagnosis;
import com.citiustech.pms.diagnosis.model.DiagnosisMaster;

@Repository
@Transactional
public interface DiagnosisRepo extends JpaRepository<Diagnosis, Long> {
	
	  

}
