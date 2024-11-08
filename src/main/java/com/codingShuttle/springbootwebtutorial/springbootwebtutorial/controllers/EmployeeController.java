package com.codingShuttle.springbootwebtutorial.springbootwebtutorial.controllers;


import com.codingShuttle.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDTO;
import com.codingShuttle.springbootwebtutorial.springbootwebtutorial.entities.EmployeeEntitiy;
import com.codingShuttle.springbootwebtutorial.springbootwebtutorial.repositories.EmployeeRepository;
import com.codingShuttle.springbootwebtutorial.springbootwebtutorial.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name = "employeeId") Long id){
        Optional<EmployeeDTO> employeeDTO =  employeeService.getEmployeeById(id);

        return employeeDTO
                .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping()
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam(required = false) Integer age){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping()
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
        EmployeeDTO savedEmployee = employeeService.createNewEmployee(inputEmployee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody EmployeeDTO employeeDTO, @PathVariable Long employeeId){
         return ResponseEntity.ok(employeeService.updateEmployeeById(employeeId, employeeDTO));
    }

    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long employeeId)
    {
        boolean gotDeleted =  employeeService.deleteEmployeeById(employeeId);
        if(gotDeleted)
            return ResponseEntity.ok(true);
        else
            return ResponseEntity.notFound().build();

    }

    @PatchMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@RequestBody Map<String, Object> updates, @PathVariable Long employeeId){
        EmployeeDTO partialUpdatedEmployee=  employeeService.updatePartialEmployeeById(employeeId, updates);
        if(partialUpdatedEmployee == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(partialUpdatedEmployee);
    }

}
