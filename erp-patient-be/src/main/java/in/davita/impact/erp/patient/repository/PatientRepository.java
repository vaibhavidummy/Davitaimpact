package in.davita.impact.erp.patient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.davita.impact.erp.patient.model.Patient;
@Repository
public interface PatientRepository extends JpaRepository<Patient, String>{

	
}
