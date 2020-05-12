package controlador;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.validacion.SpecimenValidador;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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
import entidades.fichas_tecnicas.Tbl_plantSpecimen;
import entidades.fichas_tecnicas.Tbl_recolection_area;
import entidades.fichas_tecnicas.Tbl_species;
import entidades.fichas_tecnicas.Tbl_state;
import util.DateProvider;
import util.Util;

import java.io.IOException;

@Controller
public class SpecimenController {
	
	@GetMapping("/fichas/newPlant")
	public String nuevaPlanta(HttpServletRequest req, HttpServletResponse res,Model model) throws IOException {
		
		DT_country dtc = new DT_country();
		JSONObject objCountry = dtc.getCountries(req.getCookies());
		
		/*if(objCountry.getInt("status") == 200) {
			Tbl_country[] countries = (Tbl_country[]) objCountry.get("countries");
			model.addAttribute("countries", countries);
		} else {
			res.sendRedirect(req.getContextPath() + "/login");
		}*/
		
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
	
	@GetMapping("/fichas/updatePlant")
	public String updatePlant(HttpServletRequest req, HttpServletResponse res,Model model) {
		
		int id;
		try {
			id = Integer.parseInt(req.getParameter("id"));
		}catch(NumberFormatException e) {
			return "/fichas/PlantList";
		}
		
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
			
			model.addAttribute("bios", bios);
		}
		
		model.addAttribute("pass", 1);
		
		
		DT_plantSpecimen dtPlant = new DT_plantSpecimen();
		JSONObject jsPlant = dtPlant.getPlant(id, req.getCookies());
		Tbl_plantSpecimen mus = null;
		
		if(jsPlant.getInt("status") == 200) {
			mus = (Tbl_plantSpecimen) jsPlant.get("plant");
			String[] cookies = (String[]) jsPlant.get("cookies");
			Util.setTokenCookies(req, res, cookies);
			model.addAttribute("mus", mus);
		}
		
		return "/fichas/updatePlant.jsp";
	}
	
	@GetMapping("/fichas/viewPlant")
	public String showPlant(HttpServletRequest req, HttpServletResponse res,Model model) {
        
		int id;
		try {
			id = Integer.parseInt(req.getParameter("id"));
		}catch(NumberFormatException e) {
			return "/fichas/PlantList";
		}
		model.addAttribute("pass", 1);
		
		
		DT_plantSpecimen dtPlant = new DT_plantSpecimen();
		JSONObject jsPlant = dtPlant.getPlant(id, req.getCookies());
		Tbl_plantSpecimen mus = null;
		
		if(jsPlant.getInt("status") == 200) {
			mus = (Tbl_plantSpecimen) jsPlant.get("plant");
			String[] cookies = (String[]) jsPlant.get("cookies");
			Util.setTokenCookies(req, res, cookies);
			model.addAttribute("mus", mus);
		}
		
		return "/fichas/viewPlant.jsp";
	}
	
	
	
	@GetMapping("/fichas/PlantList")
	public String SpecimenPlant() {
		return "/fichas/PlantList.jsp";
	}
	
