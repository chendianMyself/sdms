package com.oracle.util;

import java.sql.Connection;
import java.sql.DriverManager;




public class JDBCUtil {
	private static String driver="com.mysql.jdbc.Driver";
	private static String url="jdbc:mysql://localhost:3306/sdms";
	private static String user="root";
	private static String ps="root";
	
	public static Connection getConn(){
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, ps);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return conn;
	}
	/*public static void main(String[] args) {
		System.out.println(getConn());
	}*/
}
