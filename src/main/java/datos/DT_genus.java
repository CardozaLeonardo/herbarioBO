package datos;

import javax.servlet.http.Cookie;

import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import entidades.fichas_tecnicas.Tbl_genus;
import util.Util;

public class DT_genus {
private RestTemplate restTemplate = new RestTemplate();
	
	public JSONObject getGenus(Cookie[] cookies) {
		
		String URL = Server.getHostname() + "genus/";
		
		
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
			
			ResponseEntity<Tbl_genus[]> response = restTemplate.exchange(URL, HttpMethod.GET, req, Tbl_genus[].class);
			Tbl_genus[] genus = response.getBody();
			
			JSONObject retorno = new JSONObject();
			retorno.put("status", response.getStatusCodeValue());
			retorno.put("genus", genus);
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

	public JSONObject getGenus(Cookie[] cookies, int idGenus) {

		String URL = Server.getHostname() + "genus/" + idGenus + "/";


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

			ResponseEntity<Tbl_genus> response = restTemplate.exchange(URL, HttpMethod.GET, req, Tbl_genus.class);
			Tbl_genus genus = response.getBody();

			JSONObject retorno = new JSONObject();
			retorno.put("status", response.getStatusCodeValue());
			retorno.put("genus", genus);
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

	public JSONObject saveGenus(MultiValueMap<String, Object> genus, Cookie[] cookies) {
		String URL = Server.getHostname() + "genus/";

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
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);



		HttpEntity<MultiValueMap<String, Object>> req =
				new HttpEntity<>(genus,headers);

		try {

			ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.POST, req, String.class);
			String fungus = response.getBody();

			JSONObject retorno = new JSONObject();
			retorno.put("status", response.getStatusCodeValue());
			retorno.put("genus", fungus);
			retorno.put("cookies", Util.parseCookie(response.getHeaders().get("Set-Cookie")));
			return retorno;

		}catch(HttpClientErrorException e)
		{

			e.getMessage();
			e.printStackTrace();
			JSONObject retorno = new JSONObject();
			retorno.put("status", e.getStatusCode().value());
			//retorno.put("user", user);
			return retorno;
		}
	}

	public JSONObject updateGenus(MultiValueMap<String, Object> genus, Cookie[] cookies, int idGenus) {
		String URL = Server.getHostname() + "genus/" + idGenus + "/";

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
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);



		HttpEntity<MultiValueMap<String, Object>> req =
				new HttpEntity<>(genus,headers);

		try {

			ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.PUT, req, String.class);
			String fungus = response.getBody();

			JSONObject retorno = new JSONObject();
			retorno.put("status", response.getStatusCodeValue());
			retorno.put("genus", fungus);
			retorno.put("cookies", Util.parseCookie(response.getHeaders().get("Set-Cookie")));
			return retorno;

		}catch(HttpClientErrorException e)
		{

			e.getMessage();
			e.printStackTrace();
			JSONObject retorno = new JSONObject();
			retorno.put("status", e.getStatusCode().value());
			//retorno.put("user", user);
			return retorno;
		}
	}
}
