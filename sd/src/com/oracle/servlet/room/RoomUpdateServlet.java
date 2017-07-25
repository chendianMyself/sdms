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

public class RoomUpdateServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str = request.getParameter("id");
		if(str!=null){
			int id = Integer.parseInt(str);
			Room room = new RoomDao().findById(id);
			DeptDao deptDao = new DeptDao();
			Dept dept = deptDao.findById(room.getDept().getId());
			
			request.setAttribute("dept", dept);
			request.setAttribute("room", room);
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
		
		request.getRequestDispatcher("/WEB-INF/jsp/room/roomUpdate2.jsp").forward(request, response);
		
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String roomNo=request.getParameter("roomNo");
		String remark=request.getParameter("remark");
		String deptId = request.getParameter("deptId");
		String floors = request.getParameter("floors");
		String id = request.getParameter("id");
		
		Room room = new Room();
		room.setId(Integer.parseInt(id));
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
		roomDao.roomUpdate(room);
		response.sendRedirect("room.do");
	}
}