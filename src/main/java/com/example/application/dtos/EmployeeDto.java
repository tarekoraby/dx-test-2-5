package com.example.application.dtos;

import com.example.application.backend.Employee;

public record EmployeeDto (
        Long id,
        String name,
        String email) {

    public static EmployeeDto from(Employee employee){
        return new EmployeeDto(
                employee.getId(),
                employee.getName(),
                employee.getEmail()
        );
    }

}
