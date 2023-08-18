package ma.enset.hopital.springboot.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import ma.enset.hopital.springboot.entities.Patient;
import ma.enset.hopital.springboot.repository.PatientRepository;

@Controller
public class PatientController {

	@Autowired
	private PatientRepository patientRepository;


	@GetMapping("/index")
	public String index(Model model,@RequestParam(name="page", defaultValue = "0") int page,@RequestParam(name="size", defaultValue = "4")int size
			,@RequestParam(name="keyword", defaultValue = "") String kw) {
		Page<Patient> pagePatients= patientRepository.findByNomContains(kw,PageRequest.of(page, size));// went to repo and findAll
		model.addAttribute("ListPatients",pagePatients.getContent()); // stock el list fi model sous le nom de ListPatients
		model.addAttribute("pages",new int[pagePatients.getTotalPages()]);
		model.addAttribute("currentPage",page);
		model.addAttribute("keyword",kw);

		return "patients";
	}
	
	@GetMapping("/delete")
	public String delete(Long id, String keyword, int page) {
		patientRepository.deleteById(id);
		return "redirect:/index?page="+page+"&keyword="+keyword;
	}
	
    @GetMapping("/")
    public String home(@RequestParam(name="page", defaultValue = "0") int page,@RequestParam(name="size", defaultValue = "4")int size,
    		@RequestParam(name="keyword", defaultValue = "") String kw) {
    	return"redirect:index";
    }

    @GetMapping("/formPatients")
    public String formPatient(Model model) {
    	
    	model.addAttribute("patient",new Patient());
    	
    	return "formPatient";
    }
    @PostMapping("/savePatient")
    public String savePatient(@Validated Patient patient, BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) {return "formPatient";}
        patientRepository.save(patient);
    	return "redirect:/index?keyword="+patient.getNom();
    }
    
    @GetMapping("/editPatient")
    public String editPatient(Long id ,Model model) {
    	Patient p = patientRepository.findById(id).get();
    	model.addAttribute("patient",p); 
    	return "formEditPatient";
    }
    
    @PostMapping("/editDbPatient")
    public String editDbPatient(@Validated Patient patient, BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) {return "formEditPatient";}
    	else patientRepository.save(patient);
    	return "redirect:/index?keyword="+patient.getNom();
    		
    }
    


}

