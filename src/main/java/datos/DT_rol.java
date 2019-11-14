
package datos;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties.Credential;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;



import entidades.Tbl_rol;

public class DT_rol {
	
	private static RestTemplate restTemplate = new RestTemplate();
	private String ENDPOINT_URL = "https://django-acacia.herokuapp.com/api/group/";
	
	public Tbl_rol[] getRoles(HttpServletResponse response, String ck, String ck2) throws IOException {
		
		Tbl_rol[] roles = null;
		HttpHeaders headers = new HttpHeaders(); // Esto sirve para agregar los headers, con .add() se agregan
		
		headers.add("Cookie", "token-access="+ ck);
		headers.add("Cookie", "token-refresh="+ ck2);
		
		HttpEntity<Tbl_rol[]> respuesta = new HttpEntity<Tbl_rol[]>(headers);
		// Usar arrays tradicionales para recibir la informacion, no funciona con ArrayList y otros.
		
		try {
			
			ResponseEntity<Tbl_rol[]> resul = restTemplate.exchange(ENDPOINT_URL, HttpMethod.GET,respuesta, Tbl_rol[].class);
			
			
			if(resul.getStatusCodeValue() == 200) {
				//String roles = resul.getBody();
				roles = resul.getBody();
				
				for(Tbl_rol r:roles) {
					System.out.println(r.getId());
					System.out.println(r.getName());
				}
				
				//System.out.println(roles);
				
				return roles;
			}else if(resul.getStatusCodeValue() == 401) {
				System.out.println("No tienes acceso we...");
			}else {
				System.out.println("Algo salio mal we...");
			}
		}catch(HttpClientErrorException e) {
			//System.out.println("Algo salio mal we... " + e.getMessage());
			
			if(e.getMessage().equals("404 Not Found")) {
				
				System.out.println("Algo salio mal we...");
			}else if(e.getMessage().equals("401 Unauthorized")) {
				System.out.println("No tienes acceso we...");
				/*response.sendRedirect("http://localhost:8080/login");
				return;*/
			}
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
	
	public Tbl_rol guardarRol(Tbl_rol rol, Cookie[] cookies) {
		
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
		
		String URL = Server.getHostname() + "group/";
		
        HttpHeaders headers = new HttpHeaders(); 
		
		headers.add("Cookie", "token-access="+ tok);
		headers.add("Cookie", "token-refresh="+ tok2);
		
		HttpEntity<Tbl_rol> respuesta = new HttpEntity<Tbl_rol>(headers);
		
		try {
			ResponseEntity<Tbl_rol> resul = restTemplate.exchange(ENDPOINT_URL, HttpMethod.POST,respuesta, Tbl_rol.class);
			
			Tbl_rol check = resul.getBody();
			
			return check;
		}catch(Exception e)
		{
			return null;
		}
		
	}
}
