package com.cruds.demo;

public class Author {

	 private String Author_name;
	 private String Author_mailid;
	 private int Book_ISBN;
	 
	public Author(String author_name, String author_mailid) {
		super();
		Author_name = author_name;
		Author_mailid = author_mailid;
	}
	
	public String getAuthor_name() {
		return Author_name;
	}
	
	public void setAuthor_name(String author_name) {
		Author_name = author_name;
	}
	
	public String getAuthor_mailid() {
		return Author_mailid;
	}
	
	public void setAuthor_mailid(String author_mailid) {
		Author_mailid = author_mailid;
	}
	
	public int getBook_ISBN() {
		return Book_ISBN;
	}
	
	public void setBook_ISBN(int book_ISBN) {
		Book_ISBN = book_ISBN;
	}
	
	@Override
	public String toString() {
		return "Author [Author_name=" + Author_name + ", Author_mailid=" + Author_mailid + "]";
	}
	
	
}