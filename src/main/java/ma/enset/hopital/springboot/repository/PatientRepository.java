package ma.enset.hopital.springboot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ma.enset.hopital.springboot.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
	
	Page<Patient> findByNomContains(String keyword, Pageable paegPageable);

}
