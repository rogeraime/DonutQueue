package com.example.demo.service;

import com.example.demo.entity.Customer;
import javassist.NotFoundException;

import java.util.List;

public interface CustomerService {
    Customer create(Customer client);
    Customer getById(Long clientId) throws NotFoundException;
    List<Customer> findAll();
}
