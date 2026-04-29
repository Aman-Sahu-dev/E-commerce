package com.example.ecommerce.Auth;
import com.example.ecommerce.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthRepository extends JpaRepository<User,Long>{
    List<User> findByEmail(String email);
}