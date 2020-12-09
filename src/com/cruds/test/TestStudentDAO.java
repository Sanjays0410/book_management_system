package com.cruds.test;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import com.cruds.db.IssueDAO;
import com.cruds.db.StudentDAO;
import com.cruds.demo.Student;

public class TestStudentDAO {

	@Test
	@Ignore
	public void Creatstudenttest() {
		StudentDAO dao=new StudentDAO();
		Student s=new Student("1cg16cs084","sanjay");
		assertTrue(dao.CreateStudent(s));
	}

}
