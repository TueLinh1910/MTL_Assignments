package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {

    @NotBlank(message = "Customer name is required")
    private String customerName;

    private String contactName;
    private String address;
    private String city;
    private String postalCode;
    private String country;
}