package com.mod;

public class Course {
	private int courseid;
	private String coursename;
	
	private int staffroomid;
	private String staffroomname;
	
	private int instid;
	private String instname;
	
	private int coursetypeid;
	private String coursetype;
	public int getCourseid() {
		return courseid;
	}
	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
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
	public int getCoursetypeid() {
		return coursetypeid;
	}
	public void setCoursetypeid(int coursetypeid) {
		this.coursetypeid = coursetypeid;
	}
	public String getCoursetype() {
		return coursetype;
	}
	public void setCoursetype(String coursetype) {
		this.coursetype = coursetype;
	}
	@Override
	public String toString() {
		return "Course [courseid=" + courseid + ", coursename=" + coursename + ", staffroomid=" + staffroomid
				+ ", staffroomname=" + staffroomname + ", instid=" + instid + ", instname=" + instname
				+ ", coursetypeid=" + coursetypeid + ", coursetype=" + coursetype + "]";
	}
	
	
	
	
	
	
	
}
