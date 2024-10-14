package org.example.coursework.clientService;

import org.example.coursework.clientService.repo.CustomerRepository;
import org.example.coursework.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public void save(Customer customer){
        if (findByID(customer).isEmpty())
            customerRepository.save(customer);
    }

    public Optional<Customer> findByID(Customer customer) {
        return customerRepository.findById(customer.getId());
    }
}
