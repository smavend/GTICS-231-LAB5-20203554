package com.example.gtics231lab520203554.repository;

import com.example.gtics231lab520203554.dto.PasswordDto;
import com.example.gtics231lab520203554.dto.SueldosDto;
import com.example.gtics231lab520203554.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EmployeeRepository  extends JpaRepository<Employee, Integer> {
    @Query(value = "SELECT e.*, j.job_title, l.city, d.department_name FROM employees e inner join jobs j on (e.job_id = j.job_id) inner join departments d on (e.department_id=d.department_id) inner join locations l on (d.location_id = l.location_id) where first_name = ?1 or last_name=?1 or job_title=?1 or department_name=?1 or city=?1",
            nativeQuery = true)
    List<Employee> buscar(String palabra);

    @Query(value = "SELECT sha2(?1,256) as password", nativeQuery = true)
    PasswordDto hashear(String password);

    @Query(nativeQuery = true, value = "select job_title as puesto,truncate(max(salary),2) as maximo, truncate(min(salary),2) as minimo, truncate(sum(salary),2) as suma, truncate(avg(salary),2) as promedio from employees e inner join jobs j on (e.job_id=j.job_id) group by e.job_id")
    List<SueldosDto> listarSueldos();

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update employees set manager_id = NULL where manager_id=?1")
    void employeeSinJefe(int manager_id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update departments set manager_id = NULL where manager_id=?1")
    void departmentSinJefe(int manager_id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "delete from job_history where (employee_id=?1)")
    void borrarHistorial(int employee_id);
}
