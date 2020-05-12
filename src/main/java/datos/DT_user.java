package datos;

import java.util.ArrayList;

import java.util.List;

import javax.servlet.http.Cookie;

import org.json.JSONObject;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties.Credential;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import entidades.MeResponse;
import entidades.Tbl_profile;
import entidades.Tbl_user;
import util.Util;

public class DT_user {
	
	private static RestTemplate restTemplate = new RestTemplate();
	//private String ENDPOINT_URL = "http://localhost:3000/usuarios";
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
	
	public JSONObject getActiveUsers(Cookie[] cookies) {
		
		String URL = Server.getHostname() + "user/";
		
		String[] tokens = Util.extractTokens(cookies);
		
		if(tokens == null) {
			
			JSONObject retorno = new JSONObject();
			retorno.put("status", 0);
			return retorno;
		}
		
		HttpHeaders headers = new HttpHeaders();
		String cookieHeader = "token-access="+tokens[0] + "; " + "token-refresh="+ tokens[1];

		headers.add("Cookie", cookieHeader);
		
		HttpEntity<Tbl_user[]> ent = new HttpEntity<Tbl_user[]>(headers);
		
		try {
			
			ResponseEntity<Tbl_user[]> result = restTemplate.exchange(URL, HttpMethod.GET, ent, Tbl_user[].class);
			
			Tbl_user[] usuarios = result.getBody();
			ArrayList<Tbl_user> usuariosActivos = new ArrayList<Tbl_user>();
			
			for(Tbl_user us: usuarios) {
				if(us.isIs_active()) {
					usuariosActivos.add(us);
				}
			}
			
			JSONObject retorno = new JSONObject();
			retorno.put("status", result.getStatusCodeValue());
			retorno.put("users", usuariosActivos);
			return retorno;
		}catch(HttpClientErrorException e) {
			JSONObject retorno = new JSONObject();
			retorno.put("status", e.getStatusCode().value());
			return retorno;
		}
		
	}
	
	
	
	public JSONObject comprobarLogin(String username, String password) {
		
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
			
			JSONObject retorno = new JSONObject();
			retorno.put("code", resul.getStatusCodeValue());
			retorno.put("cookies", cks);
			return retorno;
			
			
			
		}catch(HttpClientErrorException e) {
			JSONObject retorno = new JSONObject();
			retorno.put("code", e.getStatusCode().value());
			return retorno;
		}
	}
	
	public JSONObject obtenerUsuarioIngresado(Cookie[] cookies) {
		
		String URL = Server.getHostname() + "me/";
		Tbl_user user = null;
		
		String[] tokens = Util.extractTokens(cookies);
		
		if(tokens == null) {
			
			JSONObject retorno = new JSONObject();
			retorno.put("code", 0);
			return retorno;
		}
		
        HttpHeaders headers = new HttpHeaders();

		String cookieHeader = "token-access="+tokens[0] + "; " + "token-refresh="+ tokens[1];

		headers.add("Cookie", cookieHeader);
		
		HttpEntity<String> req = new HttpEntity<String>(headers);
		
		try {
			
			ResponseEntity<MeResponse> response = restTemplate.exchange(URL, HttpMethod.GET, req, MeResponse.class);
			MeResponse me = response.getBody();
			
			JSONObject retorno = new JSONObject();
			retorno.put("code", response.getStatusCodeValue());
			retorno.put("user", me.getData());
			retorno.put("cookies", Util.parseCookie(response.getHeaders().get("Set-Cookie")));
			return retorno;
			
		}catch(HttpClientErrorException e)
		{
			
			System.err.println(e.getStatusText());
			JSONObject retorno = new JSONObject();
			retorno.put("code", e.getStatusCode().value());
			//retorno.put("user", user);
			return retorno;
		}
		
	}
	
