var userId = getUrlParam("userId");
if(null == userId || "" == userId) {
	window.location.href = "/user/login.html";
}
var userInfo = eval("(" + getCookie(userId) + ")");

if (null == userInfo || "" == userInfo) {
	window.location.href = "/user/login.html";
}

var head = userInfo.userName + ":" + userInfo.userPasswd;
$(document).ready(function() {
	var loginSuccess = false;
	$.post("/api/login", {
		head : head,
		userName : userInfo.userName,
		userPasswd : userInfo.userPasswd
	}, function(data, status) {
		temp = eval("(" + data + ")");
		if (false == temp.success) {
			alert(temp.msg);
			window.location.href = "/user/login.html";
		} else {
			loginSuccess = true;
			var cookietime = new Date();
			cookietime.setTime(new Date().getTime() + (60 * 60 * 1000));//coockie保存一小时
			$.cookie(userId, JSON.stringify(temp.data),{expires:cookietime});
			if(1 != temp.data.userPriv) {
				$("#group").hide();
			}
			createUserPage();
		}
	}).error(function() {
		alert("账户或密码错误，请重新登录");
		window.location.href = "/user/login.html";
	});
	
	$(window).bind({
		beforeunload:function() {
			var userInfo = eval("(" + getCookie(userId) + ")");
			head = userInfo.userName + ":" + userInfo.userPasswd;
			if(loginSuccess = true) {
				$.post("/api/logout", {
					head : head,
					userId : userInfo.id
				});
			}
		}
	});
	getRecivMsgs();
	
});


function createUserPage() {
	$( "#right" ).load( "user.html", function( response, status, xhr ) {
		  $('#right').html(response);
		});
	
	
}

function createGroupPage() {
	$( "#right" ).load( "group.html", function( response, status, xhr ) {
		  $('#right').html(response);
		});
}

function createfriendPage() {
	$( "#right" ).load( "friend.html", function( response, status, xhr ) {
		  $('#right').html(response);
		});
}


var getRecivMsgs = function(){
	var url = "/api/queryNotReadMsg?" +"head=" + head +
	"&recivUserId=" + userInfo.id;
	$.get(url,
			function(data,status){
		    var d = eval("("+data+")");
		    if(true == d.success) {
		    	var temp = d.data;
		    	for(i=0; i<temp.length;i++) {
		    		var alertUrl = "/user/prompt.html?sendUserName=" + temp[i].sendUserName +
		    		"&msg=" + escape(temp[i].msg) + "&msgType=" + temp[i].msgType +
		    		"&sendType=" +temp[i].sendType;
		    		layer.open({
		    			  type: 2,
		    			  title: "消息提示",
		    			  shade: [0],
		    			  area: ['340px', '215px'],
		    			  offset: 'rb', //右下角弹出
		    			  time: 5000, //5秒后自动关闭
		    			  shift: 2,
		    			  content: [alertUrl, 'no'] //iframe的url，no代表不显示滚动条
		    			 
		    			});
		    	}
		    	
		    }
	    });
	setTimeout("getRecivMsgs()",1000);
}


