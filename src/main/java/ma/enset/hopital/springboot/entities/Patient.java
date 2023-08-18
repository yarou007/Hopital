package ma.enset.hopital.springboot.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Patient {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//@NotEmpty // je ne laisse pas ni les chaines vides (mais accept les espaces ) ni les nulls 
	@NotBlank // n'accepte pas les espaces et aussi n'accepte pas les chaines vides , ni les nulls
	@Size(min = 4,max = 20)
	private String nom;
	
	@NotNull // je passe les chaines vides et les espaces passe mais n'accepte pas les null
	
	@Temporal(TemporalType.DATE)
	private Date dateNaissence;

	private boolean malade;
    @Min(50)	
	private int score;
	
}
