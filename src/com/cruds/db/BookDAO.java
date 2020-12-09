package com.cruds.db;


import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.cruds.demo.Author;
import com.cruds.demo.Book;
import com.cruds.exception.SMSException;



public class BookDAO 
{

	public boolean Create(Book b)
	{
		int rows=0,autrows=0;
		String sql="insert into book(book_isbn,book_title,category,no_of_books) values(?,?,?,?)";
		String authersql="insert into author(author_name,author_mail_id,book_isbn) values(?,?,?)";
		try(Connection conn=DBconnectionManager.getConnection())
		{
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1,b.getBookISBN());
			ps.setString(2, b.getBooktitle());
			ps.setString(3, b.getCategory());
			ps.setInt(4, b.getNoofbooks());
			rows=ps.executeUpdate();


			ps=conn.prepareStatement(authersql);
			ps.setString(1,b.getAuthor().getAuthor_name());
			ps.setString(2,b.getAuthor().getAuthor_mailid());
			ps.setInt(3, b.getBookISBN());
			autrows=ps.executeUpdate();

		}catch(SQLException e){
			e.printStackTrace();
			if(e.getMessage().contains("Duplicate"))
			{
				throw new SMSException(b.getBookISBN() +" already exists! duplicate entry");
			}
			else
			{   
				throw new SMSException(e.getMessage() +"please contact system admin");
			}

		}
		return rows>0 && autrows>0;
	}

	public Vector<Vector<String>> getBooktitle(String Book_title)
	{
		Book_title = "%" + Book_title + "%";

		String sql="select book_isbn, book_title, category, no_of_books from book where book_title like ?";

		Vector<String> row=new Vector<>();
		Vector<Vector<String>> data=new Vector<>();
		try(Connection conn=DBconnectionManager.getConnection())
		{
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,Book_title);
			ResultSet rs=ps.executeQuery();
			while(rs!=null && rs.next())
			{
				row=new Vector<>();
				row.add(String.valueOf(rs.getInt(1)));
				row.add(rs.getString(2));
				row.add(rs.getString(3));
				row.add(String.valueOf(rs.getInt(4)));
				//row.add(rs.getString(5));
				data.add(row);

			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return data;

	}
	public Vector<Vector<String>> getBookCategory(String Category)
	{
		//Category = "%" + Category + "%";

		String sql="select book_isbn,book_title,category,no_of_books from book where Category=?";
		Vector<String> row=new Vector<>();
		Vector<Vector<String>> data=new Vector<>();

		try(Connection conn=DBconnectionManager.getConnection())
		{
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,Category);
			ResultSet rs=ps.executeQuery();
			while(rs!=null && rs.next())
			{
				row=new Vector<>();
				row.add(String.valueOf(rs.getInt(1)));
				row.add(rs.getString(2));
				row.add(rs.getString(3));
				row.add(String.valueOf(rs.getInt(4)));
				data.add(row);

			}
		}catch (SQLException e) {
			e.printStackTrace();
			
		}
		return data;
	}
	public Vector<Vector<String>> getBookAuthor(String Author_name)
	{
		Author_name = "%" + Author_name + "%";

		String sql="select b.book_isbn,b.book_title,b.category,b.no_of_books,a.author_name,a.author_mail_id from book b,author a  where Author_name like ? and b.book_isbn=a.book_isbn";
		Vector<String> row=new Vector<>();
		Vector<Vector<String>> data=new Vector<>();

		try(Connection conn=DBconnectionManager.getConnection())
		{
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,Author_name);
			ResultSet rs=ps.executeQuery();
			while(rs!=null && rs.next())
			{
				row=new Vector<>();
				row.add(String.valueOf(rs.getInt(1)));
				row.add(rs.getString(2));
				row.add(rs.getString(3));
				row.add(String.valueOf(rs.getInt(4)));
				row.add(rs.getString(5));
				row.add(rs.getString(6));
				data.add(row);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return data;

	}

	public Vector<Vector<String>> getAllbook()
	{
		
		String sql="select b.book_isbn,b.book_title,b.category,b.no_of_books,a.author_name,a.author_mail_id from book b, author a where b.book_isbn=a.book_isbn";

		Vector<String> row=new Vector<>();

		Vector<Vector<String>> data=new Vector<>();
		try(Connection conn=DBconnectionManager.getConnection())
		{
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs !=null && rs.next())
			{
				row=new Vector<>();
				row.add(String.valueOf(rs.getInt(1)));
				row.add(rs.getString(2));
				row.add(rs.getString(3));
				row.add( String.valueOf(rs.getInt(4)));
				row.add(rs.getString(5));
				row.add(rs.getString(6));
				data.add(row);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}


}
