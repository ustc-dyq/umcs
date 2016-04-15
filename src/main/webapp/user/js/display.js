var userId = getUrlParam("userId");
$(document).ready(function() {
	if(null == userId || "" == userId) {
		window.location.href = "/user/login.html";
	}
	var userInfo = eval("(" + getCookie(userId) + ")");
	var loginSuccess = false;
	var head;
	if (null == userInfo || "" == userInfo) {
		window.location.href = "/user/login.html";
	} else {
		head = userInfo.userName + ":" + userInfo.userPasswd;
		
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
				createUserPage();
			}
		}).error(function() {
			alert("账户或密码错误，请重新登录");
			window.location.href = "/user/login.html";
		});
	}
	
	$(window).bind({
		beforeunload:function() {
			var userInfo = eval("(" + getCookie(userId) + ")");
			head = userInfo.userName + ":" + userInfo.userPasswd;
			if(loginSuccess = true) {
				$.post(baseUrl + "/api/logout", {
					head : head,
					userId : userInfo.id
				});
			}
		}
	});
	
});
function load() {
	data = getCookie('data');
	if (null == data || "" == data) {
		window.location.href = baseUrl + "index.html";
	} else {
		userInfo = getUserInfo();
		head = getHead(userInfo);
		$.post(baseUrl + "login", {
			head : head,
			userName : userName,
			userPasswd : userPasswd
		}, function(data, status) {
			document.cookie = "data=" + data;
			if (0 == userInfo.userPriv) {
				$("#group").hide();
			}
			createUserPage();
		}).error(function() {
			window.location.href = baseUrl + "index.html";
		});
	}
	getAllMsgs();
}

function getAllMsgs() {
	userInfo = getUserInfo();
	head = getHead(userInfo);
	var url1 = baseUrl + "recivMsg?" +"head=" + head +
	"&recivUserId=" + userInfo.id;
	$.get(url1,
			function(data,status){
		    var d = eval("("+data+")");
		    if(true == d.success) {
		    	var temp = d.data;
		    	for(i=0; i<temp.length;i++) {
		    		method(temp[i].sendUserId,temp[i].msg);
		    	}
		    	
		    }
	    });
	//查询文件发送
	var url2 = baseUrl + "recivFile?" +"head=" + head +
	"&recivUserId=" + userInfo.id;
	$.get(url2,
			function(data,status){
		    var d = eval("("+data+")");
		    if(true == d.success) {
		    	
		    	var temp = d.data;
		    	for(i=0; i<temp.length;i++) {
		    		method(temp[i].sendUserId,temp[i].originName);
		    	}
		    	
		    }
	    });
	setTimeout("getAllMsgs()",1000);
}

function method(id,msg) {
	userInfo = getUserInfo();
	head = getHead(userInfo);
	var url = baseUrl + "queryUserById?" +"head=" + head +
	"&id=" + id;
	$.get(url,
			function(data,status){
		    var d = eval("("+data+")");
		    if(true == d.success) {
		    	var u = d.data;
		    	alert("用户" + u.userName + "发来：" + msg); 	
		    }
	    });
	
}

function logout() {
	userInfo = getUserInfo();
	head = getHead(userInfo);
	$.cookie(userName + "login=1");
	$.post(baseUrl + "/api/logout", {
		head : head,
		userId : userInfo.id
	});
}

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

function getUserInfo() {
	data = getCookie('data');
	d = eval("(" + data + ")");
	return d.data;
}

function getHead(userInfo) {
	userName = userInfo.userName;
	userPasswd = userInfo.userPasswd;
	head = userName + ":" + userPasswd;
	return head;
}


function getCookie(name) {
	var cookieValue = null;
	if (document.cookie && document.cookie != '') {
		var cookies = document.cookie.split(';');
		for (var i = 0; i < cookies.length; i++) {
			var cookie = jQuery.trim(cookies[i]);
			if (cookie.substring(0, name.length + 1) == (name + '=')) {
				cookieValue = decodeURIComponent(cookie
						.substring(name.length + 1));
				break;
			}
		}
	}
	return cookieValue;
}