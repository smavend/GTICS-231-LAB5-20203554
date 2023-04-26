package com.example.gtics231lab520203554.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "locations")
public class Location {
    @Id
    @Column(name = "location_id", nullable = false)
    private int locationId;
    @Column(nullable = false)
    private String city;
}
