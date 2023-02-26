package com.jdc.student.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.jdc.student.model.Student;
import com.jdc.student.model.impl.StudentModelImpl;

public class StudentModelTest {

	@Test
	void test_find_by_id() {
		var model = new StudentModelImpl();
		var student = new Student();
		student.setName("Aung Aung");
		student.setPhone("0928282872");
		student.setEmail("aung@gmail.com");
		var id = model.create(student);
		assertEquals(1, id);
	}
}
