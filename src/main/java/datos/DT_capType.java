package datos;

import javax.servlet.http.Cookie;

import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import entidades.fichas_tecnicas.Tbl_capType;
import entidades.fichas_tecnicas.Tbl_city;
import util.Util;

public class DT_capType {
	
   private RestTemplate restTemplate = new RestTemplate();
	
	public JSONObject getCountries(Cookie[] cookies) {
		
		String URL = Server.getHostname() + "mushroom_cap_type/";
		
		
		
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
			
			ResponseEntity<Tbl_capType[]> response = restTemplate.exchange(URL, HttpMethod.GET, req, Tbl_capType[].class);
			Tbl_capType[] caps = response.getBody();
			
			JSONObject retorno = new JSONObject();
			retorno.put("status", response.getStatusCodeValue());
			retorno.put("caps", caps);
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

	public JSONObject saveCap(Tbl_capType cap, Cookie[] cookies) {

		String URL = Server.getHostname() + "mushroom_cap_type/";

		//Tbl_user user = null;

		JSONObject data = new JSONObject(cap);
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
			String caps = response.getBody();

			JSONObject retorno = new JSONObject();
			retorno.put("status", response.getStatusCodeValue());
			retorno.put("cap", caps);
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

	public JSONObject deleteCap(int id, Cookie[] cookies) {

		String URL = Server.getHostname() + "mushroom_cap_type/" + id + "/";

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
