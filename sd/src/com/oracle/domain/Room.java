package com.oracle.domain;

public class Room {

	private Integer id;
	
	//多对一关联
	private Dept dept;
	
	
	@Override
	public String toString() {
		return "Room [id=" + id + ", dept=" + dept + ", roomNo=" + roomNo + ", remark=" + remark + ", flag=" + flag
				+ ", floors=" + floors + "]";
	}
	private String roomNo;
	private String remark;
	private int flag=0;
	private int floors=1;
	
	public int getFloors() {
		return floors;
	}
	public void setFloors(int floors) {
		this.floors = floors;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
}
