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
<title>EMS-demo</title>

<link href="css/font-awesome.min.css" rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/templatemo-style.css" rel="stylesheet">
<link rel="stylesheet" href="css/c1.css?v=1" type="text/css">
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
	
	$.ajax({
        url: "SelectServlet",
        type: "POST",
        dataType:"JSON",
        data: {
			select:3
        },
        success: function (data) {
            $.each(data.instList, function(index, item) {
	            $("#option_search").append(  
	    			"<option value="+item.instid+">" + item.instname+ "</option>");
	        });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            /*弹出jqXHR对象的信息*/
            alert(jqXHR.responseText);
            alert(jqXHR.status);
            alert(jqXHR.readyState);
            alert(jqXHR.statusText);
            /*弹出其他两个参数的信息*/
            alert(textStatus);
            alert(errorThrown);
        }

    });
	
	$.ajax({
        url: "CourseControlServlet",
        type: "GET",
        dataType:"JSON",
        data: {
        	flag:"get_staffroom"
        },
        success: function (data) {
        	$(".inst_content tr").remove();
        	 var str = "'确定要删除吗？'";
             $.each(data, function(index, item) {
             	$(".inst_content").append('<tr><td>'+(++index)+'.</td><td class="edit_staffroomid">'+item.staffroomid+'</td><td>'+item.instname+'</td><td class="edit_staffroomname">'+item.staffroomname+'</td><td><a onclick="edit_get('+index+','+item.instid+')" class="templatemo-edit-btn">Edit</a></td><td><a href="CourseControlServlet?flag=delete_staffroom&staffroomid='+item.staffroomid+'" class="templatemo-link" onclick="return confirm('+str+')">Delete</a></td></tr>');
 	        });
         },
         error: function (jqXHR, textStatus, errorThrown) {
             alert(errorThrown);
         }
    });
	
});

function showInst(val){
	
	var fg = "select_staffroom";
	if(val == 'all')
		fg= "get_staffroom";
	$.ajax({
        url: "CourseControlServlet",
        type: "GET",
        dataType:"JSON",
        data: {
			flag:fg,
			instid:val,
			date:new Date()
        },
        success: function (data) {
            $(".inst_content tr").remove();
            var str = "'确定要删除吗？'";
            $.each(data, function(index, item) {
            	$(".inst_content").append('<tr><td>'+(++index)+'.</td><td class="edit_staffroomid">'+item.staffroomid+'</td><td>'+item.instname+'</td><td class="edit_staffroomname">'+item.staffroomname+'</td><td><a onclick="edit_get('+index+','+item.instid+')" class="templatemo-edit-btn">Edit</a></td><td><a href="CourseControlServlet?flag=delete_staffroom&staffroomid='+item.staffroomid+'" class="templatemo-link" onclick="return confirm('+str+')">Delete</a></td></tr>');
            });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(errorThrown);
        }

    });
}

function edit_get(i,id){
	var a = $(".edit_staffroomid").eq(i - 1).text();
	$(".upd_con input")[0].value = $(".edit_staffroomid").eq(i - 1).text();
	$(".upd_con input")[1].value = $(".edit_staffroomname").eq(i - 1).text();
	
	$.ajax({
        url: "SelectServlet",
        type: "POST",
        dataType:"JSON",
        data: {
			select:3,
			date:new Date()
        },
        success: function (data) {
            /*console.log(typeof(data));*/
            $("#option_tip option").remove();
            $.each(data.instList, function(index, item) {
            	if(item.instid == id){
            		$("#option_tip").append(
                 	    	"<option class='slt' value="+item.instid+">" + item.instname+ "</option>");
            	}else{
            	$("#option_tip").append(
         	    	"<option value="+item.instid+">" + item.instname+ "</option>");
            	}
	        });
            
            $(".slt").attr("selected",true);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(errorThrown);
        }

    });
	
	
	$(".upd_tip").fadeIn("fast");//淡入淡出效果 显示div
	$(".upd_close").click(function(){
		$(".upd_tip").fadeOut("fast");//淡入淡出效果 隐藏div
	})
}

