package controlador;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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
	
	@GetMapping("/fichas/viewSpecie")
    public String showSpecie(HttpServletRequest req, HttpServletResponse res, Model model) {
		
		model.addAttribute("pass", 1);
		int id = Integer.parseInt(req.getParameter("id"));
		DT_species dts = new DT_species();
		
		JSONObject json = dts.getSpecie(id, req.getCookies());
		Tbl_species specie = null;
		
		if(json.getInt("status") == 200) {
			specie = (Tbl_species) json.get("specie");
			String[] cookies = (String[]) json.get("cookies");
			Util.setTokenCookies(req, res, cookies);
		}
		
		model.addAttribute("specie", specie);
		
		return "/fichas/viewSpecie.jsp";
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
			String[] cookies = (String[]) objGen.get("cookies");
			Util.setTokenCookies(req, res, cookies);
		}
		
		model.addAttribute("pass", 1);
		
		return "/fichas/newSpecie.jsp";
	}
	
	@GetMapping("/fichas/updateSpecie")
	public String actualizarSpecie(HttpServletRequest req, HttpServletResponse res, Model model) {
		
		int id = Integer.parseInt(req.getParameter("id"));
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
		
        DT_species dts = new DT_species();
		
		JSONObject json = dts.getSpecie(id, req.getCookies());
		Tbl_species specie = null;
		
		if(json.getInt("status") == 200) {
			specie = (Tbl_species) json.get("specie");
			String[] cookies = (String[]) json.get("cookies");
			Util.setTokenCookies(req, res, cookies);
		}
		
		model.addAttribute("specie", specie);
		
		model.addAttribute("pass", 1);
		
		return "/fichas/updateSpecie.jsp";
	}
	
	@PostMapping("/saveSpecie")
	public RedirectView saveSpecie(HttpServletRequest req, HttpServletResponse res, RedirectAttributes redir) {
		RedirectView rv = new RedirectView(req.getContextPath() + "/fichas/species");
		DT_species dts = new DT_species();
		
		int family = Integer.parseInt(req.getParameter("family"));
		int genus = Integer.parseInt(req.getParameter("genus"));
		
		JSONObject data = new JSONObject();
		data.put("common_name", req.getParameter("common_name"));
		data.put("scientific_name", req.getParameter("scientific_name"));
		data.put("description", req.getParameter("description"));
		data.put("genus", genus);
		
		JSONObject response = dts.saveSpecie(data, req.getCookies());
		
		if(response.getInt("status") == 200 || response.getInt("status") == 201) {
			redir.addFlashAttribute("msg", 1);
			redir.addFlashAttribute("type", "success");
			redir.addFlashAttribute("cont", "¡Registro guardado con éxito!");
		}else if(response.getInt("status") == 401 || response.getInt("status") == 0) {
			rv = new RedirectView(req.getContextPath() + "/login");
			redir.addFlashAttribute("error", 1);
			redir.addFlashAttribute("type", "success");
			redir.addFlashAttribute("msg", "¡Debe Iniciar Sesión!");
		}
		
		return rv;
	}
	
	
	@PostMapping("/updateSpecie")
	public RedirectView updateSpecie(HttpServletRequest req, HttpServletResponse res, RedirectAttributes redir) {
		RedirectView rv = new RedirectView(req.getContextPath() + "/fichas/species");
		DT_species dts = new DT_species();
		
		int family = Integer.parseInt(req.getParameter("family"));
		int genus = Integer.parseInt(req.getParameter("genus"));
		int id = Integer.parseInt(req.getParameter("idSpecie"));
		
		JSONObject data = new JSONObject();
		data.put("common_name", req.getParameter("common_name"));
		data.put("scientific_name", req.getParameter("scientific_name"));
		data.put("description", req.getParameter("description"));
		data.put("type", req.getParameter("type"));
		data.put("family", family);
		data.put("genus", genus);
		data.put("id", id);
		
		JSONObject response = dts.updateSpecie(data, req.getCookies());
		
		if(response.getInt("status") == 200) {
			redir.addFlashAttribute("msg", 1);
			redir.addFlashAttribute("type", "success");
			redir.addFlashAttribute("cont", "¡Registro actualizado con éxito!");
			
			String[] cookies = (String[]) response.get("cookies");
			Util.setTokenCookies(req, res, cookies);
			
		}else if(response.getInt("status") == 401 || response.getInt("status") == 0) {
			rv = new RedirectView(req.getContextPath() + "/login");
			redir.addFlashAttribute("error", 1);
			redir.addFlashAttribute("type", "info");
			redir.addFlashAttribute("msg", "¡Debe Iniciar Sesión!");
		}
		
		return rv;
	}
	
	@GetMapping("/deleteSpecie")
	public RedirectView deleteSpecie(HttpServletRequest req, HttpServletResponse res, RedirectAttributes redir) {
		RedirectView rv = new RedirectView(req.getContextPath() + "/fichas/species");
		DT_species dts = new DT_species();
		
		int id = Integer.parseInt(req.getParameter("id"));
		
         JSONObject response = dts.deleteSpecie(id, req.getCookies());
		
		if(response.getInt("status") == 200 || response.getInt("status") == 204) {
			redir.addFlashAttribute("msg", 1);
			redir.addFlashAttribute("type", "success");
			redir.addFlashAttribute("cont", "¡Registro eliminado con éxito!");
			
			String[] cookies = (String[]) response.get("cookies");
			Util.setTokenCookies(req, res, cookies);
			
		}else if(response.getInt("status") == 401 || response.getInt("status") == 0) {
			rv = new RedirectView(req.getContextPath() + "/login");
			redir.addFlashAttribute("error", 1);
			redir.addFlashAttribute("type", "info");
			redir.addFlashAttribute("msg", "¡Debe Iniciar Sesión!");
		}
		
		return rv;
	}

}
