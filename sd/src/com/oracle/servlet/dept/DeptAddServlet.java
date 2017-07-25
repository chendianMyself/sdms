package com.oracle.servlet.dept;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.dao.DeptDao;
import com.oracle.domain.Dept;

public class DeptAddServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get请求打开页面
		
		request.getRequestDispatcher("/WEB-INF/jsp/dept/deptAdd.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String deptNo=request.getParameter("deptNo");
		String remark=request.getParameter("remark");
		String floors=request.getParameter("floors");
		Dept dept=new Dept();
		dept.setDeptNo(deptNo);
		dept.setRemark(remark);
		if(floors!=null){
			
			dept.setFloors(Integer.parseInt(floors));
		}
		new DeptDao().save(dept);
		response.sendRedirect("dept.do");
	}
}