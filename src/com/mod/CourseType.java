package com.mod;

public class CourseType {
	private int coursetypeid;
	private String coursetype;
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
		return "CourseType [coursetypeid=" + coursetypeid + ", coursetype=" + coursetype + "]";
	}
	
	

}
