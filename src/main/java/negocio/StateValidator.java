package negocio;


import datos.DT_state;
import entidades.fichas_tecnicas.Tbl_state;
import org.json.JSONObject;

import javax.servlet.http.Cookie;

public class StateValidator {

    DT_state data = new DT_state();
    public int alreadyExistName(String name, int countryId ,Cookie[] cookies) {


        JSONObject result = data.getFamilies(cookies);

        if(result.getInt("status") == 401)
        {
            return -1;
        }

        Tbl_state[] states = (Tbl_state[]) result.get("states");

        for(Tbl_state state: states) {
            if(state.getName().equalsIgnoreCase(name) && state.getCountry().getId() == countryId) {
                return 1;
            }
        }

        return 0;

    }

    public int alreadyExistNameUpdate(String name, Cookie[] cookies, int idState, int idCountry) {

        JSONObject result = data.getFamilies(cookies);

        if(result.getInt("status") == 401)
        {
            return -1;
        }

        Tbl_state[] states = (Tbl_state[]) result.get("states");

        for(Tbl_state state: states) {
            if(state.getName().equalsIgnoreCase(name) && state.getId() != idState) {

                if(state.getCountry().getId() == idCountry) {
                    return 1;
                }

            }
        }

        return 0;
    }
}
