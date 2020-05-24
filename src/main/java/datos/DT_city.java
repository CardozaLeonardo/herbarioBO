package datos;

import javax.servlet.http.Cookie;

import org.json.JSONObject;
import org.springframework.http.*;
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
			retorno.put("status", 401);
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

			if(response.getHeaders().get("Set-Cookie") == null) {
				retorno.put("status", 401);
				return retorno;
			}
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

	public JSONObject saveCity(JSONObject city,Cookie[] cookies) {

		String URL = Server.getHostname() + "city/";



		String[] tokens = Util.extractTokens(cookies);

		if(tokens == null) {

			JSONObject retorno = new JSONObject();
			retorno.put("status", 401);
			return retorno;
		}

		HttpHeaders headers = new HttpHeaders();

		String cookieHeader = "token-access="+tokens[0] + "; " + "token-refresh="+ tokens[1];

		headers.add("Cookie", cookieHeader);
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> req = new HttpEntity<String>(city.toString(),headers);

		try {

			ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.POST, req, String.class);
			String countries = response.getBody();

			JSONObject retorno = new JSONObject();
			//System.out.println("DT_Country: " + response.getStatusCodeValue());

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

	public JSONObject updateCity(JSONObject city, int id, Cookie[] cookies) {

		String URL = Server.getHostname() + "city/" + id + "/";



		String[] tokens = Util.extractTokens(cookies);

		if(tokens == null) {

			JSONObject retorno = new JSONObject();
			retorno.put("status", 401);
			return retorno;
		}

		HttpHeaders headers = new HttpHeaders();

		String cookieHeader = "token-access="+tokens[0] + "; " + "token-refresh="+ tokens[1];

		headers.add("Cookie", cookieHeader);
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> req = new HttpEntity<String>(city.toString(),headers);

		try {

			ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.PUT, req, String.class);
			String countries = response.getBody();

			JSONObject retorno = new JSONObject();
			//System.out.println("DT_Country: " + response.getStatusCodeValue());

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

	public JSONObject getCity(int id, Cookie[] cookies) {

		String URL = Server.getHostname() + "city/" + id + "/";



		String[] tokens = Util.extractTokens(cookies);

		if(tokens == null) {

			JSONObject retorno = new JSONObject();
			retorno.put("status", 401);
			return retorno;
		}

		HttpHeaders headers = new HttpHeaders();

		String cookieHeader = "token-access="+tokens[0] + "; " + "token-refresh="+ tokens[1];

		headers.add("Cookie", cookieHeader);
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> req = new HttpEntity<String>(headers);

		try {

			ResponseEntity<Tbl_city> response = restTemplate.exchange(URL, HttpMethod.GET, req, Tbl_city.class);
			Tbl_city city = response.getBody();

			JSONObject retorno = new JSONObject();

			if(response.getHeaders().get("Set-Cookie") == null) {
				retorno.put("status", 401);
				return retorno;
			}
			retorno.put("status", response.getStatusCodeValue());
			retorno.put("city", city);
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

	public JSONObject deleteCity(int id,Cookie[] cookies) {

		String URL = Server.getHostname() + "city/" + id +"/";



		String[] tokens = Util.extractTokens(cookies);

		if(tokens == null) {

			JSONObject retorno = new JSONObject();
			retorno.put("status", 401);
			return retorno;
		}

		HttpHeaders headers = new HttpHeaders();

		String cookieHeader = "token-access="+tokens[0] + "; " + "token-refresh="+ tokens[1];

		headers.add("Cookie", cookieHeader);
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> req = new HttpEntity<String>(headers);

		try {

			ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.DELETE, req, String.class);
			String countries = response.getBody();

			JSONObject retorno = new JSONObject();
			//System.out.println("DT_Country: " + response.getStatusCodeValue());

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
