package com.example.project_2.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Teacher {

    @NotEmpty(message = "ID should be not empty!")
    private String id;

    @NotEmpty(message = "Name should be not empty!")
    private String name; 

    @NotNull(message = "Salary should be not empty!")
    private double salary; 
    
}
