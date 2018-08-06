package com.mod;

public class Student {
	private String studentid;
	private String studentname;
	private int instid;
	private String instname;
	private int majorid;
	private String majorname;
	private int classid;
	private String classname;
	
	
	public String getStudentid() {
		return studentid;
	}
	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}
	public String getStudentname() {
		return studentname;
	}
	public void setStudentname(String studentname) {
		this.studentname = studentname;
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
	public int getMajorid() {
		return majorid;
	}
	public void setMajorid(int majorid) {
		this.majorid = majorid;
	}
	public String getMajorname() {
		return majorname;
	}
	public void setMajorname(String majorname) {
		this.majorname = majorname;
	}
	public int getClassid() {
		return classid;
	}
	public void setClassid(int classid) {
		this.classid = classid;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	@Override
	public String toString() {
		return "Student [studentid=" + studentid + ", studentname=" + studentname + ", instid=" + instid + ", instname="
				+ instname + ", majorid=" + majorid + ", majorname=" + majorname + ", classid=" + classid
				+ ", classname=" + classname + "]";
	}
	
	
}
