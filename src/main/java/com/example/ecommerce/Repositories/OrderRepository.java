package com.example.ecommerce.Repositories;
import com.example.ecommerce.Model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
public interface OrderRepository extends JpaRepository<Orders,Long> {
}
