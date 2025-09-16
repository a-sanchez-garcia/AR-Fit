package com.albertoandraul.arfit.dto;

public class UserDto {

    // DTO para crear usuario (lo que manda el cliente)
    public record UserRequestDto(String username, String email, String password) {}


    // DTO para devolver usuario (lo que devuelve tu API)
    public record UserResponseDto(Long id, String username, String email) {}
}
