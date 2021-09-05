package com.example.demo.repository;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Order;
import com.example.demo.entity.ennum.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByClient(Customer customer);

    List<Order> findAllByStatusOrderByCreatedAsc(OrderStatus status);
}
