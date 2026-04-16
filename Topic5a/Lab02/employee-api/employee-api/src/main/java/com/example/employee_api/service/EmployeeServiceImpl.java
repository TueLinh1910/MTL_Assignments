package com.example.employee_api.service;

import com.example.employee_api.dto.EmployeeRequest;
import com.example.employee_api.dto.EmployeeResponse;
import com.example.employee_api.entity.Employee;
import com.example.employee_api.exception.ResourceNotFoundException;
import com.example.employee_api.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.employee_api.exception.ResourceNotFoundException;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // ===== GET ALL =====
    @Override
    public List<EmployeeResponse> getAll() {
        return employeeRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // ===== GET BY ID =====
    @Override
    public EmployeeResponse getById(Integer id) {
        Employee employee = employeeRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id = " + id));
        return toResponse(employee);
    }

    // ===== CREATE =====
    @Override
    public EmployeeResponse create(EmployeeRequest request) {
        Employee employee = new Employee();

        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setBirthDate(request.getBirthDate());

        // supervisor xử lý sau (tạm thời bỏ qua)
        
        Employee saved = employeeRepository.save(employee);

        return toResponse(saved);
    }

    // ===== UPDATE =====
    @Override
    public EmployeeResponse update(Integer id, EmployeeRequest request) {
        Employee employee = employeeRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id = " + id));

        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setBirthDate(request.getBirthDate());

        Employee updated = employeeRepository.save(employee);

        return toResponse(updated);
    }

    // ===== DELETE =====
    @Override
    public void delete(Integer id) {
        Employee employee = employeeRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id = " + id));

        employeeRepository.delete(employee);
    }

    // ===== MAPPER =====
    private EmployeeResponse toResponse(Employee e) {
        EmployeeResponse res = new EmployeeResponse();

        res.setEmployeeId(e.getEmployeeId());
        res.setFirstName(e.getFirstName());
        res.setLastName(e.getLastName());
        res.setBirthDate(e.getBirthDate());

        if (e.getSupervisor() != null) {
            res.setSupervisorId(e.getSupervisor().getEmployeeId());
        }

        return res;
    }
}