package datos;

import javax.servlet.http.Cookie;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import entidades.MeResponse;
import entidades.Tbl_user;
import entidades.fichas_tecnicas.Tbl_country;
import util.Util;

public class DT_country {
	
	private RestTemplate restTemplate = new RestTemplate();
	
	public JSONObject getCountries(Cookie[] cookies) {
		
		String URL = Server.getHostname() + "country/";
		
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
			
			ResponseEntity<Tbl_country[]> response = restTemplate.exchange(URL, HttpMethod.GET, req, Tbl_country[].class);
			Tbl_country[] countries = response.getBody();
			
			JSONObject retorno = new JSONObject();
			System.out.println("DT_Country: " + response.getStatusCodeValue());

			if(response.getHeaders().get("Set-Cookie") == null) {
				retorno.put("status", 401);
			}else{
				retorno.put("status", 200);
			}
			retorno.put("countries", countries);
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
