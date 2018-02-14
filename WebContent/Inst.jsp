<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.mod.Course"%>
<%@ page import="com.dao.DBC"%>
<%@ page import="com.dao.ShowSelect"%>
<%@ page import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EAS-demo</title>

<link href="css/font-awesome.min.css" rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/templatemo-style.css" rel="stylesheet">
<link rel="stylesheet" href="css/c1.css" type="text/css">
<script type=text/javascript src="js/jquery.min.js"></script>
<script type=text/javascript src="js/c1.js"></script>
<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<script type="text/javascript" src="js/templatemo-script.js"></script>
<script>
$(document).ready(function(){
	// Content widget with background image                                         
	var imageUrl = $('img.content-bg-img').attr('src');                             
	$('.templatemo-content-img-bg').css('background-image','url(' + imageUrl + ')');                                               
	$('img.content-bg-img').hide(); 
	/**********************************************************************************************/
	$(".upd_tip").hide(); //先让div隐藏
	$(".add_tip").hide(); //先让div隐藏
	
});
</script>
</head>
<body>
	<!-- Left column -->
	<div class="templatemo-flex-row">
		<div class="templatemo-sidebar">
			<header class="templatemo-site-header">
			<div class="square"></div>
			<h1>EAS-demo</h1>
			</header>
			<div class="profile-photo-container">
				<img src="images/profile-photo.jpg" alt="Profile Photo"
					class="img-responsive">
				<div class="profile-photo-overlay"></div>
			</div>
			<!-- Search box -->
			<form class="templatemo-search-form" role="search">
				<div class="input-group">
					<button type="submit" class="fa fa-search"></button>
					<input type="text" class="form-control" placeholder="Search"
						name="srch-term" id="srch-term">
				</div>
			</form>
			<div class="mobile-menu-icon">
				<i class="fa fa-bars"></i>
			</div>
			<nav class="templatemo-left-nav">
			<ul>
				<li><a href="index.jsp"><i class="fa fa-home fa-fw"></i>首页</a></li>
				<li><a href="CourseControlServlet?flag=get_inst"  class="active"><i class="fa fa-bar-chart fa-fw"></i>机构管理</a></li>
				<li><a href="index.jsp"><i class="fa fa-database fa-fw"></i>教师管理</a></li>
				<li><a href="index.jsp"><i class="fa fa-map-marker fa-fw"></i>学生管理</a></li>
				<li><a href="CourseControlServlet?flag=get_course"><i class="fa fa-users fa-fw"></i>课程管理</a></li>
				<li><a href="index.jsp"><i class="fa fa-sliders fa-fw"></i>评价管理</a></li>
				<li><a href="login.html"><i class="fa fa-eject fa-fw"></i>Sign Out</a></li>
			</ul>
			</nav>
		</div>
		<!-- Main content -->
		<div class="templatemo-content col-1 light-gray-bg">
			<div class="templatemo-top-nav-container">
				<div class="row">
					<nav class="templatemo-top-nav col-lg-12 col-md-12">
					<ul class="text-uppercase">
						<li><a href="CourseControlServlet?flag=get_inst" class="active">学院管理</a></li>
						<li><a href="CourseControlServlet?flag=get_staffroom">教研室管理</a></li>
						<li><a href="">Overview</a></li>
						<li><a href="login.html">Sign in form</a></li>
					</ul>
					</nav>
				</div>
			</div>



			<div class="templatemo-content-container">

				<!--Search&&add-->
				<div class="templatemo-content-widget white-bg"
					style="padding: 15px">
					<div class="row ">
						<div class="col-lg-6 col-md-6 "
							style="width: 35%; padding-top: 16px">
							<button type="reset" class="templatemo-white-button" onclick="add()"
								style="margin-left: 10px">Add</button>
							<button type="reset" class="templatemo-white-button"
								style="margin-left: 10px">Import</button>
						</div>
					</div>
				</div>



				<div class="templatemo-content-widget no-padding">
					<div class="panel panel-default table-responsive">
						<table
							class="table table-striped table-bordered templatemo-user-table">
							<thead>
								<tr>
									<td><a href="" class="white-text templatemo-sort-by">#
											<span class="caret"></span>
									</a></td>
									<td><a href="" class="white-text templatemo-sort-by">学院代码<span
											class="caret"></span></a></td>
									<td><a href="" class="white-text templatemo-sort-by">学院名称<span
											class="caret"></span></a></td>
									<td>Edit</td>
									<td>Delete</td>
								</tr>
							</thead>
							<tbody>
								<%request.getAttribute("list");%>
								<c:forEach var="i" items="${list}">
								<c:set var="index" value="${index+1}" /> 
								<tr>
									<td>${index}.</td>
									<td class="edit_courseid">${i.instid}</td>
									<td class="edit_coursename">${i.instname}</td>
									<td><a onclick="edit_get(${index})" class="templatemo-edit-btn">Edit</a></td>
									<td><a href="CourseControlServlet?flag=delete&courseid=${course.courseid}" class="templatemo-link"
											 onclick="return confirm('确定要删除吗?')">Delete</a></td>
								</tr>
								</c:forEach>
					<!------------------------------------------------------------------------------------------------------------------------------>
									<div class="upd_tip">
										<div>
											<form action="CourseControlServlet" method="post">
											<div class="upd_con">
												课程代码: <input name="courseid" value="" type="text" readonly="true" /><br><br>
												课程名称: <input  type="text" name="coursename" value=""/><br>
											</div>
								
											<div class="upd_updbtn">
												<input  type="submit" value="保存"/>
												<input class="upd_close"  type="button" value="取消"/>
											</div>
											<input type="hidden" name="courseid" value="" />
											<input type="hidden" name="flag" value="update" />
											</form>
										</div>
									</div>
									
					<!------------------------------------------------------------------------------------------------------------------------------>
								
							</tbody>
						</table>
					</div>
				</div>
				<!-- Second row ends -->

				<div class="pagination-wrap">
					<ul class="pagination">
						<li><a href="#">1</a></li>
						<li><a href="#">2</a></li>
						<li class="active"><a href="#">3 <span class="sr-only">(current)</span></a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">5</a></li>
						<li><a href="#" aria-label="Next"> <span
								aria-hidden="true"><i class="fa fa-play"></i></span>
						</a></li>
					</ul>
				</div>
				<footer class="text-right">
				<p>
					Copyright &copy; 2084 Company Name | More Templates <a href=""
						target="" title="">HBUN</a> - Collect from <a href="" title=""
						target="">HBUN</a>
				</p>
				</footer>
			</div>
		</div>
	</div>








<div class="add_tip">
	<div>
		<form action="CourseControlServlet" method="post">
		<div class="add_con">
			课程代码: <input name="courseid" value="" type="text" /><br>
			开课学院: <select name="instid">
							<option value="0" selected>——————————</option>
							<option value="1">数计学院</option>
							<option value="2">文传学院</option>
							<option value="3">资环学院</option>
			         </select><br>
			开课教研室: <select name="staffroomid">
							<option value="0" selected>——————————</option>
							<option value="5623">军事理论与训练教研室</option>
							<option value="9462">信息素质教研室</option>
							<option value="9999">网络教学平台</option>
			         </select><br>
			课程名称: <input  type="text" name="coursename" value=""/><br>
			课程类型: <select name="coursetypeid">
							<option value="0" selected>——————————</option>
							<option value="1">理论课</option>
							<option value="2">实验课</option>
							<option value="3">实践课</option>
							<option value="4">其他</option>
			         </select><br>
		</div>
		<div class="add_addbtn">
			<input  type="submit" value="添加"/>
			<input class="add_close"  type="button" value="取消"/>
		</div>
		<input type="hidden" name="flag" value="add" />
		</form>
	</div>
</div>	
</body>
</html>