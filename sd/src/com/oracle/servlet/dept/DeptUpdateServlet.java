package com.oracle.servlet.dept;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.dao.DeptDao;
import com.oracle.domain.Dept;

public class DeptUpdateServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str = request.getParameter("id");
		if(str!=null){
			int id = Integer.parseInt(str);
			Dept dept = new DeptDao().findById(id);
			request.setAttribute("dept", dept);
		}
		request.getRequestDispatcher("/WEB-INF/jsp/dept/deptUpdate.jsp").forward(request, response);
		
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str = request.getParameter("id");
		String deptNo = request.getParameter("deptNo");
		String remark = request.getParameter("remark");
		String f = request.getParameter("floors");
		int floors=0;
		if(f!=null){
			floors=Integer.parseInt(f);
		}
		if(str!=null ){
			Dept dept = new Dept();
			dept.setDeptNo(deptNo);
			dept.setRemark(remark);
			dept.setFloors(floors);
			dept.setId(Integer.parseInt(str));
			new DeptDao().deptUpdate(dept);
			
		}
		
		response.sendRedirect("dept.do");
	}
}