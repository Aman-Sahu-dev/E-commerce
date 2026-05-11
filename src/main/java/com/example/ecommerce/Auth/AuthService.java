package com.example.ecommerce.Auth;

import com.example.ecommerce.Auth.dto.AuthResponse;
import com.example.ecommerce.Auth.dto.LoginRequest;
import com.example.ecommerce.Auth.dto.RegisterRequest;
import com.example.ecommerce.Security.JwtUtil;
import com.example.ecommerce.User.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtutil;
    public AuthResponse register(RegisterRequest request){
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .role("User")
                .build();
        authRepository.save(user);
        String token = jwtutil.generateToken(user.getEmail());
        return new AuthResponse(token);
    }
    public AuthResponse login(LoginRequest request){
        User user = authRepository.findByEmail(request.getEmail()).orElseThrow(()->  new RuntimeException("error"));
        if(!passwordEncoder.matches(request.getPassword(),user.getPassword())){
            throw new RuntimeException("wrong password");
        }
        String token = jwtutil.generateToken(request.getEmail());
        return new AuthResponse(token);
    }
}
