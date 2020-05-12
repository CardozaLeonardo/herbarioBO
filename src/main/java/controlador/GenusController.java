package controlador;

import datos.DT_family;
import datos.DT_genus;
import entidades.fichas_tecnicas.Tbl_family;
import entidades.fichas_tecnicas.Tbl_genus;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class GenusController {


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
            res.sendRedirect(req.getContextPath() + "/login");
            return null;
        }

        Tbl_genus[] genus = (Tbl_genus[]) response.get("genus");
        model.addAttribute("genus", genus);
        String[] cookies = (String[]) response.get("cookies");
        Util.setTokenCookies(req, res, cookies);

        return "/fichas/genus.jsp";
    }

    public String newGenus(HttpServletRequest req, HttpServletResponse res, Model model) throws IOException {

        DT_family famDt = new DT_family();
        JSONObject result = famDt.getFamilies(req.getCookies());
        Tbl_family[] families;

        if(result.getInt("status") == 401) {
            res.sendRedirect(req.getContextPath() + "/ToLoginPage");
            return null;
        }

        families = (Tbl_family[]) result.get("families");

        model.addAttribute("families", families);
        return "/fichas/newGenus.jsp";
    }
}
