package com.cognizant.truyum.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionHandler {
	public static Connection getConnection() {
		Connection con=null;
		try {
			Properties prop = new Properties();
	    	prop.load(new FileReader("D:\\892723\\truYum\\src\\connection.properties"));
	    	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/truYum",prop);
	    	if(con!=null) 
	    	{
	    		System.out.println("Connected to database");	
	    	}
		}catch(SQLException e) {
			System.out.println("Not connected to the database");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Connection Properties file not found");
			e.printStackTrace();
		}
		return con;
	}
}
