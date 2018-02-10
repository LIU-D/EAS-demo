$(document).ready(function(){

	$(".upd_tip").hide(); //先让div隐藏
	$(".templatemo-edit-btn").click(function(){
	  $(".upd_tip").fadeIn("slow");//淡入淡出效果 显示div
	});
	
	$(".upd_close").click(function(){
	  $(".upd_tip").fadeOut("slow");//淡入淡出效果 隐藏div
	})
	
	

});
