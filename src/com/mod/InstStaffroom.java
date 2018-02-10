package com.mod;

public class InstStaffroom {
	private int instid;
	private String instname;
	private int staffroomid;
	private String staffroomname;
	public int getInstid() {
		return instid;
	}
	public void setInstid(int instid) {
		this.instid = instid;
	}
	public String getInstname() {
		return instname;
	}
	public void setInstname(String instname) {
		this.instname = instname;
	}
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
		return "InstStaffroom [instid=" + instid + ", instname=" + instname + ", staffroomid=" + staffroomid
				+ ", staffroomname=" + staffroomname + "]";
	}
	
	
}
