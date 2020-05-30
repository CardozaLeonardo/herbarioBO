package controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datos.DT_permissions;
import entidades.PermissionRecep;
import entidades.Tbl_opcion;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.databind.ObjectMapper;

import datos.DT_user;
import entidades.Tbl_user;
import util.MessageAlertUtil;
import util.Util;

@Controller
public class UserController {
	
	@GetMapping("/seguridad/usuarios")
	public String usuarios(Model model) {
		model.addAttribute("right", 1);
		return "/seguridad/usuarios.jsp";
	}
	
	@GetMapping("/seguridad/newUser")
	public String nuevo() {
		return "/seguridad/newUser.jsp";
	}
	
	@PostMapping("/newUser")
    public ModelAndView guardarUser(HttpServletRequest request, HttpServletResponse response, ModelMap map) throws IOException {
        
        String last_login = null;
        boolean is_superuser = false;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String first_name = request.getParameter("nombre1")  +  request.getParameter("nombre2");
        String last_name = request.getParameter("apellido1") + request.getParameter("apellido2");
        String email = request.getParameter("email");
        //String date_joined = "2019-11-15";
        boolean is_staff = true;
        boolean is_active = true;
        String name = null;
        
        Tbl_user user = new Tbl_user();
        
        user.setLast_login(last_login);
        user.setIs_superuser(is_superuser);
        user.setUsername(username);
        user.setFirst_name(first_name);
        user.setLast_name(last_name);
        user.setPassword(password);
        user.setEmail(email);
        user.setIs_staff(is_staff);
        user.setIs_active(is_active);
        user.setDate_joined(null);
        user.setName(name);
        user.setGroups(new int[0]);
        
        
        DT_user dtu= new DT_user();
        
        JSONObject respuesta = dtu.guardarUser(user, request.getCookies());
        if(respuesta.getInt("status") == 200) {
            map.addAttribute("exito", 1);
            map.addAttribute("type", "success");
            map.addAttribute("msg", "Se ha logrado agregar correctamente");
            String[] cks = (String[]) respuesta.get("cookies");
			Util.setTokenCookies(request, response, cks);
            return new ModelAndView("redirect:/seguridad/usuarios");
            //response.sendRedirect("/seguridad/usuarios");
        }else {
            response.sendRedirect("/login?status=" + respuesta.getInt("status"));
        }
        
        return null;
    }
	
	@GetMapping("/seguridad/updateUser")
	public String showUpdateUser(HttpServletRequest request, HttpServletResponse response, Model model) {
		DT_user dtu = new DT_user();
		int idUser =Integer.parseInt(request.getParameter("id"));
		
		JSONObject respuesta = dtu.obtenerUser(idUser, request.getCookies());
		
		if(respuesta.getInt("status") == 200) {
			
			model.addAttribute("user", respuesta.get("user"));
			String[] cks = (String[]) respuesta.get("cookies");
			Util.setTokenCookies(request, response, cks);
			return "/seguridad/updateUser.jsp";
			
		}else if(respuesta.getInt("status") == 0 || respuesta.getInt("status") == 401) {
			model.addAttribute("error", "Ingrese primero");
			return request.getContextPath() + "/login";
		}
		
		return "/seguridad/updateUser.jsp";
	}
	
	@RequestMapping(value = "/verify", method = RequestMethod.POST)
	public RedirectView login(HttpServletRequest req, ModelMap model,
			HttpServletResponse res, RedirectAttributes redir) throws IOException {
		
		String usr = req.getParameter("username");
		String pass = req.getParameter("password");
		DT_user dtu = new DT_user();
		DT_permissions dtpp = new DT_permissions();
		
		ModelAndView ma = new ModelAndView();
		
		JSONObject respuesta = dtu.comprobarLogin(usr, pass);
		Cookie ck = null;
		Cookie ck2 = null;
		
		if(respuesta.getInt("code") == 200) {
			String[] cookies =  (String[]) respuesta.get("cookies");
			System.out.println("Cookies: " + cookies.length);
			String [] parts = cookies[0].split("=");
			String [] parts2 = cookies[1].split("=");
			ck = new Cookie(parts[0],parts[1]);
			ck2 = new Cookie(parts2[0],parts2[1]);
			ck.setMaxAge(1800);
			ck.setPath("/");
			ck2.setMaxAge(1800);
			ck2.setPath("/");
			res.addCookie(ck);
			res.addCookie(ck2);

			
			 Cookie [] cks = new Cookie[]{ck, ck2};
			 dtu = new DT_user();
			 JSONObject obj = dtu.obtenerUsuarioIngresado(cks);
			 Tbl_user us = (Tbl_user) obj.get("user");
			 JSONObject permissionsData = dtpp.getUserPermissions(us.getId(), cks);
			Tbl_opcion[] permissions = (Tbl_opcion[]) permissionsData.get("permissions");
			 if(obj.getInt("code") == 200)
			 {
				 /*HttpSession hts1 = req.getSession(false);
			 	if(hts1 != null){
			 		hts1.invalidate();
				} */
				 HttpSession hts = req.getSession(true);



				 //System.out.println("valor: " +us.getUsername() + " " + obj.getInt("code"));
				 hts.setAttribute("user_permissions", permissions);
				 hts.setAttribute("user", us);
				 hts.setMaxInactiveInterval(1800);
			 }
			RedirectView rv = new RedirectView(req.getContextPath()+"/");
			
			return rv;
			
		}else if(respuesta.getInt("code") == 401){
			
			RedirectView rv = new RedirectView(req.getContextPath()+"/login");
			redir.addFlashAttribute("error", 1);
			redir.addFlashAttribute("type", "danger");
			redir.addFlashAttribute("msg", "¡Error, <strong>usuario o contraseña</strong> incorrecta!");
			return rv;
			
		}

	    res.sendRedirect("/login?status=" + respuesta.getInt("code"));
	    return null;
	}
	
