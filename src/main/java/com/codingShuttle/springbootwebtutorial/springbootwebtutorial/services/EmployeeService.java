package com.codingShuttle.springbootwebtutorial.springbootwebtutorial.services;

import com.codingShuttle.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDTO;
import com.codingShuttle.springbootwebtutorial.springbootwebtutorial.entities.EmployeeEntitiy;
import com.codingShuttle.springbootwebtutorial.springbootwebtutorial.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }


    public EmployeeDTO getEmployeeById(Long id) {
        EmployeeEntitiy employeeEntitiy =  employeeRepository.findById(id).orElse(null);
        ModelMapper modelMApper = new ModelMapper();
        return modelMApper.map(employeeEntitiy, EmployeeDTO.class);

    }


    public List<EmployeeDTO> getAllEmployees() {

        List<EmployeeEntitiy> employeeEntities = employeeRepository.findAll();

        return employeeEntities
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO createNewEmployee(EmployeeDTO inputEmployee) {
        EmployeeEntitiy toSaveEmployee = modelMapper.map(inputEmployee, EmployeeEntitiy.class);
        EmployeeEntitiy employeeEntitiy = employeeRepository.save(toSaveEmployee);
        return modelMapper.map(employeeEntitiy, EmployeeDTO.class);
    }
}
