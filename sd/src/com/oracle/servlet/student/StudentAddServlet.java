package com.oracle.servlet.student;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.dao.DeptDao;
import com.oracle.domain.Dept;
import com.oracle.domain.Room;
import com.oracle.domain.Student;
import com.oracle.service.StudentService;

public class StudentAddServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get请求打开页面
		List<Dept> deptList=new DeptDao().getAll();
		request.setAttribute("deptList", deptList);
		String deptId=request.getParameter("deptId");
		String roomId=request.getParameter("roomId");
		String name=request.getParameter("name");
		String no=request.getParameter("no");
		String floors=request.getParameter("floors");
		
		
		StudentService studentService = new StudentService();
		List<Student> list = studentService.findAll(deptId, roomId, name, no);
		
		request.setAttribute("list", list);
		request.setAttribute("deptId", deptId);
		request.setAttribute("floors", floors);
		request.setAttribute("roomId", roomId);
	
		
		request.getRequestDispatcher("/WEB-INF/jsp/student/studentAdd.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("name");
		String no=request.getParameter("no");
		String tel = request.getParameter("tel");
		String f = request.getParameter("floors");
		String deptId = request.getParameter("deptId");
		
		
		Student student = new Student();
		Dept dept=new Dept();
		
		Room room=new Room();
		
		
		response.sendRedirect("student.do");
	}
}