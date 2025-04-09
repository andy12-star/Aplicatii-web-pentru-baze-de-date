package com.andy.master.controller;

import com.andy.master.model.dto.request.AuthRequest;
import com.andy.master.model.dto.request.RegisterRequest;
import com.andy.master.security.JwtUtil;
import com.andy.master.service.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        System.out.println("Received login request for: " + authRequest.getEmail());
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

            String token = jwtUtil.generateToken(authentication.getName(), authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList());
            return ResponseEntity.ok().body(token);

        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        userService.register(request);
        return ResponseEntity.ok("Registered successfully");
    }
}
