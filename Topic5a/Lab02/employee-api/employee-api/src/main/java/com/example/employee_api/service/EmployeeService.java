package com.example.employee_api.service;

import com.example.employee_api.dto.EmployeeRequest;
import com.example.employee_api.dto.EmployeeResponse;

import java.util.List;

public interface EmployeeService {

    List<EmployeeResponse> getAll();

    EmployeeResponse getById(Integer id);

    EmployeeResponse create(EmployeeRequest request);

    EmployeeResponse update(Integer id, EmployeeRequest request);

    void delete(Integer id);
}
