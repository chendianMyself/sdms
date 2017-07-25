package com.oracle.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.persistence.Temporal;

import org.junit.Test;

import com.oracle.domain.user;
import com.oracle.util.JDBCUtil;

public class userDao extends BaseDao{
	public user login(String name ,String pw){
		getConn();
		String sql ="select id,name,password from users where name=? and password=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, pw);
			rs = ps.executeQuery();
			while(rs.next()){
				user user = new user();
				user.setId(rs.getString(1));
				user.setName(rs.getString(2));
				user.setPassword(rs.getString(3));
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeaAll();
		}
	
		return null;
		
	}
	
}
