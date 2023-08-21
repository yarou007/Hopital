package ma.enset.hopital.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class WebConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.
	       authorizeHttpRequests().anyRequest().authenticated()
           .and()
	       .formLogin();
		   return http.build();
	}
	
	@Bean
	public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
		
		
		InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager(
				User.withUsername("user1").password("{noop}123").roles("USER").build(),
				User.withUsername("user2").password("{noop}456").roles("USER").build());
		
		return  inMemoryUserDetailsManager;
				
	}
}
