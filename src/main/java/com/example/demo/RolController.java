package com.example.demo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;

import datos.DT_rol;
import entidades.Tbl_rol;

public class RolController {
	
	
	@PostMapping("/nuevoRol")
	public void guardarRol(HttpServletRequest req, HttpServletResponse res) {
		
		String rolName = req.getParameter("rolName");
		Tbl_rol rol = new Tbl_rol();
		
		rol.setName(rolName);
		
		DT_rol dtr = new DT_rol();
		
		if(DT_rol.guardarRol(rol) != null) {
			res.sendRedirect("/seguridad/roles");
		}else {
			res.sendRedirect("/login");
		}
	}
}
