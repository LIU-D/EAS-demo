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
	
	//加载联级菜单
	$.ajax({
        url: "SelectServlet",
        type: "POST",
        dataType:"JSON",
        data: {
			select:3
        },
        success: function (data) {
            $.each(data.instList, function(index, item) {
	            $("#loading_option_1").append(  
	    			"<option value="+item.instid+">" + item.instname+ "</option>");
	        });
            $.each(data.staffroomList, function(index, item) {
	            $("#loading_option_2").append(  
	    			"<option value="+item.staffroomid+">" + item.staffroomname+ "</option>");
	        });
            $.each(data.typeList, function(index, item) {
	            $("#loading_option_3").append(  
	    			"<option value="+item.coursetypeid+">" + item.coursetype+ "</option>");
	        });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(errorThrown);
        }

    });
	
	//加载选课信息
	$.ajax({
        url: "CourseControlServlet",
        type: "POST",
        dataType:"JSON",
        data: {
        	flag:"loading_elective"
        },
        success: function (data) {
        	$(".elective_content tr").remove();
        	 var str = "'确定要删除吗？'";
             $.each(data, function(index, item) {
             	$(".elective_content").append('<tr><td>'+(++index)+'.</td><td class="edit_courseid">'+item.studentid+'</td><td class="edit_courseid">'+item.studentname+'</td><td class="edit_coursename">'+item.stinstname+'</td><td>'+item.majorname+'</td><td>'+item.classname+'</td><td>'+item.courseid+'</td><td>'+item.coursename+'</td><td>'+item.coinstname+'</td><td>'+item.staffroomname+'</td><td>'+item.teacherid+'</td><td>'+item.teachername+'</td><td>'+item.teinstname+'</td><td>'+item.schoolyear+' '+item.termname+'</td><td><a onclick="edit_get('+index+','+item.instid+','+item.staffroomid+','+item.coursetypeid+')" class="templatemo-edit-btn">Edit</a></td><td><a href="CourseControlServlet?flag=delete_course&courseid='+item.courseid+'" class="templatemo-link" onclick="return confirm('+str+')">Delete</a></td></tr>');
 	        });
         },
         error: function (jqXHR, textStatus, errorThrown) {
             alert(errorThrown);
         }
    });
});


function changeStaffroom(val){
	if(val == 'all'){
		$.ajax({
	        url: "SelectServlet",
	        type: "POST",
	        dataType:"JSON",
	        data: {
				select:3
	        },
	        success: function (data) {
	        	$("#loading_option_2 option").remove();
	        	 $("#loading_option_2").append(  
			    			"<option select='select' value='all'>全部</option>");
	            $.each(data.staffroomList, function(index, item) {
		            $("#loading_option_2").append(  
		    			"<option value="+item.staffroomid+">" + item.staffroomname+ "</option>");
		        });
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            alert(errorThrown);
	        }

	    });
	}else{
		$.ajax({
	        url: "SelectServlet",
	        type: "POST",
	        dataType:"JSON",
	        data: {
				select:1,
				instid:val
	        },
	        success: function (data) {
	        	$("#loading_option_2 option").remove();
	        	$("#loading_option_2").append(  
    			"<option select='select' value='all'>全部</option>");
	            $.each(data, function(index, item) {
		            $("#loading_option_2").append(  
		    			"<option value="+item.staffroomid+">" + item.staffroomname+ "</option>");
		        });
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            alert(errorThrown);
	        }//error
	    });//ajax
	}//else
}

