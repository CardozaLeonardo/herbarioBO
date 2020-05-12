package negocio.validacion;


import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;


public class SpecimenValidador {

    public static boolean validar(RedirectAttributes redir,
                       HttpServletRequest req) {



        if(req.getParameter("longitude") != null) {

            float longitude = Float.parseFloat(req.getParameter("longitude"));

            if(longitude < 0 || longitude > 90) {
                redir.addFlashAttribute("msg", 1);
                redir.addFlashAttribute("type", "danger");
                redir.addFlashAttribute("cont", "¡El campo <strong>Longitud</strong> está" +
                        " fuera de rango! Mantenga los valores entre 0 y 90");

                return false;
            }
        }

        if(req.getParameter("latitude") != null) {

            float latitude = Float.parseFloat(req.getParameter("latitude"));

            if(latitude < 0 || latitude > 180) {
                redir.addFlashAttribute("msg", 1);
                redir.addFlashAttribute("type", "danger");
                redir.addFlashAttribute("cont", "¡El campo <strong>Latitud</strong> está" +
                        " fuera de rango! Mantenga los valores entre 0 y 180");

                return false;
            }
        }

        return true;
    }
}
