package ma.enset.hopital.springboot.configg;

import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class WebConfig {
	
	@Autowired
	PasswordEncoder passwordEncoder;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       http
         .authorizeHttpRequests((authz) -> authz
             .requestMatchers("/admin/**").hasAnyAuthority("ADMIN")
             .anyRequest().authenticated()
            
         );
		 http 
         .exceptionHandling().accessDeniedPage("/notAuthorized");
    http 
		.formLogin(
			    form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/index")
                .permitAll());
    http .logout(
            logout -> logout
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .permitAll()
    );
		return http.build();
	}
    
    // jdbc = java database connectivity 
    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource datasource) {
    	
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(datasource);
	 return jdbcUserDetailsManager;
    	
    }
	
	/*
	@Bean
	public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
		
		
		InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager(
				User.withUsername("user1").password(passwordEncoder.encode("123")).roles("ADMIN").build(),
				User.withUsername("user2").password(passwordEncoder.encode("456")).roles("USER").build());
		
		return  inMemoryUserDetailsManager;			
	}*/
}
