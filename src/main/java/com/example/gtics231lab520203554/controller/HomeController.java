package com.example.gtics231lab520203554.controller;

import com.example.gtics231lab520203554.dto.PasswordDto;
import com.example.gtics231lab520203554.entity.Department;
import com.example.gtics231lab520203554.entity.Employee;
import com.example.gtics231lab520203554.entity.Job;
import com.example.gtics231lab520203554.repository.DepartmentRepository;
import com.example.gtics231lab520203554.repository.EmployeeRepository;
import com.example.gtics231lab520203554.repository.JobRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/empleados/nuevo")
    public String nuevoEmpleado(Model model){
        List<Job> trabajos = jobRepository.findAll();
        List<Employee> empleados = employeeRepository.findAll();
        List<Department> departamentos = departmentRepository.findAll();

        model.addAttribute("trabajos", trabajos);
        model.addAttribute("empleados", empleados);
        model.addAttribute("departamentos", departamentos);

        return "empleadosform";
    }

    @PostMapping("/empleados/save")
    public String guardarEmpleado(Employee employee,
                                  RedirectAttributes attr){
        System.out.println("hola");
        String password = employee.getPassword();
        PasswordDto passwDto = employeeRepository.hashear(password);
        employee.setPassword(passwDto.getPassword());
        System.out.println(employee.getPassword()+ ","+employee.getDepartment().getDepartmentName() +","+ employee.getJob().getJobTitle()+","+employee.getManager().getFirstName());
        employeeRepository.save(employee);
        attr.addFlashAttribute("msg","Empleado creado exitosamente");
        return "redirect:/empleados";
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
