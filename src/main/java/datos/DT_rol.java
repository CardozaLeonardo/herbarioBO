
package datos;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties.Credential;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import entidades.Tbl_rol;

public class DT_rol {
	
	private static RestTemplate restTemplate = new RestTemplate();
	private String ENDPOINT_URL = "https://django-acacia.herokuapp.com/api/group/";
	
	public JSONObject getRoles(Cookie[] cookies) throws IOException {
		
		boolean access = false;
		boolean refresh = false;
		String tok  = null;
		String tok2 = null;
		
		
		for(Cookie c : cookies)
		{
			if(c.getName().equals("token-access")) {
				access = true;
				tok = c.getValue();
			}
			
			if(c.getName().equals("token-refresh")) {
				refresh = true;
				tok2 = c.getValue();
			}
		}
		
		if(!(access && refresh)) {
			JSONObject retorno = new JSONObject();
			retorno.put("status", 0);
			return retorno;
		}
		
		Tbl_rol[] roles = null;
		HttpHeaders headers = new HttpHeaders(); // Esto sirve para agregar los headers, con .add() se agregan
		
		headers.add("Cookie", "token-access="+ tok);
		headers.add("Cookie", "token-refresh="+ tok2);
		
		HttpEntity<Tbl_rol[]> respuesta = new HttpEntity<Tbl_rol[]>(headers);
		// Usar arrays tradicionales para recibir la informacion, no funciona con ArrayList y otros.
		
		try {
			
			ResponseEntity<Tbl_rol[]> resul = restTemplate.exchange(ENDPOINT_URL, HttpMethod.GET,respuesta, Tbl_rol[].class);
			
			
			if(resul.getStatusCodeValue() == 200) {
				
				roles = resul.getBody();
				
				
				JSONObject retorno = new JSONObject();
				retorno.put("status", resul.getStatusCodeValue());
				retorno.put("roles", roles);
				return retorno;
				
			}else if(resul.getStatusCodeValue() == 401) {
				System.out.println("No tienes acceso we...");
			}else {
				System.out.println("Algo salio mal we...");
			}
		}catch(HttpClientErrorException e) {
			
			JSONObject retorno = new JSONObject();
			retorno.put("status", e.getStatusCode().value());
			return retorno;
		}
		
		
		
		return null;
		//Tbl_rol[] roles = restTemplate.getForEntity(ENDPOINT_URL, respuesta, Tbl_rol[].class);
	}

	public String [] optenerCredenciales() {
		
		
		
		String URL = Server.getHostname() + "login/";
		
		Credential cred = new Credential();
		
		cred.setUsername("admin");
		cred.setPassword("admin");
		
		HttpEntity<Credential> resp = new HttpEntity<>(cred);
		ResponseEntity<String> resul = restTemplate.exchange(URL, HttpMethod.POST,resp, String.class);
		
		String ck = resul.getHeaders().getFirst(HttpHeaders.SET_COOKIE);
		List<String> list = resul.getHeaders().get("Set-Cookie");
		
		String[] cks = new String[2];
		//System.out.println(resul.getHeaders().getValuesAsList("Set-Cookie"));
		
		System.out.println(ck);
		
		String [] parts = list.get(0).split(";");
		String [] parts2 = list.get(1).split(";");
		cks[0] = parts[0];
		cks[1] = parts2[0];
		
		
		return cks;
	}
	
