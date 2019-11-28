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

import entidades.fichas_tecnicas.Tbl_capType;
import entidades.fichas_tecnicas.Tbl_city;
import entidades.fichas_tecnicas.Tbl_family;
import entidades.fichas_tecnicas.Tbl_formType;
import util.Util;

public class DT_formType {
	
   private RestTemplate restTemplate = new RestTemplate();
	
	public JSONObject getCountries(Cookie[] cookies) {
		
		String URL = Server.getHostname() + "mushroom_form_type/";
		
		
		
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
			
			ResponseEntity<Tbl_formType[]> response = restTemplate.exchange(URL, HttpMethod.GET, req, Tbl_formType[].class);
			Tbl_formType[] forms = response.getBody();
			
			JSONObject retorno = new JSONObject();
			retorno.put("status", response.getStatusCodeValue());
			retorno.put("forms", forms);
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
	
public JSONObject saveForm(Tbl_formType form, Cookie[] cookies) {
		
		String URL = Server.getHostname() + "mushroom_form_type/";
		
		//Tbl_user user = null;
		
		JSONObject data = new JSONObject(form);
		data.remove("id");
		
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

public JSONObject deleteForm(int id, Cookie[] cookies) {
	
	String URL = Server.getHostname() + "mushroom_form_type/" + id + "/";
	
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
