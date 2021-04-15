package com.citiustech.pms.diagnosis.repository;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.citiustech.pms.diagnosis.model.DiagnosisMaster;

@Repository
public interface DiagnosisMasterRepo extends JpaRepository<DiagnosisMaster, Serializable> {

}
