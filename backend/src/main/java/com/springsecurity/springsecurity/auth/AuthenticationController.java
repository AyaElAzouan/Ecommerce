package com.springsecurity.springsecurity.auth;

import com.springsecurity.springsecurity.config.JwtService;
import com.springsecurity.springsecurity.entities.User;
import com.springsecurity.springsecurity.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    @Autowired
    private JwtService jwtService;

private  final AuthenticationSercvice authenticationSercvice;

    @PostMapping("/register")
    public  ResponseEntity<User> register(
            @RequestBody User user
    ){
        return ResponseEntity.ok(authenticationSercvice.register(user));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(authenticationSercvice.authenticate(request));
    }


    @GetMapping("/me")
    public UserDetails getCurrentUser(@RequestHeader("Authorization") String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        return jwtService.getUserFromToken(token);
    }
    @GetMapping("/{email}")
    public Integer getUserIdByEmail(@PathVariable String email) {
        return jwtService.getUserIdByEmail(email);
    }
}
