package in.davita.impact.erp.patient.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.davita.impact.erp.patient.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, String>{

	
}
