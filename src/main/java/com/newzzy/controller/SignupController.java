package com.newzzy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newzzy.exception.UsernameAlreadyExistsException;
import com.newzzy.model.Signup;
import com.newzzy.service.SignupService;

@RestController
@RequestMapping(value="/auth")
public class SignupController {
	@Autowired
    private SignupService signupService;
	@PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestParam String username, @RequestParam String password,
                                          @RequestParam String email, @RequestParam String location) {
        try {
            Signup signup = new Signup();
            signup.setUsername(username);
            signup.setPassword(password);
            signup.setEmail(email);
            signup.setLocation(location);

            signupService.registerUser(signup);
            return ResponseEntity.ok("User registered successfully.");
        } catch (UsernameAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }
}
