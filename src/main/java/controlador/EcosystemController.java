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

import datos.DT_ecosystem;
import entidades.fichas_tecnicas.Tbl_ecosystem;
import util.Util;

@Controller
public class EcosystemController {
	
	@GetMapping("/fichas/ecosystems")
	public String showEcosystems(HttpServletRequest req, HttpServletResponse res, Model model) {
		
		DT_ecosystem dte = new DT_ecosystem();
		JSONObject response = dte.getBioStatus(req.getCookies()); // Nombre incorrect, corregir despues
		Tbl_ecosystem[] ecosystems = null;
		
		if(response.getInt("status") == 200) {
			ecosystems = (Tbl_ecosystem[]) response.get("ecosystems");
			String[] cookies = (String[]) response.get("cookies");
			Util.setTokenCookies(req, res, cookies);
		}
		
		model.addAttribute("pass", 1);
		model.addAttribute("ecosystems", ecosystems);
		
		return "/fichas/ecosystems.jsp";
	}
	
	@PostMapping("/saveEcosystem")
	public RedirectView saveEcosystem(HttpServletRequest req, HttpServletResponse res, RedirectAttributes redir) {
		RedirectView rv = new RedirectView(req.getContextPath() + "/fichas/ecosystems");
		Tbl_ecosystem eco = new Tbl_ecosystem();
		eco.setName(req.getParameter("name"));
		
		DT_ecosystem dte = new DT_ecosystem();
		JSONObject response = dte.saveEcosystem(eco, req.getCookies());
		
		if(response.getInt("status") == 200 || response.getInt("status") == 201) {
			redir.addFlashAttribute("msg", 1);
			redir.addFlashAttribute("type", "success");
			redir.addFlashAttribute("cont", "¡Registro guardado con éxito!");
			
		}else if(response.getInt("status") == 401 || response.getInt("status") == 0) {
			rv = new RedirectView(req.getContextPath() + "/login");
			redir.addFlashAttribute("error", 1);
			redir.addFlashAttribute("type", "success");
			redir.addFlashAttribute("msg", "¡Debe Iniciar Sesión!");
			
		}else if(response.getInt("status") == 400) {
			redir.addFlashAttribute("msg", 1);
			redir.addFlashAttribute("type", "warning");
			redir.addFlashAttribute("cont", "¡El ecosistema especificado ya existe!");
		}
		
		return rv;
	}
	
	@GetMapping("/deleteEcosystem")
	public RedirectView deleteEcosystem(HttpServletRequest req, HttpServletResponse res, RedirectAttributes redir) {
		RedirectView rv = new RedirectView(req.getContextPath() + "/fichas/ecosystems");
		int id;
		try {
			
			id = Integer.parseInt(req.getParameter("id"));
		}catch(NumberFormatException e) {
			return rv;
		}
		
		DT_ecosystem dte = new DT_ecosystem();
		JSONObject response = dte.deleteEcosystem(id, req.getCookies());
		
		if(response.getInt("status") == 200 || response.getInt("status") == 204) {
			redir.addFlashAttribute("msg", 1);
			redir.addFlashAttribute("type", "success");
			redir.addFlashAttribute("cont", "¡Registro eliminado correctamente!");
			
		}else if(response.getInt("status") == 401 || response.getInt("status") == 0) {
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
	
}
