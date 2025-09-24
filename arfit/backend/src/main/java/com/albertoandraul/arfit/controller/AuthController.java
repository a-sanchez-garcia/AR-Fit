package com.albertoandraul.arfit.controller;

import com.albertoandraul.arfit.dto.AuthRequestDto;
import com.albertoandraul.arfit.dto.UserDto;
import com.albertoandraul.arfit.config.JwtUtil;
import com.albertoandraul.arfit.model.User;
import com.albertoandraul.arfit.repository.UserRepository;
import com.albertoandraul.arfit.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final UserRepository userRepository;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil,
                          UserService userService,
                          UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public UserDto.UserResponseDto register(@RequestBody UserDto.UserRequestDto request) {
        return userService.register(request);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody AuthRequestDto request,
                                     HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        String username = authentication.getName();
        String accessToken = jwtUtil.generateAccessToken(username);
        String refreshToken = jwtUtil.generateRefreshToken(username);

        // Guardar refresh token en la cookie HttpOnly
        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setHttpOnly(true);
        cookie.setSecure(true); // HTTPS
        cookie.setPath("/api/auth/refresh"); // solo para endpoint refresh
        cookie.setMaxAge(7 * 24 * 60 * 60); // 7 días
        response.addCookie(cookie);

        return Map.of("accessToken", accessToken);
    }

    @PostMapping("/refresh")
    public Map<String, String> refreshToken(@CookieValue(name = "refreshToken", required = false) String refreshToken,
                                            HttpServletResponse response) {
        if (refreshToken == null) {
            throw new RuntimeException("No se encontró refresh token");
        }

        if (jwtUtil.isTokenExpired(refreshToken)) {
            throw new RuntimeException("Refresh token expirado, por favor loguearse de nuevo");
        }

        String username = jwtUtil.extractUsername(refreshToken);

        // Generar nuevos tokens
        String newAccessToken = jwtUtil.generateAccessToken(username);
        String newRefreshToken = jwtUtil.generateRefreshToken(username);

        // Actualizar cookie con nuevo refresh token (rotación)
        Cookie cookie = new Cookie("refreshToken", newRefreshToken);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/api/auth/refresh");
        cookie.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(cookie);

        return Map.of("accessToken", newAccessToken);
    }

    @PostMapping("/logout")
    public void logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("refreshToken", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/api/auth/refresh");
        cookie.setMaxAge(0); // borrar cookie
        response.addCookie(cookie);
    }
}
