package com.oracle.service;

import java.util.List;

import com.oracle.dao.studentDao;
import com.oracle.domain.Dept;
import com.oracle.domain.Room;
import com.oracle.domain.Student;

public class StudentService {
	public List<Student> findAll(String deptId,String roomId,String name,String no){
		int dId=0,rId=0;
		if(deptId!=null){
			dId=Integer.parseInt(deptId);
		}
		if(roomId!=null){
			rId=Integer.parseInt(roomId);
		}
		if(name==null){
			name="";
		}
		if(no==null){
			no="";
		}
		Student student =new Student();
		
		Dept dept=new Dept();
		dept.setId(dId);
		
		Room room=new Room();
		room.setId(rId);
		
		
		student.setDept(dept);
		student.setRoom(room);
		student.setName(name);
		student.setNo(no);
		
		List<Student> list=new studentDao().findAll(student);
		return list;
		
	}

}
