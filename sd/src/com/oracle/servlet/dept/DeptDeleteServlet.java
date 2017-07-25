package com.oracle.servlet.dept;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.dao.DeptDao;
import com.oracle.domain.Dept;

public class DeptDeleteServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str = request.getParameter("id");
		if(str!=null){
			int id = Integer.parseInt(str);
			new DeptDao().deptDelete(id);
		}
		response.sendRedirect("dept.do");
	}

}