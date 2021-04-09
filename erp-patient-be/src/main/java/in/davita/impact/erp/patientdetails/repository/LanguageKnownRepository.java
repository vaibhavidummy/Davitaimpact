package in.davita.impact.erp.patientdetails.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.davita.impact.erp.patientdetails.model.LanguageKnown;

@Repository
public interface LanguageKnownRepository extends JpaRepository<LanguageKnown, Integer>{

}
