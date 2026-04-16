package com.example.lab04.controller;

import com.example.lab04.dto.OrderRequest;
import com.example.lab04.dto.OrderResponse;
import com.example.lab04.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    // CREATE
    @PostMapping
    public ResponseEntity<OrderResponse> create(@RequestBody OrderRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(req));
    }

    // GET BY ID
    @GetMapping("/{id}")
    public OrderResponse get(@PathVariable Integer id) {
        return service.getById(id);
    }

    // GET ALL
    @GetMapping
    public List<OrderResponse> getAll() {
        return service.getAll();
    }

    // GET BY EMPLOYEE
    @GetMapping("/by-employee/{employeeId}")
    public List<OrderResponse> byEmployee(@PathVariable Integer employeeId) {
        return service.getByEmployee(employeeId);
    }

   
    // UPDATE
    @PutMapping("/{id}")
    public OrderResponse update(@PathVariable Integer id, @RequestBody OrderRequest req) {
        return service.update(id, req);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}