function edit_get(i,instid,staffroomid,coursetypeid){
	//加载联级菜单
	$.ajax({
        url: "SelectServlet",
        type: "POST",
        dataType:"JSON",
        data: {
			select:3,
			date:new Date()
        },
        success: function (data) {
            $("#edit_option_1 option").remove();
            $("#edit_option_3 option").remove();
            $.each(data.instList, function(index, item) {
            	if(item.instid == instid){
            		$("#edit_option_1").append(
                 	    	"<option class='slt' value="+item.instid+">" + item.instname+ "</option>");
            	}else{
            	$("#edit_option_1").append(
         	    	"<option value="+item.instid+">" + item.instname+ "</option>");
            	}
	        });
            $.each(data.typeList, function(index, item) {
            	if(item.coursetypeid == coursetypeid){
            		 $("#edit_option_3").append("<option class='slt' value="+item.coursetypeid+">" + item.coursetype+ "</option>");
            	}else{
            		 $("#edit_option_3").append("<option value="+item.coursetypeid+">" + item.coursetype+ "</option>");
            	}
	        });
            
            $(".slt").attr("selected",true);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(errorThrown);
        }
    });
	
	$.ajax({
        url: "SelectServlet",
        type: "POST",
        dataType:"JSON",
        data: {
			select:1,
			instid:instid,
			date:new Date()
        },
        success: function (data) {
            $("#edit_option_2 option").remove();
            $.each(data, function(index, item) {
            	if(item.staffroomid == staffroomid){
            		$("#edit_option_2").append(
                 	    	"<option class='slt' value="+item.staffroomid+">" + item.staffroomname+ "</option>");
            	}else{
            	$("#edit_option_2").append(
         	    	"<option value="+item.staffroomid+">" + item.staffroomname+ "</option>");
            	}
	        });
            
            $(".slt").attr("selected",true);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(errorThrown);
        }

    });
	
	var a = $(".edit_courseid").eq(i - 1).text();
	$(".upd_con input")[0].value = $(".edit_courseid").eq(i - 1).text();
	$(".upd_con input")[1].value = $(".edit_coursename").eq(i - 1).text();
	$(".upd_tip").fadeIn("fast");//淡入淡出效果 显示div
	$(".upd_close").click(function(){
		$(".upd_tip").fadeOut("fast");//淡入淡出效果 隐藏div
	})
}

function select_course(){
	var instid = $("#loading_option_1 option:selected").val();
	var staffroomid = $("#loading_option_2 option:selected").val();
	var coursetypeid = $("#loading_option_3 option:selected").val();
	console.log(instid);
	console.log(typeof instid);
	//加载课程信息
	$.ajax({
        url: "CourseControlServlet",
        type: "POST",
        dataType:"JSON",
        data: {
        	flag:"select_course",
        	instid:instid,
        	staffroomid:staffroomid,
        	coursetypeid:coursetypeid
        },
        success: function (data) {
        	$(".course_content tr").remove();
        	 var str = "'确定要删除吗？'";
             $.each(data, function(index, item) {
             	$(".course_content").append('<tr><td>'+(++index)+'.</td><td class="edit_courseid">'+item.courseid+'</td><td class="edit_coursename">'+item.coursename+'</td><td>'+item.instname+'</td><td>'+item.staffroomname+'</td><td>'+item.coursetype+'</td><td><a onclick="edit_get('+index+','+item.instid+','+item.staffroomid+','+item.coursetypeid+')" class="templatemo-edit-btn">Edit</a></td><td><a href="CourseControlServlet?flag=delete_course&courseid='+item.courseid+'" class="templatemo-link" onclick="return confirm('+str+')">Delete</a></td></tr>');
 	        });
         },
         error: function (jqXHR, textStatus, errorThrown) {
             alert(errorThrown);
         }
    });
}



