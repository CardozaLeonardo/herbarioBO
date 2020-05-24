package negocio;

import datos.DT_city;
import entidades.fichas_tecnicas.Tbl_city;
import org.json.JSONObject;

import javax.servlet.http.Cookie;

public class CityValidator {

    DT_city data = new DT_city();
    public int alreadyExistName(String name, int stateId , Cookie[] cookies) {


        JSONObject result = data.getCountries(cookies);

        if(result.getInt("status") == 401)
        {
            return -1;
        }

        Tbl_city[] cities = (Tbl_city[]) result.get("cities");

        for(Tbl_city city: cities) {
            if(city.getName().equalsIgnoreCase(name) && city.getState().getId() == stateId) {
                return 1;
            }
        }

        return 0;

    }

    public int alreadyExistNameUpdate(String name, Cookie[] cookies, int idCity, int idState) {

        JSONObject result = data.getCountries(cookies);

        if(result.getInt("status") == 401)
        {
            return -1;
        }

        Tbl_city[] cities = (Tbl_city[]) result.get("cities");

        for(Tbl_city city: cities) {
            if(city.getName().equalsIgnoreCase(name) && city.getId() != idCity) {

                if(city.getState().getId() == idState) {
                    return 1;
                }

            }
        }

        return 0;
    }
}
