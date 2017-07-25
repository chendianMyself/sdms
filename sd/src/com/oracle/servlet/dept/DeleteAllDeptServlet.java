package com.oracle.servlet.dept;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.dao.DeptDao;

public class DeleteAllDeptServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String ids=req.getParameter("ids");
		System.out.println(ids);
		new DeptDao().deleteAll(ids);
		
		resp.sendRedirect("dept.do");
	}
	
}
