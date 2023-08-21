package ma.enset.hopital.springboot;

import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import ma.enset.hopital.springboot.entities.Patient;
import ma.enset.hopital.springboot.repository.PatientRepository;


@SpringBootApplication
public class HopitalApplication    {
//implements CommandLineRunner

	public static void main(String[] args) {
		SpringApplication.run(HopitalApplication.class, args);
	}

	 
 
	/*@Override
	public void run(String... args) throws Exception {
		// en utlisant No args constructor with setter and getters 
		 Patient patient = new Patient();
	     patient.setId(null);
	     patient.setNom("Mohamed");
	     patient.setDateNaissence(new Date());
	     patient.setMalade(false);
	     patient.setScore(25);
	     
	     // en utilisant Args Constructor 
		 Patient patient2 = new Patient(null,"Yassine",new Date(),false,123);
		 
		 //En utilisant Builder 
		 Patient patient3 =Patient.builder()
				 .nom("iman")
				 .dateNaissence(new Date())
				 .score(35)
				 .malade(true)
				 .build();
		 
		 // en utlisant repository 
		 patientRepository.save(patient);
		 patientRepository.save(patient2);
		 patientRepository.save(patient3);
	} */

}
