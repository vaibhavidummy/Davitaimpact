
  package com.citiustech.pms.repository;
  


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.citiustech.pms.model.Diagnosis;
  
@Repository
  public interface DiagnosisRepo extends JpaRepository<Diagnosis,Long>{
  
  }
 