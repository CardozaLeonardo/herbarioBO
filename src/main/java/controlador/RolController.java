package controlador;

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
import negocio.NG_rol;
import util.Util;

@Controller
public class RolController {
	
	
	@GetMapping("/seguridad/roles")
	public String showRoles() {
		return "/seguridad/roles.jsp";
	}
	
	@GetMapping("/seguridad/rolesUsuarios")
	public String showRolesUsuarios() {
		return "/seguridad/rolesUsuarios.jsp";
	}
	
	@GetMapping("/seguridad/rolesOpciones")
	public String showRolesOpciones() {
		return "/seguridad/rolesOpciones.jsp";
	}
	
	
	@PostMapping("/nuevoRol")
	public RedirectView guardarRol(HttpServletRequest req, HttpServletResponse res, RedirectAttributes redir) throws IOException {
		
		String rolName = req.getParameter("rolName");
		Tbl_rol rol = new Tbl_rol();
		
		rol.setName(rolName);
		
		DT_rol dtr = new DT_rol();
		NG_rol ngr = new NG_rol();
		int validacion = ngr.existeRol(rolName, req.getCookies());
		
		if(validacion == 0) {
			
			JSONObject obj = dtr.guardarRol(rol, req.getCookies());
			System.out.println("Respuesta de create ROl: "+obj.getInt("status"));
			
			if(obj.getInt("status") == 201) {
				RedirectView rv = new RedirectView(req.getContextPath() + "/seguridad/roles");
				redir.addFlashAttribute("error", 1);
				redir.addFlashAttribute("type", "success");
				redir.addFlashAttribute("msg", "¡Rol creado <strong>exitósamete</strong>!");
				String[] cks = (String[]) obj.get("cookies");
				Util.setTokenCookies(req, res, cks);
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
		}else if(validacion == 1) {
			RedirectView rv = new RedirectView(req.getContextPath() + "/seguridad/roles");
			redir.addFlashAttribute("error", 1);
			redir.addFlashAttribute("type", "danger");
			redir.addFlashAttribute("msg", "¡<strong>Error</strong>: el nuevo nombre para el rol"
					+ " ya existe!");
			return rv;
		}else if(validacion == 2) {
			RedirectView rv = new RedirectView(req.getContextPath() + "/login");
			redir.addFlashAttribute("error", 1);
			redir.addFlashAttribute("type", "info");
			redir.addFlashAttribute("msg", "Tiene que iniciar sesión primero");
			return rv;
		}
		
		
		return null;
	}
	
	@PostMapping("/actualizarRol")
	public RedirectView actualizarRol(HttpServletRequest req, HttpServletResponse res, RedirectAttributes redir) throws IOException {
		int idRol = Integer.parseInt(req.getParameter("idRol"));
		String rolName = req.getParameter("rolName");
		NG_rol ngr = new NG_rol();
		DT_rol dtr = new DT_rol();
		Tbl_rol tbr = new Tbl_rol();
		tbr.setId(idRol);
		tbr.setName(rolName);
		RedirectView rv = new RedirectView(req.getContextPath() + "/seguridad/roles");
		int validacion = ngr.existeRol(rolName, idRol, req.getCookies());
		
		if(validacion == 1) {
			redir.addFlashAttribute("error", 1);
			redir.addFlashAttribute("type", "danger");
			redir.addFlashAttribute("msg", "¡<strong>Error</strong>: el nuevo nombre para el rol"
					+ " ya existe!");
			return rv;
		}else if(validacion == 0) {
			JSONObject obj = dtr.actualizarRol(tbr, req.getCookies());
			if(obj.getInt("status") == 200) {
				redir.addFlashAttribute("error", 1);
				redir.addFlashAttribute("type", "success");
				redir.addFlashAttribute("msg", "¡Rol actualizado <strong>exitosamente</strong>!");
				String[] cks = (String[]) obj.get("cookies");
				Util.setTokenCookies(req, res, cks);
			}
			
		}else if(validacion == 2) {
			rv = new RedirectView(req.getContextPath() + "/login");
			redir.addFlashAttribute("error", 1);
			redir.addFlashAttribute("type", "info");
			redir.addFlashAttribute("msg", "Tiene que iniciar sesión primero");
		}
		
		return rv;
	}
	
	@GetMapping("/deleteRol")
	public RedirectView eliminarRol(HttpServletRequest req, HttpServletResponse res, RedirectAttributes redir) throws IOException {
		//ModelAndView mav = new ModelAndView();
		
		int idRol = Integer.parseInt(req.getParameter("id"));
		DT_rol dtr = new DT_rol();
		RedirectView rv = new RedirectView(req.getContextPath() + "/seguridad/roles");
		
		JSONObject respuesta = dtr.eliminarRol(idRol, req.getCookies());
		
		if(respuesta.getInt("code") == 405)
		{
			rv = new RedirectView(req.getContextPath() + "/seguridad/roles?status=405");
			
			
		}else if(respuesta.getInt("code") == 204) {
			
			redir.addFlashAttribute("error", 1);
			redir.addFlashAttribute("type", "success");
			redir.addFlashAttribute("msg", "¡Rol eliminado <strong>exitosamente</strong>!");
			String[] cks = (String[]) respuesta.get("cookies");
			Util.setTokenCookies(req, res, cks);
			 
		}else if(respuesta.getInt("code") == 401) {
			rv = new RedirectView(req.getContextPath() + "/login");
			redir.addFlashAttribute("error", 1);
			redir.addFlashAttribute("type", "warning");
			redir.addFlashAttribute("msg", "Primero debe <strong>iniciar sesión</strong>");
			
		}else if(respuesta.getInt("code") == 500)
		{
			redir.addFlashAttribute("error", 1);
			redir.addFlashAttribute("type", "danger");
			redir.addFlashAttribute("msg", "¡Ha ocurrido un error la realizar la <strong>eliminación</strong>!");
		}
		
		
		return rv;
	}
}
