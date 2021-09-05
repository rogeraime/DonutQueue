package com.example.demo;

import com.example.demo.controller.OrderController;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Product;
import com.example.demo.service.CustomerService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
@EnableScheduling
public class DemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    private static final Logger LOG = Logger.getLogger(DemoApplication.class.getName());


    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService clientService;

    @Override
    public void run(String... args) throws Exception {
        // Initialization of the product and some clients
        //Initialization of the product
        Product product = new Product();
        product.setCode("P001");
        product.setLabel("Dotnut");
        product.setDescription("Sweet doughnuts");
        product.setQuantite(100000);
        productService.create(product);
        LOG.log(Level.INFO, "Muster Product is added");
        //Intialisation of client 1
        Customer client1 = new Customer();
        client1.setId(1L);
        client1.setName("Junior Balle");
        client1.setEmail("jniorballe@gmail.com");
        //Intialisation of client 2
        Customer client2 = new Customer();
        client2.setId(2L);
        client2.setName("Brundon Jean");
        client2.setEmail("brundonjean@gamil.com");
        //Intialisation of client 3
        Customer client3 = new Customer();
        client3.setId(1001L);
        client3.setName("Markus Jean");
        client3.setEmail("markusjean@gamil.com");
        //
        clientService.create(client1);
        clientService.create(client2);
        clientService.create(client3);
        LOG.log(Level.INFO, "Muster Clients are added");
    }
}
