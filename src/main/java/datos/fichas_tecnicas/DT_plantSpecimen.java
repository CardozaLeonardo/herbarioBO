package datos.fichas_tecnicas;

import java.io.IOException;

import javax.servlet.http.Cookie;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import datos.Server;
import entidades.Tbl_rol;
import entidades.fichas_tecnicas.Tbl_mushroomSpecimen;
import entidades.fichas_tecnicas.Tbl_plantSpecimen;
import util.Util;
import utils.Cookies;

public class DT_plantSpecimen {

	private static RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
	/*private static Server server = new Server();*/
	private static String ENDPOINT_URL = Server.getHostname() + "/plant_specimen/";
	
	public JSONObject getPlantSpecimens(Cookie[] cookies) throws IOException {
		
		Cookies setCookies  = new Cookies();
		HttpHeaders headers = Cookies.setCookies(cookies);	
		JSONObject result = new JSONObject();
		
		String set_cookie = headers.getFirst(HttpHeaders.SET_COOKIE);
		
		
		/*if (headers ) {
			JSONObject retorno = new JSONObject();
			retorno.put("status", 0);
			return retorno;
		}*/
		
		Tbl_plantSpecimen[] plantSpecimen = null;
		
		HttpEntity<Tbl_plantSpecimen[]> response = new HttpEntity<Tbl_plantSpecimen[]>(headers);
		
		try {
			
			ResponseEntity<Tbl_plantSpecimen[]> resul = restTemplate.exchange(ENDPOINT_URL, HttpMethod.GET,response, Tbl_plantSpecimen[].class);
			
			
			if(resul.getStatusCodeValue() == 200) {
				
				plantSpecimen = resul.getBody();
				
				
				JSONObject retorno = new JSONObject();
				retorno.put("status", resul.getStatusCodeValue());
				retorno.put("plant_specimens", plantSpecimen);
				return retorno;
				
			}else if(resul.getStatusCodeValue() == 401) {
				System.out.println("No tienes acceso we...");
			}else {
				System.out.println("Algo salio mal we...");
			}
		}catch(HttpClientErrorException e) {
			
			JSONObject retorno = new JSONObject();
			retorno.put("status", e.getStatusCode().value());
			return retorno;
		
			}
			
	
		return null;
	}
	
public JSONObject getPlant(int id, Cookie[] cookies) {
		
		String URL = Server.getHostname() + "plant_specimen/" + id + "/";
		
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
			
			ResponseEntity<Tbl_plantSpecimen> response = restTemplate.exchange(URL, HttpMethod.GET, req, Tbl_plantSpecimen.class);
			Tbl_plantSpecimen plant = response.getBody();
			
			JSONObject retorno = new JSONObject();
			retorno.put("status", response.getStatusCodeValue());
			retorno.put("plant", plant);
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
	
	
public JSONObject guardarPlanta(MultiValueMap<String, Object> newFungus, Cookie[] cookies) {
		
		String URL = Server.getHostname() + "plant_specimen/";
		
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
		//headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);



	HttpEntity<MultiValueMap<String, Object>> req =
			new HttpEntity<>(newFungus,headers);
		
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


public JSONObject actualizarPlanta(MultiValueMap<String, Object> newFungus, Cookie[] cookies, int ID) {
	
	int id = ID;
	String URL = Server.getHostname() + "plant_specimen/" + id + "/";
	
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
	headers.setContentType(MediaType.MULTIPART_FORM_DATA);
	
	System.out.println(newFungus.toString());

	HttpEntity<MultiValueMap<String, Object>> req =
			new HttpEntity<>(newFungus,headers);
	
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
		

		System.out.println(e.getMessage());

		JSONObject retorno = new JSONObject();
		retorno.put("status", e.getStatusCode().value());
		//retorno.put("user", user);
		return retorno;
	}
}

	public JSONObject actualizarPlantaPatch(MultiValueMap<String, Object> newFungus, Cookie[] cookies, int ID) {

		int id = ID;
		String URL = Server.getHostname() + "plant_specimen/" + id + "/";

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
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		System.out.println(newFungus.toString());

		HttpEntity<MultiValueMap<String, Object>> req =
				new HttpEntity<>(newFungus,headers);

		try {

			ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.PATCH, req, String.class);
			String fungus = response.getBody();

			JSONObject retorno = new JSONObject();
			retorno.put("status", response.getStatusCodeValue());
			retorno.put("fungus", fungus);
			retorno.put("cookies", Util.parseCookie(response.getHeaders().get("Set-Cookie")));
			return retorno;

		}catch(HttpClientErrorException e)
		{


			System.out.println(e.getMessage());

			JSONObject retorno = new JSONObject();
			retorno.put("status", e.getStatusCode().value());
			//retorno.put("user", user);
			return retorno;
		}
	}

public JSONObject deletePlant(int id, Cookie[] cookies) {
	
	String URL = Server.getHostname() + "plant_specimen/" + id + "/";
	
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