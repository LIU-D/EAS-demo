package com.mod;

public class Classroom {
	private int instid;
	private String instname;
	private int classid;
	private String classname;
	private int year;
	private int majorid;
	private String majorname;
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
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
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
	@Override
	public String toString() {
		return "Classroom [instid=" + instid + ", instname=" + instname + ", classid=" + classid + ", classname="
				+ classname + ", year=" + year + ", majorid=" + majorid + "]";
	}
	
	
}
