package com.example.lab04.service;

import com.example.lab04.dto.OrderRequest;
import com.example.lab04.dto.OrderResponse;

import java.util.List;

public interface OrderService {

    OrderResponse create(OrderRequest req);

    OrderResponse update(Integer id, OrderRequest req);

    void delete(Integer id);

    OrderResponse getById(Integer id);

    List<OrderResponse> getAll();

    List<OrderResponse> getByEmployee(Integer employeeId);


}