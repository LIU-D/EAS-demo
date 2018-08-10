package com.mod;

public class Evaluation {
	private int evaluationid;
	private String content;
	private int coursetypeid;
	private String coursetype;
	public int getEvaluationid() {
		return evaluationid;
	}
	public void setEvaluationid(int evaluation) {
		this.evaluationid = evaluation;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
		return "Evaluation [evaluationid=" + evaluationid + ", content=" + content + ", coursetypeid=" + coursetypeid
				+ ", coursetype=" + coursetype + "]";
	}
	
	
	
}
