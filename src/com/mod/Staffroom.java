package com.mod;

public class Staffroom {
	private int staffroomid;
	private String staffroomname;
	public int getStaffroomid() {
		return staffroomid;
	}
	public void setStaffroomid(int staffroomid) {
		this.staffroomid = staffroomid;
	}
	public String getStaffroomname() {
		return staffroomname;
	}
	public void setStaffroomname(String staffroomname) {
		this.staffroomname = staffroomname;
	}
	@Override
	public String toString() {
		return "Staffroom [staffroomid=" + staffroomid + ", staffroomname=" + staffroomname + "]";
	}
	

}
