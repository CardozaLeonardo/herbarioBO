package negocio;

import datos.DT_genus;

import entidades.fichas_tecnicas.Tbl_genus;
import org.json.JSONObject;

import javax.servlet.http.Cookie;


public class GenusValidator {

    DT_genus dt_genus = new DT_genus();
    public int alreadyExistName(String name, Cookie[] cookies) {


        JSONObject result = dt_genus.getGenus(cookies);

        if(result.getInt("status") == 401)
        {
            return -1;
        }

        Tbl_genus[] genus = (Tbl_genus[]) result.get("genus");

        for(Tbl_genus gen: genus) {
            if(gen.getName().equalsIgnoreCase(name)) {
                return 1;
            }
        }

        return 0;

    }

    public int alreadyExistNameUpdate(String name, Cookie[] cookies, int idGenus) {

        JSONObject result = dt_genus.getGenus(cookies);

        if(result.getInt("status") == 401)
        {
            return -1;
        }

        Tbl_genus[] genus = (Tbl_genus[]) result.get("genus");

        for(Tbl_genus gen: genus) {
            if(gen.getName().equalsIgnoreCase(name) && gen.getId() != idGenus) {
                return 1;
            }
        }

        return 0;
    }
}
