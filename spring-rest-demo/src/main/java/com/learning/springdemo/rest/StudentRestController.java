package com.learning.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.springdemo.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentRestController {
	
	private List<Student> students ;
	
	@PostConstruct
	public void loadData() {
		students = new ArrayList<>();
		Student student1 = new Student("Anas", "PM");
		Student student2 = new Student("Mehjabeen", "Begum");
		students.add(student1);
		students.add(student2);
	}
	
	@GetMapping("/students")
	public List<Student> getStudents(){
		
		return students;
	}

	@GetMapping("/students/{studentId}")
	public Student getStudent(@PathVariable int studentId) {
		if(studentId >= students.size() || studentId < 0) {
			throw new StudentNotFoundException("Student not found :: "+studentId);
		}
		return students.get(studentId);
	}
		
}
