package com.oracle.servlet;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.dao.userDao;
import com.oracle.domain.user;

public class loginServlet extends HttpServlet{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/jsp/frame/login.html").forward(request, response);
		
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		userDao userdao = new userDao();
		String regex="\\w+";
		if(name.matches(regex) && password.matches(regex)){
		
		user user = userdao.login(name, password);
		if(user!=null){
			request.getSession().setAttribute("user", user);
			request.getRequestDispatcher("/WEB-INF/jsp/frame/main.html").forward(request, response);
			return;
			}
		}
			request.getRequestDispatcher("/WEB-INF/jsp/frame/login.html").forward(request, response);
	
	}
}
