package com.davita.impact.erp.patient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.davita.impact.erp.patient.model.Allergies;

@Repository
public interface AllergiesRepo extends JpaRepository<Allergies, Integer>{

}
