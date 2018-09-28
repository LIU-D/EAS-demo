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
            $.each(data.majorList, function(index, item) {
	            $("#loading_option_3").append(  
	    			"<option value="+item.majorid+">" + item.majorname+ "</option>");
	        });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(errorThrown);
        }

    });
	
	//加载年级菜单
	$.ajax({
        url: "SelectServlet",
        type: "POST",
        dataType:"JSON",
        data: {
			select:"classroom_loadingyear"
        },
        success: function (data) {
            $.each(data, function(index, item) {
	            $("#loading_option_2").append(  
	    			"<option value="+item.year+">" + item.year+ "</option>");
	        });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(errorThrown);
        }

    });
	
	//加载班级信息
	$.ajax({
        url: "StudentControlServlet",
        type: "POST",
        dataType:"JSON",
        data: {
        	flag:"loading_classroom"
        },
        success: function (data) {
        	$(".classroom_content tr").remove();
        	 var str = "'确定要删除吗？'";
             $.each(data, function(index, item) {
             	$(".classroom_content").append('<tr><td>'+(++index)+'.</td><td class="edit_classid">'+item.classid+'</td><td>'+item.instname+'</td><td>'+item.majorname+'</td><td class="edit_year">'+item.year+'</td><td class="edit_classname">'+item.classname+'</td><td><a onclick="edit_get('+index+','+item.instid+','+item.majorid+','+item.year+')" class="templatemo-edit-btn">Edit</a></td><td><a href="StudentControlServlet?flag=delete_classroom&classid='+item.classid+'" class="templatemo-link" onclick="return confirm('+str+')">Delete</a></td></tr>');
 	        });
         },
         error: function (jqXHR, textStatus, errorThrown) {
             alert(errorThrown);
         }
    });
});


