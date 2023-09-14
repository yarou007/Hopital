package ma.enset.hopital.springboot.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

	@GetMapping("/notAuthorized")
	public String notAuthorzied(){
		return "403";
	}
}
