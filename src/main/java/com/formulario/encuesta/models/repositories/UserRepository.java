package com.formulario.encuesta.models.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.formulario.encuesta.models.entities.User;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

    @Query("select user from User user where user.email = :email ")
    Optional<User> findByEmail(@Param("email") String email);
    
}
