package com.cruds.demo;

import java.util.Date;

public class Issue {

	private int issue_id;
	private Date issue_date;
	private Date return_date;
	
	private String USN;
	
	private int Book_isbn;

	

	public Issue(Date issue_date, Date return_date, String uSN, int book_isbn) {
		super();
		this.issue_date = issue_date;
		this.return_date = return_date;
		USN = uSN;
		Book_isbn = book_isbn;
	}



	public Issue(int issue_id, Date issue_date, Date return_date) {
		super();
		this.issue_id = issue_id;
		this.issue_date = issue_date;
		this.return_date = return_date;
	}



	public Issue(Date issue_date, Date return_date) {
		super();
		//this.issue_id=issue_id;
		this.issue_date = issue_date;
		this.return_date = return_date;
		//this.student=stud;
	}
	
	

	public Date getIssue_date() {
		return issue_date;
	}
	
	public void setIssue_date(Date issue_date) {
		this.issue_date = issue_date;
	}
	
	public String getUSN() {
		return USN;
	}

	public void setUSN(String uSN) {
		USN = uSN;
	}

	public int getBook_isbn() {
		return Book_isbn;
	}

	public void setBook_isbn(int book_isbn) {
		Book_isbn = book_isbn;
	}

	public Date getReturn_date() {
		return return_date;
	}
	
	public int getIssue_id() {
		return issue_id;
	}

	public void setIssue_id(int issue_id) {
		this.issue_id = issue_id;
	}

	public void setReturn_date(Date return_date) {
		this.return_date = return_date;
	}
	
	
	
	


	@Override
	public String toString() {
		return "Issue [issue_id=" + issue_id + ", issue_date=" + issue_date + ", return_date=" + return_date + ", USN="
				+ USN + ", Book_isbn=" + Book_isbn + "]";
	}
	
	
	
	
}
