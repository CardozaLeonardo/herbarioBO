package datos;

import javax.servlet.http.Cookie;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import entidades.fichas_tecnicas.Tbl_city;
import util.Util;

public class DT_city {
	
   private RestTemplate restTemplate = new RestTemplate();
	
	public JSONObject getCountries(Cookie[] cookies) {
		
		String URL = Server.getHostname() + "city/";
		
		
		
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
			
			ResponseEntity<Tbl_city[]> response = restTemplate.exchange(URL, HttpMethod.GET, req, Tbl_city[].class);
			Tbl_city[] cities = response.getBody();
			
			JSONObject retorno = new JSONObject();
			retorno.put("status", response.getStatusCodeValue());
			retorno.put("cities", cities);
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
