$(document).ready(function() {

	$("#imgFile").mouseover(function() {
		var top = $("#openImg").offset().top;
		var left = $("#openImg").offset().left;
		$(".tip").css({
			'top' : top + 45,
			'left' : left + 20
		});
		$(".tip").html("打开文件");
		$(".tip").show();
		$("#imgFile").mouseout(function() {
			$(".tip").hide();
		});
	});
	
	$("#file").mouseover(function() {
		var top = $("#openFile").offset().top;
		var left = $("#openFile").offset().left;
		$(".tip").css({
			'top' : top + 45,
			'left' : left + 20
		});
		$(".tip").html("打开文件");
		$(".tip").show();
		$("#file").mouseout(function() {
			$(".tip").hide();
		});
	});
	
	$("#msg").mouseover(function() {
		var top = $("#openMsg").offset().top;
		var left = $("#openMsg").offset().left;
		$(".tip").css({
			'top' : top + 45,
			'left' : left + 20
		});
		$(".tip").html("显示历史消息");
		$(".tip").show();
		$("#msg").mouseout(function() {
			$(".tip").hide();
		});
	});

});
