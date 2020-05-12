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

import entidades.fichas_tecnicas.Tbl_ecosystem;
import util.Util;

public class DT_ecosystem {
private RestTemplate restTemplate = new RestTemplate();
	
	public JSONObject getBioStatus(Cookie[] cookies) {
		
		String URL = Server.getHostname() + "ecosystem/";
		
		
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
			
			ResponseEntity<Tbl_ecosystem[]> response = restTemplate.exchange(URL, HttpMethod.GET, req, Tbl_ecosystem[].class);
			Tbl_ecosystem[] ecosystems = response.getBody();
			
			JSONObject retorno = new JSONObject();
			retorno.put("status", response.getStatusCodeValue());
			retorno.put("ecosystems", ecosystems);
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
	
public JSONObject getEcosystem(int id, Cookie[] cookies) {
		
		String URL = Server.getHostname() + "ecosystem/" + id + "/";
		
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
			
			ResponseEntity<Tbl_ecosystem> response = restTemplate.exchange(URL, HttpMethod.GET, req, Tbl_ecosystem.class);
			Tbl_ecosystem ecosystem = response.getBody();
			
			JSONObject retorno = new JSONObject();
			retorno.put("status", response.getStatusCodeValue());
			retorno.put("ecosystem", ecosystem);
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
	
	
public JSONObject saveEcosystem(Tbl_ecosystem ecosystem, Cookie[] cookies) {
		
		String URL = Server.getHostname() + "ecosystem/";
		
		//Tbl_user user = null;
		
		JSONObject data = new JSONObject(ecosystem);
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
		
		//System.out.println(data.toString());
		
		HttpEntity<String> req = new HttpEntity<String>(data.toString(),headers);
		
		try {
			
			ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.POST, req, String.class);
			String eco = response.getBody();
			
			JSONObject retorno = new JSONObject();
			retorno.put("status", response.getStatusCodeValue());
			retorno.put("ecosystem", eco);
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


public JSONObject updateEcosystem(Tbl_ecosystem ecosystem, Cookie[] cookies) {
	
	int id = ecosystem.getId();
	String URL = Server.getHostname() + "ecosystem/" + id + "/";
	
	//Tbl_user user = null;
	JSONObject data = new JSONObject(ecosystem);
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
		String eco = response.getBody();
		
		JSONObject retorno = new JSONObject();
		retorno.put("status", response.getStatusCodeValue());
		retorno.put("ecosystem", eco);
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

public JSONObject deleteEcosystem(int id, Cookie[] cookies) {
	
	String URL = Server.getHostname() + "ecosystem/" + id + "/";
	
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
		String eco = response.getBody();
		
		JSONObject retorno = new JSONObject();
		retorno.put("status", response.getStatusCodeValue());
		retorno.put("ecosystem", eco);
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
