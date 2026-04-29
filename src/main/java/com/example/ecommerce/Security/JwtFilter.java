package com.example.ecommerce.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.sql.rowset.serial.SerialException;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter {
    private final JwtUtil jwtUtil;
    @Override
    protected void doFilterchain(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws SerialException , IOException{
            String authHeader = request.getHeader("Authorization");
            if(authHeader == null || !authHeader.startsWith("Bearer ")){
                filterChain.doFilter(request,response);
                return;
            }
    }
}
