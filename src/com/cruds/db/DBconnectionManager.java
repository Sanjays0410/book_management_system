package com.cruds.db;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.cruds.exception.SMSException;


public class DBconnectionManager 
{
	static 
	{
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}

	public static Connection getConnection() 
	{
		Properties prop=new Properties();
		Connection conn=null;
		try{
			
				prop.load(new FileInputStream("DBlink.properties"));

				//conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/SanjayTest","root","sada@123");
				conn=DriverManager.getConnection(prop.getProperty("db_url"),prop.getProperty("username"),prop.getProperty("password"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			

		}catch(SQLException e){
			e.printStackTrace();
			throw new SMSException(" Data base ERROR PLease contact system admin");
		}
		return conn;

	}

}
