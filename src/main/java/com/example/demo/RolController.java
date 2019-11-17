package com.example.demo;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import datos.DT_rol;
import entidades.Tbl_rol;

@Controller
public class RolController {
	
	
	@GetMapping("/seguridad/roles")
	public String showRoles() {
		return "/seguridad/roles.jsp";
	}
	
	
	@PostMapping("/nuevoRol")
	public RedirectView guardarRol(HttpServletRequest req, HttpServletResponse res, RedirectAttributes redir) throws IOException {
		
		String rolName = req.getParameter("rolName");
		Tbl_rol rol = new Tbl_rol();
		
		rol.setName(rolName);
		
		DT_rol dtr = new DT_rol();
		
		JSONObject obj = dtr.guardarRol(rol, req.getCookies());
		System.out.println("Respuesta de create ROl: "+obj.getInt("status"));
		
		if(obj.getInt("status") == 201) {
			RedirectView rv = new RedirectView(req.getContextPath() + "/seguridad/roles");
			redir.addFlashAttribute("error", 1);
			redir.addFlashAttribute("type", "success");
			redir.addFlashAttribute("msg", "¡Rol creado <strong>exitósamete</strong>!");
			return rv;
		}else if(obj.getInt("status") == 401){
			RedirectView rv = new RedirectView(req.getContextPath() + "/login");
			redir.addFlashAttribute("error", 1);
			redir.addFlashAttribute("type", "warning");
			redir.addFlashAttribute("msg", "Primero debe <strong>iniciar sesión</strong>");
			return rv;
		}
		else if(obj.getInt("status") == 500){
			RedirectView rv = new RedirectView(req.getContextPath() + "/login");
			redir.addFlashAttribute("error", 1);
			redir.addFlashAttribute("type", "danger");
			redir.addFlashAttribute("msg", "Ha ocurrido un error en el <strong>servidor</strong>.");
			return rv;
		}
		
		return null;
	}
	
	@GetMapping("/deleteRol")
	public RedirectView eliminarRol(HttpServletRequest req, HttpServletResponse res, RedirectAttributes redir) throws IOException {
		//ModelAndView mav = new ModelAndView();
		
		int idRol = Integer.parseInt(req.getParameter("id"));
		DT_rol dtr = new DT_rol();
		
		JSONObject respuesta = dtr.eliminarRol(idRol, req.getCookies());
		
		if(respuesta.getInt("code") == 405)
		{
			RedirectView rv = new RedirectView(req.getContextPath() + "/seguridad/roles?status=405");
			return rv;
			
		}else if(respuesta.getInt("code") == 204) {
			RedirectView rv = new RedirectView(req.getContextPath() + "/seguridad/roles");
			redir.addFlashAttribute("error", 1);
			redir.addFlashAttribute("type", "success");
			redir.addFlashAttribute("msg", "¡Rol eliminado <strong>exitosamente</strong>!");
			return rv; 
		}else if(respuesta.getInt("code") == 401) {
			RedirectView rv = new RedirectView(req.getContextPath() + "/login");
			redir.addFlashAttribute("error", 1);
			redir.addFlashAttribute("type", "warning");
			redir.addFlashAttribute("msg", "Primero debe <strong>iniciar sesión</strong>");
			return rv;
		}
		
		//mav.setViewName("redirect:/seguridad/roles");
		res.sendRedirect(req.getContextPath() + "/seguridad/roles");
		return null;
		//return mav;
	}
}
