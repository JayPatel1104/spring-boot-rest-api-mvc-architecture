package com.codingShuttle.springbootwebtutorial.springbootwebtutorial.services;

import com.codingShuttle.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDTO;
import com.codingShuttle.springbootwebtutorial.springbootwebtutorial.entities.EmployeeEntitiy;
import com.codingShuttle.springbootwebtutorial.springbootwebtutorial.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.util.ReflectionUtils.findRequiredField;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }


    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        return employeeRepository.findById(id).map(employeeEntitiy -> modelMapper.map(employeeEntitiy, EmployeeDTO.class));
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

    public EmployeeDTO updateEmployeeById(Long employeeId, EmployeeDTO employeeDTO) {
        EmployeeEntitiy employeeEntitiy = modelMapper.map(employeeDTO, EmployeeEntitiy.class);
        employeeEntitiy.setId(employeeId);
        EmployeeEntitiy savedEmployeeEntity = employeeRepository.save(employeeEntitiy);
        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }

    public boolean deleteEmployeeById(Long employeeId) {
        if(!isExistsEmployeeById(employeeId)) return false;
        employeeRepository.deleteById(employeeId);
        return true;

    }

    public EmployeeDTO updatePartialEmployeeById(Long employeeId, Map<String, Object> updates) {
        if (!isExistsEmployeeById(employeeId)) return null;

        EmployeeEntitiy employeeEntitiy = employeeRepository.findById(employeeId).get();
        updates.forEach((field, value) -> {

            //Find a field from the required class which is EmployeeEntity in this case and give the field name (variable name of that classs)
            Field fieldToBeUpdated = ReflectionUtils.findRequiredField(EmployeeEntitiy.class, field);

            //Since the variables of Employee enitity are private, we need to set them accessible using below method
            fieldToBeUpdated.setAccessible(true);

            //now set the field of that class using the object, field and the value
            ReflectionUtils.setField(fieldToBeUpdated, employeeEntitiy, value);
        });

        return modelMapper.map(employeeRepository.save(employeeEntitiy), EmployeeDTO.class);
    }

    public boolean isExistsEmployeeById(Long employeeId){
        return employeeRepository.existsById(employeeId);
    }
}
