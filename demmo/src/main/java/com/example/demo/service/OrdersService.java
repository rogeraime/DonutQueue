package com.example.demo.service;

import com.example.demo.entity.Order;
import javassist.NotFoundException;

import java.util.List;
import java.util.PriorityQueue;

public interface OrdersService {
    Order addNewOrder(Long clientId, int quantite) throws NotFoundException;
    int checkPosition(Long clientId) throws NotFoundException;
    PriorityQueue<Order> getPQueue();
    Order getNextOrder();
    Order cancelOrder(Long clientId) throws NotFoundException;
    List<Order> findAllOrder();
}