public JSONObject guardarUser(Tbl_user user, Cookie[] cookies){
        
    String[] tokens = Util.extractTokens(cookies);
	
	if(tokens == null) {
		
		JSONObject retorno = new JSONObject();
		retorno.put("status", 0);
		return retorno;
	}
        
        JSONObject userJson = new JSONObject(user);
        Tbl_profile tb = null;
        
        userJson.remove("id");
        userJson.remove("profile");
        userJson.put("profile", tb);
        userJson.put("user_permissions", new int[0]);
        
        
        //rolJson.put("name", rol.getName());
        String base = userJson.toString();
        
        
        
        String URL = Server.getHostname() + "user/";
        
        HttpHeaders headers = new HttpHeaders(); 
        
        System.out.println(userJson.toString());

	   String cookieHeader = "token-access="+tokens[0] + "; " + "token-refresh="+ tokens[1];

	   headers.add("Cookie", cookieHeader);
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<String> resp = new HttpEntity<String>(userJson.toString(), headers);
        
        
        
        try {
            ResponseEntity<Tbl_user> resul = restTemplate.exchange(URL, HttpMethod.POST,resp, Tbl_user.class);
            
            Tbl_user check = resul.getBody();
            
            
            
            JSONObject retorno = new JSONObject();
            retorno.put("status", resul.getStatusCodeValue());
            retorno.put("user",check);
            retorno.put("cookies", Util.parseCookie(resul.getHeaders().get("Set-Cookie")));
            return retorno;
        }catch(HttpClientErrorException e)
        {
            JSONObject retorno = new JSONObject();
            retorno.put("status", e.getStatusCode().value());
            return retorno;
        }
        
    }

public JSONObject obtenerUser(int idUser, Cookie[] cookies) {
	String URL = Server.getHostname() + "user/" + idUser + "/";
	
    String[] tokens = Util.extractTokens(cookies);
	
	if(tokens == null) {
		
		JSONObject retorno = new JSONObject();
		retorno.put("status", 0);
		return retorno;
	}
	
    HttpHeaders headers = new HttpHeaders();

	String cookieHeader = "token-access="+tokens[0] + "; " + "token-refresh="+ tokens[1];

	headers.add("Cookie", cookieHeader);
	
	HttpEntity<String> req = new HttpEntity<String>(headers);
	
	try {
		ResponseEntity<Tbl_user> response = restTemplate.exchange(URL, HttpMethod.GET, req, Tbl_user.class);
		Tbl_user usr = response.getBody();
		
		JSONObject retorno = new JSONObject();
		retorno.put("status", response.getStatusCodeValue());
		retorno.put("user", usr);
		retorno.put("cookies", Util.parseCookie(response.getHeaders().get("Set-Cookie")));
		return retorno;
		
	}catch(HttpClientErrorException e) {
		JSONObject retorno = new JSONObject();
		retorno.put("status", e.getStatusCode().value());
		return retorno;
	}
	
}

public JSONObject updateUser(Tbl_user usr, Cookie[] cookies) {
    String URL = Server.getHostname() + "user/" +usr.getId() +"/";
    
    String[] tokens = Util.extractTokens(cookies);
	
	if(tokens == null) {
		
		JSONObject retorno = new JSONObject();
		retorno.put("status", 0);
		return retorno;
	}
	
	
	JSONObject datos = new JSONObject(usr);
	datos.remove("groups");
	datos.remove("id");
	datos.remove("is_staff");
	datos.remove("is_superuser");
	datos.remove("name");
	datos.remove("date_joined");
	datos.remove("profile");
	
    HttpHeaders headers = new HttpHeaders();

	String cookieHeader = "token-access="+tokens[0] + "; " + "token-refresh="+ tokens[1];

	headers.add("Cookie", cookieHeader);
	headers.setContentType(MediaType.APPLICATION_JSON);
	HttpEntity<String> req = new HttpEntity<String>(datos.toString(),headers);
	
	try {
		ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.PUT, req, String.class);
		String str = response.getBody();
		
		JSONObject retorno = new JSONObject();
		retorno.put("status", response.getStatusCodeValue());
		retorno.put("str", str);
		retorno.put("cookies", Util.parseCookie(response.getHeaders().get("Set-Cookie")));
		return retorno;
	}catch(HttpClientErrorException e) {
	    System.err.println(e.getMessage());
		JSONObject retorno = new JSONObject();
		retorno.put("status", e.getStatusCode().value());
		return retorno;
	}
}

