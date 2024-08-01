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
import com.example.project_2.Model.Teacher;
import com.example.project_2.Service.TeacherService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService; 

    @GetMapping("/get")
    public ResponseEntity getAllTeachers(){
        List<Teacher> allTeachers = teacherService.getAllTeachers(); 
        if(allTeachers.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("Not found Teachers"));
        }

        return ResponseEntity.status(200).body(allTeachers); 
    }

    @PostMapping("/add")
    public ResponseEntity addTeacher(@Valid @RequestBody Teacher teacher, Errors err){
        if(err.hasErrors()){
            String message = err.getFieldError().getDefaultMessage(); 
            return ResponseEntity.status(400).body(message); 
        }
        
        teacherService.addTeacher(teacher);
        
        return ResponseEntity.status(200).body(new ApiResponse(teacher.getId() + " Added")); 
    }

    @PutMapping("/{id}/update")
    public ResponseEntity updateStudent(@PathVariable String id, @Valid @RequestBody Teacher teacher, Errors err){
        if(err.hasErrors()){
            String message = err.getFieldError().getDefaultMessage(); 
            return ResponseEntity.status(400).body(message);
        }

        boolean isFound = teacherService.updateTeacher(id, teacher);
        if(isFound){
        return ResponseEntity.status(200).body(new ApiResponse("Teacher with this (" + id + ") Updated")); 
        } 
        return ResponseEntity.status(400).body(new ApiResponse("Teacher with this (" + id + ") Not found")); 
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity deleteTeacher(@PathVariable String id ){
        boolean isFound = teacherService.deleteTeacher(id); 
        if(isFound){
            return ResponseEntity.status(200).body(new ApiResponse("Teacher with this " + id + " Deleted")); 
        }
        return ResponseEntity.status(400).body(new ApiResponse("Teacher with this (" + id + ") Not found")); 
    }


    @GetMapping("/getTeacher/{id}")
    public ResponseEntity getTeacher(@PathVariable String id){

        if(teacherService.getTeacher(id) != null){
            return ResponseEntity.status(200).body(teacherService.getTeacher(id)); 
        } 
        return ResponseEntity.status(400).body(new ApiResponse("Student " + id +  " not found")); 
    }


    @GetMapping("/getSalary/{salary}")
    public ResponseEntity getAllSalary(@PathVariable double salary){
        List<Teacher> teacherSalary = teacherService.getAllSalary(salary); 

        if(teacherSalary.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("Not found this salary for any teachers")); 
        }

        return ResponseEntity.status(200).body(teacherSalary); 
    }

}
