package controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpecimenController {
	
	@GetMapping("/fichas/newPlant")
	public String nuevaPlanta() {
		return "/fichas/newPlant.jsp";
	}
	
	@GetMapping("/fichas/especimen-planta")
	public String SpecimenPlant() {
		return "/fichas/PlantList.jsp";
	}
}

