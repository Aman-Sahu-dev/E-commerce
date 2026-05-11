package com.example.ecommerce.Model;

import com.example.ecommerce.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@Data
@Builder
@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Orders { //orders are like cart and order_items are products belong to one cart, one cart can have many order_items
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User  user;
    private BigDecimal price;
    private String status;
    private LocalDateTime created_at;

    @OneToMany(mappedBy = "orders",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItems> items;

    
}
