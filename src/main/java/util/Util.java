package util;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class Util {
	
	public static void setTokenCookies(HttpServletRequest req, HttpServletResponse res, String[] cookies) {
		
		//String cookies[] =  (String[]) respuesta.get("cookies");
		String [] parts = cookies[0].split("=");
		String [] parts2 = cookies[1].split("=");
		Cookie ck = new Cookie(parts[0],parts[1]);
		Cookie ck2 = new Cookie(parts2[0],parts2[1]);
		ck.setMaxAge(1800);
		ck.setPath("/");
		ck2.setMaxAge(1800);
		ck2.setPath("/");
		res.addCookie(ck);
		res.addCookie(ck2);
	}
	
public static void setTokenCookiesJsp(HttpServletRequest req, HttpServletResponse res, String[] cookies) {
		
		//String cookies[] =  (String[]) respuesta.get("cookies");
		Cookie ck = new Cookie("token-access",cookies[0]);
		Cookie ck2 = new Cookie("token-refresh", cookies[1]);
		ck.setMaxAge(1800);
		ck.setPath("/");
		ck2.setMaxAge(1800);
		ck2.setPath("/");
		res.addCookie(ck);
		res.addCookie(ck2);
	}
	
	public static String[] parseCookie(List<String> cookies) {
		
		String[] cks = new String[2];
		String [] parts = cookies.get(0).split(";");
		String [] parts2 = cookies.get(1).split(";");
		cks[0] = parts[0];
		cks[1] = parts2[0];
		
		
		
		return cks;
	}
	
	public static String[] extractTokens(Cookie[] cookies) {
		boolean access = false;
		boolean refresh = false;
		String[] tokens = new String[2];

		if(cookies == null) {
			tokens[0] = "-123";
			tokens[1] = "-123";
			return tokens;
		}
		
		for(Cookie c : cookies)
		{
			if(c.getName().equals("token-access")) {
				access = true;
				tokens[0] = c.getValue();
			}
			
			if(c.getName().equals("token-refresh")) {
				refresh = true;
				tokens[1] = c.getValue();
			}
		}
		
		if(!(access && refresh)) {
			
			//return null;
			return null;
		}else {
			return tokens;
		}
	}

}
