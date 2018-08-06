package com.mod;

public class Classroom {
	private int classid;
	private String classname;
	private int year;
	private int majorid;
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
	@Override
	public String toString() {
		return "Classroom [classid=" + classid + ", classname=" + classname + ", year=" + year + ", majorid=" + majorid
				+ "]";
	}
	
	

}
