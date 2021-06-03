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

import com.davita.impact.erp.patient.exception.EntityDetailsNotFoundException;
import com.davita.impact.erp.patient.feign.client.UdateUserStatus;
import com.davita.impact.erp.patient.feign.client.UdateUserStatusFallbackFactory;
import com.davita.impact.erp.patient.model.Allergies;
import com.davita.impact.erp.patient.model.LanguageKnown;
import com.davita.impact.erp.patient.model.PatientDetails;
import com.davita.impact.erp.patient.repository.AllergiesRepo;
import com.davita.impact.erp.patient.repository.LanguageKnownRepository;
import com.davita.impact.erp.patient.repository.LanguageRepo;
import com.davita.impact.erp.patient.repository.PatientRepository;
import com.davita.impact.erp.patient.service.PatientServices;

@Service
@Transactional(rollbackFor = Exception.class, noRollbackFor = FileNotFoundException.class)
public class PatientServiceImpl implements PatientServices {

	@Autowired
	UdateUserStatus udateUserStatus;
	
	@Autowired
	PatientRepository patientRepository;
	@Autowired
	LanguageRepo languageKnownRepository;

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
		PatientDetails addedPatient = patientRepository.save(patient);
		
		/*
		 * if(addedPatient==null) throw new
		 * EntityDetailsNotFoundException("Patient  Details Not Save ", new Object[]{
		 * patient.getBasicDetails().getFirstName() }); Boolean udpateStatusAtAdmin =
		 * udateUserStatus.afterFirstAuthParamterChange(false, false,
		 * patient.getUser_id_fk()); if(udpateStatusAtAdmin==false) throw new
		 * EntityDetailsNotFoundException("Patient  Details Not Save  due to some admin Services Issue"
		 * , new Object[]{ patient.getBasicDetails().getFirstName() });
		 */


		/*
		 * ------------------- Yet To test following  start Code -------------------
		 */
		
		/*
		 * if (addedPatient==null) { // return ResponseEntity.notFound().build(); throw
		 * new
		 * EntityDetailsNotFoundException("Pataient Details are not save some issue is there..."
		 * , new Object[]{ patient.getBasicDetails().getFirstName() }); }
		 */
		
		/*
		 * ------------------- Yet To test following  End Code -------------------
		 */
		
		
		return addedPatient;
		// return null;
	}

	/* update patient Details */
	@Override
	@Transactional
	public PatientDetails updatePatient(PatientDetails patient) throws Exception {

		Optional<PatientDetails> findById2 = patientRepository.findById(patient.getId());

		if (!findById2.isPresent()) {
			// return ResponseEntity.notFound().build();
			throw new EntityDetailsNotFoundException("Id not found",
					new Object[]{ patient.getId() });
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

	/* get Specific Pataient Details */
	@Override
	@Transactional
	public PatientDetails getPatientById(String id) {
		Optional<PatientDetails> findById = patientRepository.findById(id);
		if (!findById.isPresent()) {
			// return ResponseEntity.notFound().build();
			throw new EntityDetailsNotFoundException("Id not found",
					new Object[]{ id });
		}
		
		
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
	public PatientDetails findPatientbyId(String id) {
		Optional<PatientDetails> findById = patientRepository.findById(id);
		if (!findById.isPresent()) {
			// return ResponseEntity.notFound().build();
			throw new EntityDetailsNotFoundException("Id not found",
					new Object[]{ id });
		}
		PatientDetails patientDetails = findById.get();
		return patientDetails;
	}

	@Override
	public PatientDetails addNewAllergy(Allergies allergies, String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
