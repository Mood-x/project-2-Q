package com.example.project_2.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project_2.API.ApiResponse;
import com.example.project_2.Model.Student;
import com.example.project_2.Service.StudentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor

public class StudentController {
    private final StudentService studentService; 


    @GetMapping("/get")
    public ResponseEntity getAllStudents(){
        List<Student> student = studentService.getAllStudents(); 
        return ResponseEntity.status(200).body(student); 
    }


    @PostMapping("/add")
    public ResponseEntity addStudent(@Valid @RequestBody Student student, Errors err){
        if(err.hasErrors()){
            String message = err.getFieldError().getDefaultMessage(); 
            return ResponseEntity.status(400).body(message); 
        }
        
        studentService.addStudent(student);
        
        return ResponseEntity.status(200).body(new ApiResponse(student.getId() + " Added")); 
    }

    @PutMapping("/{id}/update")
    public ResponseEntity updateStudent(@PathVariable String id, @Valid @RequestBody Student student, Errors err){
        if(err.hasErrors()){
            String message = err.getFieldError().getDefaultMessage(); 
            return ResponseEntity.status(400).body(message);
        }

        boolean isFound = studentService.updateStudent(id, student);
        if(isFound){
        return ResponseEntity.status(200).body(new ApiResponse("Student with this (" + id + ") Updated")); 
        } 
        return ResponseEntity.status(400).body(new ApiResponse("Student with this (" + id + ") Not found")); 
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity deleteStudent(@PathVariable String id ){
        boolean isFound = studentService.deleteStudent(id); 
        if(isFound){
            return ResponseEntity.status(200).body(new ApiResponse("Student with this " + id + " Deleted")); 
        }
        return ResponseEntity.status(400).body(new ApiResponse("Student with this (" + id + ") Not found")); 
    }

    @GetMapping("/{name}")
    public ResponseEntity getStudent(@PathVariable String name){
        if(studentService.getStudent(name) != null){
            return ResponseEntity.status(200).body(studentService.getStudent(name)); 
        } 
        return ResponseEntity.status(400).body(new ApiResponse("Student " + name +  " not found")); 
    }

    @GetMapping("/getMajor/{major}")
    public ResponseEntity getMajor(@PathVariable String major){
        List<Student> studentMajor = studentService.getMajor(major); 
        if(studentMajor.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("Not found")); 
        }
        
        return ResponseEntity.status(200).body(studentMajor); 
    }
}
