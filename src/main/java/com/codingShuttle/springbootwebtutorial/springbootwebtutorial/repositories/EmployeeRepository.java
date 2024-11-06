package com.codingShuttle.springbootwebtutorial.springbootwebtutorial.repositories;


import com.codingShuttle.springbootwebtutorial.springbootwebtutorial.entities.EmployeeEntitiy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntitiy, Long> {



}