	public JSONObject guardarRol(Tbl_rol rol, Cookie[] cookies){
		
		boolean access = false;
		boolean refresh = false;
		String tok  = null;
		String tok2 = null;
		
		ObjectMapper om = new ObjectMapper();
		rol.setPermissions(new int[0]);
		
		JSONObject rolJson = new JSONObject(rol);
		
		rolJson.remove("id");
		String base = rolJson.toString();
		
		
		for(Cookie c : cookies)
		{
			if(c.getName().equals("token-access")) {
				access = true;
				tok = c.getValue();
			}
			
			if(c.getName().equals("token-refresh")) {
				refresh = true;
				tok2 = c.getValue();
			}
		}
		
		if(!(access && refresh)) {
			JSONObject retorno = new JSONObject();
			retorno.put("status", 0);
			return retorno;
		}
		
		String URL = Server.getHostname() + "group/";
		
        HttpHeaders headers = new HttpHeaders(); 
		
		headers.add("Cookie", "token-access="+ tok);
		headers.add("Cookie", "token-refresh="+ tok2);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> respuesta = new HttpEntity<String>(rolJson.toString(), headers);
		
		try {
			String str = om.writeValueAsString(rol);
			System.out.println(rolJson.toString());
			//System.out.println(str);
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			ResponseEntity<Tbl_rol> resul = restTemplate.exchange(ENDPOINT_URL, HttpMethod.POST,respuesta, Tbl_rol.class);
			
			Tbl_rol check = resul.getBody();
			
			JSONObject retorno = new JSONObject();
			retorno.put("status", resul.getStatusCodeValue());
			retorno.put("rol", check);
			return retorno;
			
			
		}catch(HttpClientErrorException e)
		{
			JSONObject retorno = new JSONObject();
			retorno.put("status", e.getStatusCode().value());
			return retorno;
		}
		
	}
	
	
	public JSONObject eliminarRol(int idRol, Cookie[] cookies) {
		boolean access = false;
		boolean refresh = false;
		String tok  = null;
		String tok2 = null;
		
		for(Cookie c : cookies)
		{
			if(c.getName().equals("token-access")) {
				access = true;
				tok = c.getValue();
			}
			
			if(c.getName().equals("token-refresh")) {
				refresh = true;
				tok2 = c.getValue();
			}
		}
		
		if(!(access && refresh)) {
			return null;
		}
		
		String URL = Server.getHostname() + "group/" + idRol + "/";
		Tbl_rol rol = new Tbl_rol();
		
        HttpHeaders headers = new HttpHeaders(); 
		
		headers.add("Cookie", "token-access="+ tok);
		headers.add("Cookie", "token-refresh="+ tok2);
		
		HttpEntity<String> req = new HttpEntity<String>(headers);
		
		try {
			ResponseEntity<String> resul = restTemplate.exchange(URL, HttpMethod.DELETE,req, String.class);
			
			String check = "eliminado";
			
			//System.out.println("Status: " + resul.getStatusCodeValue());
			//return check;
		    JSONObject retorno = new JSONObject();
		    retorno.put("objecto", rol);
		    retorno.put("code", resul.getStatusCodeValue());
			return retorno;
		}catch(Exception e)
		{
			System.err.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	public JSONObject actualizarRol(Tbl_rol rol, Cookie[] cookies) {
		boolean access = false;
		boolean refresh = false;
		String tok  = null;
		String tok2 = null;
		
		for(Cookie c : cookies)
		{
			if(c.getName().equals("token-access")) {
				access = true;
				tok = c.getValue();
			}
			
			if(c.getName().equals("token-refresh")) {
				refresh = true;
				tok2 = c.getValue();
			}
		}
		
		if(!(access && refresh)) {
			
			JSONObject retorno = new JSONObject();
			retorno.put("status", 0);
			return retorno;
		}
		
		String URL = Server.getHostname() + "group/" + rol.getId() + "/";
		
		JSONObject datos = new JSONObject(rol);
		datos.remove("id");
		datos.remove("permissions");
		
        HttpHeaders headers = new HttpHeaders(); 
		
		headers.add("Cookie", "token-access="+ tok);
		headers.add("Cookie", "token-refresh="+ tok2);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> req = new HttpEntity<String>(datos.toString(),headers);
		
		try {
			ResponseEntity<String> resul = restTemplate.exchange(URL, HttpMethod.PUT,req, String.class);
			
			String check = "eliminado";
			
			
		    JSONObject retorno = new JSONObject();
		    retorno.put("objecto", rol);
		    retorno.put("code", resul.getStatusCodeValue());
			return retorno;
		}catch(Exception e)
		{
			System.err.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
}
