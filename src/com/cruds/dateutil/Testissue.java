package com.cruds.dateutil;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import com.cruds.demo.Issue;

public class Testissue {

	@Test
	public void testissue() {
		
		//Issue issue=new Issue(Dateutil.getCurrentDate(), Dateutil.addToCurrentDate(7));
	//	Date date=Dateutil.getUtilDateFromString("06-10-2020");
	//	Date date1=Dateutil.addToCurrentDate(7);
	//	System.out.println(date);
		IssueDAO dao=new IssueDAO();
	//	int issue_id=dao.create(issue);
		//System.out.println(issue_id);
		//assertTrue(issue_id>0);
		
		
		
		Issue is=dao.getIssue(1);
		System.out.println(is);
		assertNotNull(is);
		
	}

}
