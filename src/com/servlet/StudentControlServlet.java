package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DBC;
import com.google.gson.Gson;
import com.mod.Course;
import com.mod.Major;
import com.mod.Student;
import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class StudentControlServlet
 */
@WebServlet("/StudentControlServlet")
public class StudentControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentControlServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String flag = request.getParameter("flag");
		DBC DBC = new DBC();
		// 查看flag值
		System.out.println("flag:" + flag);
		if (flag.equals(null)) {
			System.err.println("flag无值！");
		} // if(!null)
		else {
			// 加载学生信息
			if (flag.equals("loading_student")) {
				try {
					List<Student> studentList = new ArrayList<Student>();
					Connection con = (Connection) DBC.getCon();
					String sql = "select * from student,inst,major,classroom where "
							+ "student.classid = classroom.classid and classroom.majorid = major.majorid and major.instid=inst.instid";
					Statement st = (Statement) con.createStatement();
					ResultSet rs = st.executeQuery(sql);
					while (rs.next()) {// 判断是否还有下一个数据
						Student student = new Student();
						student.setStudentid(rs.getString("studentid"));
						student.setStudentname(rs.getString("studentname"));
						student.setInstid(rs.getInt("instid"));
						student.setInstname(rs.getString("instname"));
						student.setMajorid(rs.getInt("majorid"));
						student.setMajorname(rs.getString("majorname"));
						student.setClassid(rs.getInt("classid"));
						student.setClassname(rs.getString("classname"));
						studentList.add(student);
					}
					Gson gson = new Gson();
					String json_list = gson.toJson(studentList);
					response.setHeader("Cache-Control", "no-cache");// 去除缓存
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
			} // loading_student

			// 选择显示学生信息
			if (flag.equals("select_student")) {
				try {
					int count = 1;
					int flag_1 = 0, flag_2 = 0, flag_3 = 0;
					List<Student> studentList = new ArrayList<Student>();
					Connection con = (Connection) DBC.getCon();
					ResultSet rs = null;
					String sql = "select * from student,inst,major,classroom where "
							+ "student.classid = classroom.classid and classroom.majorid = major.majorid and major.instid=inst.instid";
					if (!request.getParameter("instid").equals("all")) {
						sql += " and inst.instid = ?";
						++flag_1;
						++count;
					}
					if (!request.getParameter("majorid").equals("all")) {
						sql += " and major.majorid = ?";
						++flag_2;
						++count;
					}
					if (!request.getParameter("classid").equals("all")) {
						sql += " and classroom.classid = ?";
						++flag_3;
					}
					System.out.println(sql);
					if (count == 1 && flag_3 == 0) {
						Statement st = (Statement) con.createStatement();
						rs = st.executeQuery(sql);
					} else {
						PreparedStatement ps = con.prepareStatement(sql);
						if (flag_1 != 0)
							ps.setString(flag_1, request.getParameter("instid"));
						if (flag_2 != 0)
							ps.setString(count - flag_2, request.getParameter("majorid"));
						if (flag_3 != 0)
							ps.setString(count, request.getParameter("classid"));
						rs = ps.executeQuery();
					}
					while (rs.next()) {// 判断是否还有下一个数据
						Student student = new Student();
						student.setStudentid(rs.getString("studentid"));
						student.setStudentname(rs.getString("studentname"));
						student.setInstid(rs.getInt("instid"));
						student.setInstname(rs.getString("instname"));
						student.setMajorid(rs.getInt("majorid"));
						student.setMajorname(rs.getString("majorname"));
						student.setClassid(rs.getInt("classid"));
						student.setClassname(rs.getString("classname"));
						studentList.add(student);
					}
					Gson gson = new Gson();
					String json_list = gson.toJson(studentList);
					response.setHeader("Cache-Control", "no-cache");// 去除缓存
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
			} // select_student

			// 加载专业信息
			if (flag.equals("loading_major")) {
				try {
					List<Major> majorList = new ArrayList<Major>();
					Connection con = (Connection) DBC.getCon();
					String sql = "select * from inst,major where major.instid=inst.instid";
					Statement st = (Statement) con.createStatement();
					ResultSet rs = st.executeQuery(sql);
					while (rs.next()) {// 判断是否还有下一个数据
						Major major = new Major();
						major.setInstid(rs.getInt("instid"));
						major.setInstname(rs.getString("instname"));
						major.setMajorid(rs.getInt("majorid"));
						major.setMajorname(rs.getString("majorname"));
						majorList.add(major);
					}
					Gson gson = new Gson();
					String json_list = gson.toJson(majorList);
					response.setHeader("Cache-Control", "no-cache");// 去除缓存
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
			} // loading_major

			// 增加专业
			if (flag.equals("add_major")) {
				try {
					DBC.getCon();
					String sql = "insert into major(majorid,majorname,instid)values(?,?,?)";
					String[] param = { request.getParameter("majorid"), request.getParameter("majorname"),
							request.getParameter("instid") };

					System.out.println(request.getParameter("majorname"));
					DBC.executeUpdate(sql, param);
					DBC.closeAll();
					response.sendRedirect("Major.jsp");
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} // add_major

			// 选择显示专业信息
			if (flag.equals("select_major")) {
				try {
					List<Major> majorList = new ArrayList<Major>();
					Connection con = (Connection) DBC.getCon();
					ResultSet rs = null;
					String sql = "select * from inst,major where major.instid=inst.instid and major.instid=?";
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setString(1, request.getParameter("instid"));
					rs = ps.executeQuery();
					while (rs.next()) {// 判断是否还有下一个数据
						Major major = new Major();
						major.setInstid(rs.getInt("instid"));
						major.setInstname(rs.getString("instname"));
						major.setMajorid(rs.getInt("majorid"));
						major.setMajorname(rs.getString("majorname"));
						majorList.add(major);
					}
					Gson gson = new Gson();
					String json_list = gson.toJson(majorList);
					response.setHeader("Cache-Control", "no-cache");// 去除缓存
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
			} // select_major

		} // else
	}

}
