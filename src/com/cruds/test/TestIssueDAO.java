package com.cruds.test;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import com.cruds.db.IssueDAO;
import com.cruds.demo.Student;

public class TestIssueDAO {

	@Test
	@Ignore
	public void Creatstudenttest() {
		IssueDAO dao=new IssueDAO();
		Student s=new Student("1cg16cs084","sanjay");
		assertTrue(dao.CreateStudent(s));
	}

}
