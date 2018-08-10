package com.mod;

public class Teacher {
	private int teacherid;
	private String teachername;
	private int staffroomid;
	private String staffroomname;
	private int instid;
	private String instname;
	
	public int getTeacherid() {
		return teacherid;
	}
	public void setTeacherid(int teacherid) {
		this.teacherid = teacherid;
	}
	public String getTeachername() {
		return teachername;
	}
	public void setTeachername(String teachername) {
		this.teachername = teachername;
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
	@Override
	public String toString() {
		return "Teacher [teacherid=" + teacherid + ", teachername=" + teachername + ", staffroomid=" + staffroomid
				+ ", staffroomname=" + staffroomname + ", instid=" + instid + ", instname=" + instname + "]";
	}
	
	

}
