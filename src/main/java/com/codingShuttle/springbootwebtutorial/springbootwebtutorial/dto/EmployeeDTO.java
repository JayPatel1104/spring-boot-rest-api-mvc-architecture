package com.codingShuttle.springbootwebtutorial.springbootwebtutorial.dto;

import com.codingShuttle.springbootwebtutorial.springbootwebtutorial.annotations.EmployeeRoleValidation;
import com.codingShuttle.springbootwebtutorial.springbootwebtutorial.annotations.PrimeNumberValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private long id;

    @NotBlank(message = "Name Cannot be empty")
    @Size(min = 3, max = 10, message = "Name should be between 3 to 10 Characters")
    private String name;

    @NotBlank(message = "Email of employee cannot be blank")
    @Email(message = "Not a Valid Email")
    private String email;

    @NotNull(message = "Age of employee cannot be blank")
    @Max(value = 80, message = "Maximum Age can be 80")
    @Min(value = 18, message = "Minimum Age can be 80")
    private Integer age;

    @NotBlank(message = "Role of employee cannot be blank")
   // @Pattern(regexp = "^(ADMIN|USER)$", message = "Role of employee can be either ADMIN or USER")
    @EmployeeRoleValidation
    private String role;

//    @NotNull(message = "Salary of employee cannot be null")
//    @Positive(message = "Salary should be positve")
//    @Digits(integer = 6, fraction = 2, message = "Salary should be only 6 digit integer and 2 digit fraction")
//    private Double salary;

    @PastOrPresent(message = "Date of Joining should be")
    private LocalDate dateOfJoining;

//    @PrimeNumberValidation
//    private Integer inputNumber;

    @JsonProperty("isActive")
    private Boolean isActive;




}
