package com.albertoandraul.arfit.dto;

public class UserDto {

    // DTO para crear usuario (POST)
    public record UserRequestDto(String username, String email, String password) {}

    // DTO para devolver usuario (Response)
    public record UserResponseDto(Long id, String username, String email) {}

    // DTO para actualizar parcialmente un usuario (PATCH)
    public static class UpdateUserRequestDto {
        private String username;
        private String email;
        private String password;

        public UpdateUserRequestDto() {} // para deserialización JSON

        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }
}
