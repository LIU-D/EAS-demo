package com.mod;

import java.util.List;

public class JsonSelect {
	public List<Inst> instList;
	public List<Staffroom> staffroomList;
	public List<CourseType> typeList;
	
	public JsonSelect(List<Inst> instList, List<Staffroom> staffroomList, List<CourseType> typeList) {
		super();
		this.instList = instList;
		this.staffroomList = staffroomList;
		this.typeList = typeList;
	}

	@Override
	public String toString() {
		return "JsonSelect [instList=" + instList + ", staffroomList=" + staffroomList + ", typeList=" + typeList + "]";
	}
	
	
}
