package com.davita.impact.erp.patient.serviceImpl;

import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.davita.impact.erp.patient.model.Allergies;
import com.davita.impact.erp.patient.model.LanguageKnown;
import com.davita.impact.erp.patient.model.PatientDetails;
import com.davita.impact.erp.patient.repository.AllergiesRepo;
import com.davita.impact.erp.patient.repository.LanguageKnownRepository;
import com.davita.impact.erp.patient.repository.PatientRepository;
import com.davita.impact.erp.patient.service.PatientServices;

@Service
//@Transactional(rollbackFor = Exception.class, noRollbackFor = FileNotFoundException.class)
public class PatientServiceImpl implements PatientServices {

	@Autowired
	PatientRepository patientRepository;
	@Autowired
	LanguageKnownRepository languageKnownRepository;

	@Autowired
	AllergiesRepo allergiesRepo;

	@Override
	@Transactional
	public PatientDetails addNewPatient(PatientDetails patient) {

		List<Integer> lagid = patient.getLanguageKnown();
		List<Integer> allergyid = patient.getAllergies();
		Set<LanguageKnown> langknown = new HashSet<LanguageKnown>();
		Set<Allergies> allergiesSet = new HashSet<Allergies>();
		lagid.forEach(id -> {
			Optional<LanguageKnown> findById = languageKnownRepository.findById(id);
			LanguageKnown languageKnown = findById.get();
			langknown.add(languageKnown);

		});
		patient.setLanguageKnownObject(langknown);

		allergyid.forEach(id -> {

			Optional<Allergies> findById = allergiesRepo.findById(id);
			Allergies allergies = findById.get();
			allergiesSet.add(allergies);
		});
		patient.setAllergiesObject(allergiesSet);

		/*
		 * if( patient.getProperty() == null) throw new IllegalArgumentException();
		 */

		PatientDetails addedPatient = patientRepository.save(patient);
		return addedPatient;
		// return null;
	}

	@Override
	@Transactional
	public PatientDetails updatePatient(PatientDetails patient) throws Exception {

		Optional<PatientDetails> findById2 = patientRepository.findById(patient.getId());

		// Optional<PatientDetails> findById =
		// patientRepository.findById(patient.getId());

		if (!findById2.isPresent()) {
			// return ResponseEntity.notFound().build();
			throw new Exception("No Id Found in DB");
		}

		List<Integer> lagid = patient.getLanguageKnown();
		List<Integer> allergyid = patient.getAllergies();
		Set<LanguageKnown> langknown = new HashSet<LanguageKnown>();
		Set<Allergies> allergiesSet = new HashSet<Allergies>();
		lagid.forEach(id -> {
			Optional<LanguageKnown> findById = languageKnownRepository.findById(id);
			LanguageKnown languageKnown = findById.get();
			langknown.add(languageKnown);

		});
		patient.setLanguageKnownObject(langknown);

		allergyid.forEach(id -> {

			Optional<Allergies> findById = allergiesRepo.findById(id);
			Allergies allergies = findById.get();
			allergiesSet.add(allergies);
		});
		patient.setAllergiesObject(allergiesSet);

		PatientDetails save = patientRepository.save(patient);
		return save;
	}

	@Override
	@Transactional
	public PatientDetails getPatientById(String id) {
		Optional<PatientDetails> findById = patientRepository.findById(id);
		PatientDetails patient = findById.get();
		return patient;
	}

	@Override
	@Transactional
	public List<PatientDetails> getAllPatient() {
		List<PatientDetails> findAll = patientRepository.findAll();
		return findAll;
	}

	@Override
	public PatientDetails addNewAllergy(Allergies allergies, String patientDetailsid) {

		return null;
	}

	@Override
	public PatientDetails findPatientbyId(String id) {
		Optional<PatientDetails> findById = patientRepository.findById(id);
		PatientDetails patientDetails = findById.get();
		return patientDetails;
	}

}
