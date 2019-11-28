package controlador;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import datos.DT_BioStatus;
import datos.DT_city;
import datos.DT_country;
import datos.DT_ecosystem;
import datos.DT_family;
import datos.DT_genus;
import datos.DT_mushroom;
import datos.DT_recolection_area;
import datos.DT_species;
import datos.DT_state;
import datos.fichas_tecnicas.DT_plantSpecimen;
import entidades.fichas_tecnicas.Tbl_biostatus;
import entidades.fichas_tecnicas.Tbl_city;
import entidades.fichas_tecnicas.Tbl_country;
import entidades.fichas_tecnicas.Tbl_ecosystem;
import entidades.fichas_tecnicas.Tbl_family;
import entidades.fichas_tecnicas.Tbl_genus;
import entidades.fichas_tecnicas.Tbl_recolection_area;
import entidades.fichas_tecnicas.Tbl_species;
import entidades.fichas_tecnicas.Tbl_state;
import util.Util;

@Controller
public class SpecimenController {
	
	@GetMapping("/fichas/newPlant")
	public String nuevaPlanta(HttpServletRequest req, HttpServletResponse res,Model model) {
		
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
		
		DT_city dtCity = new DT_city();
		JSONObject objCity = dtCity.getCountries(req.getCookies());
		
		if(objCity.getInt("status") == 200) {
			Tbl_city[] cities = (Tbl_city[]) objCity.get("cities");
			model.addAttribute("cities", cities);
		}
		
		DT_ecosystem dtEco = new DT_ecosystem();
		JSONObject jEco = dtEco.getBioStatus(req.getCookies());
		
		if(jEco.getInt("status") == 200) {
			Tbl_ecosystem[] ecosystems = (Tbl_ecosystem[]) jEco.get("ecosystems");
			model.addAttribute("ecosystems", ecosystems);
		}
		
		DT_recolection_area dra = new DT_recolection_area();
		JSONObject jDra = dra.getReco(req.getCookies());
		
		if(jDra.getInt("status") == 200) {
			Tbl_recolection_area[] reco = (Tbl_recolection_area[]) jDra.get("reco");
			model.addAttribute("reco", reco);
		}
		
		DT_species dtSpe = new DT_species();
		JSONObject jSpe = dtSpe.getSpecies(req.getCookies());
		
		if(jSpe.getInt("status") == 200) {
			Tbl_species[] species = (Tbl_species[]) jSpe.get("species");
			model.addAttribute("species", species);
		}
		
		DT_state dtSt = new DT_state();
		JSONObject jSt = dtSt.getFamilies(req.getCookies());
		
		if(jSt.getInt("status") == 200) {
			Tbl_state[] states = (Tbl_state[]) jSt.get("states");
			model.addAttribute("states", states);
		}
		
		DT_BioStatus dtbio = new DT_BioStatus();
		JSONObject jsBio = dtbio.getBioStatus(req.getCookies());
		
		if(jsBio.getInt("status") == 200) {
			Tbl_biostatus[] bios = (Tbl_biostatus[]) jsBio.get("biostatus");
			String[] cookies = (String[]) jsBio.get("cookies");
			Util.setTokenCookies(req, res, cookies);
			model.addAttribute("bios", bios);
		}
		
		model.addAttribute("pass", 1);
		
		return "/fichas/newPlant.jsp";
	}
	
	@GetMapping("/fichas/PlantList")
	public String SpecimenPlant() {
		return "/fichas/PlantList.jsp";
	}
	
	@PostMapping("/savePlant")
	public RedirectView guardarPlanta(@RequestParam("photo") MultipartFile file, HttpServletRequest req, HttpServletResponse res,
			RedirectAttributes redir) {
		RedirectView rv = new RedirectView(req.getContextPath() + "/fichas/newFungus");
		DT_plantSpecimen dtp = new DT_plantSpecimen();
		JSONObject newFungus = new JSONObject();
		
		int idUser = Integer.parseInt(req.getParameter("idUser"));
		int number_of_samples = Integer.parseInt(req.getParameter("number_of_samples"));
		int family = Integer.parseInt(req.getParameter("family"));
		int genus = Integer.parseInt(req.getParameter("genus"));
		int species = Integer.parseInt(req.getParameter("species"));
		int status = 2;
		int ecosystem = Integer.parseInt(req.getParameter("ecosystem"));
		int recolection_area_status = Integer.parseInt(req.getParameter("recolection_area_status"));
		int country = Integer.parseInt(req.getParameter("country"));
		int state = Integer.parseInt(req.getParameter("state"));
		int city = Integer.parseInt(req.getParameter("city"));
		boolean complete = Boolean.parseBoolean(req.getParameter("complete"));
		int biostatus = Integer.parseInt(req.getParameter("biostatus"));
		
		
		newFungus.put("date_received", "2019-11-25");
		newFungus.put("number_of_samples", number_of_samples);
		newFungus.put("description", req.getParameter("description"));
		newFungus.put("latitude", req.getParameter("latitude"));
		newFungus.put("longitude", req.getParameter("longitude"));
		newFungus.put("location", "Nicaragua");
		newFungus.put("aditional_info", req.getParameter("aditional_info"));
		newFungus.put("user", idUser);
		newFungus.put("family", family);
		newFungus.put("complete", complete);
		newFungus.put("genus", genus);
		newFungus.put("species", species);
		newFungus.put("status", status);
		newFungus.put("ecosystem", ecosystem);
		newFungus.put("recolection_area_status", recolection_area_status);
		newFungus.put("country", country);
		newFungus.put("state", state);
		newFungus.put("city", city);
		newFungus.put("biostatus", biostatus);
		
		
		
		/*if(file.getContentType() != null) {
			
			servicioArchivo = new ServicioArchivo();
			
			if(servicioArchivo.subirArchivo(file) == false) {
				rv = new RedirectView(req.getContextPath() + "/fichas/newFungus");
				redir.addFlashAttribute("msg", 1);
				redir.addFlashAttribute("type", "danger");
				redir.addFlashAttribute("cont", "¡Ha ocurrido un error guardando el registro!");
				return rv;
			}
			
			newFungus.put("photo","/storage/" + file.getOriginalFilename());
		}*/
		JSONObject respuesta = dtp.guardarPlanta(newFungus, req.getCookies());
		
		if(respuesta.getInt("status") == 201 || respuesta.getInt("status") == 200) {
			rv = new RedirectView(req.getContextPath() + "/fichas/PlantList");
			redir.addFlashAttribute("msg", 1);
			redir.addFlashAttribute("type", "success");
			redir.addFlashAttribute("cont", "¡Se ha guardado correctamente!");
		}else if(respuesta.getInt("status") == 401)
		{
			rv = new RedirectView(req.getContextPath() + "/login");
			redir.addFlashAttribute("error", 1);
			redir.addFlashAttribute("type", "info");
			redir.addFlashAttribute("cont", "¡Debe iniciar sesión!");
		}
		
		
		
		return rv;
	}
}

