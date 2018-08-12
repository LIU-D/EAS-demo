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
import com.mod.Classroom;
import com.mod.Course;
import com.mod.Evaluation;
import com.mod.Major;
import com.mod.Student;
import com.mod.Teacher;
import com.mod.Term;
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
		DBC DBC = new DBC();
		String flag = request.getParameter("flag");
		// 查看flag值
		System.out.println("get flag: " + flag);
		if (flag == null) {
			System.err.println("flag无值！");
		} else {

			// 删除教师信息
			if (flag.equals("delete_teacher")) {
				try {
					DBC.getCon();
					String sql = "delete from teacher where teacherid= ?";
					String[] param = { request.getParameter("teacherid"), };
					DBC.executeUpdate(sql, param);
					DBC.closeAll();
					response.sendRedirect("Teacher.jsp");

				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} // flag=delete_teacherid

			// 删除学生信息
			if (flag.equals("delete_student")) {
				try {
					DBC.getCon();
					String sql = "delete from student where studentid= ?";
					String[] param = { request.getParameter("studentid"), };
					DBC.executeUpdate(sql, param);
					DBC.closeAll();
					response.sendRedirect("Student.jsp");

				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} // flag=delete_studentid

			// 删除班级
			if (flag.equals("delete_classroom")) {
				try {
					DBC.getCon();
					String sql = "delete from classroom where classid= ?";
					String[] param = { request.getParameter("classid"), };
					DBC.executeUpdate(sql, param);
					DBC.closeAll();
					response.sendRedirect("Class.jsp");

				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} // flag=delete_classroom

			// 删除专业
			if (flag.equals("delete_major")) {
				try {
					DBC.getCon();
					String sql = "delete from major where majorid= ?";
					String[] param = { request.getParameter("majorid"), };
					DBC.executeUpdate(sql, param);
					DBC.closeAll();
					response.sendRedirect("Major.jsp");

				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} // flag=delete_major

			// 删除评价指标信息
			if (flag.equals("delete_evaluation")) {
				try {
					DBC.getCon();
					String sql = "delete from evaluation where evaluationid= ?";
					String[] param = { request.getParameter("evaluationid"), };
					DBC.executeUpdate(sql, param);
					DBC.closeAll();
					response.sendRedirect("Evaluation.jsp");

				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} // flag=delete_evaluationid

			// 删除学期信息
			if (flag.equals("delete_term")) {
				try {
					DBC.getCon();
					String sql = "delete from term where id= ?";
					String[] param = { request.getParameter("id"), };
					DBC.executeUpdate(sql, param);
					DBC.closeAll();
					response.sendRedirect("Term.jsp");

				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} // flag=delete_termid

		} // else
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
			// 增加教师信息
			if (flag.equals("add_teacher")) {
				try {
					DBC.getCon();
					String sql = "insert into teacher(teacherid,teachername,staffroomid)values(?,?,?)";
					String[] param = { request.getParameter("teacherid"), request.getParameter("teachername"),
							request.getParameter("staffroomid") };
					DBC.executeUpdate(sql, param);
					DBC.closeAll();
					response.sendRedirect("Teacher.jsp");
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} // add_teacher

			// 修改教师信息
			if (flag.equals("update_teacher")) {
				try {
					DBC.getCon();
					String sql = "update teacher set teachername = ?,staffroomid=? where teacherid=?";
					String[] param = { request.getParameter("teachername"), request.getParameter("staffroomid"),
							request.getParameter("teacherid") };
					DBC.executeUpdate(sql, param);
					DBC.closeAll();
					response.sendRedirect("Teacher.jsp");
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} // update_teacher

			// 加载教師信息
			if (flag.equals("loading_teacher")) {
				try {
					List<Teacher> teacherList = new ArrayList<Teacher>();
					Connection con = (Connection) DBC.getCon();
					String sql = "select * from teacher,inst,staffroom where teacher.staffroomid = staffroom.staffroomid and staffroom.instid=inst.instid";
					Statement st = (Statement) con.createStatement();
					ResultSet rs = st.executeQuery(sql);
					while (rs.next()) {// 判断是否还有下一个数据
						Teacher teacher = new Teacher();
						teacher.setTeacherid(rs.getInt("teacherid"));
						teacher.setTeachername(rs.getString("teachername"));
						teacher.setInstid(rs.getInt("instid"));
						teacher.setInstname(rs.getString("instname"));
						teacher.setStaffroomid(rs.getInt("staffroomid"));
						teacher.setStaffroomname(rs.getString("staffroomname"));
						teacherList.add(teacher);
					}
					Gson gson = new Gson();
					String json_list = gson.toJson(teacherList);
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
			} // loading_teacher

			// 选择显示教师信息
			if (flag.equals("select_teacher")) {
				try {
					int count = 1;
					int flag_1 = 0, flag_2 = 0;
					List<Teacher> teacherList = new ArrayList<Teacher>();
					Connection con = (Connection) DBC.getCon();
					ResultSet rs = null;
					String sql = "select * from teacher,inst,staffroom where "
							+ "teacher.staffroomid = staffroom.staffroomid and staffroom.instid=inst.instid";
					if (!request.getParameter("instid").equals("all")) {
						sql += " and inst.instid = ?";
						++flag_1;
						++count;
					}
					if (!request.getParameter("staffroomid").equals("all")) {
						sql += " and staffroom.staffroomid = ?";
						++flag_2;
						++count;
					}
					if (count == 1) {
						Statement st = (Statement) con.createStatement();
						rs = st.executeQuery(sql);
					} else {
						PreparedStatement ps = con.prepareStatement(sql);
						if (flag_1 != 0)
							ps.setString(flag_1, request.getParameter("instid"));
						if (flag_2 != 0)
							ps.setString(count - flag_2, request.getParameter("staffroomid"));
						rs = ps.executeQuery();
					}
					while (rs.next()) {// 判断是否还有下一个数据
						Teacher teacher = new Teacher();
						teacher.setTeacherid(rs.getInt("teacherid"));
						teacher.setTeachername(rs.getString("teachername"));
						teacher.setInstid(rs.getInt("instid"));
						teacher.setInstname(rs.getString("instname"));
						teacher.setStaffroomid(rs.getInt("staffroomid"));
						teacher.setStaffroomname(rs.getString("staffroomname"));
						teacherList.add(teacher);
					}
					Gson gson = new Gson();
					String json_list = gson.toJson(teacherList);
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
			} // select_teacher

			// 增加学生信息
			if (flag.equals("add_student")) {
				try {
					DBC.getCon();
					String sql = "insert into student(studentid,studentname,classid)values(?,?,?)";
					String[] param = { request.getParameter("studentid"), request.getParameter("studentname"),
							request.getParameter("classid") };
					DBC.executeUpdate(sql, param);
					DBC.closeAll();
					response.sendRedirect("Student.jsp");
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} // add_student

			// 修改学生信息
			if (flag.equals("update_student")) {
				try {
					DBC.getCon();
					String sql = "update student set studentname = ?,classid=? where studentid=?";
					String[] param = { request.getParameter("studentname"), request.getParameter("classid"),
							request.getParameter("studentid") };
					DBC.executeUpdate(sql, param);
					DBC.closeAll();
					response.sendRedirect("Student.jsp");
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} // update_student

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

			// 增加班级
			if (flag.equals("add_classroom")) {
				try {
					DBC.getCon();
					String sql = "insert into classroom(classid,classname,year,majorid)values(?,?,?,?)";
					String[] param = { request.getParameter("classid"), request.getParameter("classname"),
							request.getParameter("year"), request.getParameter("majorid") };
					DBC.executeUpdate(sql, param);
					DBC.closeAll();
					response.sendRedirect("Class.jsp");
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} // add_classroom

			// 加载班级信息
			if (flag.equals("loading_classroom")) {
				try {
					List<Classroom> classroomList = new ArrayList<Classroom>();
					Connection con = (Connection) DBC.getCon();
					String sql = "select * from inst,major,classroom where classroom.majorid=major.majorid and major.instid=inst.instid";
					Statement st = (Statement) con.createStatement();
					ResultSet rs = st.executeQuery(sql);
					while (rs.next()) {// 判断是否还有下一个数据
						Classroom classroom = new Classroom();
						classroom.setInstid(rs.getInt("instid"));
						classroom.setInstname(rs.getString("instname"));
						classroom.setClassid(rs.getInt("classid"));
						classroom.setClassname(rs.getString("classname"));
						classroom.setMajorid(rs.getInt("majorid"));
						classroom.setYear(rs.getInt("year"));
						classroom.setMajorname(rs.getString("majorname"));
						classroomList.add(classroom);
					}
					Gson gson = new Gson();
					String json_list = gson.toJson(classroomList);
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
			} // loading_classroom

			// 选择显示班级信息
			if (flag.equals("select_classroom")) {
				try {
					int count = 1;
					int flag_1 = 0, flag_2 = 0, flag_3 = 0;
					List<Classroom> classroomList = new ArrayList<Classroom>();
					Connection con = (Connection) DBC.getCon();
					ResultSet rs = null;
					String sql = "select * from inst,major,classroom where classroom.majorid=major.majorid and major.instid=inst.instid";
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
					if (!request.getParameter("year").equals("all")) {
						sql += " and classroom.year = ?";
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
							ps.setString(count, request.getParameter("year"));
						rs = ps.executeQuery();
					}
					while (rs.next()) {// 判断是否还有下一个数据
						Classroom classroom = new Classroom();
						classroom.setInstid(rs.getInt("instid"));
						classroom.setInstname(rs.getString("instname"));
						classroom.setClassid(rs.getInt("classid"));
						classroom.setClassname(rs.getString("classname"));
						classroom.setMajorid(rs.getInt("majorid"));
						classroom.setYear(rs.getInt("year"));
						classroom.setMajorname(rs.getString("majorname"));
						classroomList.add(classroom);
					}
					Gson gson = new Gson();
					String json_list = gson.toJson(classroomList);
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
			} // select_classroom

			// 修改班级信息
			if (flag.equals("update_classroom")) {
				try {
					DBC.getCon();
					String sql = "update classroom set classname = ?,majorid=?,year=? where classid=?";
					String[] param = { request.getParameter("classname"), request.getParameter("majorid"),
							request.getParameter("year"), request.getParameter("classid") };
					DBC.executeUpdate(sql, param);
					DBC.closeAll();
					response.sendRedirect("Class.jsp");
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} // update_classroom

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

			// 修改专业
			if (flag.equals("update_major")) {
				try {
					DBC.getCon();
					String sql = "update major set majorname = ?,instid=? where majorid=?";
					String[] param = { request.getParameter("majorname"), request.getParameter("instid"),
							request.getParameter("majorid") };
					DBC.executeUpdate(sql, param);
					DBC.closeAll();
					response.sendRedirect("Major.jsp");
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} // update_major

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

			// 增加评价指标信息
			if (flag.equals("add_evaluation")) {
				try {
					DBC.getCon();
					String sql = "insert into evaluation(evaluationid,content,coursetypeid)values(?,?,?)";
					String[] param = { request.getParameter("evaluationid"), request.getParameter("content"),
							request.getParameter("coursetypeid") };
					DBC.executeUpdate(sql, param);
					DBC.closeAll();
					response.sendRedirect("Evaluation.jsp");
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} // add_evaluation

			// 修改评价指标信息
			if (flag.equals("update_evaluation")) {
				try {
					DBC.getCon();
					String sql = "update evaluation set content = ?,coursetypeid=? where evaluationid=?";
					String[] param = { request.getParameter("content"), request.getParameter("coursetypeid"),
							request.getParameter("evaluationid") };
					DBC.executeUpdate(sql, param);
					DBC.closeAll();
					response.sendRedirect("Evaluation.jsp");
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} // update_evaluation

			// 加载评价指标信息
			if (flag.equals("loading_evaluation")) {
				try {
					List<Evaluation> evaluationList = new ArrayList<Evaluation>();
					Connection con = (Connection) DBC.getCon();
					String sql = "select * from evaluation,coursetype where evaluation.coursetypeid = coursetype.coursetypeid";
					Statement st = (Statement) con.createStatement();
					ResultSet rs = st.executeQuery(sql);
					while (rs.next()) {// 判断是否还有下一个数据
						Evaluation evaluation = new Evaluation();
						evaluation.setEvaluationid(rs.getInt("evaluationid"));
						evaluation.setContent(rs.getString("content"));
						evaluation.setCoursetypeid(rs.getInt("coursetypeid"));
						evaluation.setCoursetype(rs.getString("coursetype"));
						evaluationList.add(evaluation);
					}
					Gson gson = new Gson();
					String json_list = gson.toJson(evaluationList);
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
			} // loading_evaluation

			// 选择显示评价指标信息
			if (flag.equals("select_evaluation")) {
				try {
					List<Evaluation> evaluationList = new ArrayList<Evaluation>();
					Connection con = (Connection) DBC.getCon();
					ResultSet rs = null;
					String sql = "select * from evaluation,coursetype where evaluation.coursetypeid=coursetype.coursetypeid and evaluation.coursetypeid=?";
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setString(1, request.getParameter("coursetypeid"));
					rs = ps.executeQuery();
					while (rs.next()) {// 判断是否还有下一个数据
						Evaluation evaluation = new Evaluation();
						evaluation.setEvaluationid(rs.getInt("evaluationid"));
						evaluation.setContent(rs.getString("content"));
						evaluation.setCoursetypeid(rs.getInt("coursetypeid"));
						evaluation.setCoursetype(rs.getString("coursetype"));
						evaluationList.add(evaluation);
					}
					Gson gson = new Gson();
					String json_list = gson.toJson(evaluationList);
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
			} // select_evaluation

			// 增加学期
			if (flag.equals("add_term")) {
				try {
					DBC.getCon();
					String sql = "insert into term(schoolyear,term)values(?,?)";
					String[] param = {request.getParameter("schoolyear"),request.getParameter("term") };
					DBC.executeUpdate(sql, param);
					DBC.closeAll();
					response.sendRedirect("Term.jsp");
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} // add_term

			// 修改学期信息
			if (flag.equals("update_term")) {
				System.out.println(request.getParameter("evaluationid"));
				try {
					DBC.getCon();
//					String sql = "update term set schoolyear = ?,term=? where id=?";
//					String[] param = { request.getParameter("schoolyear"), request.getParameter("term"),
//							request.getParameter("id") };
//					DBC.executeUpdate(sql, param);
//					DBC.closeAll();
					response.sendRedirect("Term.jsp");
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} // update_term

			// 加载学期信息
			if (flag.equals("loading_term")) {
				try {
					List<Term> termList = new ArrayList<Term>();
					Connection con = (Connection) DBC.getCon();
					String sql = "select * from term order by schoolyear";
					Statement st = (Statement) con.createStatement();
					ResultSet rs = st.executeQuery(sql);
					while (rs.next()) {// 判断是否还有下一个数据
						Term term = new Term();
						term.setId(rs.getInt("id"));
						term.setSchoolyear(rs.getString("schoolyear"));
						term.setTerm(rs.getString("term"));
						term.setOpen(rs.getString("open"));
						termList.add(term);
					}
					Gson gson = new Gson();
					String json_list = gson.toJson(termList);
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
			} // loading_term

		} // else
	}

}
