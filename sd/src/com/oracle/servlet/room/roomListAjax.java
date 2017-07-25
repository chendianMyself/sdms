package com.oracle.servlet.room;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.oracle.dao.RoomDao;
import com.oracle.domain.Room;

public class roomListAjax extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String deptId=request.getParameter("deptId");
		String floors=request.getParameter("floors");
		
		int dId=0,f=0;
		if(deptId!=null){
			dId=Integer.parseInt(deptId);
		}
		if(floors!=null){
			f=Integer.parseInt(floors);
		}
		
		List<Room> list=new RoomDao().findListForAjax(dId, f);
		
		
		Gson gson=new Gson();
		String json=gson.toJson(list);
		
		response.getWriter().print(json);
		System.out.println(json);
		//JSON
		//[{id:3,roomNo:'201'},{id:4,roomNo:'203'}]
		
	}

	
}
