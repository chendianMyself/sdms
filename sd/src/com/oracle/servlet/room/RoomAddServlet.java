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

public class RoomAddServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get请求打开页面
		List<Dept> deptList=new DeptDao().getAll();
		request.setAttribute("deptList", deptList);
		
		request.getRequestDispatcher("/WEB-INF/jsp/room/roomAdd.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String roomNo=request.getParameter("roomNo");
		String remark=request.getParameter("remark");
		String deptId = request.getParameter("deptId");
		String floors = request.getParameter("floors");
		
		Room room = new Room();
		room.setRemark(remark);
		room.setRoomNo(roomNo);
		if(floors!=null){
			
			room.setFloors(Integer.parseInt(floors));
		}
		if(deptId!=null){
			Dept dept = new Dept();
			dept.setId(Integer.parseInt(deptId));
			room.setDept(dept);
		}
		RoomDao roomDao = new RoomDao();
		roomDao.save(room);
		response.sendRedirect("room.do?deptId="+deptId+"&floors="+floors+"");
	}
}