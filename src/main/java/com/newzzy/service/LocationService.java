package com.newzzy.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.http.HttpServletRequest;

import java.net.InetAddress;

@Service
public class LocationService {

	public String getClientIp(HttpServletRequest request) {
	    String ipAddress = request.getHeader("X-Forwarded-For");
	    if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
	        ipAddress = request.getHeader("Proxy-Client-IP");
	    }
	    if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
	        ipAddress = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
	        ipAddress = request.getRemoteAddr();
	    }
	    return ipAddress;
	}



    public String getLocationFromIp(String ipAddress) {
        // Call a third-party API to get the location based on IP
        // Example: using ipinfo.io
        // String apiKey = "your_api_key";
        // RestTemplate restTemplate = new RestTemplate();
        // String url = "https://ipinfo.io/" + ipAddress + "?token=" + apiKey;
        // ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        // Process the response to extract location data
        // return location;

        // Mocked location for illustration
        return "Kolkata, India";
    }
}
