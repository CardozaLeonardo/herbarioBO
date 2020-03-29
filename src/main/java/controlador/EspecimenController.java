package controlador;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
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

import datos.DT_capType;
import datos.DT_city;
import datos.DT_country;
import datos.DT_ecosystem;
import datos.DT_family;
import datos.DT_formType;
import datos.DT_genus;
import datos.DT_mushroom;
import datos.DT_recolection_area;
import datos.DT_rol;
import datos.DT_species;
import datos.DT_state;
import entidades.fichas_tecnicas.Tbl_capType;
import entidades.fichas_tecnicas.Tbl_city;
import entidades.fichas_tecnicas.Tbl_country;
import entidades.fichas_tecnicas.Tbl_ecosystem;
import entidades.fichas_tecnicas.Tbl_family;
import entidades.fichas_tecnicas.Tbl_formType;
import entidades.fichas_tecnicas.Tbl_genus;
import entidades.fichas_tecnicas.Tbl_mushroomSpecimen;
import entidades.fichas_tecnicas.Tbl_recolection_area;
import entidades.fichas_tecnicas.Tbl_species;
import entidades.fichas_tecnicas.Tbl_state;
import util.ServicioArchivo;
import util.Util;

import java.io.IOException;

@Controller
public class EspecimenController {
	
	//@Autowired
	ServicioArchivo servicioArchivo;
	
