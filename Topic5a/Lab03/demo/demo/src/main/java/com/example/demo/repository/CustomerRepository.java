package com.example.demo.repository;

import com.example.demo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    // tìm theo city
    List<Customer> findByCity(String city);

    // tìm theo tên gần đúng
    List<Customer> findByCustomerNameContainingIgnoreCase(String keyword);

    // JPQL query
    @Query("SELECT c FROM Customer c WHERE c.country = :country")
    List<Customer> findCustomersByCountry(@Param("country") String country);
}