package com.mod;

public class Major {
	private int majorid;
	private String majorname;
	private int instid;
	
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
	
	@Override
	public String toString() {
		return "Major [majorid=" + majorid + ", majorname=" + majorname + ", instid=" + instid + "]";
	}
	
}
