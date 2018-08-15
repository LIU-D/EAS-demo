package com.mod;

public class Term {
	private int termid;
	private String termname;
	private String schoolyear;
	private String open;

	public int getTermid() {
		return termid;
	}
	public void setTermid(int termid) {
		this.termid = termid;
	}
	public String getTermname() {
		return termname;
	}
	public void setTermname(String termname) {
		this.termname = termname;
	}
	public String getSchoolyear() {
		return schoolyear;
	}
	public void setSchoolyear(String schoolyear) {
		this.schoolyear = schoolyear;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	@Override
	public String toString() {
		return "Term [termid=" + termid + ", termname=" + termname + ", schoolyear=" + schoolyear + ", open=" + open
				+ "]";
	}

	
	

}
