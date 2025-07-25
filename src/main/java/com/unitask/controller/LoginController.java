package com.unitask.controller;

import com.unitask.dto.user.LoginRequest;
import com.unitask.dto.user.OtpRequest;
import com.unitask.dto.user.SignUpRequest;
import com.unitask.security.JwtUtils;
import com.unitask.service.UserService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping(path = "auth")
public class LoginController {

    @Autowired
    private JwtUtils utils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = utils.generateJwtToken(authentication);
        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> singUp(@RequestBody SignUpRequest signUpRequest) {
        userService.addUser(signUpRequest.getEmail(), signUpRequest.getPassword(), signUpRequest.getFirstName(), signUpRequest.getLastName(), signUpRequest.getUserName());
        return ResponseEntity.ok("SIGN UP");
    }

    @GetMapping("/getOTP")
    public ResponseEntity<?> getOTP(@RequestParam String email) throws MessagingException {
        userService.getOtp(email);
        return ResponseEntity.ok().body("OK");
    }

    @PostMapping("/validateOTP")
    public ResponseEntity<?> validateOTP(@RequestBody OtpRequest otpRequest) {
        return ResponseEntity.ok(userService.validateOtp(otpRequest.getEmail(), otpRequest.getOtp()));
    }

    //TODO oauth
}
