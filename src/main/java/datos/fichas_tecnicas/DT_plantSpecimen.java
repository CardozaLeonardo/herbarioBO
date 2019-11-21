package datos.fichas_tecnicas;

import java.io.IOException;

import javax.servlet.http.Cookie;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import datos.Server;
import entidades.Tbl_rol;
import entidades.fichas_tecnicas.Tbl_plantSpecimen;
import utils.Cookies;

public class DT_plantSpecimen {

	private static RestTemplate restTemplate = new RestTemplate();
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
}