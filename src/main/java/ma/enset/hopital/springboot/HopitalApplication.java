package ma.enset.hopital.springboot;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import ma.enset.hopital.springboot.repository.PatientRepository;



@SpringBootApplication
public class HopitalApplication    {
//implements CommandLineRunner

	public static void main(String[] args) {
		SpringApplication.run(HopitalApplication.class, args);
	}
	
	
	@Bean	
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public CommandLineRunner commandLineRunnerJdbcUsers(JdbcUserDetailsManager jdbcUserDetailsManager) {
		PasswordEncoder passwordEncoder = passwordEncoder();
		return args -> {
			if (!jdbcUserDetailsManager.userExists("user1")) {
			jdbcUserDetailsManager.createUser(
                   User.withUsername("user1").password(passwordEncoder.encode("123")).authorities("USER").build()
					); 
			}
			if (!jdbcUserDetailsManager.userExists("user2")) {
			jdbcUserDetailsManager.createUser(
	                   User.withUsername("user2").password(passwordEncoder.encode("456")).authorities("USER").build()
						); 
			}
			if (!jdbcUserDetailsManager.userExists("admin")) {
			jdbcUserDetailsManager.createUser(
	                   User.withUsername("admin").password(passwordEncoder.encode("admin")).authorities("USER","ADMIN").build()
						);
			}
		};
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
