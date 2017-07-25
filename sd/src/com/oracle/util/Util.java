package com.oracle.util;

import java.io.IOException;
import java.util.Properties;

public class Util {
	public static String driver=null;
	public static String url=null;
	public static String user=null;
	public static String ps=null;
	static{
		Properties p =new Properties();
		try {
			p.load(Util.class.getResourceAsStream("/jdbc.properties"));
			driver = p.getProperty("driver");
			url = p.getProperty("url");
			user= p.getProperty("username");
			ps = p.getProperty("password");
					
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*public static void main(String[] args) {
		System.out.println(url);
	}*/
}
