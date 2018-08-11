package com.mod;

public class Term {
	private int id;
	private String term;
	private String schoolyear;
	private String open;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
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
		return "Term [id=" + id + ", term=" + term + ", schoolyear=" + schoolyear + ", open=" + open + "]";
	}
	
	

}
