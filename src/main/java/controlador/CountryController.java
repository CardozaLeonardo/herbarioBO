package controlador;

import datos.DT_country;
import entidades.fichas_tecnicas.Tbl_country;
import negocio.CountryValidator;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import util.MessageAlertUtil;
import util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class CountryController {

    CountryValidator countryValidator = new CountryValidator();
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

    @GetMapping("/location/newCountry")
    public String createCountry() {
        return "/location/newCountry.jsp";
    }

    @PostMapping("/saveCountry")
    public RedirectView saveCountry(HttpServletRequest req, HttpServletResponse res,
                                    RedirectAttributes redir) {
        RedirectView rv = new RedirectView( req.getContextPath() + "/location/country");

        JSONObject newCountry = new JSONObject();
        String name = req.getParameter("name");
        newCountry.put("name", name);
        DT_country dtCountry = new DT_country();



        int valid = countryValidator.alreadyExistName(name, req.getCookies());

        if( valid == -1) {
            rv = new RedirectView( req.getContextPath() + "/login");
            MessageAlertUtil.UnauthorizedAccessMessage(redir);
            return rv;
        }

        if(valid == 1) {
            rv = new RedirectView( req.getContextPath() + "/location/newCountry");
            MessageAlertUtil.alreadyExistMessage(redir, "el nombre");
            return rv;
        }

        JSONObject result = dtCountry.saveCountry(newCountry, req.getCookies());

        if(result.getInt("status") == 401) {
            rv = new RedirectView( req.getContextPath() + "/login");
            MessageAlertUtil.UnauthorizedAccessMessage(redir);
            return rv;
        }

        MessageAlertUtil.SuccessStoreMessage(redir);

        String[] cookies = (String[]) result.get("cookies");
        Util.setTokenCookies(req, res, cookies);

        return rv;
    }

    @GetMapping("/location/updateCountry")
    public String updateCountry(HttpServletRequest req, HttpServletResponse res, Model model) throws IOException {

        DT_country dtCountry = new DT_country();
        int id = Integer.parseInt(req.getParameter("id"));

        JSONObject result = dtCountry.getCountry(id, req.getCookies());

        if(result.getInt("status") == 401) {
            res.sendRedirect(req.getContextPath() + "/toLoginPage");
            return null;
        }

        Tbl_country country = (Tbl_country) result.get("country");
        model.addAttribute("country", country);

        String[] cookies = (String[]) result.get("cookies");
        Util.setTokenCookies(req, res, cookies);

        return "/location/updateCountry.jsp";
    }

    @PostMapping("/changeCountry")
    public RedirectView changeCountry(HttpServletRequest req, HttpServletResponse res,
                                      RedirectAttributes redir) {
        RedirectView rv = new RedirectView( req.getContextPath() + "/location/country");

        JSONObject newCountry = new JSONObject();
        String name = req.getParameter("name");
        int id = Integer.parseInt(req.getParameter("countryID"));
        newCountry.put("name", name);
        DT_country dtCountry = new DT_country();



        int valid = countryValidator.alreadyExistNameUpdate(name, req.getCookies(), id);

        if(valid == -1) {
            rv = new RedirectView( req.getContextPath() + "/login");
            MessageAlertUtil.UnauthorizedAccessMessage(redir);
            return rv;
        }

        if(valid == 1) {
            rv = new RedirectView( req.getContextPath() + "/location/newCountry");
            MessageAlertUtil.alreadyExistMessage(redir, "el nombre");
            return rv;
        }

        JSONObject result = dtCountry.updateCountry(newCountry, id, req.getCookies());

        if(result.getInt("status") == 401) {
            rv = new RedirectView( req.getContextPath() + "/login");
            MessageAlertUtil.UnauthorizedAccessMessage(redir);
            return rv;
        }

        MessageAlertUtil.SuccessUpdateMessage(redir);

        String[] cookies = (String[]) result.get("cookies");
        Util.setTokenCookies(req, res, cookies);

        return rv;
    }

    @GetMapping("/deleteCountry")
    public RedirectView deleteCountry(HttpServletRequest req, HttpServletResponse res,
                                      RedirectAttributes redir) {
        RedirectView rv = new RedirectView( req.getContextPath() + "/location/country");

        int id = Integer.parseInt(req.getParameter("id"));
        DT_country dtCountry = new DT_country();

        JSONObject result = dtCountry.deleteCountry(id, req.getCookies());

        if(result.getInt("status") == 401) {
            rv = new RedirectView( req.getContextPath() + "/login");
            MessageAlertUtil.UnauthorizedAccessMessage(redir);
            return rv;
        }

        MessageAlertUtil.SuccessDeleteMessage(redir);

        String[] cookies = (String[]) result.get("cookies");
        Util.setTokenCookies(req, res, cookies);

        return rv;
    }
}
