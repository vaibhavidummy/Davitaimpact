package com.davita.impact.erp.patient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.davita.impact.erp.patient.model.LanguageKnown;

@Repository
public interface LanguageKnownRepository extends JpaRepository<LanguageKnown, Integer>{

}
