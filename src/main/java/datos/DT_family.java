package datos;

import javax.servlet.http.Cookie;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import entidades.fichas_tecnicas.Tbl_family;
import util.Util;

public class DT_family {
 private RestTemplate restTemplate = new RestTemplate();
	
	public JSONObject getFamilies(Cookie[] cookies) {
		
		String URL = Server.getHostname() + "family/";
		
		
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
			
			ResponseEntity<Tbl_family[]> response = restTemplate.exchange(URL, HttpMethod.GET, req, Tbl_family[].class);
			Tbl_family[] families = response.getBody();
			
			JSONObject retorno = new JSONObject();
			if(response.getHeaders().get("Set-Cookie") == null) {
				retorno.put("status", 401);
				return retorno;
			}
			retorno.put("status", response.getStatusCodeValue());
			retorno.put("families", families);

			retorno.put("cookies", Util.parseCookie(response.getHeaders().get("Set-Cookie")));
			return retorno;
			
		}catch(HttpClientErrorException e)
		{
			
			
			JSONObject retorno = new JSONObject();
			retorno.put("status", e.getStatusCode().value());
			//retorno.put("user", user);
			return retorno;
		}
	}
	
public JSONObject getFamily(int id, Cookie[] cookies) {
		
		String URL = Server.getHostname() + "family/" + id + "/";
		
		//Tbl_user user = null;
		
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
			
			ResponseEntity<Tbl_family> response = restTemplate.exchange(URL, HttpMethod.GET, req, Tbl_family.class);
			Tbl_family family = response.getBody();
			
			JSONObject retorno = new JSONObject();
			retorno.put("status", response.getStatusCodeValue());
			retorno.put("family", family);
			retorno.put("cookies", Util.parseCookie(response.getHeaders().get("Set-Cookie")));
			return retorno;
			
		}catch(HttpClientErrorException e)
		{
			
			
			JSONObject retorno = new JSONObject();
			retorno.put("status", e.getStatusCode().value());
			//retorno.put("user", user);
			return retorno;
		}
	}
	
	
public JSONObject saveFamily(Tbl_family family, Cookie[] cookies) {
		
		String URL = Server.getHostname() + "family/";
		
		//Tbl_user user = null;
		
		JSONObject data = new JSONObject(family);
		data.remove("id");
		
		String[] tokens = Util.extractTokens(cookies);
		
		if(tokens == null) {
			
			JSONObject retorno = new JSONObject();
			retorno.put("status", 0);
			return retorno;
		}
		
        HttpHeaders headers = new HttpHeaders();

	   String cookieHeader = "token-access="+tokens[0] + "; " + "token-refresh="+ tokens[1];

	    headers.add("Cookie", cookieHeader);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		System.out.println(data.toString());
		
		HttpEntity<String> req = new HttpEntity<String>(data.toString(),headers);
		
		try {
			
			ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.POST, req, String.class);
			String fungus = response.getBody();
			
			JSONObject retorno = new JSONObject();
			retorno.put("status", response.getStatusCodeValue());
			retorno.put("specie", fungus);
			retorno.put("cookies", Util.parseCookie(response.getHeaders().get("Set-Cookie")));
			return retorno;
			
		}catch(HttpClientErrorException e)
		{
			
			
			System.err.println(e.getMessage());
			e.printStackTrace();
			JSONObject retorno = new JSONObject();
			retorno.put("status", e.getStatusCode().value());
			//retorno.put("user", user);
			return retorno;
		}
	}


public JSONObject updateFamily(Tbl_family family, Cookie[] cookies) {
	
	int id = family.getId();
	String URL = Server.getHostname() + "family/" + id + "/";
	
	//Tbl_user user = null;
	JSONObject data = new JSONObject(family);
	data.remove("id");
	
	String[] tokens = Util.extractTokens(cookies);
	
	if(tokens == null) {
		
		JSONObject retorno = new JSONObject();
		retorno.put("status", 0);
		return retorno;
	}
	
	
	
    HttpHeaders headers = new HttpHeaders();

	String cookieHeader = "token-access="+tokens[0] + "; " + "token-refresh="+ tokens[1];

	headers.add("Cookie", cookieHeader);
	headers.setContentType(MediaType.APPLICATION_JSON);
	
	//System.out.println(newFungus.toString());
	
	HttpEntity<String> req = new HttpEntity<String>(data.toString(),headers);
	
	try {
		
		ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.PUT, req, String.class);
		String fam = response.getBody();
		
		JSONObject retorno = new JSONObject();
		retorno.put("status", response.getStatusCodeValue());
		retorno.put("family", fam);
		retorno.put("cookies", Util.parseCookie(response.getHeaders().get("Set-Cookie")));
		return retorno;
		
	}catch(HttpClientErrorException e)
	{
		
		e.getMessage();
		JSONObject retorno = new JSONObject();
		retorno.put("status", e.getStatusCode().value());
		//retorno.put("user", user);
		return retorno;
	}
}

public JSONObject deleteFamily(int id, Cookie[] cookies) {
	
	String URL = Server.getHostname() + "family/" + id + "/";
	
	//Tbl_user user = null;
	
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
		
		ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.DELETE, req, String.class);
		String family = response.getBody();
		
		JSONObject retorno = new JSONObject();
		retorno.put("status", response.getStatusCodeValue());
		retorno.put("family", family);
		retorno.put("cookies", Util.parseCookie(response.getHeaders().get("Set-Cookie")));
		return retorno;
		
	}catch(HttpClientErrorException e)
	{
		
		
		JSONObject retorno = new JSONObject();
		retorno.put("status", e.getStatusCode().value());
		//retorno.put("user", user);
		return retorno;
	}
}
}
