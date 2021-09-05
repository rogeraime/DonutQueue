package com.example.demo.service.impl;

import com.example.demo.Utils.CreateQueue;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Order;
import com.example.demo.entity.Product;
import com.example.demo.entity.ennum.OrderPriority;
import com.example.demo.entity.ennum.OrderStatus;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.CustomerService;
import com.example.demo.service.OrdersService;
import com.example.demo.service.ProductService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderRepository orderRepository;

    // Only one instance of this Object is needed
    private static PriorityQueue<Order> queue = CreateQueue.getInstance();


    private static final long PREMIUM_ID_MAX = 1000;
    private static final long CUSTOMER_ID_MIN = 1;

    @Override
    public Order addNewOrder(Long clientId, int quantity) throws NotFoundException {
        // A Customer should have a single order.
        // This is why we need to check that a customer has not yet placed an order before adding their current order
        Product product = productService.findAll().get(0);
        Customer customer = customerService.getById(clientId);
        List<Order> orders = orderRepository.findAllByClient(customer);
        if(orders.isEmpty()) {
            Order order = new Order();
            order.setClient(customer);
            order.setProduct(product);
            order.setQuantite(quantity);
            order.setStatus(OrderStatus.BOOKED);
            // Premiun customers have an id between 1 and 1000 and their orders have the priority "HIGHT".
            if (customer.getId() >= CUSTOMER_ID_MIN && customer.getId() < PREMIUM_ID_MAX) {
                order.setPriority(OrderPriority.HIGHT);
            } else {
                order.setPriority(OrderPriority.NORMAL);
            }
            return orderRepository.save(order);
        }

        return null;
    }

    @Override
    public int checkPosition(Long clientId) throws NotFoundException {
        List<Order> orders = new ArrayList<>(queue);
        Customer customer = customerService.getById(clientId);
        List<Order> customerOrders = orderRepository.findAllByClient(customer);
        if (!orders.isEmpty() && !customerOrders.isEmpty()){
            return orders.indexOf(customerOrders.get(0))+1;
        }
        return 0;
    }


    @Override
    @Scheduled(cron = "${demo.cronExpression}")
    public List<Order> findAllOrder() {
        // update queue
        try {
            Thread.currentThread().setName("UPDATE P-QUEUE");
        } catch (Exception ex) { }
         queue.clear();
        List<Order> orders = orderRepository.findAllByStatusOrderByCreatedAsc(OrderStatus.BOOKED);
        orders.forEach( order ->{
            queue.add(order);
        });
        return orders;
    }

    @Override
    public PriorityQueue<Order> getPQueue() {
        return queue;
    }

    @Override
    public Order getNextOrder() {
        if (!queue.isEmpty()) {
            return queue.peek();
        }
        return null;
    }

    @Override
    public Order cancelOrder(Long clientId) throws NotFoundException {
        Customer customer = customerService.getById(clientId);
        List<Order> orders = orderRepository.findAllByClient(customer);
        if(!orders.isEmpty()) {
            Order order = orders.get(0);
            order.setStatus(OrderStatus.CANCELED);
            //we also remove this order in the queue
            queue.remove(order);
            return orderRepository.save(order);
        }
        return null;
    }
}
