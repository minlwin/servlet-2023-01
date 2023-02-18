package com.jdc.student.model;

import java.util.List;

public interface StudentModel {

	int create(Student s);
	
	List<Student> search(String name);
	
	Student findById(int id);
	
	void update(int id, Student s);
	
	void delete(int id);
}
