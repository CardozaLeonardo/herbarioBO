package com.example.demo;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import datos.DT_user;
import entidades.Tbl_user;

@Controller
public class UserController {
	
	@GetMapping("/seguridad/usuarios")
	public String usuarios() {
		return "/seguridad/usuarios.jsp";
	}
	
	@RequestMapping(value = "/verify", method = RequestMethod.POST)
	public void login(HttpServletRequest req, ModelMap map,
			HttpServletResponse res) throws IOException {
		
		String usr = req.getParameter("username");
		String pass = req.getParameter("password");
		DT_user dtu = new DT_user();
		System.out.println(usr);
		
		String[] cookies = dtu.comprobarLogin(usr, pass);
		
		if(cookies != null) {
			String [] parts = cookies[0].split("=");
			String [] parts2 = cookies[1].split("=");
			Cookie ck = new Cookie(parts[0],parts[1]);
			Cookie ck2 = new Cookie(parts2[0],parts2[1]);
			ck.setMaxAge(300);
			ck2.setMaxAge(300);
			res.addCookie(ck);
			res.addCookie(ck2);
			
			res.sendRedirect("/");
			return;
			//return "charts.html";
		}else {
			//return "login.jsp";
		}
		
		//return "charts.html";
	}
}
