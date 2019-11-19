package com.example.demo;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import datos.DT_user;
import entidades.Tbl_user;

@Controller
public class UserController {
	
	@GetMapping("/seguridad/usuarios")
	public String usuarios() {
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
		System.out.println(usr);
		
		ModelAndView ma = new ModelAndView();
		
		JSONObject respuesta = dtu.comprobarLogin(usr, pass);
		
		if(respuesta.getInt("code") == 200) {
			String cookies[] =  (String[]) respuesta.get("cookies");
			String [] parts = cookies[0].split("=");
			String [] parts2 = cookies[1].split("=");
			Cookie ck = new Cookie(parts[0],parts[1]);
			Cookie ck2 = new Cookie(parts2[0],parts2[1]);
			ck.setMaxAge(1800);
			ck2.setMaxAge(1800);
			res.addCookie(ck);
			res.addCookie(ck2);
			
			 Cookie [] cks = new Cookie[]{ck, ck2};
			 JSONObject obj = dtu.obtenerUsuarioIngresado(cks);
			 if(obj.getInt("code") == 200)
			 {
				 HttpSession hts = req.getSession(true);
				 Tbl_user us = (Tbl_user) obj.get("user");
				 System.out.println("valor: " +us.getUsername() + " " + obj.getInt("code"));
				 hts.setAttribute("user", us);
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
        	response.sendRedirect( request.getContextPath() + "/seguridad/usuarios");
        }else if(respuesta.getInt("status") == 0 || respuesta.getInt("status") == 401){
        	response.sendRedirect( request.getContextPath() + "/login");
        }else if(respuesta.getInt("status") == 500) {
        	response.sendRedirect( request.getContextPath() + "/login?status=500");
        }
    
    }
	
	@GetMapping("/deleteUser")
    public void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	boolean is_active = false;
       
        Tbl_user user = new Tbl_user();
       
        user.setIs_active(is_active);
        
        DT_user dtu= new DT_user();
        
        JSONObject respuesta = dtu.deleteUser(user, request.getCookies());
        
        if (respuesta.getInt("status") == 200) {
        	response.sendRedirect( request.getContextPath() + "/seguridad/usuarios");
        } else if(respuesta.getInt("status") == 0 || respuesta.getInt("status") == 401){
        	response.sendRedirect( request.getContextPath() + "/login");
        } else if(respuesta.getInt("status") == 500) {
        	response.sendRedirect( request.getContextPath() + "/login?status=500");
        }
    
    }
	
	@GetMapping("/exit")
	public void cerrarSesion(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Cookie ta = new Cookie("token-access", "-");
		ta.setMaxAge(0);
		Cookie tr = new Cookie("token-refresh", "-");
		tr.setMaxAge(0);
		res.addCookie(ta);
		
		req.getSession().invalidate();
		
		res.sendRedirect(req.getContextPath() + "/login");
		return;
		
	}
	
	
}
