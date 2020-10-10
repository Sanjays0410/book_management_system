package com.cruds.test;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import com.cruds.db.BookDAO;
import com.cruds.demo.Book;

public class TestBookDAO {

	@Test
	@Ignore
	public void testCreate() {
		BookDAO dao =new BookDAO();
		Book b=new Book(001,"j2ee","Technical",1,null);
		assertTrue(dao.Create(b));

	}
	
	@Test
	
	public void testgetBooktitle()
	{
		
		BookDAO dao=new BookDAO();
		assertNotNull(dao.getBooktitle(null));
		}
	@Test
	@Ignore
	public void testgetBookCategory()
	{
		BookDAO dao=new BookDAO();
		assertNotNull(dao.getBookCategory(null));
	}
	@Test
	@Ignore
	public void testgetBookauthor_name()
	{
		BookDAO dao=new BookDAO();
		assertNotNull(dao.getBookAuthor(null));
	}

}
