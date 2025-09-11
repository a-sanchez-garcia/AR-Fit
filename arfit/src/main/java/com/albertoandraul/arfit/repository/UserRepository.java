package com.albertoandraul.arfit.repository;

import com.albertoandraul.arfit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<Object> findByUsername(String username); // esto hace que devuelva un Optional de User para manejar el caso de que no se encuentre el usuario

}
