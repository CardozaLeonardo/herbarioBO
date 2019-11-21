package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Locale;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import datos.DT_rol;
import entidades.Tbl_rol;

//import com.example.dao.DT_user;
//import com.example.entity.User;

@Controller
public class MainController {
	
	@GetMapping("/")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="XD") String name, Model model, 
			@CookieValue(value = "token-access", defaultValue = "Atta") String token,
			@CookieValue(value = "token-refresh", defaultValue = "Atta") String token2
			, HttpServletResponse res) {
		
		
		/*DT_rol dtr = new DT_rol();
		if(token.equals("Atta")) {
			
			String[] sessions = dtr.optenerCredenciales();
			if(sessions != null) {
				String [] parts = sessions[0].split("=");
				String [] parts2 = sessions[1].split("=");
				Cookie ck = new Cookie(parts[0],parts[1]);
				Cookie ck2 = new Cookie(parts2[0],parts2[1]);
				ck.setMaxAge(300);
				ck2.setMaxAge(300);
				res.addCookie(ck);
				res.addCookie(ck2);
			}
		}*/
		//dtr.getRoles(res, token, token2);
        return "index.jsp";
    }
	
	@GetMapping("/login")
	public String login(@RequestParam(name="name", required=false, defaultValue="XD") String name, Model model, 
			@CookieValue(value = "token-access", defaultValue = "Atta") String token,
			@CookieValue(value = "token-refresh", defaultValue = "Atta") String token2
			, HttpServletResponse res, HttpServletRequest req) {
		
		
		/*DT_rol dtr = new DT_rol();
		Tbl_rol[] roles = null;
		try {
			
			if(token.equals("Atta")) {
				
				String[] sessions = dtr.optenerCredenciales();
				if(sessions != null) {
					String [] parts = sessions[0].split("=");
					String [] parts2 = sessions[1].split("=");
					Cookie ck = new Cookie(parts[0],parts[1]);
					Cookie ck2 = new Cookie(parts2[0],parts2[1]);
					ck.setMaxAge(300);
					ck2.setMaxAge(300);
					res.addCookie(ck);
					res.addCookie(ck2);
				}
			}
			JSONObject obj = dtr.getRoles(req.getCookies());
			if(obj.getInt("status") == 200) {
				
				roles = (Tbl_rol[]) obj.get("roles");
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.getMessage();
			//return "redirect:http://localhost:8080/login";
			
			//e.printStackTrace();
		}
        
		if(roles != null) {
			model.addAttribute("listaRoles", roles);
			return "login.jsp";
		}*/
		return "login.jsp";
    }
	
	
	

}
