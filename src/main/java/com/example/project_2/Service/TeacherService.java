package com.example.project_2.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.project_2.Model.Teacher;

@Service
public class TeacherService {
    List<Teacher> teachers = new ArrayList<>(); 

    public List<Teacher> getAllTeachers(){
        return teachers;
        
    }

    public void addTeacher(Teacher teacher){
        teachers.add(teacher);
    }
    

    public boolean updateTeacher(String id, Teacher teacher){
        for(int i = 0; i < teachers.size(); i++){
            if(teachers.get(i).getId().equals(id)){
                teachers.set(i, teacher); 
                return true; 
            }
        }

        return false; 
    }

    public boolean deleteTeacher(String id){
        for(int i = 0; i < teachers.size(); i++){
            if(teachers.get(i).getId().equals(id)){
                teachers.remove(i); 
                return true; 
            }
        }
        return false;
    }


    public Teacher getTeacher(String id){
        for(Teacher teacher : teachers){
            if(teacher.getId().equals(id)){
                return teacher; 
            }
        }
        return null; 
    }


    public List getAllSalary(double salary){
        List salaryList = new ArrayList<>(); 
        for(Teacher teacher : teachers){
            if(teacher.getSalary() >= salary){
                salaryList.add(teacher); 
            }
        }

        return salaryList; 
    }
    
}