	@PostMapping("/savePlant")
	public RedirectView guardarPlanta(@RequestParam("photo") MultipartFile file, HttpServletRequest req, HttpServletResponse res,
			RedirectAttributes redir) {
		RedirectView rv = new RedirectView(req.getContextPath() + "/fichas/PlantList");

		if(!SpecimenValidador.validar(redir, req)) {
			return rv;
		}


		DT_plantSpecimen dtp = new DT_plantSpecimen();
		MultiValueMap<String, Object> newFungus = new LinkedMultiValueMap<>();
		
		int idUser = Integer.parseInt(req.getParameter("idUser"));
		int number_of_samples = Integer.parseInt(req.getParameter("number_of_samples"));

		int species = Integer.parseInt(req.getParameter("species"));
		int status = 2;
		int ecosystem = Integer.parseInt(req.getParameter("ecosystem"));
		int recolection_area_status = Integer.parseInt(req.getParameter("recolection_area_status"));

		int city = Integer.parseInt(req.getParameter("city"));
		boolean complete = Boolean.parseBoolean(req.getParameter("complete"));
		int biostatus = Integer.parseInt(req.getParameter("biostatus"));
		
		
		newFungus.add("date_received", DateProvider.getCurrentTime());
		newFungus.add("number_of_samples", number_of_samples);
		newFungus.add("description", req.getParameter("description"));
		newFungus.add("latitude", req.getParameter("latitude"));
		newFungus.add("longitude", req.getParameter("longitude"));
		newFungus.add("location", req.getParameter("location"));
		newFungus.add("aditional_info", req.getParameter("aditional_info"));
		newFungus.add("user", idUser);
		newFungus.add("complete", complete);
		newFungus.add("species", species);
		newFungus.add("status", status);
		newFungus.add("ecosystem", ecosystem);
		newFungus.add("recolection_area_status", recolection_area_status);
		newFungus.add("approved", true);

		newFungus.add("city", city);
		newFungus.add("biostatus", biostatus);

		if(file.getResource().exists()) {
			newFungus.add("photo", file.getResource());
		}

		
		
		
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
	
	@PostMapping("/actualizarPlant")
	public RedirectView actualizarPlanta(@RequestParam("photo") MultipartFile file, HttpServletRequest req, HttpServletResponse res,
			RedirectAttributes redir) {
		RedirectView rv = new RedirectView(req.getContextPath() + "/fichas/PlantList");
		DT_plantSpecimen dtp = new DT_plantSpecimen();
		MultiValueMap<String, Object> newFungus = new LinkedMultiValueMap<>();
		
		int idUser = Integer.parseInt(req.getParameter("idUser"));
		int idPlant = Integer.parseInt(req.getParameter("idPlant"));
		int number_of_samples = Integer.parseInt(req.getParameter("number_of_samples"));
		//int family = Integer.parseInt(req.getParameter("family"));
		//int genus = Integer.parseInt(req.getParameter("genus"));
		int species = Integer.parseInt(req.getParameter("species"));
		int status;
		int ecosystem = Integer.parseInt(req.getParameter("ecosystem"));
		int recolection_area_status = Integer.parseInt(req.getParameter("recolection_area_status"));

		int city = Integer.parseInt(req.getParameter("city"));
		boolean complete = Boolean.parseBoolean(req.getParameter("complete"));
		int biostatus = Integer.parseInt(req.getParameter("biostatus"));
		
		newFungus.add("id", idPlant);
		newFungus.add("number_of_samples", number_of_samples);
		newFungus.add("description", req.getParameter("description"));
		newFungus.add("latitude", req.getParameter("latitude"));
		newFungus.add("longitude", req.getParameter("longitude"));
		newFungus.add("location", req.getParameter("location"));
		newFungus.add("aditional_info", req.getParameter("aditional_info"));
		newFungus.add("user", idUser);
		newFungus.add("complete", complete);
		newFungus.add("species", species);
		
		newFungus.add("ecosystem", ecosystem);
		newFungus.add("recolection_area_status", recolection_area_status);
		//newFungus.put("status", 4);
		newFungus.add("city", city);
		newFungus.add("biostatus", biostatus);

		if(!file.isEmpty()) {
			newFungus.add("photo", file.getResource());
		}

		
		if(req.getParameter("opt") != null) {
			status = Integer.parseInt(req.getParameter("opt"));
			newFungus.add("status", status);
		}else {
			newFungus.add("status", 2);
		}
		

		JSONObject respuesta = dtp.actualizarPlantaPatch(newFungus, req.getCookies(), idPlant);
		
		if(respuesta.getInt("status") == 201 || respuesta.getInt("status") == 200) {
			rv = new RedirectView(req.getContextPath() + "/fichas/PlantList");
			redir.addFlashAttribute("msg", 1);
			redir.addFlashAttribute("type", "success");
			redir.addFlashAttribute("cont", "¡Se ha actualizado correctamente!");
		}else if(respuesta.getInt("status") == 401 	|| respuesta.getInt("status") == 0)
		{
			rv = new RedirectView(req.getContextPath() + "/login");
			redir.addFlashAttribute("error", 1);
			redir.addFlashAttribute("type", "info");
			redir.addFlashAttribute("cont", "¡Debe iniciar sesión!");
		}
		
		
		
		return rv;
	}
	
	@GetMapping("/deletePlant")
	public RedirectView eliminarPlanta(HttpServletRequest req, HttpServletResponse res, RedirectAttributes redir) {
		RedirectView rv = new RedirectView(req.getContextPath() + "/fichas/PlantList");
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		
		int id;
		
		try {
			id = Integer.parseInt(req.getParameter("id"));
			
		}catch(NumberFormatException e) {
			return rv;
		}
		
		DT_plantSpecimen dtm = new DT_plantSpecimen();
		body.add("status", 5);
		
		JSONObject response = dtm.actualizarPlantaPatch(body, req.getCookies(), id);
		
		if(response.getInt("status") == 200 || response.getInt("status") == 204) {
			redir.addFlashAttribute("msg", 1);
			redir.addFlashAttribute("type", "success");
			redir.addFlashAttribute("cont", "¡Registro eliminado correctamente!");
		}else if(response.getInt("status") == 401 || response.getInt("status") == 0)
		{
			rv = new RedirectView(req.getContextPath() + "/login");
			redir.addFlashAttribute("error", 1);
			redir.addFlashAttribute("type", "info");
			redir.addFlashAttribute("cont", "¡Debe iniciar sesión!");
		}else if(response.getInt("status") == 500) {
			redir.addFlashAttribute("msg", 1);
			redir.addFlashAttribute("type", "danger");
			redir.addFlashAttribute("cont", "¡Ha ocurrido un error en el servidor y falló el proceso!");
		}
		
		return rv;
	}
	
	@GetMapping("/fichas/receivedPlants")
	public String showReceivedPlants(HttpServletRequest req, HttpServletResponse res,Model model) {
		
		return "/fichas/FichasList.jsp";
	}
	
	@GetMapping("/fichas/checkPlant")
	public String checkPlant(HttpServletRequest req, HttpServletResponse res,Model model) {
		
		int id;
		try {
			id = Integer.parseInt(req.getParameter("id"));
		}catch(NumberFormatException e) {
			return "/fichas/PlantList.jsp";
		}
		
		DT_plantSpecimen dtp = new DT_plantSpecimen();
		JSONObject response = dtp.getPlant(id, req.getCookies());
		Tbl_plantSpecimen mus = null;
		
		if(response.getInt("status") == 200) {
			mus = (Tbl_plantSpecimen) response.get("plant");
			String[] cookies = (String[]) response.get("cookies");
			Util.setTokenCookies(req, res, cookies);
			model.addAttribute("mus", mus);
		}
		
		model.addAttribute("pass", 1);
		
		return "/fichas/checkPlant.jsp";
	}
	
	@PostMapping("/parsePlant")
	public RedirectView parsePlanta( HttpServletRequest req, HttpServletResponse res,
			RedirectAttributes redir) {
		RedirectView rv = new RedirectView(req.getContextPath() + "/fichas/receivedPlants");
		
		int idUser = Integer.parseInt(req.getParameter("idUser"));
		int idPlant = Integer.parseInt(req.getParameter("idPlant"));
		MultiValueMap<String, Object> newFungus = new LinkedMultiValueMap<>();
		
		DT_plantSpecimen dtp = new DT_plantSpecimen();
		JSONObject response = dtp.getPlant(idPlant, req.getCookies());
		Tbl_plantSpecimen spe = null;
		
		if(response.getInt("status") == 200) {
			spe = (Tbl_plantSpecimen) response.get("plant");
		}else {
			return rv;
		}
		

		newFungus.add("number_of_samples", spe.getNumber_of_samples());
		newFungus.add("description", spe.getDescription());
		newFungus.add("latitude", spe.getLatitude());
		newFungus.add("longitude", spe.getLongitude());
		newFungus.add("location", "Nicaragua");
		newFungus.add("aditional_info", "-");
		newFungus.add("user", idUser);
		newFungus.add("complete", spe.getComplete());
		newFungus.add("species", spe.getSpecies().getId());
		newFungus.add("ecosystem", spe.getEcosystem().getId());
		newFungus.add("recolection_area_status", spe.getRecolection_area_status().getId());
		newFungus.add("status", 2);

		//newFungus.put("status", 4);
		newFungus.add("city", spe.getCity().getId());
		newFungus.add("biostatus",spe.getBiostatus().getId());
		
		if(req.getParameter("opt") != null) {
			boolean status = Boolean.parseBoolean(req.getParameter("opt"));
			newFungus.add("approved", status);
		}else {
			newFungus.add("approved", false);
		}
		
       JSONObject respuesta = dtp.actualizarPlantaPatch(newFungus, req.getCookies(), idPlant);

		
		if(respuesta.getInt("status") == 201 || respuesta.getInt("status") == 200) {
			//rv = new RedirectView(req.getContextPath() + "/fichas/PlantList");
			redir.addFlashAttribute("msg", 1);
			redir.addFlashAttribute("type", "success");
			redir.addFlashAttribute("cont", "¡Se ha procesado correctamente!");
		}else if(respuesta.getInt("status") == 401 	|| respuesta.getInt("status") == 0)
		{
			rv = new RedirectView(req.getContextPath() + "/login");
			redir.addFlashAttribute("error", 1);
			redir.addFlashAttribute("type", "info");
			redir.addFlashAttribute("cont", "¡Debe iniciar sesión!");
		}


		
		return rv;
	}
	
}

