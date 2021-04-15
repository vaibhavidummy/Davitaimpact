package com.citiustech.pms.procedure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.citiustech.pms.procedure.model.ProcedureMain;

@Repository
public interface ProdecureDetailRepository extends JpaRepository<ProcedureMain, Long> {

}