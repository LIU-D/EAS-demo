package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.mod.CourseType;
import com.mod.Inst;
import com.mod.InstStaffroom;
import com.mod.JsonSelect;
import com.mod.Staffroom;
import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class CourseControlServlet
 */
@WebServlet("/CourseControlServlet")
public class CourseControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CourseControlServlet() {
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
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		request.setCharacterEncoding("UTF-8");
		DBC DBC = new DBC();
		String flag = request.getParameter("flag");
		// 查看flag值
		System.out.println("get flag: " + flag);

		if (flag == null) {
			System.err.println("flag无值！");
		} else {

			// 获得所有数据
			if (flag.equals("get_course")) {
				try {
					List<Course> courseList = new ArrayList<Course>();

					List<Course> instList = new ArrayList<Course>();
					List<Course> staffroomList = new ArrayList<Course>();
					List<Course> coursetypeList = new ArrayList<Course>();

					Connection con = (Connection) DBC.getCon();
					String sql = "select * from course,inst,coursetype,staffroom where "
							+ "course.staffroomid = staffroom.staffroomid and staffroom.instid = inst.instid and course.coursetypeid=coursetype.coursetypeid";
					Statement st = (Statement) con.createStatement();
					ResultSet rs = st.executeQuery(sql);
					while (rs.next()) {// 判断是否还有下一个数据
						Course course = new Course();
						course.setCourseid(rs.getInt("courseid"));
						course.setCoursename(rs.getString("coursename"));
						course.setStaffroomid(rs.getInt("staffroomid"));
						course.setStaffroomname(rs.getString("staffroomname"));
						course.setInstid(rs.getInt("instid"));
						course.setInstname(rs.getString("instname"));
						course.setCoursetypeid(rs.getInt("coursetypeid"));
						;
						course.setCoursetype(rs.getString("coursetype"));
						courseList.add(course);
					}
					request.setAttribute("C_list", courseList);
					// Gson gson = new Gson();
					// String C_list = gson.toJson(courseList);
					System.out.println(courseList);
					// response.setCharacterEncoding("UTF-8");
					// PrintWriter out = response.getWriter();
					// out.print(C_list);

					DBC.closeAll();
					request.getRequestDispatcher("Course.jsp").forward(request, response);

				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} // flag ==get_course

			if (flag.equals("get_staffroom")) {
				try {
					List<InstStaffroom> List = new ArrayList<InstStaffroom>();
					Connection con = (Connection) DBC.getCon();
					String sql_1 = "select * from staffroom";
					Statement st_1 = (Statement) con.createStatement();
					ResultSet rs_1 = st_1.executeQuery(sql_1);
					while (rs_1.next()) {// 判断是否还有下一个数据
						InstStaffroom item = new InstStaffroom();
						Staffroom staffroom = new Staffroom();
						item.setInstid(rs_1.getInt("instid"));
						item.setStaffroomid(rs_1.getInt("staffroomid"));
						item.setStaffroomname(rs_1.getString("staffroomname"));
						List.add(item);
					}
					for (int i = 0; i < List.size(); i++) {
						String sql_2 = "select * from inst";
						ResultSet rs_2 = st_1.executeQuery(sql_2);
						while (rs_2.next()) {// 判断是否还有下一个数据
							if (rs_2.getInt("instid") == List.get(i).getInstid()) {
								List.get(i).setInstname(rs_2.getString("instname"));
							}
						}
					}
					request.setAttribute("list", List);
					DBC.closeAll();
					request.getRequestDispatcher("Staffroom.jsp").forward(request, response);

				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} // flag == get_staffroom
			
			
			if (flag.equals("select_staffroom")) {
				try {
					List<InstStaffroom> List = new ArrayList<InstStaffroom>();
					DBC.getCon();
					String sql_1 = "select * from staffroom where instid = ?";
					String[] param = { request.getParameter("instid") };
					ResultSet rs_1 = DBC.executeQuery(sql_1,param);
					while (rs_1.next()) {// 判断是否还有下一个数据
						InstStaffroom item = new InstStaffroom();
						Staffroom staffroom = new Staffroom();
						item.setInstid(rs_1.getInt("instid"));
						item.setStaffroomid(rs_1.getInt("staffroomid"));
						item.setStaffroomname(rs_1.getString("staffroomname"));
						List.add(item);
					}
					for (int i = 0; i < List.size(); i++) {
						String sql_2 = "select * from inst";
						Connection con = (Connection) DBC.getCon();
						Statement st = (Statement) con.createStatement();
						ResultSet rs_2 = st.executeQuery(sql_2);
						while (rs_2.next()) {// 判断是否还有下一个数据
							if (rs_2.getInt("instid") == List.get(i).getInstid()) {
								List.get(i).setInstname(rs_2.getString("instname"));
							}
						}
					}
						
					Gson gson = new Gson();
					String json_list = gson.toJson(List);
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

			} // flag == select_staffroom

			if (flag.equals("get_inst")) {
				try {
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
					request.setAttribute("list", instList);
					DBC.closeAll();
					request.getRequestDispatcher("Inst.jsp").forward(request, response);

				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} // flag == get_inst

			// 删除学院
			if (flag.equals("delete_inst")) {
				try {
					DBC.getCon();
					String sql = "delete from inst where instid= ?";
					String[] param = { request.getParameter("instid"), };
					DBC.executeUpdate(sql, param);
					DBC.closeAll();
					response.sendRedirect("Inst.jsp");

				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} // flag=delete_inst

			// 删除课程
			if (flag.equals("delete_course")) {
				try {
					DBC.getCon();
					String sql = "delete from course where courseid= ?";
					String[] param = { request.getParameter("courseid"), };
					DBC.executeUpdate(sql, param);
					DBC.closeAll();
					response.sendRedirect("Course.jsp");

				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} // flag=delete_course

		} // flag!=null

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);

		request.setCharacterEncoding("UTF-8");
		DBC DBC = new DBC();
		String flag = request.getParameter("flag");
		// 查看flag值
		System.out.println("flag:" + flag);
		if (flag.equals(null)) {
			System.err.println("flag无值！");
		} // if(!null)

		else {
			// 增加学院
			if (flag.equals("add_inst")) {
				try {
					String sql = "insert into inst(instid,instname)values(?,?)";
					String instidflag = null;
					Boolean idflag = false;
					Connection con = (Connection) DBC.getCon();
					String sql_1 = "select * from inst";
					Statement st_1 = (Statement) con.createStatement();
					ResultSet rs_1 = st_1.executeQuery(sql_1);
					while (rs_1.next()) {// 判断是否还有下一个数据
						if (request.getParameter("instid").equals(rs_1.getString("instid")))
							idflag = true;
					}
					if (idflag) {
						DBC.closeAll();
						request.setAttribute("IDalreadyexists", instidflag);
						request.getRequestDispatcher("Inst.jsp").forward(request, response);
					} else {
						String[] param = { request.getParameter("instid"), request.getParameter("instname") };
						DBC.executeUpdate(sql, param);
						DBC.closeAll();
						response.sendRedirect("Inst.jsp");
					}
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			// 修改学院
			if (flag.equals("update_inst")) {
				try {
					DBC.getCon();
					String sql = "update inst set instname = ? where instid = ?";
					String[] param = { request.getParameter("instname"), request.getParameter("instid"), };
					System.out.println(param[0]);
					System.out.println(param[1]);
					DBC.executeUpdate(sql, param);

					DBC.closeAll();
					response.sendRedirect("Inst.jsp");
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			// 增加课程
			if (flag.equals("add_course")) {
				try {
					DBC.getCon();
					String sql = "insert into course(courseid,coursename,staffroomid,coursetypeid)values(?,?,?,?)";
					String[] param = { request.getParameter("courseid"), request.getParameter("coursename"),
							request.getParameter("staffroomid"), request.getParameter("coursetypeid") };
					DBC.executeUpdate(sql, param);
					DBC.closeAll();
					response.sendRedirect("Course.jsp");
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			// 修改课程
			if (flag.equals("update_course")) {
				try {
					DBC.getCon();
					String sql = "update course set coursename = ? where courseid = ?";
					String[] param = { request.getParameter("coursename"), request.getParameter("courseid"), };
					System.out.println(param[0]);
					System.out.println(param[1]);
					DBC.executeUpdate(sql, param);

					DBC.closeAll();
					response.sendRedirect("Course.jsp");
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			// 修改教研室
			if (flag.equals("update_staffroom")) {
				try {
					DBC.getCon();
					String sql = "update staffroom set staffroomname = ?,instid = ? where staffroomid = ?";
					String[] param = { request.getParameter("staffroomname"),request.getParameter("instid"),request.getParameter("staffroomid")};
					System.out.println(param[0]);
					System.out.println(param[1]);
					DBC.executeUpdate(sql, param);
					DBC.closeAll();
					response.sendRedirect("Staffroom.jsp");
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			// 按课程代码查询课程
			if (flag.equals("query")) {
				try {
					List<Course> courseList = new ArrayList<Course>();
					DBC.getCon();
					String sql = "select * from course,inst,coursetype where course.instid = inst.instid "
							+ "and course.coursetypeid = coursetype.coursetypeid and courseid = ?";
					String[] param = { request.getParameter("courseid"), };
					ResultSet rs = DBC.executeQuery(sql, param);
					while (rs.next()) {// 判断是否还有下一个数据
						Course course = new Course();
						course.setCourseid(rs.getInt("courseid"));
						course.setCoursename(rs.getString("coursename"));
						course.setStaffroomid(rs.getInt("staffroomid"));
						course.setStaffroomname(rs.getString("staffroomname"));
						course.setInstid(rs.getInt("instid"));
						course.setInstname(rs.getString("instname"));
						course.setCoursetypeid(rs.getInt("coursetypeid"));
						;
						course.setCoursetype(rs.getString("coursetype"));
						courseList.add(course);
					}
					request.setAttribute("C_list", courseList);
					DBC.closeAll();
					request.getRequestDispatcher("Course.jsp").forward(request, response);

				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
					e.printStackTrace();
				}
			} // if(flag=query)

		} // else

		// String select = request.getParameter("select");
		// System.out.println("select:" + select);
		// 得到option数据
		// if(select.equals(null)) {
		// System.err.println("select无值！");
		// }
		// else {
		// if (select.equals("1")) {
		// try {
		// List<Inst> instList = new ArrayList<Inst>();
		// Connection con = (Connection) DBC.getCon();
		// String sql_1 = "select * from inst";
		// Statement st_1 = (Statement) con.createStatement();
		// ResultSet rs_1 = st_1.executeQuery(sql_1);
		// while (rs_1.next()) {// 判断是否还有下一个数据
		// Inst inst = new Inst();
		// inst.setInstid(rs_1.getInt("instid"));
		// inst.setInstname(rs_1.getString("instname"));
		// instList.add(inst);
		// }
		// Gson gson = new Gson();
		// String inst_list = gson.toJson(instList);
		// System.out.println(inst_list);
		// response.setCharacterEncoding("UTF-8");
		// PrintWriter out = response.getWriter();
		// out.print(inst_list);
		// DBC.closeAll();
		// } catch (ClassNotFoundException | InstantiationException |
		// IllegalAccessException | SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// } // select == 1
		//
		// if (select.equals("2")) {
		//
		// try {
		// List<Staffroom> staffroomList = new ArrayList<Staffroom>();
		// DBC.getCon();
		// String sql_2 = "select * from staffroom where instid = ?";
		// String[] param = { request.getParameter("instid"), };
		// ResultSet rs_2 = DBC.executeQuery(sql_2, param);
		// while (rs_2.next()) {// 判断是否还有下一个数据
		// Staffroom staffroom = new Staffroom();
		// staffroom.setStaffroomid(rs_2.getInt("staffroomid"));
		// staffroom.setStaffroomname(rs_2.getString("staffroomname"));
		// staffroomList.add(staffroom);
		// }
		// Gson gson = new Gson();
		// String staffroom_list = gson.toJson(staffroomList);
		// System.out.println(staffroom_list);
		// response.setCharacterEncoding("UTF-8");
		// PrintWriter out = response.getWriter();
		// out.print(staffroom_list);
		// DBC.closeAll();
		// } catch (ClassNotFoundException | InstantiationException |
		// IllegalAccessException | SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// }//select == 2
		// }// option-else

		// String option = request.getParameter("option");
		// System.err.println("option:" + option);
		// if (option.equals("option_3")) {
		// try {
		// List<CourseType> typeList = new ArrayList<CourseType>();
		// Connection con = (Connection) DBC.getCon();
		// String sql_1 = "select * from coursetype";
		// Statement st_1 = (Statement) con.createStatement();
		// ResultSet rs_1 = st_1.executeQuery(sql_1);
		// while (rs_1.next()) {// 判断是否还有下一个数据
		// CourseType c = new CourseType();
		// c.setCoursetypeid(rs_1.getInt("coursetypeid"));
		// c.setCoursetype(rs_1.getString("coursetype"));
		// typeList.add(c);
		// }
		// Gson gson = new Gson();
		// String type_list = gson.toJson(typeList);
		// System.out.println(type_list);
		// response.setCharacterEncoding("UTF-8");
		// PrintWriter out = response.getWriter();
		// out.print(type_list);
		// DBC.closeAll();
		// } catch (ClassNotFoundException | InstantiationException |
		// IllegalAccessException | SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
	}

}
