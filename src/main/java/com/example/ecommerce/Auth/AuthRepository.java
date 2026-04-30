package com.example.ecommerce.Auth;
import com.example.ecommerce.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthRepository extends JpaRepository<User,Long>{
    Optional<User> findByEmail(String email);
}