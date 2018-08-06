package com.mod;

import java.util.List;

public class JsonSelect {
	public List<Inst> instList;
	public List<Staffroom> staffroomList;
	public List<CourseType> typeList;
	private List<Major> majorList;
	private List<Classroom> classList;
	
	public JsonSelect(List<Inst> instList, List<Staffroom> staffroomList, List<CourseType> typeList, List<Major> majorList, List<Classroom> classList) {
		super();
		this.instList = instList;
		this.staffroomList = staffroomList;
		this.typeList = typeList;
		this.majorList = majorList;
		this.classList = classList;
	}

	@Override
	public String toString() {
		return "JsonSelect [instList=" + instList + ", staffroomList=" + staffroomList + ", typeList=" + typeList
				+ ", majorList=" + majorList + ", classList=" + classList + "]";
	}


}
