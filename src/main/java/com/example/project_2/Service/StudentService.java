package com.example.project_2.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.project_2.Model.Student;

@Service
public class StudentService {
    List<Student> students = new ArrayList<>(); 
    

    public List<Student> getAllStudents(){
        return students; 
    }

    public void addStudent(Student student){
        students.add(student);
    }
    

    public boolean updateStudent(String id, Student student){
        for(int i = 0; i < students.size(); i++){
            if(students.get(i).getId().equals(id)){
                students.set(i, student); 
                return true; 
            }
        }

        return false; 
    }

    public boolean deleteStudent(String id){
        for(int i = 0; i < students.size(); i++){
            if(students.get(i).getId().equals(id)){
                students.remove(i); 
                return true; 
            }
        }
        return false;
    }

    public Student getStudent(String name){
        for(Student student : students){
            if(student.getName().equalsIgnoreCase(name)){
                return student; 
            }
        }
        return null; 
    }

    public List getMajor(String major){
        List majors = new ArrayList<>(); 
        for(Student student : students){
            if(student.getMajor().equalsIgnoreCase(major)){
                majors.add(student);
            }
        }
        return majors; 
    }
}
