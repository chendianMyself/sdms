package com.oracle.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.oracle.domain.Dept;
import com.oracle.domain.Room;


public class RoomDao extends BaseDao{

	
	public List<Room> findAll(int deptId,int floors){
		getConn();
		try {
			//查询全部内容
			StringBuffer sql=new StringBuffer("select id,room_no as roomNo,remark,floors from room where flag= 0 ");
			if(deptId!=0)sql.append(" and dept_id = ?");
			if(floors!=0)sql.append(" and floors = ?");
			ps=conn.prepareStatement(sql.toString());
			
			if(deptId!=0)ps.setInt(1, deptId);
			if(floors!=0 && deptId!=0)ps.setInt(2, floors);
			else if(floors!=0 && deptId==0)ps.setInt(1, floors);
			
			rs=ps.executeQuery();
			List<Room> list=new ArrayList<Room>();
			while(rs.next()){
				Room room=new Room();
				room.setId(rs.getInt(1));
				room.setRoomNo(rs.getString(2));
				room.setRemark(rs.getString(3));
				room.setFloors(rs.getInt(4));
				list.add(room);
			}
			return list;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeaAll();
		}
		
		return null;
		
	}

	public void save(Room room) {
		getConn();
		try {
			String sql="insert into room(room_no,remark,dept_id,floors) values(?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, room.getRoomNo());
			ps.setString(2, room.getRemark());
			ps.setInt(3, room.getDept().getId());
			ps.setInt(4, room.getFloors());
			
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeaAll();
		}
	}

	public void roomDelete(int id) {
		getConn();
		try {
			//物理刪除
			//String sql ="delete from room where id=?";
			//邏輯刪除
			String sql ="update room set flag=1 where id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeaAll();
		}
		
		
		
	}

	public Room findById(int id) {
		String sql = "select id,dept_id ,remark,flag,floors ,room_no from room where id =?";
		getConn();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				Room room = new Room();
				Dept dept = new Dept();
				dept.setId(rs.getInt(2));
				room.setDept(dept);
				room.setId(rs.getInt(1));
				room.setRemark(rs.getString(3));
				room.setFlag(rs.getInt(4));
				room.setFloors(rs.getInt(5));
				room.setRoomNo(rs.getString(6));
				return room;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeaAll();
		}
		return null;
		
	}

	public void roomUpdate(Room room) {
		getConn();
		try {
			String sql ="update room set room_no  = ?, dept_id=?,remark = ?,floors=? where id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, room.getRoomNo());
			ps.setInt(2, room.getDept().getId());
			ps.setString(3, room.getRemark());
			ps.setInt(4, room.getFloors());
			ps.setInt(5, room.getId());
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeaAll();
		}
		
		
		
	}

	public List<Room> findListForAjax(int dId, int f) {
		getConn();
		List<Room> list=new ArrayList<Room>();
		try {
			String sql="select id,room_no as roomNo from room where flag = 0 and dept_id = ? and floors = ?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, dId);
			ps.setInt(2, f);
			rs=ps.executeQuery();
			
			while(rs.next()){
				Room room=new Room();
				room.setId(rs.getInt(1));
				room.setRoomNo(rs.getString(2));
				
				list.add(room);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeaAll();
		}
		System.out.println(list);
		return list;
	}
}
