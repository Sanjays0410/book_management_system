package com.cruds.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cruds.demo.AdminLogin;

public class AdminDAO {

	
	public boolean User(AdminLogin u)
	{
		String sql="select 1 from login where username=? and password=?";
		
		boolean found=false;
		try(Connection conn=DBconnectionManager.getConnection())
		{
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,u.getUsername());
			ps.setString(2, u.getPassword());
			ResultSet rs=ps.executeQuery();
			if(rs!=null && rs.next())
			{
				found=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return found;
		
	}
}
