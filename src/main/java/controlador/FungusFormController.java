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

import datos.DT_capType;
import datos.DT_formType;
import entidades.fichas_tecnicas.Tbl_capType;
import entidades.fichas_tecnicas.Tbl_formType;
import util.Util;

@Controller
public class FungusFormController {
	
	@GetMapping("/fichas/fungusForms")
	public String showForms(HttpServletRequest req, HttpServletResponse res, Model model) {
		
		DT_formType dtf = new DT_formType();
		JSONObject response = dtf.getCountries(req.getCookies());
		Tbl_formType[] forms = null;
		
		if(response.getInt("status") == 200) {
			forms = (Tbl_formType[]) response.get("forms");
			String[] cookies = (String[]) response.get("cookies");
			Util.setTokenCookies(req, res, cookies);
		}
		
		model.addAttribute("pass", 1);
		model.addAttribute("forms", forms);
		
		
		return "/fichas/fungusForms.jsp";
	}
	
	@PostMapping("/saveForm")
	public RedirectView saveForm(HttpServletRequest req, HttpServletResponse res, RedirectAttributes redir){

		RedirectView rv = new RedirectView(req.getContextPath() + "/fichas/fungusForms");
		Tbl_formType form = new Tbl_formType();
		DT_formType dtf = new DT_formType();
		
		form.setName(req.getParameter("name"));

		JSONObject response = dtf.saveForm(form, req.getCookies());

		if(response.getInt("status") == 200 || response.getInt("status") == 201){
			redir.addFlashAttribute("msg", 1);
			redir.addFlashAttribute("type", "success");
			redir.addFlashAttribute("cont", "¡Registro creado correctamente!");

		}else if(response.getInt("status") == 401 || response.getInt("status") == 0){
			rv = new RedirectView(req.getContextPath() + "/login");
			redir.addFlashAttribute("error", 1);
			redir.addFlashAttribute("type", "info");
			redir.addFlashAttribute("msg", "¡Debe Iniciar Sesión!");

		}else if(response.getInt("status") == 400){
			redir.addFlashAttribute("msg", 1);
			redir.addFlashAttribute("type", "warning");
			redir.addFlashAttribute("cont", "¡La forma especificada ya existe!");
		}

		return rv;
	}
	
	@GetMapping("/deleteForm")
	public RedirectView deleteForm(HttpServletRequest req, HttpServletResponse res, RedirectAttributes redir) {
		RedirectView rv = new RedirectView(req.getContextPath() + "/fichas/fungusForms");
		int id;
		System.out.println("ID:" + req.getAttribute("javax.servlet.forward.request_uri"));
		try{
			id = Integer.parseInt(req.getParameter("id"));
		}catch(NumberFormatException e){
			return rv;
		}


		DT_formType dtf = new DT_formType();
		JSONObject response = dtf.deleteForm(id, req.getCookies());

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
