package com.example.service;

import java.util.List;
import com.example.entities.Student;

public interface StudentService {
    
	Student createStudent(Student student);
    
	List<Student> getAllStudents();
   
	Student getStudentById(Long id); 
   
	String deleteStudentById(Long id);  
}
