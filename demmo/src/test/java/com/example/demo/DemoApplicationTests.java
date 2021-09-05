package com.example.demo;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Order;
import com.example.demo.entity.Product;
import com.example.demo.entity.ennum.OrderStatus;
import com.example.demo.service.CustomerService;
import com.example.demo.service.OrdersService;
import com.example.demo.service.ProductService;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    OrdersService ordersService;

    @Test
    void testOrder() {
        try {
            Order savedOrder = ordersService.addNewOrder(1L, 5);
            Order savedOrder2 = ordersService.addNewOrder(2L, 4);
            //
            assertNotNull(savedOrder);
            assertNotNull(savedOrder);
            assertEquals(savedOrder.getQuantite(), 5);
            //
            Customer savedCustomer = savedOrder.getClient();
            assertNotNull(savedCustomer);
            assertEquals(savedCustomer.getId(), 1L);
            assertEquals(savedCustomer.getName(), "Junior Balle");
            assertEquals(savedCustomer.getEmail(), "jniorballe@gmail.com");
            //
            Product savedProduct = savedOrder.getProduct();
            assertNotNull(savedProduct);
            assertNotNull(savedProduct.getId());
            assertEquals(savedProduct.getCode(), "P001");
            assertEquals(savedProduct.getLabel(), "Dotnut");
            ///
            List<Order> orders = ordersService.findAllOrder();
            assertNotNull(orders);
            assertEquals(orders.size(), 2);
            assertEquals(orders.get(0).getId(), savedOrder.getId());
            assertEquals(orders.get(1).getId(), savedOrder2.getId());
            ///
            Order order = ordersService.getNextOrder();
            assertNotNull(order);
            assertEquals(order.getId(), savedOrder.getId());
            ///
            int position = ordersService.checkPosition(1L);
            assertEquals(position, 1);
            ///
            Order cancelOrder = ordersService.cancelOrder(2L);
            assertNotNull(cancelOrder);
            assertEquals(cancelOrder.getId(), savedOrder2.getId());
            assertEquals(cancelOrder.getStatus(), OrderStatus.CANCELED);
        } catch (NotFoundException e) {
            assertTrue(false);
        }
    }

}
