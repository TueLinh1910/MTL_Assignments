package com.example.lab04.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class OrderResponse {

    private Integer orderId;
    private LocalDateTime orderDate;

    private Integer customerId;
    private String customerName;

    private Integer employeeId;
    private String employeeName;
}
