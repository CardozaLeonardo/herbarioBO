package repository;

import datos.Server;
import org.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import util.Util;

import javax.servlet.http.Cookie;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class GenericRepository<T> {

     protected RestTemplate restTemplate;
     protected String apiUrl;
     protected ParameterizedTypeReference<T> responseTypeOne;
     protected ParameterizedTypeReference<List<T>> responseTypeList;
     protected Class<T> type;

    public GenericRepository(String apiUrl) {
        // Url must be specified without '/' at the beginning
        this.apiUrl = Server.getHostname() + apiUrl;
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
        restTemplate = new RestTemplate();
    }

    public GenericRepository(String apiUrl, ParameterizedTypeReference<T> responseType,
                             ParameterizedTypeReference<List<T>> responseTypeList) {
        this.apiUrl = Server.getHostname() + apiUrl;
        this.responseTypeOne = responseType;
        this.responseTypeList = responseTypeList;
        restTemplate = new RestTemplate();
    }


    public Class<T> getType() {
        return type;
    }

    public void setType(Class<T> type) {
        this.type = type;
    }

    public JSONObject findById(int id, Cookie[] cookies, ParameterizedTypeReference<T> responseType) {
        String[] tokens = Util.extractTokens(cookies);
        String tempUrl = apiUrl + id + "/";

        if(tokens == null) {

            JSONObject retorno = new JSONObject();
            retorno.put("status", 0);
            return retorno;
        }

        HttpHeaders headers = new HttpHeaders();

        headers.add("Cookie", "token-access="+ tokens[0]);
        headers.add("Cookie", "token-refresh="+ tokens[1]);

        HttpEntity<String> req = new HttpEntity<String>(headers);

        try {
            ResponseEntity<T> response = restTemplate.exchange(apiUrl, HttpMethod.GET,req, responseTypeOne);
            T t = response.getBody();
            JSONObject jso = new JSONObject();
            jso.put("status", response.getStatusCodeValue());
            jso.put("peo", t);
            return jso;
        }catch(HttpClientErrorException e) {

            return null;
        }
    }

    public JSONObject testFindById(String url, ParameterizedTypeReference<T> responseType) {


        HttpHeaders headers = new HttpHeaders();


        HttpEntity<String> req = new HttpEntity<String>(headers);

        try {
            ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.GET,req, responseTypeOne);
            T t = response.getBody();
            JSONObject jso = new JSONObject();
            jso.put("status", response.getStatusCodeValue());
            jso.put("peo", t);
            return jso;
        }catch(HttpClientErrorException e) {

            e.printStackTrace();
            e.getCause();
            return null;
        }
    }
}
