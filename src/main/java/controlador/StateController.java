package controlador;

import datos.DT_country;
import datos.DT_state;
import entidades.fichas_tecnicas.Tbl_country;
import entidades.fichas_tecnicas.Tbl_state;
import negocio.StateValidator;
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
public class StateController {

    StateValidator validator = new StateValidator();
    @GetMapping("/location/states")
    public String getStates(HttpServletRequest req, HttpServletResponse res,
                            Model model) throws IOException {
        DT_state dtState = new DT_state();

        JSONObject result = dtState.getFamilies(req.getCookies());

        if(result.getInt("status") == 401) {
            res.sendRedirect(req.getContextPath() + "/toLoginPage");
            return null;
        }

        Tbl_state[] states = (Tbl_state[]) result.get("states");
        model.addAttribute("states", states);

        String[] cookies = (String[]) result.get("cookies");
        Util.setTokenCookies(req, res, cookies);

        return "/location/states.jsp";
    }

    @GetMapping("/location/newState")
    public String createState(HttpServletRequest req, HttpServletResponse res, Model model) throws IOException {


        DT_country dtCountry = new DT_country();



        JSONObject result = dtCountry.getCountries(req.getCookies());

        if(result.getInt("status") == 401) {
            res.sendRedirect(req.getContextPath() + "/toLoginPage");
            return null;
        }

        Tbl_country[] countries = (Tbl_country[]) result.get("countries");

        model.addAttribute("countries", countries);

        String[] cookies = (String[]) result.get("cookies");
        Util.setTokenCookies(req, res, cookies);

        return "/location/newState.jsp";
    }

    @GetMapping("/location/updateState")
    public String updateState(HttpServletRequest req, HttpServletResponse res, Model model) throws IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        DT_country dtCountry = new DT_country();
        DT_state dtState = new DT_state();



        JSONObject result = dtCountry.getCountries(req.getCookies());

        if(result.getInt("status") == 401) {
            res.sendRedirect(req.getContextPath() + "/toLoginPage");
            return null;
        }

        JSONObject resultSt = dtState.getState(id, req.getCookies());

        Tbl_state state = (Tbl_state) resultSt.get("state");

        Tbl_country[] countries = (Tbl_country[]) result.get("countries");

        model.addAttribute("countries", countries);
        model.addAttribute("state", state);

        String[] cookies = (String[]) result.get("cookies");
        Util.setTokenCookies(req, res, cookies);

        return "/location/updateState.jsp";
    }



    @PostMapping("/saveState")
    public RedirectView saveState(HttpServletRequest req, HttpServletResponse res,
                                  RedirectAttributes redir) {
        RedirectView rv = new RedirectView(req.getContextPath() + "/location/states");
        String name = req.getParameter("name");
        int country = Integer.parseInt(req.getParameter("country"));
        DT_state dtState = new DT_state();

        JSONObject newState = new JSONObject();
        newState.put("name", name);
        newState.put("country", country);

        int valid = validator.alreadyExistName(name, country, req.getCookies());

        if(valid == -1) {
            rv = new RedirectView(req.getContextPath() + "/login");
            MessageAlertUtil.SuccessStoreMessage(redir);
            return rv;
        }

        if(valid == 1) {
            rv = new RedirectView(req.getContextPath() + "/location/newState");
            MessageAlertUtil.alreadyExistCustomMsg(redir, "Ya existe un estado con el" +
                    " nombre especificado");
            return rv;
        }

        JSONObject result = dtState.saveState(newState, req.getCookies());

        if(result.getInt("status") == 401) {
            rv = new RedirectView(req.getContextPath() + "/login");
            MessageAlertUtil.SuccessStoreMessage(redir);
            return rv;
        }

        MessageAlertUtil.SuccessStoreMessage(redir);

        String[] cookies = (String[]) result.get("cookies");
        Util.setTokenCookies(req, res, cookies);

        return rv;
    }

    @PostMapping("/changeState")
    public RedirectView changeState(HttpServletRequest req, HttpServletResponse res,
                                  RedirectAttributes redir) {
        RedirectView rv = new RedirectView(req.getContextPath() + "/location/states");
        String name = req.getParameter("name");
        int id = Integer.parseInt(req.getParameter("stateID"));
        int country = Integer.parseInt(req.getParameter("country"));
        DT_state dtState = new DT_state();

        JSONObject newState = new JSONObject();
        newState.put("name", name);
        newState.put("country", country);

        int valid = validator.alreadyExistNameUpdate(name, req.getCookies(),id, country);

        if(valid == -1) {
            rv = new RedirectView(req.getContextPath() + "/login");
            MessageAlertUtil.SuccessStoreMessage(redir);
            return rv;
        }

        if(valid == 1) {
            rv = new RedirectView(req.getContextPath() + "/location/updateState?id=" + id);
            MessageAlertUtil.alreadyExistCustomMsg(redir, "Ya existe un estado con el" +
                    " nombre especificado para el mismo estado");
            return rv;
        }



        JSONObject result = dtState.updateState(newState,id, req.getCookies());

        if(result.getInt("status") == 401) {
            rv = new RedirectView(req.getContextPath() + "/login");
            MessageAlertUtil.SuccessStoreMessage(redir);
            return rv;
        }

        MessageAlertUtil.SuccessUpdateMessage(redir);

        String[] cookies = (String[]) result.get("cookies");
        Util.setTokenCookies(req, res, cookies);

        return rv;
    }

    @GetMapping("/deleteState")
    public RedirectView deleteState(HttpServletRequest req, HttpServletResponse res,
                                    RedirectAttributes redir) {
        RedirectView rv = new RedirectView(req.getContextPath() + "/location/states");
        int id = Integer.parseInt(req.getParameter("id"));
        DT_state dtState = new DT_state();


        JSONObject result = dtState.deleteState(id, req.getCookies());

        if(result.getInt("status") == 401) {
            rv = new RedirectView(req.getContextPath() + "/login");
            MessageAlertUtil.SuccessStoreMessage(redir);
            return rv;
        }

        MessageAlertUtil.SuccessDeleteMessage(redir);

        String[] cookies = (String[]) result.get("cookies");
        Util.setTokenCookies(req, res, cookies);

        return rv;
    }
}
