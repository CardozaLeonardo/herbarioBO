package negocio;

import java.io.IOException;

import javax.servlet.http.Cookie;

import org.json.JSONObject;

import datos.DT_rol;
import entidades.Tbl_rol;


public class NG_rol {
	
	public int existeRol(String name, Cookie[] cookies) throws IOException {
		int existe = 0;
		
		
		
		DT_rol dtr = new DT_rol();
		JSONObject obj = dtr.getRoles(cookies);
		
		if(obj.getInt("status") == 200) {
			Tbl_rol[] roles = (Tbl_rol[]) obj.get("roles");
			
			for(Tbl_rol u: roles) {
				if(u.getName().equalsIgnoreCase(name)) {
					existe = 1;
				}
			}
		}else if (obj.getInt("status") == 401) {
			existe = 2;
		}
		
		return existe;
	}
	
	public int existeRol(String name,int id, Cookie[] cookies) throws IOException {
		int existe = 0;
		
		
		
		DT_rol dtr = new DT_rol();
		JSONObject obj = dtr.getRoles(cookies);
		
		if(obj.getInt("status") == 200) {
			Tbl_rol[] roles = (Tbl_rol[]) obj.get("roles");
			
			for(Tbl_rol u: roles) {
				if(u.getName().equalsIgnoreCase(name) && u.getId() != id) {
					existe = 1;
				}
			}
		}else if (obj.getInt("status") == 401) {
			existe = 2;
		}
		
		return existe;
	}
}
