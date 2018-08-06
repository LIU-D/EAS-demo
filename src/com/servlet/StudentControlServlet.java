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
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			}
		}//else
	}

}
