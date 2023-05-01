package com.example.gtics231lab520203554.controller;

import com.example.gtics231lab520203554.dto.SueldosDto;
import com.example.gtics231lab520203554.repository.DepartmentRepository;
import com.example.gtics231lab520203554.repository.EmployeeRepository;
import com.example.gtics231lab520203554.repository.JobRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    final EmployeeRepository employeeRepository;
    final JobRepository jobRepository;
    final DepartmentRepository departmentRepository;

    public HomeController(EmployeeRepository employeeRepository, JobRepository jobRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.jobRepository = jobRepository;
        this.departmentRepository = departmentRepository;
    }

    @GetMapping("/")
    public String home(){
        return "home";
    }

    // REPORTES

    @GetMapping("/reportes")
    public String menuReportes(){
        return "reportes";
    }

    @GetMapping("/reportes/salario")
    public String mostrarSueldos(Model model){
        List<SueldosDto> lista = employeeRepository.listarSueldos();
        model.addAttribute("sueldos", lista);
        return "sueldos";
    }
    @GetMapping("/reportes/tentativa")
    public String mostrarAviso(){
        return "tentativa";
    }
}
