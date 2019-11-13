package datos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties.Credential;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import entidades.Tbl_user;
import entidades.User;

public class DT_user {
	
	private static RestTemplate restTemplate = new RestTemplate();
	private String ENDPOINT_URL = "http://localhost:3000/usuarios";
	//geeksforgeeks.org/md5-hash-in-java
	
	public Tbl_user[] getUsers(String token, String tokenRefresh) {
		
		String URL = Server.getHostname() + "user/";
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Cookie", "token-access="+ token);
		headers.add("Cookie", "token-refresh="+ tokenRefresh);
		
		HttpEntity<Tbl_user[]> ent = new HttpEntity<Tbl_user[]>(headers);
		
		try {
			
			ResponseEntity<Tbl_user[]> result = restTemplate.exchange(URL, HttpMethod.GET, ent, Tbl_user[].class);
			
			Tbl_user[] usuarios = result.getBody();
			
			return usuarios;
		}catch(HttpClientErrorException e) {
			return null;
		}
		
	}
	
	public String[] comprobarLogin(String username, String password) {
		
        String URL = Server.getHostname() + "login/";
		
		Credential cred = new Credential();
		
		cred.setUsername(username);
		cred.setPassword(password);
		
		HttpEntity<Credential> resp = new HttpEntity<>(cred);
		
		try {
			
			ResponseEntity<String> resul = restTemplate.exchange(URL, HttpMethod.POST,resp, String.class);
			
			String ck = resul.getHeaders().getFirst(HttpHeaders.SET_COOKIE);
			List<String> list = resul.getHeaders().get("Set-Cookie");
			
			String[] cks = new String[2];
			
			System.out.println(ck);
			
			String [] parts = list.get(0).split(";");
			String [] parts2 = list.get(1).split(";");
			cks[0] = parts[0];
			cks[1] = parts2[0];
			
			
			return cks;
		}catch(Exception e) {
			if(e.getMessage().equals("401 Unauthorized")) {
				System.out.println("No tienes acceso we...");
				System.err.println(e.getMessage());
				return null;
			}
		}
		
		return null;
	}
}
