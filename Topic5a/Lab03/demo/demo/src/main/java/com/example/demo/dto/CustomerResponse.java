package com.example.demo.dto;

import lombok.*;

@Getter
@AllArgsConstructor
public class CustomerResponse {

    private String customerName;
    private String contactName;
    private String address;
    private String city;
    private String postalCode;
    private String country;
}