package com.example.employee_api.controller;

import com.example.employee_api.dto.EmployeeRequest;
import com.example.employee_api.dto.EmployeeResponse;
import com.example.employee_api.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // ===== CREATE =====
    @PostMapping
    public ResponseEntity<EmployeeResponse> create(@RequestBody EmployeeRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(employeeService.create(request));
    }

    // ===== GET ALL =====
    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAll() {
        return ResponseEntity.ok(employeeService.getAll());
    }

    // ===== GET BY ID =====
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(employeeService.getById(id));
    }

    // ===== UPDATE =====
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> update(
            @PathVariable Integer id,
            @RequestBody EmployeeRequest request) {

        return ResponseEntity.ok(employeeService.update(id, request));
    }

    // ===== DELETE =====
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
