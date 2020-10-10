package com.cruds.dateutil;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cruds.db.DBconnectionManager;
import com.cruds.demo.Issue;


public class IssueDAO {

	
	public Issue getIssue(int id)
	{
		String sql="select issued_date,return_date from book_issue where issue_id=?";
		Issue issue=null;
		try(Connection conn=DBconnectionManager.getConnection())
		{
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs=ps.executeQuery();
			if(rs!=null && rs.next())
			{
				issue=new Issue(id, rs.getDate(1), rs.getDate(2));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return issue;
	}
	
	public int create(Issue issue)
	{
		String sql="insert into book_issue(issued_date,return_date) values(?,?)";
		int issue_id=0;
		try(Connection conn=DBconnectionManager.getConnection())
		{
			PreparedStatement ps=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setDate(1, Dateutil.getSQLDate(issue.getIssue_date()));
			ps.setDate(2, Dateutil.getSQLDate(issue.getReturn_date()));
			
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
}
