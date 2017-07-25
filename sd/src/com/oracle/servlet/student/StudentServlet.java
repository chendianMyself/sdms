package com.oracle.servlet.student;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.dao.DeptDao;
import com.oracle.dao.RoomDao;
import com.oracle.domain.Dept;
import com.oracle.domain.Room;
import com.oracle.domain.Student;
import com.oracle.service.StudentService;

public class StudentServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String deptId=request.getParameter("deptId");
		String roomId=request.getParameter("roomId");
		String name=request.getParameter("name");
		String no=request.getParameter("no");
		String floors=request.getParameter("floors");
		
		
		List<Dept> deptList=new DeptDao().getAll();
		request.setAttribute("deptList", deptList);
		StudentService studentService = new StudentService();
		List<Student> list = studentService.findAll(deptId, roomId, name, no);
		
		request.setAttribute("list", list);
		request.setAttribute("deptId", deptId);
		request.setAttribute("floors", floors);
		request.setAttribute("roomId", roomId);
		request.setAttribute("name", name);
		request.setAttribute("no", no);
	
		
		
		//list2为jquery版本
		request.getRequestDispatcher("/WEB-INF/jsp/student/list2.jsp").forward(request, response);
	}
}
