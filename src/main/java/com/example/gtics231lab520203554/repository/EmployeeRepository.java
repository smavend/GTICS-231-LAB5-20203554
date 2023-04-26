package com.example.gtics231lab520203554.repository;

import com.example.gtics231lab520203554.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository  extends JpaRepository<Employee, Integer> {
    @Query(value = "SELECT e.*, j.job_title, l.city FROM employees e inner join jobs j on (e.job_id = j.job_id) inner join departments d on (e.department_id=d.department_id) inner join locations l on (d.location_id = l.location_id) where first_name = ?1 or last_name=?1 or job_title=?1 or city=?1",
            nativeQuery = true)
    List<Employee> buscar(String palabra);
}
