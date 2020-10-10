package com.cruds.db;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;

import com.cruds.dateutil.Dateutil;
import com.cruds.demo.Book;
import com.cruds.demo.Issue;
import com.cruds.demo.Student;

public class IssueDAO{

	public boolean CreateStudent(Student s){

		String sql="insert into Student(usn,name) values (?,?)";

		int rows=0;
		try(Connection conn=DBconnectionManager.getConnection())
		{
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, s.getUSN());
			ps.setString(2, s.getName());
			rows=ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rows>0;	
	}
	
	public Vector<Vector<String>> getStudent(String USN)
	{
		String sql="select 	USN,name from student where USN=?";
		Vector<String> row=new Vector<>();
		Vector<Vector<String>> data=new Vector<>();
		try(Connection conn=DBconnectionManager.getConnection())
		{
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,USN);
			ResultSet rs=ps.executeQuery();
			if(rs!=null && rs.next())
			{
				row=new Vector<>();
				row.add(rs.getString(1));
				row.add(rs.getString(2));
				data.add(row);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	
	public int create(Issue issue)
	{
		String sql="insert into book_issue(USN,Issued_Date,Return_Date,Book_ISBN) values(?,?,?,?)";
		int issue_id=0;
		try(Connection conn=DBconnectionManager.getConnection())
		{
			PreparedStatement ps=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,issue.getUSN());
			ps.setDate(2, Dateutil.getSQLDate(issue.getIssue_date()));
			ps.setDate(3, Dateutil.getSQLDate(issue.getReturn_date()));
			ps.setInt(4, issue.getBook_isbn());
			
		//	ps.SetIn(4,issue.getBook().getBookISBN());
			
			ps.executeUpdate();
			ResultSet rs=ps.getGeneratedKeys();
			if(rs!=null && rs.next())
			{
				issue_id=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return issue_id;
	}

	public int updatebook(int book_isbn){
		int status=0;
		int count=0;
		String sql="select no_of_books from book where book_isbn=?";
		String sqladd="update book set no_of_books=? where book_isbn=?";
		try(Connection conn=DBconnectionManager.getConnection())
		{
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1,book_isbn);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				count=rs.getInt("no_of_books");
			}
			if(count>0){
			PreparedStatement ps2=conn.prepareStatement(sqladd);
			ps2.setInt(1,count-1);
			ps2.setInt(2, book_isbn);
			status=ps2.executeUpdate();
			}
			
		}catch(Exception e){
			System.out.println(e);
			}
		return status;
	}
	
	public Vector<Vector<String>> Issuebook(String usn)
	{
		String sql="select b.book_isbn, b.book_title, i.usn, i.issued_date, i.return_date from book b,book_issue i  where b.book_isbn=i.book_isbn and USN=?";
		Vector<String> row=new Vector<>();
		Vector<Vector<String>> data=new Vector<>();

		try(Connection conn=DBconnectionManager.getConnection())
	{
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setString(1,usn);
		ResultSet rs=ps.executeQuery();
		while(rs!=null && rs.next())
		{
			row=new Vector<>();
			row.add(String.valueOf(rs.getInt(1)));
			row.add(rs.getString(2));
			row.add(rs.getString(3));
			row.add(String.valueOf(rs.getDate(4)));
			row.add(String.valueOf(rs.getDate(5)));
			data.add(row);
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return data;
	}
	
	}
	
	




