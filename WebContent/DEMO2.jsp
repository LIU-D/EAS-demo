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
		<form action="StudentControlServlet" method="post">
			<label><input type="checkbox" name="ee" value="A" />A</label>
			<label><input type="checkbox" name="ee" value="B" />B</label>
			<label><input type="checkbox" name="ee" value="C" />C</label>
			<label><input type="checkbox" name="ee" value="D" />D</label>
			<input type="hidden" name="flag" value="update_term" />
			<input type="submit" value="保存" /> 
		</form>
	</div>
</div>
</body>
</html>