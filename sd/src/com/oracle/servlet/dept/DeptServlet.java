package com.oracle.servlet.dept;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.dao.DeptDao;
import com.oracle.domain.Dept;

public class DeptServlet extends HttpServlet{
		@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DeptDao deptDao = new DeptDao();
		List<Dept> list = deptDao.getAll();
		request.setAttribute("list",list);
		System.out.println(list);
		request.getRequestDispatcher("/WEB-INF/jsp/dept/list.jsp").forward(request, response);
		
	}	

}
