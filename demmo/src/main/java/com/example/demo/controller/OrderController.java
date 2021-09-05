package com.example.demo.controller;

import com.example.demo.entity.Order;
import com.example.demo.service.OrdersService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.PriorityQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrdersService ordersService;

    private static final Logger LOG = Logger.getLogger(OrderController.class.getName());



    @PostMapping("/{clientId}/{quantite}")
    public ResponseEntity<Order> addNewOrder(@PathVariable Long clientId, @PathVariable int quantite) throws NotFoundException {
        Order order = ordersService.addNewOrder(clientId, quantite);
        LOG.log(Level.INFO, "Order for customer with id:{0} is added", clientId);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping("/checkPosition/{clientId}")
    public ResponseEntity<Integer> checkPostion(@PathVariable Long clientId) throws NotFoundException {
        int position = ordersService.checkPosition(clientId);
        return new ResponseEntity<>(position, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PriorityQueue<Order>> getAllOrders(){
        PriorityQueue<Order> orders = ordersService.getPQueue();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/nextOrder")
    public ResponseEntity<Order> getNextOrder(){
        Order order = ordersService.getNextOrder();
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping("/cancel/{clientId}")
    public ResponseEntity<Order> cancelOrder(@PathVariable Long clientId) throws NotFoundException {
        Order order = ordersService.cancelOrder(clientId);
        LOG.log(Level.INFO, "Order for customer with id:{0} is cancelled", clientId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
