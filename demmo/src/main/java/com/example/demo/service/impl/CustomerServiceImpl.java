package com.example.demo.service.impl;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository clientRepository;

    @Override
    public Customer create(Customer client) {
        return clientRepository.save(client);
    }

    @Override
    public Customer getById(Long clientId) throws NotFoundException {
        return clientRepository.findById(clientId).orElseThrow( ()-> new NotFoundException("Customer Not Found"));
    }

    @Override
    public List<Customer> findAll() {
        return clientRepository.findAll();
    }
}
