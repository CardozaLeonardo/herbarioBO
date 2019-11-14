package com.example.demo;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import datos.DT_rol;
import entidades.Tbl_rol;

@Controller
public class RolController {
	
	
	@GetMapping("/seguridad/roles")
	public String showRoles() {
		return "/seguridad/roles.jsp";
	}
	
	
	@PostMapping("/nuevoRol")
	public void guardarRol(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		String rolName = req.getParameter("rolName");
		Tbl_rol rol = new Tbl_rol();
		
		rol.setName(rolName);
		
		DT_rol dtr = new DT_rol();
		
		if(dtr.guardarRol(rol, req.getCookies()) != null) {
			res.sendRedirect("/seguridad/roles");
		}else {
			res.sendRedirect("/login");
		}
	}
}
