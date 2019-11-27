package controlador;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import datos.DT_family;
import datos.DT_genus;
import datos.DT_species;
import entidades.fichas_tecnicas.Tbl_family;
import entidades.fichas_tecnicas.Tbl_genus;
import entidades.fichas_tecnicas.Tbl_species;
import util.Util;

@Controller
public class SpeciesController {
	
	@GetMapping("/fichas/species")
	public String showSpecies(HttpServletRequest req, HttpServletResponse res, Model model) {
		
		model.addAttribute("pass", 1);
		DT_species dts = new DT_species();
		
		JSONObject json = dts.getSpecies(req.getCookies());
		Tbl_species[] species = null;
		
		if(json.getInt("status") == 200) {
			species = (Tbl_species[]) json.get("species");
			String[] cookies = (String[]) json.get("cookies");
			Util.setTokenCookies(req, res, cookies);
		}
		
		model.addAttribute("species", species);
		
		return "/fichas/species.jsp";
	}
	
	@GetMapping("/fichas/newSpecie")
	public String createSpecie(HttpServletRequest req, HttpServletResponse res, Model model) {
		
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
		
		model.addAttribute("pass", 1);
		
		return "/fichas/newSpecie.jsp";
	}

}
