package com.oracle.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.oracle.domain.Dept;
import com.oracle.domain.Room;
import com.oracle.domain.Student;

public class studentDao extends BaseDao{
	public List<Student> findAll(Student student){
		getConn();
		try {
			//查询全部内容
			String sql="select s.id,s.name,s.no,s.tel,d.id as deptId,d.dept_no as deptNo,r.id as roomId,r.room_no as roomNo "
					+" from student s left join dept d on s.dept_id=d.id left join  room r  on s.room_id=r.id"
					+" where s.flag=0 and s.name like concat('%',concat(?,'%')) and s.no like concat('%',concat(?,'%'))";
			if(student.getDept().getId()!=0)sql+= " and s.dept_id ="+student.getDept().getId();
			if(student.getRoom().getId()!=0)sql+= " and s.room_id ="+student.getRoom().getId();
			
			ps=conn.prepareStatement(sql);
			ps.setString(1, student.getName());
			ps.setString(2, student.getNo());
			
			
			rs=ps.executeQuery();
			List<Student> list=new ArrayList<Student>();
			while(rs.next()){
				Student stu=new Student();
				Dept dept=new Dept();
				Room room=new Room();
				
				stu.setId(rs.getInt(1));
				stu.setName(rs.getString(2));
				stu.setNo(rs.getString(3));
				stu.setTel(rs.getString(4));
				//查询dept的数据
				dept.setId(rs.getInt(5));
				dept.setDeptNo(rs.getString(6));
				//查询room的数据
				room.setId(rs.getInt(7));
				room.setRoomNo(rs.getString(8));
				//将dept和room信息赋予student,则student中包含全部数据
				stu.setDept(dept);
				stu.setRoom(room);
				
				list.add(stu);
			}
			return list;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeaAll();
		}
		return null;
	}

	public void studentDelete(int id) {
		getConn();
		try {
			//物理刪除
			//String sql ="delete from room where id=?";
			//邏輯刪除
			String sql ="update student set flag=1 where id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeaAll();
		}
		
		
		
		
	}
	
	
}