function add(){
	
	$.ajax({
        url: "SelectServlet",
        type: "POST",
        dataType:"JSON",
        data: {
			select:3,
			date:new Date()
        },
        success: function (data) {
            $("#option_add_tip option").remove();
            $.each(data.instList, function(index, item) {
            	$("#option_add_tip").append(
         	    	"<option value="+item.instid+">" + item.instname+ "</option>");
	        });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(errorThrown);
        }

    });
	
	$(".add_tip").fadeIn("fast");//淡入淡出效果 显示div
	$(".add_close").click(function(){
		$(".add_tip").fadeOut("fast");//淡入淡出效果 隐藏div
	})
}
</script>
</head>
<body>
	<!-- Left column -->
	<div class="templatemo-flex-row">
		<div class="templatemo-sidebar">
			<header class="templatemo-site-header">
			<div class="square"></div>
			<h1>EMS-demo</h1>
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
				<li><a href="Inst.jsp" class="active"><i class="fa fa-bar-chart fa-fw"></i>机构管理</a></li>
				<li><a href="index.jsp"><i class="fa fa-database fa-fw"></i>教师管理</a></li>
				<li><a href="index.jsp"><i class="fa fa-map-marker fa-fw"></i>学生管理</a></li>
				<li><a href="Course.jsp"><i class="fa fa-users fa-fw"></i>课程管理</a></li>
				<li><a href="index.jsp"><i class="fa fa-sliders fa-fw"></i>评价管理</a></li>
				<li><a href="login.html"><i class="fa fa-eject fa-fw"></i>Sign
						Out</a></li>
			</ul>
			</nav>
		</div>
		<!-- Main content -->
		<div class="templatemo-content col-1 light-gray-bg">
			<div class="templatemo-top-nav-container">
				<div class="row">
					<nav class="templatemo-top-nav col-lg-12 col-md-12">
					<ul class="text-uppercase">
						<li><a href="Inst.jsp">学院管理</a></li>
						<li><a href="Staffroom.jsp"	class="active">教研室管理</a></li>
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
						<div class="col-lg-6 col-md-6 " style="width: 20%">
							<label class="control-label templatemo-block">开课学院</label> 
							<select onchange="showInst(this.value)" class="form-control" id="option_search">
								<option value="all" checked>全部</option>
							</select>
						</div>
						<div class="col-lg-6 col-md-6 "
							style="width: 35%; padding-top: 16px">
							<button type="submit" class="templatemo-blue-button">Update</button>
							<button type="reset" class="templatemo-white-button"
								onclick="add()" style="margin-left: 10px">Add</button>
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
									<td><a href="" class="white-text templatemo-sort-by">教研室代码<span
											class="caret"></span></a></td>
									<td><a href="" class="white-text templatemo-sort-by">所属学院<span
											class="caret"></span></a></td>
									<td><a href="" class="white-text templatemo-sort-by">教研室名称<span
											class="caret"></span></a></td>
									<td>Edit</td>
									<td>Delete</td>
								</tr>
							</thead>
							<tbody class="inst_content">
								<!-- <tr><td  colspan="6" align="center">没有信息！</td></tr> -->
								<!------------------------------------------------------------------------------------------------------------------------------>
								<div class="upd_tip">
									<div>
										<form action="CourseControlServlet" method="post">
											<div class="upd_con">教研室代码: <input name="staffroomid" value="" type="text" readonly="true" /> <br>
												<br>所属学院：<select name="instid" class="" id="option_tip"></select>
												<br><br>教研室名称: <input type="text" name="staffroomname" value="" />
											</div>

											<div class="upd_updbtn">
												<input type="submit" value="保存" /> <input class="upd_close"
													type="button" value="取消" />
											</div>
											<input type="hidden" name="staffroomid" value="" /> <input
												type="hidden" name="flag" value="update_staffroom" />
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
				教研室代码: <input name="staffroomid" value="" type="text" /> <br>
				<br>所属学院：<select name="instid" class="" id="option_add_tip"></select>
				<br><br>教研室名称: <input type="text" name="staffroomname" value="" />
				</div>
				<div class="add_addbtn">
					<input type="submit" value="添加" /> <input class="add_close"
						type="button" value="取消" />
				</div>
				<input type="hidden" name="flag" value="add_staffroom" />
			</form>
		</div>
	</div>
</body>
</html>