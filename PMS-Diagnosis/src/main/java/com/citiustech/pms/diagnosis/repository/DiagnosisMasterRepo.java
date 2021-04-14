
  package com.citiustech.pms.diagnosis.repository;
  
  import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.citiustech.pms.diagnosis.model.DiagnosisMaster;
  @Repository
  @Transactional
  public interface DiagnosisMasterRepo extends JpaRepository<DiagnosisMaster,
  Serializable>{
	  
	/*
	 * @Query(value = "select * from davita.diagnosis_master", nativeQuery = true)
	 * public List<DiagnosisMaster> findAllDiagnosis();
	 */

  
  }
 