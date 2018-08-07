package com.mod;

public class Major {
	private int majorid;
	private String majorname;
	private int instid;
	private String instname;
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
		return "Major [majorid=" + majorid + ", majorname=" + majorname + ", instid=" + instid + ", instname="
				+ instname + "]";
	}
	
	
	
}
