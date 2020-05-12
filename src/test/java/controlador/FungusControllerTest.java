package controlador;
import datos.DT_mushroom;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import javax.servlet.http.Cookie;

import static org.junit.jupiter.api.Assertions.*;

public class FungusControllerTest {

    @Test
    void getFungus() {
        int fungusId = 19;

        Cookie[] cookies = new Cookie[2];
        cookies[0] = new Cookie("token-access", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNTg5MTU4MzY4LCJqdGkiOiJjODNlYzYzZTVkNDU0MWQzYWY4ZmRiOWVkNDI0MzI3YyIsInVzZXJfaWQiOjY0fQ.lp4ByCYDyFWFXCyMsCQ1X-A9IeY_EW4OSsurKJwlwHo");
        cookies[1] = new Cookie("token-refresh", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoicmVmcmVzaCIsImV4cCI6MTU4OTE3OTY2OCwianRpIjoiOGJhYzNiZjc4MTJlNDkzMTg0OTU0NzkwOTQyOWFhODAiLCJ1c2VyX2lkIjo2NH0.BODtvyAYcQzy-dBZdMn2_chpEanILqiFLn8N91NN9s8");

        DT_mushroom dtmus = new DT_mushroom();
        JSONObject response = dtmus.getFungus(fungusId, cookies);

        assertEquals(401, response.getInt("status"));
    }

    @Test
    void saveFungus() {

    }
}
