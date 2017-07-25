package com.oracle.servlet.student;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.dao.DeptDao;
import com.oracle.domain.Dept;
import com.oracle.domain.Student;
import com.oracle.service.StudentService;
import com.oracle.util.PageUtil;

public class StudentServlet extends HttpServlet{
	public static final int pageSize=1;
	
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
		
		
		//当前页
				int currentPage=1;
				String currentPageStr=request.getParameter("currentPage");
				if(currentPageStr!=null)currentPage=Integer.parseInt(currentPageStr);
				//总数量
				int maxSize=new StudentService().countAll(deptId, roomId, name, no);
				//总页数
				int maxPage=PageUtil.getMaxPage(maxSize, pageSize);
				//页容量pageSize
				
				//对当前页的取值范围进行限定
				//如果输入的页码小于1,则返回第一页,如果输入的页码大于最后一页,则返回最后一页
				if(currentPage<1)currentPage=1;
				if(currentPage>maxPage)currentPage=maxPage;
				
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("maxSize", maxSize);
				request.setAttribute("maxPage", maxPage);
				request.setAttribute("pageSize", pageSize);
				
				int begin=(currentPage-1)*pageSize;
				int length=pageSize;
		
				List<Student> list = studentService.findAll(deptId, roomId, name, no,begin,pageSize);
		
		
		
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
