package com.citiustech.pms.procedure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.citiustech.pms.procedure.model.ProcedureDetail;

@Repository
public interface ProdecureDetailRepository extends JpaRepository<ProcedureDetail, Long> {

}
