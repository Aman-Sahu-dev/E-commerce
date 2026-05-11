package com.example.ecommerce.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Data
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name ="name",nullable = false)
    private String name;
    @Column(name ="email",nullable = false)
    private String email;
    @Column(name ="password",nullable = false)
    private String password;
    @Builder.Default
    @Column(name ="role")
    private String role = "User";
    @Column(name ="phone")
    private String phone;

}

