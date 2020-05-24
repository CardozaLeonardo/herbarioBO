package controlador;

import datos.DT_city;
import datos.DT_state;
import entidades.fichas_tecnicas.Tbl_city;
import entidades.fichas_tecnicas.Tbl_state;
import negocio.CityValidator;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import util.MessageAlertUtil;
import util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class CityController {

    DT_city dtCity = new DT_city();
    CityValidator validator = new CityValidator();
    @GetMapping("/location/cities")
    public String showCities(HttpServletRequest req, HttpServletResponse res,
                             Model model) throws IOException {

        JSONObject result = dtCity.getCountries(req.getCookies());

        if(result.getInt("status") == 401) {
            res.sendRedirect(req.getContextPath() + "/toLoginPage");
            return null;
        }

        Tbl_city[] cities = (Tbl_city[]) result.get("cities");

        model.addAttribute("cities", cities);

        String[] cookies = (String[]) result.get("cookies");
        Util.setTokenCookies(req, res, cookies);

        return "/location/cities.jsp";
    }

    @GetMapping("/location/newCity")
    public String createCity(HttpServletRequest req, HttpServletResponse res,
                             Model model) throws IOException {
        DT_state dtState = new DT_state();

        JSONObject resultSt = dtState.getFamilies(req.getCookies());

        if(resultSt.getInt("status") == 401) {
            res.sendRedirect(req.getContextPath() + "/toLoginPage");
            return null;
        }

        Tbl_state[] states = (Tbl_state[]) resultSt.get("states");

        model.addAttribute("states", states);

        String[] cookies = (String[]) resultSt.get("cookies");
        Util.setTokenCookies(req, res, cookies);

        return "/location/newCity.jsp";

    }

    @GetMapping("/location/updateCity")
    public String updateCity(HttpServletRequest req, HttpServletResponse res,
                             Model model) throws IOException {
        DT_state dtState = new DT_state();
        int id = Integer.parseInt(req.getParameter("id"));



        JSONObject resultSt = dtState.getFamilies(req.getCookies());

        if(resultSt.getInt("status") == 401) {
            res.sendRedirect(req.getContextPath() + "/toLoginPage");
            return null;
        }

        JSONObject result = dtCity.getCity(id, req.getCookies());

        if(result.getInt("status") == 401) {
            res.sendRedirect(req.getContextPath() + "/toLoginPage");
            return null;
        }

        Tbl_city city = (Tbl_city) result.get("city");

        Tbl_state[] states = (Tbl_state[]) resultSt.get("states");

        model.addAttribute("states", states);
        model.addAttribute("city", city);

        String[] cookies = (String[]) resultSt.get("cookies");
        Util.setTokenCookies(req, res, cookies);

        return "/location/updateCity.jsp";
    }

    @PostMapping("/save_city")
    public RedirectView saveCity(HttpServletRequest req, HttpServletResponse res,
                                 RedirectAttributes redir) {
        RedirectView rv = new RedirectView(req.getContextPath() + "/location/cities");

        String name = req.getParameter("name");
        int state = Integer.parseInt(req.getParameter("state"));

        JSONObject newCity = new JSONObject();
        newCity.put("name", name);
        newCity.put("state", state);

        int valid = validator.alreadyExistName(name, state, req.getCookies());

        if(valid == -1) {
            rv = new RedirectView(req.getContextPath() + "/login");
            MessageAlertUtil.SuccessStoreMessage(redir);
            return rv;
        }

        if(valid == 1) {
            rv = new RedirectView(req.getContextPath() + "/location/newCity");
            MessageAlertUtil.alreadyExistCustomMsg(redir, "Ya existe una ciudad con el" +
                    " nombre especificado");
            return rv;
        }

        JSONObject result = dtCity.saveCity(newCity, req.getCookies());

        if(result.getInt("status") == 401) {
            rv = new RedirectView(req.getContextPath() + "/login");
            MessageAlertUtil.UnauthorizedAccessMessage(redir);
            return rv;
        }

        String[] cookies = (String[]) result.get("cookies");
        Util.setTokenCookies(req, res, cookies);

        MessageAlertUtil.SuccessStoreMessage(redir);
        return rv;

    }

    @PostMapping("change_city")
    public RedirectView changeCity(HttpServletRequest req, HttpServletResponse res,
                                   RedirectAttributes redir) {
        RedirectView rv = new RedirectView(req.getContextPath() + "/location/cities");

        String name = req.getParameter("name");
        int state = Integer.parseInt(req.getParameter("state"));
        int idCity = Integer.parseInt(req.getParameter("cityID"));

        JSONObject newCity = new JSONObject();
        newCity.put("name", name);
        newCity.put("state", state);

        int valid = validator.alreadyExistNameUpdate(name, req.getCookies(), idCity, state);

        if(valid == -1) {
            rv = new RedirectView(req.getContextPath() + "/login");
            MessageAlertUtil.SuccessStoreMessage(redir);
            return rv;
        }

        if(valid == 1) {
            rv = new RedirectView(req.getContextPath() + "/location/updateCity?id=" + idCity);
            MessageAlertUtil.alreadyExistCustomMsg(redir, "Ya existe una ciudad con el" +
                    " nombre especificado para el mismo estado");
            return rv;
        }

        JSONObject result = dtCity.updateCity(newCity, idCity, req.getCookies());

        if(result.getInt("status") == 401) {
            rv = new RedirectView(req.getContextPath() + "/login");
            MessageAlertUtil.UnauthorizedAccessMessage(redir);
            return rv;
        }

        String[] cookies = (String[]) result.get("cookies");
        Util.setTokenCookies(req, res, cookies);

        MessageAlertUtil.SuccessUpdateMessage(redir);
        return rv;
    }

    @GetMapping("/deleteCity")
    public RedirectView deleteCity(HttpServletRequest req, HttpServletResponse res,
                                   RedirectAttributes redir) {
        int id = Integer.parseInt(req.getParameter("id"));
        RedirectView rv = new RedirectView(req.getContextPath() + "/location/cities");

        JSONObject result = dtCity.deleteCity(id, req.getCookies());

        if(result.getInt("status") == 401) {
            rv = new RedirectView(req.getContextPath() + "/login");
            MessageAlertUtil.UnauthorizedAccessMessage(redir);
            return rv;
        }

        MessageAlertUtil.SuccessDeleteMessage(redir);

        String[] cookies = (String[]) result.get("cookies");
        Util.setTokenCookies(req, res, cookies);

        return rv;
    }
}