	@GetMapping("/fichas/fungus")
	public String showFungus(HttpServletRequest req,Model model) {
		
		model.addAttribute("pass", 1);
		DT_mushroom dtm = new DT_mushroom();
		JSONObject obj = dtm.getCountries(req.getCookies());
		
		if(obj.getInt("status") == 200) {
			Tbl_mushroomSpecimen[] fungus = (Tbl_mushroomSpecimen[]) obj.get("fungus");
			model.addAttribute("fungus", fungus);
		}
		
		return "/fichas/fungus.jsp";
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
		
		DT_formType dtft = new DT_formType();
		JSONObject dtfo = dtft.getCountries(req.getCookies());
		
		if(dtfo.getInt("status") == 200) {
			Tbl_formType[] forms = (Tbl_formType[]) dtfo.get("forms");
			model.addAttribute("forms", forms);
		}
		
		DT_capType dtCap = new DT_capType();
		JSONObject objCap = dtCap.getCountries(req.getCookies());
		
		if(objCap.getInt("status") == 200) {
			Tbl_capType[] caps = (Tbl_capType[]) objCap.get("caps");
			model.addAttribute("caps", caps);
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
		
		model.addAttribute("pass", 1);
		
		return "/fichas/newFungus.jsp";
	}
	
	@GetMapping("/fichas/updateFungus")
	public String updateFungus(HttpServletRequest req, HttpServletResponse res, Model model) {
		int id = Integer.parseInt(req.getParameter("id"));
		
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
		
		DT_formType dtft = new DT_formType();
		JSONObject dtfo = dtft.getCountries(req.getCookies());
		
		if(dtfo.getInt("status") == 200) {
			Tbl_formType[] forms = (Tbl_formType[]) dtfo.get("forms");
			model.addAttribute("forms", forms);
		}
		
		DT_capType dtCap = new DT_capType();
		JSONObject objCap = dtCap.getCountries(req.getCookies());
		
		if(objCap.getInt("status") == 200) {
			Tbl_capType[] caps = (Tbl_capType[]) objCap.get("caps");
			model.addAttribute("caps", caps);
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
		
		model.addAttribute("pass", 1);
		
		DT_mushroom dtmus = new DT_mushroom();
		JSONObject jMus = dtmus.getFungus(id, req.getCookies());
		
		if(jMus.getInt("status") == 200) {
			Tbl_mushroomSpecimen mus = (Tbl_mushroomSpecimen) jMus.get("fungus");
			model.addAttribute("mus", mus);
		}
		
		return "/fichas/updateFungus.jsp";
	}
	
	@GetMapping("/fichas/viewFungus")
	public String viewFungus(HttpServletRequest req, HttpServletResponse res, Model model) {
		int id = Integer.parseInt(req.getParameter("id"));
		
		model.addAttribute("pass", 1);
		
		DT_mushroom dtmus = new DT_mushroom();
		JSONObject jMus = dtmus.getFungus(id, req.getCookies());
		
		if(jMus.getInt("status") == 200) {
			Tbl_mushroomSpecimen mus = (Tbl_mushroomSpecimen) jMus.get("fungus");
			model.addAttribute("mus", mus);
			String[] cookies = (String[]) jMus.get("cookies");
			Util.setTokenCookies(req, res, cookies);
		}
		
		return "/fichas/viewFungus.jsp";
	}
	
	
	@GetMapping("/fichas/especimenPlanta")
	public String especimenPlanta(HttpServletRequest req, HttpServletResponse res,Model model) {
		return "/fichas/PlantList.jsp";
	}
	
	
	@PostMapping("/guardarHongo")
	public RedirectView guardarHongo(@RequestParam("photo") MultipartFile file, HttpServletRequest req, HttpServletResponse res,
			RedirectAttributes redir) {
		RedirectView rv = new RedirectView(req.getContextPath() + "/fichas/newFungus");
		DT_mushroom dtm = new DT_mushroom();
		MultiValueMap<String, Object> newFungus = new LinkedMultiValueMap<>();
		
		int idUser = Integer.parseInt(req.getParameter("idUser"));
		int cap = Integer.parseInt(req.getParameter("cap"));
		int forms = Integer.parseInt(req.getParameter("forms"));
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
		boolean crust = Boolean.parseBoolean(req.getParameter("crust"));
		
		newFungus.add("cap", cap);
		newFungus.add("forms", forms);
		newFungus.add("date_received", "2019-11-25");
		newFungus.add("number_of_samples", number_of_samples);
		newFungus.add("description", req.getParameter("description"));
		newFungus.add("latitude", req.getParameter("latitude"));
		newFungus.add("longitude", req.getParameter("longitude"));
		newFungus.add("location", "Nicaragua");
		newFungus.add("crust", crust);
		newFungus.add("color", req.getParameter("color"));
		newFungus.add("change_of_color", req.getParameter("change_of_color"));
		newFungus.add("smell", req.getParameter("smell"));
		newFungus.add("aditional_info", req.getParameter("aditional_info"));
		newFungus.add("user", idUser);
		newFungus.add("family", family);
		newFungus.add("genus", genus);
		newFungus.add("species", species);
		newFungus.add("status", status);
		newFungus.add("ecosystem", ecosystem);
		newFungus.add("recolection_area_status", recolection_area_status);
		newFungus.add("country", country);
		newFungus.add("state", state);
		newFungus.add("city", city);
		newFungus.add("photo", file.getResource());
		
		
		
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
		JSONObject respuesta = dtm.guardarHongo(newFungus, req.getCookies());
		
		if(respuesta.getInt("status") == 201) {
			rv = new RedirectView(req.getContextPath() + "/fichas/fungus");
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
	
	@PostMapping("/actualizarHongo")
	public RedirectView actualizarHongo(@RequestParam("photo") MultipartFile file, HttpServletRequest req, HttpServletResponse res,
			RedirectAttributes redir) {
		RedirectView rv = new RedirectView(req.getContextPath() + "/fichas/newFungus");
		DT_mushroom dtm = new DT_mushroom();
		
		int idUser = Integer.parseInt(req.getParameter("idUser"));
		int id = Integer.parseInt(req.getParameter("fungusID"));
		int cap = Integer.parseInt(req.getParameter("cap"));
		int forms = Integer.parseInt(req.getParameter("forms"));
		int number_of_samples = Integer.parseInt(req.getParameter("number_of_samples"));
		int family = Integer.parseInt(req.getParameter("family"));
		int genus = Integer.parseInt(req.getParameter("genus"));
		int species = Integer.parseInt(req.getParameter("species"));
		int status;
		int ecosystem = Integer.parseInt(req.getParameter("ecosystem"));
		int recolection_area_status = Integer.parseInt(req.getParameter("recolection_area_status"));
		int country = Integer.parseInt(req.getParameter("country"));
		int state = Integer.parseInt(req.getParameter("state"));
		int city = Integer.parseInt(req.getParameter("city"));
		boolean crust = Boolean.parseBoolean(req.getParameter("crust"));


		/*FileSystemResource fileSystemResource = null;
		try {
			fileSystemResource = new FileSystemResource(file.getInputStream());
			fileSystemResource = file.getInputStream();
		} catch (Exception e) {
			e.printStackTrace();
		}*/

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		//body.add("id",id);
		body.add("cap",cap);
		body.add("forms", forms);
		body.add("date_received", "2019-11-25");
		body.add("number_of_samples", number_of_samples);
		body.add("description", req.getParameter("description"));
		body.add("latitude", req.getParameter("latitude"));
		body.add("longitude", req.getParameter("longitude"));
		body.add("location", "Nicaragua");
		body.add("crust", crust);
		body.add("color", req.getParameter("color"));
		body.add("change_of_color", req.getParameter("change_of_color"));
		body.add("smell", req.getParameter("smell"));
		body.add("aditional_info", req.getParameter("aditional_info"));
		body.add("user", idUser);
		body.add("family", family);
		body.add("genus", genus);
		body.add("species", species);
		body.add("ecosystem", ecosystem);
		body.add("recolection_area_status", recolection_area_status);
		body.add("country", country);
		body.add("state", state);
		body.add("city", city);
		body.add("description", req.getParameter("description"));
		body.add("photo", file.getResource());
		/*try {
			body.add("photo", new ClassPathResource(file.getResource().getFile().getPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}*/


		if(req.getParameter("opt") != null) {
			status = Integer.parseInt(req.getParameter("opt"));
			body.add("status", status);
		}else {
			body.add("status", 2);
		}
		

		JSONObject respuesta = dtm.actualizarHongo(body, req.getCookies(), id);
		
		if(respuesta.getInt("status") == 200) {
			rv = new RedirectView(req.getContextPath() + "/fichas/fungus");
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
	
	
	@GetMapping("/deleteFungus")
	public RedirectView eliminarHongo(HttpServletRequest req, HttpServletResponse res, RedirectAttributes redir) {
		RedirectView rv = new RedirectView(req.getContextPath() + "/fichas/fungus");
		
		int id = Integer.parseInt(req.getParameter("id"));
		
		DT_mushroom dtm = new DT_mushroom();
		
		JSONObject response = dtm.deleteFungus(id, req.getCookies());
		
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
	
	@GetMapping("/fichas/receivedFungus")
	public String showReceivedFungus(HttpServletRequest req, HttpServletResponse res,Model model) {
		return "/fichas/FungusList.jsp";
	}
	
	@GetMapping("/fichas/checkFungus")
	public String checkPlant(HttpServletRequest req, HttpServletResponse res, Model model) {
		
		int id;
		try {
			id = Integer.parseInt(req.getParameter("id"));
		}catch(NumberFormatException e) {
			return "/fichas/FungusList.jsp";
		}
		
		DT_mushroom dtm = new DT_mushroom();
		JSONObject response = dtm.getFungus(id, req.getCookies());
		Tbl_mushroomSpecimen mus = null;
		
		if(response.getInt("status") == 200) {
			mus = (Tbl_mushroomSpecimen) response.get("fungus");
			String[] cookies = (String[]) response.get("cookies");
			Util.setTokenCookies(req, res, cookies);
			model.addAttribute("mus", mus);
		}
		
		model.addAttribute("pass", 1);
		
		return "/fichas/checkFungus.jsp";
	}
}
