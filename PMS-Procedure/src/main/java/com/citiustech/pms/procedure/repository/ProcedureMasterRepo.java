package com.citiustech.pms.procedure.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.citiustech.pms.procedure.model.ProcedureMaster;

@Repository
public interface ProcedureMasterRepo extends JpaRepository<ProcedureMaster, Serializable>{
	
}