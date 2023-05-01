package com.example.gtics231lab520203554.repository;

import com.example.gtics231lab520203554.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location,Integer> {
}
