package com.greatlearning.studentManagement.service;

import java.util.List;

import com.greatlearning.studentManagement.model.Student;

public interface StudentService {
	
	public List<Student> findAll();
	
	public Student findById(int id);
	
	public void save(Student customer);
	
	public void deleteById(int id);

}