package com.newzzy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newzzy.service.LocationService;
import com.newzzy.service.LoginService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value="/home")
@CrossOrigin(origins = "*") 
public class LoginController {
	@Autowired
	private LoginService loginService;
	
	@PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        boolean isVerified = loginService.verifyUser(username, password);
        if (isVerified) {
            return ResponseEntity.ok("Login successful.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");
        }
    }
	@Autowired
    private LocationService locationService;
	
	@GetMapping("/user")
	public String getUserLocation(HttpServletRequest request) {
	    String ipAddress = locationService.getClientIp(request);
	    String location = locationService.getLocationFromIp(ipAddress);
	    return "User IP: " + ipAddress + ", Location: " + location;
	}

}
