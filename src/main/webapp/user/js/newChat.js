var limit = 0;
var sendUserId = getUrlParam("sendUserId");
var recivId = getUrlParam("recivId");
var sendType = getUrlParam("sendType");
var userInfo = eval("(" + getCookie(sendUserId) + ")");
var head = userInfo.userName + ":" + userInfo.userPasswd;
$(document).ready(function() {	
	$("#imgFile").mouseover(function() {
		var top = $("#openImg").offset().top;
		var left = $("#openImg").offset().left;
		$(".tip").css({
			'top' : top + 45,
			'left' : left + 20
		});
		$(".tip").html("打开图片");
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
	
	$("#imgFile").change(function(){
		var imgPath = $("#imgFile").val();
		var extStart = imgPath.lastIndexOf(".");
		var ext = imgPath.substring(extStart, imgPath.length).toUpperCase();
		if (ext != ".BMP" && ext != ".PNG" && ext != ".GIF" && ext != ".JPG"
				&& ext != ".JPEG") {
			alert("图片限于bmp,png,gif,jpeg,jpg格式");
			return false;
		}
		var size = this.files[0].size;
        if (size > 10 * 1024 * 1024) {
            alert("上传的图片大小不能超过10M！");
            return false;
        }
        
        
		var objUrl = getObjectURL(this.files[0]);		
		var oldHtml = $("#showText").html();
		var newHtml = "<div id=\"sendModule\"><div id=\"sendUserName\">" + userInfo.userName + "</div>";
		newHtml += "<div id=\"sendImg\"><img id=\"img\" src=" + objUrl + "></div></div>";
		newHtml = oldHtml + newHtml;
		upload("imgFile",2,newHtml);
	});
	
	$("#file").change(function(){
		var filePath = $("#file").val();
		var extStart = filePath.lastIndexOf("\\");
		if(-1 != extStart) {
			fileName = filePath.substring(extStart+1, filePath.length);
		} else {
			extStart = filePath.lastIndexOf("\/");
			fileName = filePath.substring(extStart+1, filePath.length);
		}
		var size = this.files[0].size;
        if (size > 1024 * 1024 * 1024) {
            alert("上传的文件大小不能超过1G！");
            return false;
        }
        var oldHtml = $("#showText").html();
		var newHtml = "<div id=\"sendModule\"><div id=\"sendUserName\">" + userInfo.userName + "</div>";
		newHtml += "<div id=\"sendFile\">文件" + fileName + "发送成功</div></div>";
		newHtml = oldHtml + newHtml;
		upload("file",3,newHtml);
		
	});
	
	$("#openMsg").click(function(){
		queryHistoryMsg();
	});
	
	$("#send").click(function(){
		var text = $("#inputText").html();
		$("#inputText").html("");
		if(null == text || "" == text.trim()) {
			alert("请不要发送空消息");
			return;
		}
		var oldHtml = $("#showText").html();
		var newHtml = "<div id=\"sendModule\"><div id=\"sendUserName\">：" + userInfo.userName + "</div>";
		newHtml += "<div id=\"sendText\">" + text + "</div></div>";
		newHtml = oldHtml + newHtml;
		sendMsg(text,1,newHtml);
	});

	var upload = function(file,msgType,html) {
		$.ajaxFileUpload({
			url : "/api/file/upload", // 需要链接到服务器地址
			type : 'post',
			data : {
				head : head,
				sendUserId : userInfo.id,
				msgType:msgType
			},
			secureuri : true,
			fileElementId : file, // 文件选择框的id属性
			success : function(data, status) {
				var d = eval("(" + data.body.innerText + ")");
				if (true == d.success) {
					sendMsg(d.data,msgType);
					$("#showText").html(html);
					alert("文件发送成功");
				} else {
					alert(d.msg);
				}
			},
			error : function(data, status, e) {
				alert("发送失败请重新发送");
			}
		});
	}
	
	var sendMsg = function(msg,msgType,html) {
		$.post("/api/sendMsg",
				  {
			 head:head,
			 sendUserId:userInfo.id,
			 recivId:recivId,
			 sendType:sendType,
			 msg:msg,
			 msgType:msgType
				  },
				  function(data,status){
					  var d = eval("("+data+")");
					  if(true != d.success) {
						  alert(d.msg)
					  } else {
						  $("#showText").html(html);
						  limit += 1;
					  }
				  }
				  ).error(function(){
						alert("发送消息失败");
					});
	}
	
	
	var queryHistoryMsg = function() {
		limit += 5;
		var url = "/api/queryHistoryMsg?" + "head=" + head +
		"&sendUserId=" + userInfo.id + "&recivId=" + recivId +"&sendType=" + sendType + "&limit=" + limit;
		$.get(url,
				function(data,status){
			    var d = eval("("+data+")");
			    if(true == d.success) {
			    	var recivMsgs = d.data;
			    	var newHtml="";
			    	for(i=recivMsgs.length-1;i>=0;i--) {
			    		var j = i;
			    		if(recivMsgs[j].sendUserId == userInfo.id) {
			    			newHtml += "<div id=\"sendModule\"><div id=\"sendUserName\">：" + userInfo.userName + "</div>";
			    			if(1 == recivMsgs[j].msgType) {
			    				newHtml += "<div id=\"sendText\">" + recivMsgs[j].msg + "</div></div>";
							} else if(2 == recivMsgs[j].msgType) {
								var src = "/user/files/" + recivMsgs[j].msg;
								var downloadUrl = "/api/download?head=" +head + "&fileName=" + recivMsgs[j].msg;
								newHtml += "<div id=\"sendImg\"><img id=\"img\" src=" + src + " onclick=\"window.open('" + downloadUrl + "')\"></div></div>";
							} else {
								var newFileName = recivMsgs[j].msg;
								var downloadUrl = "/api/download?head=" +head + "&fileName=" + newFileName;
								var oldFileName = newFileName.substring(newFileName.indexOf("-")+1,newFileName.length);
								newHtml += "<div id=\"sendFile\"><a href=" + downloadUrl + ">文件" + oldFileName + "，点此下载</a></div></div>";
							}				    		
							
			    		} else {
			    			 newHtml += "<div id=\"recivModule\"><div id=\"recivUserName\">" + recivMsgs[j].sendUserName + "：</div>";
					    		if(1 == recivMsgs[j].msgType) {
									newHtml += "<div id=\"recivText\">" + recivMsgs[j].msg + "</div></div>";
								} else if(2 == recivMsgs[j].msgType) {
									var src = "/user/files/" + recivMsgs[j].msg;
									var downloadUrl = "/api/download?head=" +head + "&fileName=" + recivMsgs[j].msg;
									newHtml += "<div id=\"recivImg\"><img id=\"img\" src=" + src + " onclick=\"window.open('" + downloadUrl + "')\"></div></div>";
								} else {
									var newFileName = recivMsgs[j].msg;
									var downloadUrl = "/api/download?head=" +head + "&fileName=" + newFileName;
									var oldFileName = newFileName.substring(newFileName.indexOf("-")+1,newFileName.length);
									newHtml += "<div id=\"reicvFile\"><a href=" + downloadUrl + ">文件" + oldFileName + "，点此下载</a></div></div>";
								}
			    			
			    		}
			    		
			    		limit += 1;
			    	}
			    	$("#showText").html(newHtml);
			    }
		    });
		
	}
	
});
var recivMsg = function() {
	var url = "/api/recivMsg?" + "head=" + head + "&sendId="
	+ recivId +"&recivUserId=" + userInfo.id + "&sendType=" + sendType;
	$.get(url,
			function(data,status){
		    var d = eval("("+data+")");
		    if(true == d.success) {
		    	var recivMsgs = d.data;
		    	for(i=0;i<recivMsgs.length;i++) {
		    		var j = i;
		    		var oldHtml = $("#showText").html();
		    		
		    		newHtml = "<div id=\"recivModule\"><div id=\"recivUserName\">" + recivMsgs[j].sendUserName + "：</div>";
		    		if(1 == recivMsgs[j].msgType) {
						newHtml += "<div id=\"recivText\">" + recivMsgs[j].msg + "</div></div>";
					} else if(2 == recivMsgs[j].msgType) {
						var src = "/user/files/" + recivMsgs[j].msg;
						var downloadUrl = "/api/download?head=" +head + "&fileName=" + recivMsgs[j].msg;
						newHtml += "<div id=\"recivImg\"><img id=\"img\" src=" + src + " onclick=\"window.open('" + downloadUrl + "')\"></div></div>";
					} else {
						var newFileName = recivMsgs[j].msg;
						var downloadUrl = "/api/download?head=" +head + "&fileName=" + newFileName;
						var oldFileName = newFileName.substring(newFileName.indexOf("-")+1,newFileName.length);
						newHtml += "<div id=\"reicvFile\"><a href=" + downloadUrl + ">文件" + oldFileName + "，点此下载</a></div></div>";
					}
		    		newHtml = oldHtml + newHtml;
					$("#showText").html(newHtml);
		    		limit += 1;
		    	}
			  
		    }
	    });
	setTimeout("recivMsg()",500);
	
}

recivMsg();


