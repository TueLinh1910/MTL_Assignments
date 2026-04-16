package com.example.demo.service;

import com.example.demo.dto.CustomerRequest;
import com.example.demo.dto.CustomerResponse;

import java.util.List;

public interface CustomerService {

    CustomerResponse create(CustomerRequest request);

    CustomerResponse update(Integer id, CustomerRequest request);

    void delete(Integer id);

    CustomerResponse getById(Integer id);

    List<CustomerResponse> getAll();

    List<CustomerResponse> searchByName(String keyword);
}