package com.oracle.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.oracle.domain.Dept;
import com.oracle.util.ArrayUtil;

public class DeptDao extends BaseDao{
	//查詢所有
	public List<Dept> getAll(){
		getConn();
		String sql = "select id,dept_no as deptNo,remark,flag,floors from dept where flag=0";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			ArrayList list = new ArrayList();
			while(rs.next()){
				Dept dept = new Dept();
				dept.setId(rs.getInt(1));
				dept.setDeptNo(rs.getString(2));
				dept.setRemark(rs.getString(3));
				dept.setFlag(rs.getInt(4));
				dept.setFloors(rs.getInt(5));
				list.add(dept);
			}
			
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeaAll();
		}
		
		
		return null;
		
	}
	//添加
	public void save(Dept dept){
		getConn();
		try {
			String sql="insert into dept(dept_no,remark,flag,floors) values(?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, dept.getDeptNo());
			ps.setString(2, dept.getRemark());
			ps.setInt(3, dept.getFlag());
			ps.setInt(4, dept.getFloors());
			
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeaAll();
		}
		
	}
	//刪除
	public void deptDelete(int id){
		getConn();
		try {
			//物理刪除
			//String sql ="delete from dept where id=?";
			//邏輯刪除
			String sql ="update dept set flag=1 where id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeaAll();
		}
	
	}
	//刪除所有
	public void deleteAll(String ids){
		int[] result=ArrayUtil.string2intArray(ids);
		if(result.length==0)return;//如果没有要删除的内容,则不删除
		
		
		getConn();
		try {
			//物理删除
			//String sql="delete from dept where id = ?";
			//逻辑删除
			String sql="update dept set flag = 1 where id in (";
					for(int i=0;i<result.length;i++){
						sql+="?,";
					}
					sql=sql.substring(0,sql.length()-1);//删除最后一个逗号
					sql+=")";
				//	System.out.println(sql);
				//	System.out.println(result.length);
			ps=conn.prepareStatement(sql);
			for(int i=0;i<result.length;i++){
				ps.setInt(i+1, result[i]);
			}
			
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeaAll();
		}
	}
		
	
	//根據id查詢公寓信息
	public Dept findById(int id){
		String sql = "select id,dept_no as deptNo,remark,flag,floors from dept where id =?";
		getConn();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				Dept dept = new Dept();
				dept.setId(rs.getInt(1));
				dept.setDeptNo(rs.getString(2));
				dept.setRemark(rs.getString(3));
				dept.setFlag(rs.getInt(4));
				dept.setFloors(rs.getInt(5));
				return dept;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeaAll();
		}
		return null;
		
	}
	//更新公寓信息
	public void deptUpdate(Dept dept){
		getConn();
		try {
			//物理刪除
			String sql ="update dept set dept_no  = ? ,remark = ?,floors=? where id = ?";
			//邏輯刪除
			ps = conn.prepareStatement(sql);
			ps.setString(1, dept.getDeptNo());
			ps.setString(2, dept.getRemark());
			ps.setInt(3, dept.getFloors());
			ps.setInt(4, dept.getId());
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeaAll();
		}
		
		
		
	}

}
