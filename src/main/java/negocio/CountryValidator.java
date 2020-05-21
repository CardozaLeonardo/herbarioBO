package negocio;

import datos.DT_country;
import entidades.fichas_tecnicas.Tbl_country;
import entidades.fichas_tecnicas.Tbl_genus;
import org.json.JSONObject;

import javax.servlet.http.Cookie;

public class CountryValidator {

    DT_country dtCountry = new DT_country();
    public int alreadyExistName(String name, Cookie[] cookies) {


        JSONObject result = dtCountry.getCountries(cookies);

        if(result.getInt("status") == 401)
        {
            return -1;
        }

        Tbl_country[] countries = (Tbl_country[]) result.get("countries");

        for(Tbl_country cou: countries) {
            if(cou.getName().equalsIgnoreCase(name)) {
                return 1;
            }
        }

        return 0;

    }

    public int alreadyExistNameUpdate(String name, Cookie[] cookies, int id) {

        JSONObject result = dtCountry.getCountries(cookies);

        if(result.getInt("status") == 401)
        {
            return -1;
        }

        Tbl_country[] countries = (Tbl_country[]) result.get("countries");

        for(Tbl_country cou: countries) {
            if(cou.getName().equalsIgnoreCase(name) && cou.getId() != id) {
                return 1;
            }
        }

        return 0;
    }
}
