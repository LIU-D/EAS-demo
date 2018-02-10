package com.mod;

public class Inst {
	private int instid;
	private String instname;
	
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
		return "Inst [instid=" + instid + ", instname=" + instname + "]";
	}
	
	
	

}
