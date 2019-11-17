package datos;

import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

public class Server {
	
	private static String hostname = "https://django-acacia.herokuapp.com/api/";
	private static String appName = "Herbario Nacional";
	
	public static String getHostname() {
		return hostname;
	}
	
	public static String getAppName() {
		return appName;
	}
	
	static ClientHttpRequestFactory getClientHttpRequestFactory() {
	    int timeout = 10*1000;
	    HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
	      = new HttpComponentsClientHttpRequestFactory();
	    clientHttpRequestFactory.setConnectTimeout(timeout);
	    return clientHttpRequestFactory;
	}
}
