package negocio;

import datos.DT_rol;
import entidades.Tbl_opcion;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RoleValidator {

    public boolean verifyAddPermission(HttpServletRequest request) {
        HttpSession hts = request.getSession();
        Tbl_opcion[] permisions = (Tbl_opcion[]) hts.getAttribute("user_permissions");
        boolean permission = false;

        for(Tbl_opcion p: permisions) {
            if (p.getCodename().equals("add_group")) {
                permission = true;
            }

        }

        return permission;
    }


}