	@PostMapping("/updateUser")
    public void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean is_superuser = false;
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String first_name = (request.getParameter("nombre1") + " " +  request.getParameter("nombre2"));
        String last_name = request.getParameter("apellido1") + " " + request.getParameter("apellido2");
        String email = request.getParameter("email");
        boolean is_active = true;
       
        Tbl_user user = new Tbl_user();
       
        user.setIs_superuser(is_superuser);
        user.setUsername(username);
        user.setPassword(password);
        user.setFirst_name(first_name);
        user.setLast_name(last_name);
        user.setEmail(email);
        user.setIs_active(is_active);
        user.setId(id);
        
        DT_user dtu= new DT_user();
        
        JSONObject respuesta = dtu.updateUser(user, request.getCookies());
        
        if(respuesta.getInt("status") == 200) {
        	
        	String[] cookies = (String[]) respuesta.get("cookies");
        	Util.setTokenCookies(request, response, cookies);
        	response.sendRedirect( request.getContextPath() + "/seguridad/usuarios");
        	
        }else if(respuesta.getInt("status") == 0 || respuesta.getInt("status") == 401){
        	response.sendRedirect( request.getContextPath() + "/login");
        }else if(respuesta.getInt("status") == 500) {
        	response.sendRedirect( request.getContextPath() + "/login?status=500");
        }
    
    }
	
	@GetMapping("/deleteUser")
    public RedirectView deleteUser(HttpServletRequest request, HttpServletResponse response,
        RedirectAttributes redir) throws IOException {
    	boolean is_active = false;
    	RedirectView rv = new RedirectView(request.getContextPath() + "/seguridad/usuarios");
        Tbl_user user = new Tbl_user();
       
        user.setIs_active(is_active);
        
        DT_user dtu= new DT_user();
        
        JSONObject respuesta = dtu.deleteUser(user, request.getCookies());
        
        if (respuesta.getInt("status") == 200) {
        	
			redir.addFlashAttribute("error", 1);
			redir.addFlashAttribute("type", "success");
			redir.addFlashAttribute("msg", "¡El usuarios se ha eliminado <strong>correctamente</strong>!");
			String[] cks = (String[]) respuesta.get("cookies");
			Util.setTokenCookies(request, response, cks);
			
        } else if(respuesta.getInt("status") == 0 || respuesta.getInt("status") == 401){
        	
        	rv = new RedirectView(request.getContextPath() + "/login");
        	redir.addFlashAttribute("error", 1);
			redir.addFlashAttribute("type", "info");
			redir.addFlashAttribute("msg", "¡Debe iniciar sessión primero!");
        	
        } else if(respuesta.getInt("status") == 500) {
        	
        	redir.addFlashAttribute("error", 1);
			redir.addFlashAttribute("type", "danger");
			redir.addFlashAttribute("msg", "¡Ha ocurrido un error y se canceló la operación!");
        }
        
        return rv;
    
    }
	
	@GetMapping("/exit")
	public void cerrarSesion(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Cookie ta = new Cookie("token-access", "-");
		ta.setMaxAge(0);
		Cookie tr = new Cookie("token-refresh", "-");
		tr.setMaxAge(0);
		res.addCookie(ta);
		res.addCookie(tr);
		
		//req.getSession().getAttribute("user").;

		req.getSession().invalidate();
		
		res.sendRedirect(req.getContextPath() + "/login");
		return;
		
	}
	
	@RequestMapping(method = RequestMethod.POST,value = "/asignarRol")
	public RedirectView asignarRolUsuario(HttpServletRequest req, HttpServletResponse res, RedirectAttributes redir) {
		RedirectView rv = new RedirectView(req.getContextPath() + "/seguridad/rolesUsuarios");
		
		String userId = req.getParameter("idUser");
		String rolId = req.getParameter("rolUser");
		String grupos = req.getParameter("grupos");
		Tbl_user usr = new Tbl_user();
		DT_user dtu = new DT_user();
		ArrayList<Integer> ids = new ArrayList<Integer>();
		 
		
		try
		{
			int user = Integer.parseInt(userId);
			int rol = Integer.parseInt(rolId);
			
			/*JSONObject jso = dtu.obtenerUser(user, req.getCookies());
			if(jso.getInt("status") == 200) {
				usr = (Tbl_user) jso.get("user");
			}else if(jso.getInt("status") == 401 || jso.getInt("status") == 0) {
				return rv;
			}*/
			
            ObjectMapper om = new ObjectMapper();
			
			int[]groupAct = om.readValue(grupos, int[].class);
			
			int[] act = new int[groupAct.length + 1];
			int index = 0;
			for(int i : groupAct) {
				act[index] = i;
				index++;
			}
			act[groupAct.length] = rol;
			
			usr.setId(user);
			usr.setGroups(act);
			
			JSONObject obj = dtu.asignarRol(usr, req.getCookies());
			
			if(obj.getInt("status") == 401) {
				rv = new RedirectView(req.getContextPath() + "/login");
				MessageAlertUtil.UnauthorizedAccessMessage(redir);
			}

			rv = new RedirectView(req.getContextPath() + "/seguridad/rolesUsuarios?user="+user);
			
			
			
		}catch(Exception e) {
			//System.err.println("Error en sevlet SL_asignarRol: ");
			e.printStackTrace();
		}
		
		return rv;
	}
	
	@GetMapping("/removerRol")
	public RedirectView removerRol(HttpServletRequest req, HttpServletResponse res, RedirectAttributes redir) {
		RedirectView rv = new RedirectView(req.getContextPath() + "/seguridad/rolesUsuarios");
		
		String idUser = req.getParameter("idUser");
		String delete = req.getParameter("delete");
		DT_user dtu = new DT_user();
		Tbl_user usr = new Tbl_user();
		
		int user = Integer.parseInt(idUser);
		int rol = Integer.parseInt(delete);
		
		JSONObject jso = dtu.obtenerUser(user, req.getCookies());
		if(jso.getInt("status") == 200) {
			usr = (Tbl_user) jso.get("user");
		}else if(jso.getInt("status") == 401 || jso.getInt("status") == 0) {
			return rv;
		}
		
		int[] grupos = usr.getGroups();
		
		ArrayList<Integer> actualizacion = new ArrayList<Integer>();
		
		for(int i:grupos) {
			if(i != rol) {
				Integer in = new Integer(i);
				actualizacion.add(in);
			}
		}
		
		int[] act = new int[actualizacion.size()];
		int index = 0;
		for(Integer a:actualizacion) {
			
			act[index] = a.intValue();
			index++;
		}
		
		usr.setGroups(act);
		JSONObject obj = dtu.asignarRol(usr, req.getCookies());
		
		
		if(obj.getInt("status") == 200) {
			rv = new RedirectView(req.getContextPath() + "/seguridad/rolesUsuarios?user="+user);
			redir.addFlashAttribute("msg", 1);
			redir.addFlashAttribute("type", "success");
			redir.addFlashAttribute("cont", "¡Rol retirado existosamente!");
		}else if(obj.getInt("status") == 401 || obj.getInt("status") == 0) {
			rv = new RedirectView(req.getContextPath() + "/login");
			redir.addFlashAttribute("msg", 1);
			redir.addFlashAttribute("type", "info");
			redir.addFlashAttribute("cont", "¡Debe iniciar sesión primero!");
		}else if(obj.getInt("status") == 500) {
			rv = new RedirectView(req.getContextPath() + "/seguridad/rolesUsuarios?user="+user);
			redir.addFlashAttribute("msg", 1);
			redir.addFlashAttribute("type", "danger");
			redir.addFlashAttribute("cont", "¡Ha ocurrido un <strong>error</strong> en al realizar la tarea!");
		}
		
		return rv;
	}

	@GetMapping("/blockUser")
	public RedirectView blockUser(HttpServletRequest req, HttpServletResponse res,
								  RedirectAttributes redir) {
		RedirectView rv = new RedirectView(req.getContextPath() + "/seguridad/usuarios");

		int idUser = Integer.parseInt(req.getParameter("id"));
		DT_user dt_user = new DT_user();

		//MultiValueMap<String, Object> user = new LinkedMultiValueMap<>();
		JSONObject user = new JSONObject();

		user.put("is_active", req.getParameter("opc") != null);



		JSONObject result = dt_user.updateUserPatch(idUser, user, req.getCookies());

		if(result.getInt("status") == 401) {
			rv = new RedirectView(req.getContextPath() + "/login");
			MessageAlertUtil.UnauthorizedAccessMessage(redir);
			return rv;
		}

		String[] cookies = (String[]) result.get("cookies");
		Util.setTokenCookies(req, res, cookies);

		MessageAlertUtil.SuccessBlockUserMessage(redir);

		return rv;

	}


	@RequestMapping(method = RequestMethod.POST,value = "/resetPassword")
	public RedirectView resetPassword(HttpServletRequest req, HttpServletResponse res,
									  RedirectAttributes redir) {
		RedirectView rv = new RedirectView(req.getContextPath() + "/forgot_password");
		String email = req.getParameter("email");
		DT_user dt_user = new DT_user();

		JSONObject object = new JSONObject();

		object.put("email", email);

		dt_user.restorePassword(object);

		MessageAlertUtil.restorePasswordMessage(redir);

		return rv;

	}
	
	
}