function changeMajor(val){
	if(val == 'all'){
		$.ajax({
	        url: "SelectServlet",
	        type: "POST",
	        dataType:"JSON",
	        data: {
				select:3
	        },
	        success: function (data) {
	        	$("#loading_option_3 option").remove();
	        	 $("#loading_option_3").append(  
			    			"<option select='select' value='all'>全部</option>");
	            $.each(data.majorList, function(index, item) {
		            $("#loading_option_3").append(  
		    			"<option value="+item.majorid+">" + item.majorname+ "</option>");
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
				select:"changeMajor",
				instid:val
	        },
	        success: function (data) {
	        	$("#loading_option_3 option").remove();
	        	$("#loading_option_3").append(  
    			"<option select='select' value='all'>全部</option>");
	            $.each(data, function(index, item) {
		            $("#loading_option_3").append(  
		    			"<option value="+item.majorid+">" + item.majorname+ "</option>");
		        });
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            alert(errorThrown);
	        }//error
	    });//ajax
	}//else
}

function edit_get(i,instid,majorid,year){
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
            $.each(data.instList, function(index, item) {
            	if(item.instid == instid){
            		$("#edit_option_1").append(
                 	    	"<option class='slt' value="+item.instid+">" + item.instname+ "</option>");
            	}else{
            	$("#edit_option_1").append(
         	    	"<option value="+item.instid+">" + item.instname+ "</option>");
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
			select:"changeMajor",
			instid:instid,
			date:new Date()
        },
        success: function (data) {
            $("#edit_option_2 option").remove();
            $.each(data, function(index, item) {
            	if(item.majorid == majorid){
            		 $("#edit_option_2").append("<option class='slt' value="+item.majorid+">" + item.majorname+ "</option>");
            	}else{
            		 $("#edit_option_2").append("<option value="+item.majorid+">" + item.majorname+ "</option>");
            	}
	        });
            $(".slt").attr("selected",true);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(errorThrown);
        }

    });
	$(".upd_con input")[0].value = $(".edit_classid").eq(i - 1).text();
	$(".upd_con input")[1].value = $(".edit_year").eq(i - 1).text();
	$(".upd_con input")[2].value = $(".edit_classname").eq(i - 1).text();
	$(".upd_tip").fadeIn("fast");//淡入淡出效果 显示div
	$(".upd_close").click(function(){
		$(".upd_tip").fadeOut("fast");//淡入淡出效果 隐藏div
	})
}
//选择班级信息
function select_classroom(){
	var instid = $("#loading_option_1 option:selected").val();
	var year = $("#loading_option_2 option:selected").val();
	var majorid = $("#loading_option_3 option:selected").val();
	//加载班级信息
	$.ajax({
        url: "StudentControlServlet",
        type: "POST",
        dataType:"JSON",
        data: {
        	flag:"select_classroom",
        	instid:instid,
        	majorid:majorid,
        	year:year
        },
        success: function (data) {
        	$(".classroom_content tr").remove();
        	 var str = "'确定要删除吗？'";
        	 $.each(data, function(index, item) {
              	$(".classroom_content").append('<tr><td>'+(++index)+'.</td><td class="edit_classid">'+item.classid+'</td><td>'+item.instname+'</td><td>'+item.majorname+'</td><td class="year">'+item.year+'</td><td class="edit_classname">'+item.classname+'</td><td><a onclick="edit_get('+index+','+item.instid+','+item.majorid+','+item.year+')" class="templatemo-edit-btn">Edit</a></td><td><a href="StudentControlServlet?flag=delete_classroom&classid='+item.classid+'" class="templatemo-link" onclick="return confirm('+str+')">Delete</a></td></tr>');
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
        	$("#add_option_1").append("<option select='select' value='all'>全部</option>");
        	$("#add_option_2").append("<option select='select' value='all'>全部</option>");
            $.each(data.instList, function(index, item) {
	            $("#add_option_1").append(  
	    			"<option value="+item.instid+">" + item.instname+ "</option>");
	        });
            $.each(data.majorList, function(index, item) {
	            $("#add_option_2").append(  
	    			"<option value="+item.majorid+">" + item.majorname+ "</option>");
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

function add_changeMajor(val){
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
	        	 $("#add_option_2").append("<option select='select' value='all'>全部</option>");
	            $.each(data.majorList, function(index, item) {
		            $("#add_option_2").append("<option value="+item.majorid+">" + item.majorname+ "</option>");
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
				select:"changeMajor",
				instid:val
	        },
	        success: function (data) {
	        	$("#add_option_2 option").remove();
	        	$("#add_option_2").append("<option select='select' value='all'>全部</option>");
	            $.each(data, function(index, item) {
		            $("#add_option_2").append(  
		            	"<option value="+item.majorid+">" + item.majorname+ "</option>");
		        });
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            alert(errorThrown);
	        }//error
	    });//ajax
	}//else
}


function edit_changeMajor(val){
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
	        	 $("#edit_option_2").append("<option select='select' value='all'>全部</option>");
	            $.each(data.majorList, function(index, item) {
		            $("#edit_option_2").append("<option value="+item.majorid+">" + item.majorname+ "</option>");
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
				select:"changeMajor",
				instid:val
	        },
	        success: function (data) {
	        	$("#edit_option_2 option").remove();
	            $.each(data, function(index, item) {
		            $("#edit_option_2").append(  
		    			"<option value="+item.majorid+">" + item.majorname+ "</option>");
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
				<li><a href="Student.jsp"  class="active"><i class="fa fa-map-marker fa-fw"></i>学生管理</a></li>
				<li><a href="Course.jsp"><i class="fa fa-users fa-fw"></i>课程管理</a></li>
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
						<li><a href="Student.jsp">学生管理</a></li>
						<li><a href="Class.jsp" class="active">班级管理</a></li>
						<li><a href="Major.jsp">专业管理</a></li>
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
								<label class="control-label templatemo-block">所属学院</label> <select
									onchange="changeMajor(this.value)" name="instid" class="form-control"
									id="loading_option_1">
									<option value="all" checked>全部</option>
								</select>
							</div>

							<div class="col-lg-6 col-md-6 " style="width: 25%">
								<label class="control-label templatemo-block">所在年级</label> <select
									name="staffroomid" class="form-control" id="loading_option_2">
									<option value="all" checked>全部</option>
								</select>
							</div>

							<div class="col-lg-6 col-md-6 " style="width: 20%">
								<label class="control-label templatemo-block">所属专业</label> <select
									name="coursetypeid" class="form-control" id="loading_option_3">
									<option value="all" checked>全部</option>
								</select>
							</div>

							<div class="col-lg-6 col-md-6 " style="width: 35%; padding-top: 16px">
								<button onclick="select_classroom()" type="submit" class="templatemo-blue-button">Update</button>
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
									<td><a href="" class="white-text templatemo-sort-by">班级代码<span
											class="caret"></span></a></td>
									<td><a href="" class="white-text templatemo-sort-by">学院<span
											class="caret"></span></a></td>
									<td><a href="" class="white-text templatemo-sort-by">专业<span
											class="caret"></span></a></td>
									<td><a href="" class="white-text templatemo-sort-by">年级<span
											class="caret"></span></a></td>
									<td>班级</td>
									<td>Edit</td>
									<td>Delete</td>
								</tr>
							</thead>
							<tbody class="classroom_content">

								<!------------------------------------------------------------------------------------------------------------------------------>
								<div class="upd_tip">
									<div>
										<form action="StudentControlServlet" method="post">
											<div class="upd_con">
												班级代码: <input readonly="true" name="classid" value="" type="text" /><br>
												所属学院: <select onchange="edit_changeMajor(this.value)" id="edit_option_1" name="instid"></select><br> 
												所属专业: <select id="edit_option_2" name="majorid"></select><br> 
												年级: <input type="text" name="year" value="" /><br>
												班级名称: <input type="text" name="classname" value="" />
											</div>

											<div class="upd_updbtn">
												<input type="submit" value="保存" /> <input class="upd_close"
													type="button" value="取消" />
											</div>
											<input type="hidden" name="courseid" value="" /> <input
												type="hidden" name="flag" value="update_classroom" />
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
			<form action="StudentControlServlet" method="post">
				<div class="add_con">
					班级代码: <input name="classid" value="" type="text" /><br>
					所属学院: <select onchange="add_changeMajor(this.value)" id="add_option_1" name="instid"></select><br> 
					所属专业: <select id="add_option_2" name="majorid"></select><br>
					年级: <input name="year" value="" type="text" /><br>
					班级名称: <input name="classname" value="" type="text" /><br>
				</div>
				<div class="add_addbtn">
					<input type="submit" value="添加" /> <input class="add_close"
						type="button" value="取消" />
				</div>
				<input type="hidden" name="flag" value="add_classroom" />
			</form>
		</div>
	</div>
</body>
</html>