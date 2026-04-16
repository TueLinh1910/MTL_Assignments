package com.example.lab04.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "employees")
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;

    private String lastName;
    private String firstName;
    private LocalDate birthDate;
    private String photo;

    @Column(columnDefinition = "TEXT")
    private String notes;

    // self reference
    @ManyToOne
    @JoinColumn(name = "supervisor_id")
    private Employee supervisor;
}