package com.example.ecommerce.Repositories;

import com.example.ecommerce.Model.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepositories extends JpaRepository<OrderItems,Long> {
}
