package controlador;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import datos.DT_country;
import datos.DT_family;
import datos.DT_genus;
import entidades.fichas_tecnicas.Tbl_country;
import entidades.fichas_tecnicas.Tbl_family;
import entidades.fichas_tecnicas.Tbl_genus;

@Controller
public class EspecimenController {
	
	@GetMapping("/fichas/newPlant")
	public String nuevaPlanta() {
		return "/fichas/newPlant.jsp";
	}
	
	@GetMapping("/fichas/newFungus")
	public String nuevoHongo(HttpServletRequest req, HttpServletResponse res,Model model) {
		
		DT_country dtc = new DT_country();
		JSONObject objCountry = dtc.getCountries(req.getCookies());
		
		if(objCountry.getInt("status") == 200) {
			Tbl_country[] countries = (Tbl_country[]) objCountry.get("countries");
			model.addAttribute("countries", countries);
		}
		
		DT_family dtf = new DT_family();
		JSONObject objFamily = dtf.getFamilies(req.getCookies());
		
		if(objFamily.getInt("status") == 200) {
			Tbl_family[] families = (Tbl_family[]) objFamily.get("families");
			model.addAttribute("families", families);
		}
		
		DT_genus dtg = new DT_genus();
		JSONObject objGen = dtg.getGenus(req.getCookies());
		
		if(objGen.getInt("status") == 200) {
			Tbl_genus[] genus = (Tbl_genus[]) objGen.get("genus");
			model.addAttribute("genus", genus);
		}
		
		return "/fichas/newFungus.jsp";
	}
}
