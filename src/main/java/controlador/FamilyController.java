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
import entidades.fichas_tecnicas.Tbl_family;
import util.Util;

@Controller
public class FamilyController {
	
	@GetMapping("/fichas/families")
	public String showFamilies(HttpServletRequest req, HttpServletResponse res, Model model) {
		DT_family dtf = new DT_family();
		
		JSONObject response = dtf.getFamilies(req.getCookies());
		Tbl_family[] families = null;
		
		if(response.getInt("status") == 200) {
			families = (Tbl_family[]) response.get("families");
			String[] cookies = (String[]) response.get("cookies");
			Util.setTokenCookies(req, res, cookies);
			
		}
		
		model.addAttribute("families", families);
		model.addAttribute("pass", 1);
		return "/fichas/families.jsp";
	}
	
	@PostMapping("/saveFamily")
	public RedirectView saveFamily(HttpServletRequest req, HttpServletResponse res, RedirectAttributes redir) {
		RedirectView rv = new RedirectView(req.getContextPath() + "/fichas/families");
		
		Tbl_family family = new Tbl_family();
		family.setName(req.getParameter("name"));
		family.setType(req.getParameter("type"));
		
		DT_family dtf = new DT_family();
		
		JSONObject json = dtf.saveFamily(family, req.getCookies());
		
		if(json.getInt("status") == 200 || json.getInt("status")==201) {
			redir.addFlashAttribute("msg", 1);
			redir.addFlashAttribute("type", "success");
			redir.addFlashAttribute("cont", "¡Registro guardado con éxito!");
		}else if(json.getInt("status") == 401 || json.getInt("status") == 0) {
			rv = new RedirectView(req.getContextPath() + "/login");
			redir.addFlashAttribute("error", 1);
			redir.addFlashAttribute("type", "success");
			redir.addFlashAttribute("msg", "¡Debe Iniciar Sesión!");
		}else if(json.getInt("status") == 400) {
			redir.addFlashAttribute("msg", 1);
			redir.addFlashAttribute("type", "warning");
			redir.addFlashAttribute("cont", "¡La familia ingresada ya existe!");
		}
		
		return rv;
	}
	
	@GetMapping("/deleteFamily")
	public RedirectView deleteFamily(HttpServletRequest req, HttpServletResponse res, RedirectAttributes redir) {
		RedirectView rv = new RedirectView(req.getContextPath() + "/fichas/families");
		
		int id = Integer.parseInt(req.getParameter("id"));
		
		DT_family dtf = new DT_family();
		JSONObject response = dtf.deleteFamily(id, req.getCookies());
		
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
