package controlador;

import datos.DT_family;
import datos.DT_genus;
import entidades.fichas_tecnicas.Tbl_family;
import entidades.fichas_tecnicas.Tbl_genus;
import negocio.GenusValidator;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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
public class GenusController {

    Logger logger = LoggerFactory.getLogger(GenusController.class);
    GenusValidator genusValidator = new GenusValidator();
    @GetMapping("/fichas/genus")
    public String getAll(HttpServletRequest req, HttpServletResponse res, Model model)
            throws IOException {

        DT_genus genusData = new DT_genus();
        // This is a comment
        JSONObject response = genusData.getGenus(req.getCookies());

        if(response.getInt("status") == 0 || response.getInt("status") == 401) {

            model.addAttribute("error", 1);
            model.addAttribute("type", "info");
            model.addAttribute("cont", "¡Debe iniciar sesión!");
            res.sendRedirect(req.getContextPath() + "/toLoginPage");
            return null;
        }

        Tbl_genus[] genus = (Tbl_genus[]) response.get("genus");
        model.addAttribute("genus", genus);
        String[] cookies = (String[]) response.get("cookies");
        Util.setTokenCookies(req, res, cookies);

        return "/fichas/genus.jsp";
    }

    @GetMapping("/fichas/newGenus")
    public String newGenus(HttpServletRequest req, HttpServletResponse res, Model model) throws IOException {

        DT_family famDt = new DT_family();
        JSONObject result = famDt.getFamilies(req.getCookies());
        Tbl_family[] families = null;
        logger.debug(".._ " + result.getInt("status"));

        if(result.getInt("status") == 401) {
            res.sendRedirect(req.getContextPath() + "/toLoginPage");
            return null;
        }
        families = (Tbl_family[]) result.get("families");


        String[] cookies = (String[]) result.get("cookies");
        Util.setTokenCookies(req, res, cookies);

        model.addAttribute("families", families);
        return "/fichas/newGenus.jsp";
    }

    @PostMapping("/saveGenus")
    public RedirectView saveGenus(HttpServletRequest req, HttpServletResponse res,
                                  RedirectAttributes redir) throws IOException {
        RedirectView rv = new RedirectView(req.getContextPath() + "/fichas/genus");

        MultiValueMap<String, Object> newGenus = new LinkedMultiValueMap<>();

        newGenus.add("name", req.getParameter("name"));
        int familyId = Integer.parseInt(req.getParameter("family"));
        newGenus.add("family", familyId);

        DT_genus dtGenus = new DT_genus();

        int valid = genusValidator.alreadyExistName(req.getParameter("name"), req.getCookies());

        if(valid == -1) {
            res.sendRedirect(req.getContextPath() + "/toLoginPage");
            return null;
        }

        if(valid == 1) {
            rv = new RedirectView(req.getContextPath() + "/fichas/newGenus");
            MessageAlertUtil.alreadyExistMessage(redir, "el nombre");
            return rv;
        }

        JSONObject result = dtGenus.saveGenus(newGenus, req.getCookies());

        if(result.getInt("status") == 401) {
            res.sendRedirect(req.getContextPath() + "/toLoginPage");
            return null;
        }

        String[] cookies = (String[]) result.get("cookies");
        Util.setTokenCookies(req, res, cookies);

        MessageAlertUtil.SuccessStoreMessage(redir);

        return rv;
    }

    @GetMapping("/fichas/updateGenus")
    public String updateGenus(HttpServletRequest req, HttpServletResponse res, Model model)
            throws IOException {
        DT_family famDt = new DT_family();
        DT_genus dtGenus = new DT_genus();
        int idGenus = Integer.parseInt(req.getParameter("id"));

        JSONObject result = famDt.getFamilies(req.getCookies());
        Tbl_family[] families;

        JSONObject genusRes = dtGenus.getGenus(req.getCookies(), idGenus);

        if(genusRes.getInt("status") == 401) {
            res.sendRedirect(req.getContextPath() + "/toLoginPage");
            return null;
        }

        if(result.getInt("status") == 401) {
            res.sendRedirect(req.getContextPath() + "/toLoginPage");
            return null;
        }

        families = (Tbl_family[]) result.get("families");
        Tbl_genus gen = (Tbl_genus) genusRes.get("genus");

        String[] cookies = (String[]) result.get("cookies");
        Util.setTokenCookies(req, res, cookies);

        model.addAttribute("families", families);
        model.addAttribute("gen", gen);
        return "/fichas/updateGenus.jsp";
    }

    @PostMapping("/changeGenus")
    public RedirectView changeGenus(HttpServletRequest req, HttpServletResponse res,
                                    RedirectAttributes redir) throws IOException {
        RedirectView rv = new RedirectView(req.getContextPath() + "/fichas/genus");

        MultiValueMap<String, Object> genus = new LinkedMultiValueMap<>();
        int idGenus = Integer.parseInt(req.getParameter("idGenus"));
        int family = Integer.parseInt(req.getParameter("family"));

        genus.add("name", req.getParameter("name"));
        genus.add("family", family);

        DT_genus dtGenus = new DT_genus();
        int valid = genusValidator.alreadyExistNameUpdate(req.getParameter("name"), req.getCookies(), idGenus);

        if(valid == -1) {
            res.sendRedirect(req.getContextPath() + "/toLoginPage");
            return null;
        }

        if(valid == 1) {
            rv = new RedirectView(req.getContextPath() + "/fichas/updateGenus?id=" +idGenus);
            MessageAlertUtil.alreadyExistMessage(redir, "el nombre");
            return rv;
        }


        JSONObject result = dtGenus.updateGenus(genus, req.getCookies(), idGenus);

        if(result.getInt("status") == 401) {
            rv = new RedirectView(req.getContextPath() + "/login");
            MessageAlertUtil.UnauthorizedAccessMessage(redir);
            return rv;
        }
        MessageAlertUtil.SuccessUpdateMessage(redir);

        String[] cookies = (String[]) result.get("cookies");
        Util.setTokenCookies(req, res, cookies);

        return rv;
    }

    @GetMapping("/deleteGenus")
    public RedirectView deleteGenus(HttpServletRequest req, HttpServletResponse res,
                       RedirectAttributes redir) {

        int idGenus = Integer.parseInt(req.getParameter("id"));
        RedirectView rv = new RedirectView(req.getContextPath() + "/fichas/genus");

        DT_genus dtGenus = new DT_genus();

        JSONObject result = dtGenus.deleteGenus(idGenus, req.getCookies());

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
