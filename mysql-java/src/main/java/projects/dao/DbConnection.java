package projects.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import projects.exception.DbException;

public class DbConnection {
//creates variables that are related to the schema and user I created using MySQL Workbench 
	private static String HOST = "localhost";
	private static String PASSWORD = "projects";
	private static int PORT = 3306;
	private static String SCHEMA = "projects";
	private static String USER = "projects";
	
	public static java.sql.Connection getConnection(){
		String uri = String.format("jdbc:mysql://%s:%d/%s?user=%s&password=%s", HOST, PORT, SCHEMA, USER, PASSWORD);
		
		try {//uses a try catch block to create a connection or catch an error 
			Connection conn = DriverManager.getConnection(uri);//establishes the connection 
			System.out.println("Connection to schema " + SCHEMA + " is successful.");//prints out if the connection is successfully made 
			return conn;
		} catch (SQLException e){//catches the exception 
			System.out.println("Unable to get connection at " + uri);//prints out if the connection is not successfully made 
			throw new DbException("Unable to get connection at \" + uri");
		}
	}
}
