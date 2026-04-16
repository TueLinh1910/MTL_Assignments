package com.example.demo.service.impl;

import com.example.demo.dto.CustomerRequest;
import com.example.demo.dto.CustomerResponse;
import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerService;
import com.example.demo.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    @Override
    public CustomerResponse create(CustomerRequest request) {
        Customer customer = mapToEntity(request);
        return mapToResponse(repository.save(customer));
    }

    @Override
    public CustomerResponse update(Integer id, CustomerRequest request) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        customer.setCustomerName(request.getCustomerName());
        customer.setContactName(request.getContactName());
        customer.setAddress(request.getAddress());
        customer.setCity(request.getCity());
        customer.setPostalCode(request.getPostalCode());
        customer.setCountry(request.getCountry());

        return mapToResponse(repository.save(customer));
    }

    @Override
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Customer not found");
        }
        repository.deleteById(id);
    }

    @Override
    public CustomerResponse getById(Integer id) {
        return repository.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
    }

    @Override
    public List<CustomerResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<CustomerResponse> searchByName(String keyword) {
        return repository.findByCustomerNameContainingIgnoreCase(keyword)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // ===== Mapping =====

    private Customer mapToEntity(CustomerRequest r) {
        Customer c = new Customer();
        c.setCustomerName(r.getCustomerName());
        c.setContactName(r.getContactName());
        c.setAddress(r.getAddress());
        c.setCity(r.getCity());
        c.setPostalCode(r.getPostalCode());
        c.setCountry(r.getCountry());
        return c;
    }

    private CustomerResponse mapToResponse(Customer c) {
        return new CustomerResponse(
                c.getCustomerName(),
                c.getContactName(),
                c.getAddress(),
                c.getCity(),
                c.getPostalCode(),
                c.getCountry()
        );
    }
}