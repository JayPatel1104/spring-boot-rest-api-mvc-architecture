package com.codingShuttle.springbootwebtutorial.springbootwebtutorial.controllers;


import com.codingShuttle.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDTO;
import com.codingShuttle.springbootwebtutorial.springbootwebtutorial.entities.EmployeeEntitiy;
import com.codingShuttle.springbootwebtutorial.springbootwebtutorial.repositories.EmployeeRepository;
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
private final EmployeeRepository employeeRepository;
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }




    @GetMapping("/{employeeId}")
    public EmployeeEntitiy getEmployeeById(@PathVariable(name = "employeeId") Long id){
        return employeeRepository.findById(id).orElse(null);
    }

    @GetMapping()
    public List<EmployeeEntitiy> getAllEmployees(@RequestParam(required = false) Integer age){
        return employeeRepository.findAll();
    }

    @PostMapping()
    public EmployeeEntitiy createNewEmployee(@RequestBody EmployeeEntitiy inputEmployee){
        return employeeRepository.save(inputEmployee);
    }
}
