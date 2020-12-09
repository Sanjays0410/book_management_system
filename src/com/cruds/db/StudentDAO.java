package com.cruds.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.cruds.demo.Student;
import com.cruds.exception.SMSException;

public class StudentDAO {

	public boolean CreateStudent(Student s){

		String sql="insert into student(usn,name) values (?,?)";

		int rows=0;
		try(Connection conn=DBconnectionManager.getConnection())
		{
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, s.getUSN());
			ps.setString(2, s.getName());
			rows=ps.executeUpdate();

		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(e.getMessage().contains("Duplicate"))
			{
				throw new SMSException(s.getUSN() +"already exists! duplicate entry");
			}
			else
			{
				throw new SMSException(e.getMessage() +"please contact system admin");
			}
			
		}
		return rows>0;	
	}
	
	public Vector<Vector<String>> getAllstudent()
	{
		String sql="select usn,name from student";
		Vector<String> row=new Vector<>();
		Vector<Vector<String>> data=new Vector<>();
		try(Connection conn=DBconnectionManager.getConnection())
		{
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs!=null && rs.next())
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
	
	
	
	
	public Vector<Vector<String>> getStudent(String USN)
	{
		USN = "%" + USN + "%";
		String sql="select 	USN,name from student where USN like ?";
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
}
