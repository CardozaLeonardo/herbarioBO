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

import entidades.fichas_tecnicas.Tbl_mushroomSpecimen;
import entidades.fichas_tecnicas.Tbl_species;
import util.Util;

public class DT_species {
private RestTemplate restTemplate = new RestTemplate();
	
	public JSONObject getSpecies(Cookie[] cookies) {
		
		String URL = Server.getHostname() + "species/";
		
		
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
			
			ResponseEntity<Tbl_species[]> response = restTemplate.exchange(URL, HttpMethod.GET, req, Tbl_species[].class);
			Tbl_species[] species = response.getBody();
			
			JSONObject retorno = new JSONObject();
			if(response.getHeaders().get("Set-Cookie") == null) {
                retorno.put("status", 401);
                return retorno;
            }

			retorno.put("status", response.getStatusCodeValue());
			retorno.put("species", species);
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
	
public JSONObject getSpecie(int id, Cookie[] cookies) {
		
		String URL = Server.getHostname() + "species/" + id + "/";
		
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
			
			ResponseEntity<Tbl_species> response = restTemplate.exchange(URL, HttpMethod.GET, req, Tbl_species.class);
			Tbl_species specie = response.getBody();
			
			JSONObject retorno = new JSONObject();
			retorno.put("status", response.getStatusCodeValue());
			retorno.put("specie", specie);
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
	
	
public JSONObject saveSpecie(JSONObject newSpecie, Cookie[] cookies) {
		
		String URL = Server.getHostname() + "species/";
		
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
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		//System.out.println(newFungus.toString());
		
		HttpEntity<String> req = new HttpEntity<String>(newSpecie.toString(),headers);
		
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
			
			e.getMessage();
			JSONObject retorno = new JSONObject();
			retorno.put("status", e.getStatusCode().value());
			//retorno.put("user", user);
			return retorno;
		}
	}


public JSONObject updateSpecie(JSONObject newSpecie, Cookie[] cookies) {
	
	int id = newSpecie.getInt("id");
	String URL = Server.getHostname() + "species/" + id + "/";
	
	//Tbl_user user = null;
	
	String[] tokens = Util.extractTokens(cookies);
	
	if(tokens == null) {
		
		JSONObject retorno = new JSONObject();
		retorno.put("status", 0);
		return retorno;
	}
	
	newSpecie.remove("id");
	
    HttpHeaders headers = new HttpHeaders();

	String cookieHeader = "token-access="+tokens[0] + "; " + "token-refresh="+ tokens[1];

	headers.add("Cookie", cookieHeader);
	headers.setContentType(MediaType.APPLICATION_JSON);
	
	//System.out.println(newFungus.toString());
	
	HttpEntity<String> req = new HttpEntity<String>(newSpecie.toString(),headers);
	
	try {
		
		ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.PUT, req, String.class);
		String fungus = response.getBody();
		
		JSONObject retorno = new JSONObject();
		retorno.put("status", response.getStatusCodeValue());
		retorno.put("species", fungus);
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

public JSONObject deleteSpecie(int id, Cookie[] cookies) {
	
	String URL = Server.getHostname() + "species/" + id + "/";
	
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
		String fungus = response.getBody();
		
		JSONObject retorno = new JSONObject();
		retorno.put("status", response.getStatusCodeValue());
		retorno.put("specie", fungus);
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
