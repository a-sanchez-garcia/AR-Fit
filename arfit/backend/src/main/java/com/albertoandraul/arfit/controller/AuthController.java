package com.albertoandraul.arfit.controller;

import com.albertoandraul.arfit.dto.AuthRequestDto;
import com.albertoandraul.arfit.dto.UserDto;
import com.albertoandraul.arfit.config.JwtUtil;
import com.albertoandraul.arfit.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserDto.UserResponseDto register(@RequestBody UserDto.UserRequestDto request) {
        return userService.register(request);
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequestDto request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        return jwtUtil.generateToken(authentication.getName());
    }



}
