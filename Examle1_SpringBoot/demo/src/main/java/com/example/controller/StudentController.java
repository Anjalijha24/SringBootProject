package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Student;
import com.example.service.StudentService;
@RequestMapping("/api")
@RestController
public class StudentController 
{
	@Autowired
	StudentService studentService;
	
	@PostMapping("/createstudents")
       Student createStudent(@RequestBody Student student)
	
       {
		return studentService.createStudent(student);
    	   
       }
	@GetMapping("/getallstudents")
	List<Student>getALLStudents()
	{
		return studentService.getAllStudents();
		
	}
	@GetMapping("/getstudentbyid/(sid)")
	Student getStudentById(Long id)
	{
		return studentService.getStudentById(id);
		
	}
	@DeleteMapping("/getStudentbyid/(sid)")
	String deleteStudent(@PathVariable("sid") Long id)
	{
		return studentService.deleteStudentById(id);
		
	}
}
