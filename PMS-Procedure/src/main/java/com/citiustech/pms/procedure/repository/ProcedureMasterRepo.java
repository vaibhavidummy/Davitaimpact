package com.citiustech.pms.procedure.repository;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.citiustech.pms.procedure.model.ProcedureMaster;

@Repository
@Transactional
public interface ProcedureMasterRepo extends JpaRepository<ProcedureMaster, Serializable>{
	
	/*
	 * @Query(value = "select * from davita.procedure_master", nativeQuery = true)
	 * public List<ProcedureMaster> findAllProcedure();
	 */
}