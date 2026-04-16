package com.example.lab04.repository;

import com.example.lab04.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    // Lấy order theo customerId
    List<Order> findByCustomerCustomerId(Integer customerId);

    // Lấy order theo employee 
    @Query("""
        SELECT o FROM Order o 
        JOIN FETCH o.customer c 
        JOIN FETCH o.employee e
        WHERE e.employeeId = :empId
    """)
    List<Order> findOrdersByEmployeeWithDetails(@Param("empId") Integer empId);
}