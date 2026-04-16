package com.example.employee_api.repository;

import com.example.employee_api.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findByLastNameContainingIgnoreCase(String lastName);
}
