package utils;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import org.springframework.http.HttpHeaders;


public class Cookies {
	
	public static HttpHeaders setCookies(Cookie[] cookies) {
		
		boolean access = false;
		boolean refresh = false;
		String tok  = null;
		String tok2 = null;
		HttpHeaders headers = new HttpHeaders(); 
		
		
		for(Cookie c : cookies)
		{
			if(c.getName().equals("token-access") ) {
				access = true;
				tok = c.getValue();
			}
			
			if(c.getName().equals("token-refresh")) {
				refresh = true;
				tok2 = c.getValue();
			}
		}
		
		if(!(access && refresh)) {
			/*JSONObject retorno = new JSONObject();
			retorno.put("status", 0);*/
			return headers;
		}
		
		// Esto sirve para agregar los headers, con .add() se agregan
		

		
		headers.add("Cookie", "token-access="+ tok);
		headers.add("Cookie", "token-refresh="+ tok2);
		
		
		return headers;
	}
	
	
	public static List<String> getCookies(Cookie[] cookies){
		
		String token = null;
		String tokenRefresh = null;
		List<String> tokens = new ArrayList<String>(); 


		for(Cookie c : cookies)
		{
			if(c.getName().equals("token-access")){
				token = c.getValue();
			}
			
			if(c.getName().equals("token-refresh")){
				tokenRefresh = c.getValue();
			}
		}

		tokens.add(token);
		tokens.add(tokenRefresh);
	
		return tokens;
	}

}

