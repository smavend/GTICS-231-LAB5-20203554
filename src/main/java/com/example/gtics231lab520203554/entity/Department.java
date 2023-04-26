package com.example.gtics231lab520203554.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "departments")
public class Department {
    @Id
    @Column(name = "department_id", nullable = false)
    private int departmentId;

    @Column(name = "department_name", nullable = false)
    private String departmentName;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

}
