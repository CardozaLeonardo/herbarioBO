package controlador;

import datos.DT_country;
import entidades.fichas_tecnicas.Tbl_country;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class CountryController {

    @GetMapping("/location/country")
    public String getCountries(HttpServletRequest req, HttpServletResponse res, Model model)
            throws IOException {
        DT_country dtCountry = new DT_country();

        JSONObject result = dtCountry.getCountries(req.getCookies());

        if(result.getInt("status") == 401) {
            res.sendRedirect(req.getContextPath() + "/toLoginPage");
            return null;
        }

        Tbl_country[] countries = (Tbl_country[]) result.get("countries");
        model.addAttribute("countries", countries);

        return "/location/country.jsp";
    }
}
