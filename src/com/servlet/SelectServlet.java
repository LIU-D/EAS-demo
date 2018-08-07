package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DBC;
import com.google.gson.Gson;
import com.mod.Classroom;
import com.mod.CourseType;
import com.mod.Inst;
import com.mod.JsonSelect;
import com.mod.Major;
import com.mod.Staffroom;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class SelectServlet
 */
@WebServlet("/SelectServlet")
public class SelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBC DBC = new DBC();
		String select = request.getParameter("select");
		System.out.println("select=" + select);
		
		if(select.equals("0")) {
			List<Inst> instList = new ArrayList<Inst>();
			try {
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
				Gson gson = new Gson();
				String json_list = gson.toJson(instList);
				response.setHeader("Cache-Control", "no-cache");//去除缓存
				response.setContentType("application/json;charset=utf-8");
				PrintWriter out = response.getWriter();
			    out.print(json_list);
			    out.flush();
				out.close();
				DBC.closeAll();
				
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if(select.equals("3")) {
			List<Inst> instList = new ArrayList<Inst>();
			List<Staffroom> staffroomList = new ArrayList<Staffroom>();
			List<CourseType> typeList = new ArrayList<CourseType>();
			List<Major> majorList = new ArrayList<Major>();
			List<Classroom> classList = new ArrayList<Classroom>();
			try {
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
				
				String sql_2 = "select * from staffroom";
				ResultSet rs_2 = st_1.executeQuery(sql_2);
				while (rs_2.next()) {// 判断是否还有下一个数据
					Staffroom staffroom = new Staffroom();
					staffroom.setStaffroomid(rs_2.getInt("staffroomid"));
					staffroom.setStaffroomname(rs_2.getString("staffroomname"));
					staffroomList.add(staffroom);
				}
				
				String sql_3= "select * from coursetype";
				ResultSet rs_3 = st_1.executeQuery(sql_3);
				while (rs_3.next()) {// 判断是否还有下一个数据
					CourseType c = new CourseType();
					c.setCoursetypeid(rs_3.getInt("coursetypeid"));
					c.setCoursetype(rs_3.getString("coursetype"));
					typeList.add(c);
				}
				
				String sql_4= "select * from major";
				ResultSet rs_4 = st_1.executeQuery(sql_4);
				while (rs_4.next()) {// 判断是否还有下一个数据
					Major m = new Major();
					m.setMajorid(rs_4.getInt("majorid"));
					m.setMajorname(rs_4.getString("majorname"));
					majorList.add(m);
				}
				
				String sql_5= "select * from classroom";
				ResultSet rs_5 = st_1.executeQuery(sql_5);
				while (rs_5.next()) {// 判断是否还有下一个数据
					Classroom c = new Classroom();
					c.setClassid(rs_5.getInt("classid"));
					c.setClassname(rs_5.getString("classname"));
					classList.add(c);
				}
				JsonSelect jsonSelect = new JsonSelect(instList,staffroomList,typeList,majorList,classList);
				
				Gson gson = new Gson();
				String json_list = gson.toJson(jsonSelect);
				response.setHeader("Cache-Control", "no-cache");//去除缓存
				response.setContentType("application/json;charset=utf-8");
				PrintWriter out = response.getWriter();
			    out.print(json_list);
			    out.flush();
				out.close();
				DBC.closeAll();
				
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if(select.equals("1")) {
			try {
				List<Staffroom> staffroomList = new ArrayList<Staffroom>();
				DBC.getCon();
				String sql_2 = "select * from staffroom where instid = ?";
				String[] param = { request.getParameter("instid") };
				ResultSet rs_2 = DBC.executeQuery(sql_2, param);
				while (rs_2.next()) {// 判断是否还有下一个数据
					Staffroom staffroom = new Staffroom();
					staffroom.setStaffroomid(rs_2.getInt("staffroomid"));
					staffroom.setStaffroomname(rs_2.getString("staffroomname"));
					staffroomList.add(staffroom);
				}
				Gson gson = new Gson();
				String json_list = gson.toJson(staffroomList);
				response.setHeader("Cache-Control", "no-cache");//去除缓存
				response.setContentType("application/json;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print(json_list);
			    out.flush();
				out.close();
				DBC.closeAll();
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//select == 1
		
		if(select.equals("2")) {
			try {
				DBC.getCon();
				String sql = "select * from staffroom where staffroomid = ?";
				String[] param = { request.getParameter("staffroomid") };
				ResultSet rs = DBC.executeQuery(sql, param);
				String instid = rs.getString("instid");
				
				String sql_2 = "select * from inst where instid = ?";
				String[] param_2 = { instid };
				ResultSet rs_2 = DBC.executeQuery(sql_2, param_2);
				String instname = rs_2.getString("instname");
				Inst inst = new Inst();
				inst.setInstid(Integer.parseInt(instid));
				inst.setInstname(instname);
				Gson gson = new Gson();
				String inst_list= gson.toJson(inst);
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
			    out.print(inst_list);
				DBC.closeAll();
				
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//select == 2
		
		
		if(select.equals("changeMajor")) {
			try {
				List<Major> majorList = new ArrayList<Major>();
				DBC.getCon();
				String sql_2 = "select * from major where instid = ?";
				String[] param = { request.getParameter("instid") };
				ResultSet rs_2 = DBC.executeQuery(sql_2, param);
				while (rs_2.next()) {// 判断是否还有下一个数据
					Major major = new Major();
					major.setMajorid(rs_2.getInt("majorid"));
					major.setMajorname(rs_2.getString("majorname"));
					majorList.add(major);
				}
				Gson gson = new Gson();
				String json_list = gson.toJson(majorList);
				response.setHeader("Cache-Control", "no-cache");//去除缓存
				response.setContentType("application/json;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print(json_list);
			    out.flush();
				out.close();
				DBC.closeAll();
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//select == changeMajor
		
		if(select.equals("changeClassroom")) {
			System.out.println(request.getParameter("majorid"));
			try {
				List<Classroom> classList = new ArrayList<Classroom>();
				DBC.getCon();
				String sql_2 = "select * from classroom where majorid = ?";
				String[] param = { request.getParameter("majorid") };
				ResultSet rs_2 = DBC.executeQuery(sql_2, param);
				while (rs_2.next()) {// 判断是否还有下一个数据
					Classroom classroom = new Classroom();
					classroom.setClassid(rs_2.getInt("classid"));
					classroom.setClassname(rs_2.getString("classname"));
					classList.add(classroom);
				}
				Gson gson = new Gson();
				String json_list = gson.toJson(classList);
				response.setHeader("Cache-Control", "no-cache");//去除缓存
				response.setContentType("application/json;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print(json_list);
			    out.flush();
				out.close();
				DBC.closeAll();
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//select == changeClassroom
		
		if(select.equals("instid_changeClassroom")) {
			try {
				List<Classroom> classList = new ArrayList<Classroom>();
				DBC.getCon();
				String sql_2 = "select * from classroom,major where classroom.majorid=major.majorid and major.instid = ?";
				String[] param = { request.getParameter("instid") };
				ResultSet rs_2 = DBC.executeQuery(sql_2, param);
				while (rs_2.next()) {// 判断是否还有下一个数据
					Classroom classroom = new Classroom();
					classroom.setClassid(rs_2.getInt("classid"));
					classroom.setClassname(rs_2.getString("classname"));
					classList.add(classroom);
				}
				Gson gson = new Gson();
				String json_list = gson.toJson(classList);
				response.setHeader("Cache-Control", "no-cache");//去除缓存
				response.setContentType("application/json;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print(json_list);
			    out.flush();
				out.close();
				DBC.closeAll();
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//select == instid_changeClassroom
		
		
		doGet(request, response);
	}

}
