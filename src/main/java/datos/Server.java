package datos;

import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

public class Server {
	
	private static String hostname = "https://django-acacia.herokuapp.com/api/";
	
	public static String getHostname() {
		return hostname;
	}
	
	static ClientHttpRequestFactory getClientHttpRequestFactory() {
	    int timeout = 60*1000;
	    HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
	      = new HttpComponentsClientHttpRequestFactory();
	    clientHttpRequestFactory.setConnectTimeout(timeout);
	    return clientHttpRequestFactory;
	}
}
