package controlador;

import datos.DT_mushroom;
import datos.fichas_tecnicas.DT_plantSpecimen;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import javax.servlet.http.Cookie;
import static org.junit.jupiter.api.Assertions.*;

public class PlantControllerTest {

    @Test
    void getPlant() {
        int plantId = 74;

        Cookie[] cookies = new Cookie[2];
        cookies[0] = new Cookie("token-access", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNTg5MTYwOTI2LCJqdGkiOiI0NzdhOWFkMTRhZWQ0ZmEwYjlkMDNlOTUzODc5NDZmMiIsInVzZXJfaWQiOjY0fQ.lH6q5yxTUXW4mbrDsxOtXuC0nB7a-2-PXXduPY0H0x8");
        cookies[1] = new Cookie("token-refresh", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoicmVmcmVzaCIsImV4cCI6MTU4OTE4MjIyNiwianRpIjoiMTZhMTNmYmVmYTE5NDA3Y2FmNDI4ZGE1N2JhYWVkMmMiLCJ1c2VyX2lkIjo2NH0.1DsjytbkiWtRLM5XulPPUTU4CgpXjG68xK4MR9vIj0I");

        DT_plantSpecimen dtp = new DT_plantSpecimen();
        JSONObject response = dtp.getPlant(plantId, cookies);

        assertEquals(200, response.getInt("status"));
    }

    @Test
    void savePlant() {

    }
}
