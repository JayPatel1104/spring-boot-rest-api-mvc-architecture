package com.codingShuttle.springbootwebtutorial.springbootwebtutorial.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees")

public class EmployeeEntitiy {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private long id;

    private String name;
    private String email;
    private Integer age;
    private LocalDate dateOfJoining;
    private String role;

    @JsonProperty("isActive")
    private Boolean isActive;

}
