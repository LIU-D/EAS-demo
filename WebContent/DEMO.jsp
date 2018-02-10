<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mod.Course"%>
<%@ page import="com.dao.DBC"%>
<%@ page import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>课程管理系统-CourseManagement system</title>
<link rel="stylesheet" href="css/c1.css" type="text/css">
<script type=text/javascript src="js/jquery.min.js"></script>
<script type=text/javascript src="js/c1.js"></script>
</head>
<body>
<h1>课程管理系统</h1>
<hr>

<div class="con">
	<!--添加课程-->
	<div class="add">
		<form action="CourseControlServlet" class="input_add" method="post">
			<span class="head_ft">添加课程：</span>
			<input type="text" placeholder="课程代码(8位数字)" name="courseid"/>
			<input type="text" placeholder="课程名称" name="coursename"/>
			
			<select name="instid">
				<option selected value="">请选择开课学院</option>
				<option value="1">数计学院</option>
				<option value="2">文传学院</option>
				<option value="3">资环学院</option>
				<option value="4">外国语学院</option>
			</select>
			
			<select name="coursetypeid">
				<option selected value="">请选择课程类型</option>
				<option value="1">理论课</option>
				<option value="2">实验课</option>
				<option value="3">实践课</option>
				<option value="4">其他</option>
			</select>

			<input type="submit" class="add_sub" value="添加" >
			<input type="hidden" name="flag" value="2">
		</form>
	</div>
	
	<!--查询课程-->
	<div class="query">
		<form action="CourseControlServlet" class="input_query" method="post">
			<span class="head_ft">查询课程：</span>
			<input type="text" placeholder="课程代码(8位数字)" name="courseid"/>
			
			<input type="hidden" name="flag" value="5"/>
			<input type="submit" class="query_sub" value="查询" >
		</form>
	</div>
	
	<hr>
	<!--内容显示-->
	<div class="content">
		<table class="table" border="1px" bordercolor="#528CE0" cellspacing="0" cellpadding="0" width="895px">
			<col width="20%" /><col width="30%" /><col width="15%" /><col width="15%" /><col width="10%" /><col width="10%" />
			<tr>
				<th>课程代码</th>
				<th>课程名称</th>
				<th>开课学院</th>
				<th>课程类型</th>
				<th colspan="2">操作</th>
			</tr>
			
			<form method="post" action="CourseControlServlet" >
				<%request.getAttribute("C_list");%>
				<c:forEach var="course" items="${C_list}">
				<tr>
					<td>${course.coursid}</td>
					<td>${course.coursename}</td>
					<td>${course.instname}</td>
					<td>${course.insttype}</td>
					<td><a href="CourseControlServlet?flag=3&courseid=${course.courseid}" onclick="return confirm('确定要删除吗?')">删除</a></td>
					<td><a class="upd_btn">修改</a></td>
				</tr>
				
				<!------------------------------------------------------------------------------------------------------------------------------>
					<div class="upd_tip">
						<div>
							<form action="CourseControlServlet" method="post">
								<div class="upd_con">
								課程代碼: <input  placeholder="${course.courseid}" type="text" disabled="disabled"/><br><br>
								課程名稱: <input  type="text" name="coursename"value="${course.coursename}"/><br>
								</div>
								
								<div class="upd_updbtn">
								<input  type="submit" value="保存"/>
								<input class="upd_close"  type="button" value="取消"/>
								</div>
								<input type="hidden" name="courseid" value="${course.courseid}" />
								<input type="hidden" name="flag" value="4" />
							</form>
						</div>
					</div>
		<!------------------------------------------------------------------------------------------------------------------------------>
				
				</c:forEach>
			
			<!------------------------------------------------------------------------------------------->
			<div class="showww">
			<input type="hidden" name="flag" value="1">
				<input type="submit" class="aaaaa" value="！"/>
			</div>
			<!------------------------------------------------------------------------------------------->
			
			</form>
		
		</table>
		</div>
		
		
		
		
		
</div>
</body>
</html>