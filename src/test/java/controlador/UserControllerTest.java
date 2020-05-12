package controlador;

import datos.DT_user;
import entidades.Tbl_user;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import javax.servlet.http.Cookie;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    @Test
    void guardarUser() {

        Tbl_user user = new Tbl_user();

        user.setLast_login(null);
        user.setIs_superuser(true);
        user.setUsername("LeoCodezs123");
        user.setFirst_name("Leonardo");
        user.setLast_name("Cardoza213");
        user.setPassword("ks2139si");
        user.setEmail("leonardoorozco629@gmail.com");
        user.setIs_staff(true);
        user.setIs_active(true);
        user.setDate_joined(null);
        user.setName("LeoCoders21");
        user.setGroups(new int[0]);

        DT_user dtu= new DT_user();
        Cookie[] cookies = new Cookie[2];
        cookies[0] = new Cookie("token-access", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNTg5MTU4NTgyLCJqdGkiOiJjY2VkZGVjOTc4YTU0ZjNjOTVjYTI2MDQ4NTkyNmQ4NiIsInVzZXJfaWQiOjY0fQ.cQws6iHLCaPsAh2Lo1KqjH_TEP71A7GSxDTHEJX3V9A");
        cookies[1] = new Cookie("token-refresh", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoicmVmcmVzaCIsImV4cCI6MTU4OTE3OTg4MiwianRpIjoiYTViY2NmYjVmODAwNDRjOTljZTkyZjJiM2FkN2I4MTAiLCJ1c2VyX2lkIjo2NH0.l6pmMmUX26MH5nErjqOrMAatd_f_bq_zgQVoi6NaaU8");

        JSONObject respuesta = dtu.guardarUser(user, cookies);

        assertEquals(200, respuesta.getInt("status"));
    }

    @Test
    void login() {
        String username = "admin2";
        String password = "admin2";
        DT_user dtu = new DT_user();

        JSONObject respuesta = dtu.comprobarLogin(username, password);

        assertEquals(200, respuesta.getInt("code"));
    }
}