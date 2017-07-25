package com.oracle.servlet.room;

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
import com.oracle.domain.user;

public class RoomServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		user user=(user) request.getSession().getAttribute("user");
		if(user==null){
			//如果用户已经注销,则返回登录页面
			request.getRequestDispatcher("/WEB-INF/jsp/frame/noPurview.jsp").forward(request, response);;
			return;
		}
		
		
		String deptStr=request.getParameter("deptId");
		String floorsStr=request.getParameter("floors");
		
		//默认值
		int deptId=0,floors=0;
		if(deptStr!=null){
			deptId=Integer.parseInt(deptStr);
		}
		if(floorsStr!=null){
			floors=Integer.parseInt(floorsStr);
		}
		
		List<Room> roomList=new RoomDao().findAll(deptId, floors);
		request.setAttribute("roomList", roomList);
		
		
		List<Dept> deptList=new DeptDao().getAll();
		request.setAttribute("deptList", deptList);
		
		request.setAttribute("deptId", deptId);
		request.setAttribute("floors", floors);
		
		
		request.getRequestDispatcher("/WEB-INF/jsp/room/list.jsp").forward(request, response);
		
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
