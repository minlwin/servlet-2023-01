package com.jdc.student.model.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jdc.student.model.Student;
import com.jdc.student.model.StudentModel;

public class StudentModelImpl implements StudentModel{
	
	private Map<Integer, Student> students;
	private int id;
	
	public StudentModelImpl() {
		students = Collections.synchronizedMap(new HashMap<>());
	}

	@Override
	public synchronized int create(Student s) {
		s.setId(++ id);
		students.put(s.getId(), s);
		return s.getId();
	}

	@Override
	public List<Student> search(String name) {
		return students.values().stream()
				.filter(s -> name == null ? true : s.getName().toLowerCase()
						.startsWith(name.toLowerCase()))
				.toList();
	}

	@Override
	public Student findById(int id) {
		return students.get(id);
	}

	@Override
	public synchronized void update(int id, Student s) {
		s.setId(id);
		students.put(id, s);
	}

	@Override
	public synchronized void delete(int id) {
		students.remove(id);
	}

}
