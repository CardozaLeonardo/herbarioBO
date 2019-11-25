package controlador;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import datos.DT_country;
import datos.DT_family;
import datos.DT_genus;
import entidades.fichas_tecnicas.Tbl_country;
import entidades.fichas_tecnicas.Tbl_family;
import entidades.fichas_tecnicas.Tbl_genus;
import util.ServicioArchivo;

@Controller
public class EspecimenController {
	
	//@Autowired
	ServicioArchivo servicioArchivo;
	
	@GetMapping("/fichas/fungus")
	public String showFungus(Model model) {
		
		model.addAttribute("pass", 1);
		
		return "/fichas/fungus.jsp";
	}
	
	@GetMapping("/fichas/newFungus")
	public String nuevoHongo(HttpServletRequest req, HttpServletResponse res,Model model) {
		
		DT_country dtc = new DT_country();
		JSONObject objCountry = dtc.getCountries(req.getCookies());
		
		if(objCountry.getInt("status") == 200) {
			Tbl_country[] countries = (Tbl_country[]) objCountry.get("countries");
			model.addAttribute("countries", countries);
		}
		
		DT_family dtf = new DT_family();
		JSONObject objFamily = dtf.getFamilies(req.getCookies());
		
		if(objFamily.getInt("status") == 200) {
			Tbl_family[] families = (Tbl_family[]) objFamily.get("families");
			model.addAttribute("families", families);
		}
		
		DT_genus dtg = new DT_genus();
		JSONObject objGen = dtg.getGenus(req.getCookies());
		
		if(objGen.getInt("status") == 200) {
			Tbl_genus[] genus = (Tbl_genus[]) objGen.get("genus");
			model.addAttribute("genus", genus);
		}
		
		model.addAttribute("pass", 1);
		
		return "/fichas/newFungus.jsp";
	}
	
	@GetMapping("/fichas/especimenPlanta")
	public String especimenPlanta(HttpServletRequest req, HttpServletResponse res,Model model) {
		return "/fichas/PlantList.jsp";
	}
	
	
	@PostMapping("/guardarHongo")
	public RedirectView guardarHongo(@RequestParam("photo") MultipartFile file, HttpServletRequest req, HttpServletResponse res,
			RedirectAttributes redir) {
		RedirectView rv = new RedirectView(req.getContextPath() + "/fichas/newFungus");
		servicioArchivo = new ServicioArchivo();
		
		if(servicioArchivo.subirArchivo(file) == false) {
			rv = new RedirectView(req.getContextPath() + "/fichas/newFungus?status=error");
		}
		
		return rv;
	}
	
}
