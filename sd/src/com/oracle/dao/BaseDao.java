package com.oracle.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.PreparedStatement;
import com.oracle.util.Util;

public class BaseDao {
	public static Connection conn=null;
	public static java.sql.PreparedStatement ps=null;
	public static ResultSet rs = null;
	
	
	protected static Connection getConn(){
		try {
			Class.forName(Util.driver);
			conn = DriverManager.getConnection(Util.url,Util.user,Util.ps);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return conn;
		
	}
	protected static void closeaAll() {
		try {
			if(rs!=null)rs.close();
			if(ps!=null)ps.close();
			if(conn!=null)conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		
	}
	/*public static void main(String[] args) {
		System.out.println(getConn() );
	}*/
}
