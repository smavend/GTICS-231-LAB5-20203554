package com.example.gtics231lab520203554.controller;

import com.example.gtics231lab520203554.entity.Employee;
import com.example.gtics231lab520203554.repository.EmployeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {
    final EmployeeRepository employeeRepository;

    public HomeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/")
    public String home(){
        return "home";
    }
    @GetMapping("/empleados")
    public String empleados(Model model){
        List<Employee> lista = employeeRepository.findAll();
        model.addAttribute("empleados", lista);
        return "empleados";
    }

    @GetMapping("/empleados/borrar")
    public String deleteEmployee(@RequestParam("id") int id,
                                 RedirectAttributes attr){
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if(optionalEmployee.isPresent()){
            employeeRepository.delete(optionalEmployee.get());
            attr.addFlashAttribute("msg", "Se eliminó el usuario correctamente");
        }else {
            attr.addFlashAttribute("err", "Hubo un error al eliminar el usuario");
        }
        return "redirect:/empleados";
    }

    @PostMapping("/empleados/buscar")
    public String buscar(@RequestParam("searchField") String search,
                         Model model){
        List<Employee> list = employeeRepository.buscar(search);
        model.addAttribute("empleados", list);
        return "empleados";
    }
}