function add(){
	//加载联级菜单
	$.ajax({
        url: "SelectServlet",
        type: "POST",
        dataType:"JSON",
        data: {
			select:3
        },
        success: function (data) {
        	$("#add_option_1 option").remove();
        	$("#add_option_2 option").remove();
        	$("#add_option_3 option").remove();
        	$("#add_option_1").append("<option value='all'>全部</option>");
            $.each(data.instList, function(index, item) {
	            $("#add_option_1").append(  
	    			"<option value="+item.instid+">" + item.instname+ "</option>");
	        });
            $.each(data.staffroomList, function(index, item) {
	            $("#add_option_2").append(  
	    			"<option value="+item.staffroomid+">" + item.staffroomname+ "</option>");
	        });
            $.each(data.typeList, function(index, item) {
	            $("#add_option_3").append(  
	    			"<option value="+item.coursetypeid+">" + item.coursetype+ "</option>");
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

function add_changeStaffroom(val){
	if(val == 'all'){
		$.ajax({
	        url: "SelectServlet",
	        type: "POST",
	        dataType:"JSON",
	        data: {
				select:3
	        },
	        success: function (data) {
	        	$("#add_option_2 option").remove();
	        	 $("#add_option_2").append(  
			    			"<option select='select' value='all'>全部</option>");
	            $.each(data.staffroomList, function(index, item) {
		            $("#add_option_2").append(  
		    			"<option value="+item.staffroomid+">" + item.staffroomname+ "</option>");
		        });
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            alert(errorThrown);
	        }

	    });
	}else{
		$.ajax({
	        url: "SelectServlet",
	        type: "POST",
	        dataType:"JSON",
	        data: {
				select:1,
				instid:val
	        },
	        success: function (data) {
	        	$("#add_option_2 option").remove();
	            $.each(data, function(index, item) {
		            $("#add_option_2").append(  
		    			"<option value="+item.staffroomid+">" + item.staffroomname+ "</option>");
		        });
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            alert(errorThrown);
	        }//error
	    });//ajax
	}//else
}


function edit_changeStaffroom(val){
	if(val == 'all'){
		$.ajax({
	        url: "SelectServlet",
	        type: "POST",
	        dataType:"JSON",
	        data: {
				select:3
	        },
	        success: function (data) {
	        	$("#edit_option_2 option").remove();
	        	 $("#edit_option_2").append(  
			    			"<option select='select' value='all'>全部</option>");
	            $.each(data.staffroomList, function(index, item) {
		            $("#edit_option_2").append(  
		    			"<option value="+item.staffroomid+">" + item.staffroomname+ "</option>");
		        });
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            alert(errorThrown);
	        }

	    });
	}else{
		$.ajax({
	        url: "SelectServlet",
	        type: "POST",
	        dataType:"JSON",
	        data: {
				select:1,
				instid:val
	        },
	        success: function (data) {
	        	$("#edit_option_2 option").remove();
	            $.each(data, function(index, item) {
		            $("#edit_option_2").append(  
		    			"<option value="+item.staffroomid+">" + item.staffroomname+ "</option>");
		        });
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            alert(errorThrown);
	        }//error
	    });//ajax
	}//else
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
				<li><a href="Inst.jsp"><i class="fa fa-bar-chart fa-fw"></i>机构管理</a></li>
				<li><a href="Teacher.jsp"><i class="fa fa-database fa-fw"></i>教师管理</a></li>
				<li><a href="Student.jsp"><i class="fa fa-map-marker fa-fw"></i>学生管理</a></li>
				<li><a href="Course.jsp"  class="active"><i class="fa fa-users fa-fw"></i>课程管理</a></li>
				<li><a href="Evaluation.jsp"><i class="fa fa-sliders fa-fw"></i>评价管理</a></li>
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
						<li><a href="Course.jsp">课程管理</a></li>
						<li><a href="Elective.jsp" class="active">选课管理</a></li>
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
								<label class="control-label templatemo-block">学生所属学院</label> <select
									onchange="changeStaffroom(this.value)" name="instid" class="form-control"
									id="loading_option_1">
									<option value="all" checked>全部</option>
								</select>
							</div>

							<div class="col-lg-6 col-md-6 " style="width: 25%">
								<label class="control-label templatemo-block">学生所属专业</label> <select
									name="staffroomid" class="form-control" id="loading_option_2">
									<option value="all" checked>全部</option>
								</select>
							</div>

							<div class="col-lg-6 col-md-6 " style="width: 20%">
								<label class="control-label templatemo-block">学生所属班级</label> <select
									name="coursetypeid" class="form-control" id="loading_option_3">
									<option value="all" checked>全部</option>
								</select>
							</div>
							
							<div class="col-lg-6 col-md-6 " style="width: 20%">
								<label class="control-label templatemo-block">开课学院</label> <select
									name="coursetypeid" class="form-control" id="loading_option_3">
									<option value="all" checked>全部</option>
								</select>
							</div>
							
							<div class="col-lg-6 col-md-6 " style="width: 20%">
								<label class="control-label templatemo-block">开课教研室</label> <select
									name="coursetypeid" class="form-control" id="loading_option_3">
									<option value="all" checked>全部</option>
								</select>
							</div>
							
							<div class="col-lg-6 col-md-6 " style="width: 20%">
								<label class="control-label templatemo-block">学期</label> <select
									name="coursetypeid" class="form-control" id="loading_option_3">
									<option value="all" checked>全部</option>
								</select>
							</div>
							
							<div class="col-lg-6 col-md-6 " style="width: 20%">
								<label class="control-label templatemo-block">教师所属学院</label> <select
									name="coursetypeid" class="form-control" id="loading_option_3">
									<option value="all" checked>全部</option>
								</select>
							</div>
							

							<div class="col-lg-6 col-md-6 " style="width: 35%; padding-top: 16px">
								<button onclick="select_course()" type="submit" class="templatemo-blue-button">Update</button>
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
									<td><a href="" class="white-text templatemo-sort-by">学生学号<span class="caret"></span></a></td>
									<td><a href="" class="white-text templatemo-sort-by">学生姓名<span class="caret"></span></a></td>
									<td><a href="" class="white-text templatemo-sort-by">学生学院<span class="caret"></span></a></td>
									<td><a href="" class="white-text templatemo-sort-by">学生专业<span class="caret"></span></a></td>
									<td><a href="" class="white-text templatemo-sort-by">学生班级<span class="caret"></span></a></td>
									<td><a href="" class="white-text templatemo-sort-by">课程代码<span class="caret"></span></a></td>
									<td><a href="" class="white-text templatemo-sort-by">课程名称<span class="caret"></span></a></td>
									<td><a href="" class="white-text templatemo-sort-by">开课学院<span class="caret"></span></a></td>
									<td><a href="" class="white-text templatemo-sort-by">开课教研室<span class="caret"></span></a></td>
									<td><a href="" class="white-text templatemo-sort-by">教师工号<span class="caret"></span></a></td>
									<td><a href="" class="white-text templatemo-sort-by">教师姓名<span class="caret"></span></a></td>
									<td><a href="" class="white-text templatemo-sort-by">教师单位<span class="caret"></span></a></td>
									<td>开课学期</td>
									<td>Edit</td>
									<td>Delete</td>
								</tr>
							</thead>
							<tbody class="elective_content">

								<!------------------------------------------------------------------------------------------------------------------------------>
								<div class="upd_tip">
									<div>
										<form action="CourseControlServlet" method="post">
											<div class="upd_con">
												课程代码: <input readonly="true" name="courseid" value="" type="text" /><br>
												开课学院: <select onchange="edit_changeStaffroom(this.value)" id="edit_option_1" name="instid"></select><br> 
												开课教研室: <select id="edit_option_2" name="staffroomid"></select><br> 
												课程名称: <input type="text" name="coursename" value="" /><br>
												课程类型: <select id="edit_option_3" name="coursetypeid"></select>
											</div>

											<div class="upd_updbtn">
												<input type="submit" value="保存" /> <input class="upd_close"
													type="button" value="取消" />
											</div>
											<input type="hidden" name="courseid" value="" /> <input
												type="hidden" name="flag" value="update_course" />
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
					开课学院: <select onchange="add_changeStaffroom(this.value)" id="add_option_1" name="instid"></select><br> 
					开课教研室: <select id="add_option_2" name="staffroomid"></select><br> 课程名称: <input type="text" name="coursename" value="" /><br>
					课程类型: <select id="add_option_3" name="coursetypeid"></select><br>
				</div>
				<div class="add_addbtn">
					<input type="submit" value="添加" /> <input class="add_close"
						type="button" value="取消" />
				</div>
				<input type="hidden" name="flag" value="add_course" />
			</form>
		</div>
	</div>
</body>
</html>