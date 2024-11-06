package com.codingShuttle.springbootwebtutorial.springbootwebtutorial.controllers;


import com.codingShuttle.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDTO;
import com.codingShuttle.springbootwebtutorial.springbootwebtutorial.entities.EmployeeEntitiy;
import com.codingShuttle.springbootwebtutorial.springbootwebtutorial.repositories.EmployeeRepository;
import com.codingShuttle.springbootwebtutorial.springbootwebtutorial.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path="/employees")
public class EmployeeController {

//    @GetMapping(path = "/getSecretMessage")
//    public String getMySuperSecrectMessage(){
//        return "Secret Message: Namaste";
//    }
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable(name = "employeeId") Long id){
        return employeeService.getEmployeeById(id);
    }

    @GetMapping()
    public List<EmployeeDTO> getAllEmployees(@RequestParam(required = false) Integer age){
        return employeeService.getAllEmployees();
    }

    @PostMapping()
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
        return employeeService.createNewEmployee(inputEmployee);
    }
}