public JSONObject asignarRol(Tbl_user usr, Cookie[] cookies) {
    String URL = Server.getHostname() + "user/" +usr.getId() +"/";
    
    String[] tokens = Util.extractTokens(cookies);
    usr.setIs_active(true);
	
	if(tokens == null) {
		
		JSONObject retorno = new JSONObject();
		retorno.put("status", 0);
		return retorno;
	}
	
	
	JSONObject datos = new JSONObject(usr);
	datos.remove("password");
	datos.remove("id");
	datos.remove("is_staff");
	datos.remove("is_superuser");
	datos.remove("name");
	datos.remove("date_joined");
	datos.remove("profile");
	datos.remove("username");
	datos.remove("email");
	datos.remove("first_name");
	datos.remove("last_name");

	System.out.println("asignarRol_DT_user: " + datos.toString());
	
    HttpHeaders headers = new HttpHeaders();

	String cookieHeader = "token-access="+tokens[0] + "; " + "token-refresh="+ tokens[1];

	headers.add("Cookie", cookieHeader);
	headers.setContentType(MediaType.APPLICATION_JSON);
	HttpEntity<String> req = new HttpEntity<String>(datos.toString(),headers);
	
	try {
		ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.PUT, req, String.class);
		String str = response.getBody();
		
		JSONObject retorno = new JSONObject();
		retorno.put("status", response.getStatusCodeValue());
		retorno.put("str", str);
		retorno.put("cookies", Util.parseCookie(response.getHeaders().get("Set-Cookie")));
		return retorno;
	}catch(HttpClientErrorException e) {
	    System.err.println(e.getMessage());
		JSONObject retorno = new JSONObject();
		retorno.put("status", e.getStatusCode().value());
		return retorno;
	}
}


public JSONObject deleteUser(Tbl_user usr, Cookie[] cookies) {
	String URL = Server.getHostname() + "user/" + usr.getId() +"/";
	
    String[] tokens = Util.extractTokens(cookies);
	
	if(tokens == null) {
		
		JSONObject retorno = new JSONObject();
		retorno.put("status", 0);
		return retorno;
	}
	
	JSONObject datos = new JSONObject(usr);
	datos.remove("groups");
	datos.remove("first_name");
	datos.remove("last_name");
	datos.remove("email");
	datos.remove("password");
	datos.remove("username");
	datos.remove("id");
	datos.remove("is_staff");
	datos.remove("is_superuser");
	datos.remove("name");
	datos.remove("date_joined");
	datos.remove("profile");

	HttpHeaders headers = new HttpHeaders();

	String cookieHeader = "token-access="+tokens[0] + "; " + "token-refresh="+ tokens[1];

	headers.add("Cookie", cookieHeader);
	headers.setContentType(MediaType.APPLICATION_JSON);
	HttpEntity<String> req = new HttpEntity<String>(datos.toString(),headers);
	
	try {
		ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.PUT, req, String.class);
		String str = response.getBody();
		
		JSONObject retorno = new JSONObject();
		retorno.put("status", response.getStatusCodeValue());
		retorno.put("str", str);
		retorno.put("cookies", Util.parseCookie(response.getHeaders().get("Set-Cookie")));
		return retorno;
	} catch(HttpClientErrorException e) {
	    System.err.println(e.getMessage());
		JSONObject retorno = new JSONObject();
		retorno.put("status", e.getStatusCode().value());
		return retorno;
	}
  }
}
