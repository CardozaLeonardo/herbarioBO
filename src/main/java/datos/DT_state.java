package datos;

import javax.servlet.http.Cookie;

import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import entidades.fichas_tecnicas.Tbl_state;
import util.Util;

public class DT_state {
    private RestTemplate restTemplate = new RestTemplate();
	
	public JSONObject getFamilies(Cookie[] cookies) {
		
		String URL = Server.getHostname() + "state/";
		
		
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
			
			ResponseEntity<Tbl_state[]> response = restTemplate.exchange(URL, HttpMethod.GET, req, Tbl_state[].class);
			Tbl_state[] states = response.getBody();
			
			JSONObject retorno = new JSONObject();
			retorno.put("status", response.getStatusCodeValue());
			retorno.put("states", states);
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



	public JSONObject getState(int id, Cookie[] cookies) {
		String URL = Server.getHostname() + "state/" + id +"/";


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

			ResponseEntity<Tbl_state> response = restTemplate.exchange(URL, HttpMethod.GET, req, Tbl_state.class);
			Tbl_state state = response.getBody();

			JSONObject retorno = new JSONObject();
			retorno.put("status", response.getStatusCodeValue());
			retorno.put("state", state);
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

	public JSONObject updateState(JSONObject newState, int id, Cookie[] cookies) {
		String URL = Server.getHostname() + "state/" + id + "/";


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

		HttpEntity<String> req = new HttpEntity<String>(newState.toString(), headers);

		try {

			ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.PUT, req, String.class);
			String state = response.getBody();

			JSONObject retorno = new JSONObject();
			retorno.put("status", response.getStatusCodeValue());
			retorno.put("state", state);
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

	public JSONObject saveState(JSONObject newState, Cookie[] cookies) {
		String URL = Server.getHostname() + "state/";


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

		HttpEntity<String> req = new HttpEntity<String>(newState.toString(), headers);

		try {

			ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.POST, req, String.class);
			String state = response.getBody();

			JSONObject retorno = new JSONObject();
			retorno.put("status", response.getStatusCodeValue());
			retorno.put("state", state);
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
