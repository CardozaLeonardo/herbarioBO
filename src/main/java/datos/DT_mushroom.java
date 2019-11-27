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
import util.Util;

public class DT_mushroom {
   private RestTemplate restTemplate = new RestTemplate();
	
	public JSONObject getCountries(Cookie[] cookies) {
		
		String URL = Server.getHostname() + "mushroom_specimen/";
		
		//Tbl_user user = null;
		
		String[] tokens = Util.extractTokens(cookies);
		
		if(tokens == null) {
			
			JSONObject retorno = new JSONObject();
			retorno.put("status", 0);
			return retorno;
		}
		
        HttpHeaders headers = new HttpHeaders(); 
		
		headers.add("Cookie", "token-access="+ tokens[0]);
		headers.add("Cookie", "token-refresh="+ tokens[1]);
		
		HttpEntity<String> req = new HttpEntity<String>(headers);
		
		try {
			
			ResponseEntity<Tbl_mushroomSpecimen[]> response = restTemplate.exchange(URL, HttpMethod.GET, req, Tbl_mushroomSpecimen[].class);
			Tbl_mushroomSpecimen[] fungus = response.getBody();
			
			JSONObject retorno = new JSONObject();
			retorno.put("status", response.getStatusCodeValue());
			retorno.put("fungus", fungus);
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
	
	
public JSONObject getFungus(int id, Cookie[] cookies) {
		
		String URL = Server.getHostname() + "mushroom_specimen/" + id + "/";
		
		//Tbl_user user = null;
		
		String[] tokens = Util.extractTokens(cookies);
		
		if(tokens == null) {
			
			JSONObject retorno = new JSONObject();
			retorno.put("status", 0);
			return retorno;
		}
		
        HttpHeaders headers = new HttpHeaders(); 
		
		headers.add("Cookie", "token-access="+ tokens[0]);
		headers.add("Cookie", "token-refresh="+ tokens[1]);
		
		HttpEntity<String> req = new HttpEntity<String>(headers);
		
		try {
			
			ResponseEntity<Tbl_mushroomSpecimen> response = restTemplate.exchange(URL, HttpMethod.GET, req, Tbl_mushroomSpecimen.class);
			Tbl_mushroomSpecimen fungus = response.getBody();
			
			JSONObject retorno = new JSONObject();
			retorno.put("status", response.getStatusCodeValue());
			retorno.put("fungus", fungus);
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
	
	
public JSONObject guardarHongo(JSONObject newFungus, Cookie[] cookies) {
		
		String URL = Server.getHostname() + "mushroom_specimen/";
		
		//Tbl_user user = null;
		
		String[] tokens = Util.extractTokens(cookies);
		
		if(tokens == null) {
			
			JSONObject retorno = new JSONObject();
			retorno.put("status", 0);
			return retorno;
		}
		
        HttpHeaders headers = new HttpHeaders(); 
		
		headers.add("Cookie", "token-access="+ tokens[0]);
		headers.add("Cookie", "token-refresh="+ tokens[1]);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		System.out.println(newFungus.toString());
		
		HttpEntity<String> req = new HttpEntity<String>(newFungus.toString(),headers);
		
		try {
			
			ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.POST, req, String.class);
			String fungus = response.getBody();
			
			JSONObject retorno = new JSONObject();
			retorno.put("status", response.getStatusCodeValue());
			retorno.put("fungus", fungus);
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


public JSONObject actualizarHongo(JSONObject newFungus, Cookie[] cookies) {
	
	int id = newFungus.getInt("id");
	String URL = Server.getHostname() + "mushroom_specimen/" + id + "/";
	
	//Tbl_user user = null;
	
	String[] tokens = Util.extractTokens(cookies);
	
	if(tokens == null) {
		
		JSONObject retorno = new JSONObject();
		retorno.put("status", 0);
		return retorno;
	}
	
	newFungus.remove("id");
	
    HttpHeaders headers = new HttpHeaders(); 
	
	headers.add("Cookie", "token-access="+ tokens[0]);
	headers.add("Cookie", "token-refresh="+ tokens[1]);
	headers.setContentType(MediaType.APPLICATION_JSON);
	
	System.out.println(newFungus.toString());
	
	HttpEntity<String> req = new HttpEntity<String>(newFungus.toString(),headers);
	
	try {
		
		ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.PUT, req, String.class);
		String fungus = response.getBody();
		
		JSONObject retorno = new JSONObject();
		retorno.put("status", response.getStatusCodeValue());
		retorno.put("fungus", fungus);
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

public JSONObject deleteFungus(int id, Cookie[] cookies) {
	
	String URL = Server.getHostname() + "mushroom_specimen/" + id + "/";
	
	//Tbl_user user = null;
	
	String[] tokens = Util.extractTokens(cookies);
	
	if(tokens == null) {
		
		JSONObject retorno = new JSONObject();
		retorno.put("status", 0);
		return retorno;
	}
	
    HttpHeaders headers = new HttpHeaders(); 
	
	headers.add("Cookie", "token-access="+ tokens[0]);
	headers.add("Cookie", "token-refresh="+ tokens[1]);
	
	HttpEntity<String> req = new HttpEntity<String>(headers);
	
	try {
		
		ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.DELETE, req, String.class);
		String fungus = response.getBody();
		
		JSONObject retorno = new JSONObject();
		retorno.put("status", response.getStatusCodeValue());
		retorno.put("fungus", fungus);
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
