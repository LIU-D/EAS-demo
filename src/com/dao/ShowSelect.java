package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mod.CourseType;
import com.mod.Inst;
import com.mod.Staffroom;
import com.mysql.jdbc.Connection;

public class ShowSelect {
	DBC DBC = new DBC();
	
	public List<Inst> getInst() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		List<Inst> instList = new ArrayList<Inst>();
		Connection con = (Connection) DBC.getCon();
		String sql_1 = "select * from inst";
		Statement st_1 = (Statement) con.createStatement();
		ResultSet rs_1 = st_1.executeQuery(sql_1);
		while (rs_1.next()) {// 判断是否还有下一个数据
			Inst inst = new Inst();
			inst.setInstid(rs_1.getInt("instid"));
			inst.setInstname(rs_1.getString("instname"));
			instList.add(inst);
		}
		DBC.closeAll();
		return instList;
	}
	
	public List<Staffroom> getStaffroom() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
		List<Staffroom> staffroomList = new ArrayList<Staffroom>();
		Connection con = (Connection) DBC.getCon();
		String sql_1 = "select * from staffroom";
		Statement st_1 = (Statement) con.createStatement();
		ResultSet rs_1 = st_1.executeQuery(sql_1);
		while (rs_1.next()) {// 判断是否还有下一个数据
			Staffroom staffroom = new Staffroom();
			staffroom.setStaffroomid(rs_1.getInt("staffroomid"));
			staffroom.setStaffroomname(rs_1.getString("staffroomname"));
			staffroomList.add(staffroom);
		}
		DBC.closeAll();
		return staffroomList;
	}
	
	public List<CourseType> getCourseType() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
		List<CourseType> typeList = new ArrayList<CourseType>();
		Connection con = (Connection) DBC.getCon();
		String sql_1 = "select * from coursetype";
		Statement st_1 = (Statement) con.createStatement();
		ResultSet rs_1 = st_1.executeQuery(sql_1);
		while (rs_1.next()) {// 判断是否还有下一个数据
			CourseType c = new CourseType();
			c.setCoursetypeid(rs_1.getInt("coursetypeid"));
			c.setCoursetype(rs_1.getString("coursetype"));
			typeList.add(c);
		}
		DBC.closeAll();
		return typeList;
	}

}
