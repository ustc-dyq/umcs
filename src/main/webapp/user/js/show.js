
var groups;
var users;
var baseUrl = "http://localhost:8080/webDemo/";
function check() {
	data = getCookie('data');
	if (null == data || "" == data) {
		window.location.href = baseUrl + "index.html";
	} else {
		d = eval("(" + data + ")");
		userInfo = d.data;
		userName = userInfo.userName;
		userPasswd = userInfo.userPasswd;
		head = userName + ":" + userPasswd;
		$.post(baseUrl + "login", {
			head : head,
			userName : userName,
			userPasswd : userPasswd
		}, function(data, status) {
			document.cookie = "data=" + data;
			load();
		}).error(function() {
			window.location.href = baseUrl + "index.html";
		});
		
	}
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

function logout() {
	userInfo = getUserInfo();
	head = getHead(userInfo);
	$.post(baseUrl + "logout", {
		head : head,
		userId : userInfo.id
	});
}

function load() {
	userInfo = getUserInfo();
	document.getElementById("imgShow").src = userInfo.imgPath;
	if (0 == userInfo.userPriv) {
		$("#createUser").hide();
		$("#newGroup").hide();
		$("#menu").hide();
	}
	getAllUsers();	
	getAllMsgs();
}

function uploadFile() {
	userInfo = getUserInfo();
	head = getHead(userInfo);
	$.ajaxFileUpload({
		url : baseUrl + "headImg/upload", // 需要链接到服务器地址
		type : 'post',
		data : {
			head : head,
			id : userInfo.id
		},
		secureuri : true,
		fileElementId : 'file', // 文件选择框的id属性
		success : function(data, status) {
			var d = eval("(" + data.body.innerText + ")");
			if(true == d.success) {
				alert("上传头像成功");
				document.getElementById("imgShow").src = d.data;
			}
		},
		error : function(data, status, e) {
			alert("上传失败请重新上传");
		}
	});
}

function createUser() {
	newUserName = document.getElementById("userName").value;
	newUserPasswd = document.getElementById("userPasswd").value;
	if(null == newUserName || "" == newUserName) {
		alert("用户名不能为空");
		return;
	}
	if(null == newUserPasswd || "" == newUserPasswd) {
		alert("密码不能为空");
		return;
	}
	$.post(baseUrl + "createUser", {
		head : head,
		userName : jQuery.trim(newUserName),
		userPasswd : newUserPasswd
	}, function(data, status) {
		var d = eval("(" + data + ")");
		if (true == d.success) {
			getAllUsers();
			alert("创建用户成功");
		} else {
			alert(d.msg);
		}
		;
	}).error(function() {
		alert("创建用户失败");
	});

}

function changeUser() {
	data = getCookie('data');
	d = eval("(" + data + ")");
	userInfo = getUserInfo();
	head = getHead(userInfo);
	newUserName = document.getElementById("userName").value;
	newUserPasswd = document.getElementById("userPasswd").value;
	if(null == newUserName || "" == newUserName) {
		alert("用户名不能为空");
		return;
	}
	if(null == newUserPasswd || "" == newUserPasswd) {
		alert("密码不能为空");
		return;
	}
	$.post(baseUrl + "modifyUser", {
		head : head,
		userId : userInfo.id,
		userName : jQuery.trim(newUserName),
		userPasswd : newUserPasswd
	}, function(data, status) {
		var temp = eval("(" + data + ")");
		if (true == temp.success) {
			alert("修改用户成功");
			d.data.userName = newUserName;
			d.data.userPasswd = newUserPasswd;
			document.cookie = "data=" + JSON.stringify(d);
		} else {
			alert(temp.msg);
		}
		;
	}).error(function() {
		alert("修改用户失败,用户名重复");
	});
}

function getAllUsers() {
	userInfo = getUserInfo();
	head = getHead(userInfo);
	var url = baseUrl + "queryUserList?" +"head=" + head +
	"&userId=" + userInfo.id + "&userPriv=" + userInfo.userPriv;
	$.get(url,
			function(data,status){
		    var d = eval("("+data+")");
		    if(true == d.success) {
		    	users = d.data.users;
		    	groups = d.data.groups;
		    	if(1 == userInfo.userPriv) {
		    		createGroupTable(groups);
			    	createUserTable(users);
		    	} else {
		    		createFriendsTable(users)
		    	}
		    	
		    }
	    });
}

function checkUsers() {
	userInfo = getUserInfo();
	head = getHead(userInfo);
	var url = baseUrl + "queryUserList?" +"head=" + head +
	"&userId=" + userInfo.id + "&userPriv=" + userInfo.userPriv;
	$.get(url,
			function(data,status){
		    var d = eval("("+data+")");
		    if(true == d.success) {
		    	if(1 == userInfo.userPriv) {
		    		if(!isUsersEqual(users,d.data.users)) {
			    		users = d.data.users;
			    		createUserTable(users);
			    	}
		    		if(!isGroupEqual(groups,d.data.groups)) {
		    			groups = d.data.groups;
		    			createGroupTable(groups);
		    		}
		    	} else {
		    		if(!isUsersEqual(users,d.data.users)) {
			    		users = d.data.users;
			    		createFriendsTable(users);
			    	}
		    	}
		    	
		    }
	    });
}

function isUsersEqual(users1,users2) {
	var flag = true;
	if(users1.length == users2.length) {
		for(i=0; i<users1.length; i++) {
			if(users1[i].id != users2[i].id || users1[i].userName != users2[i].userName ||
					users1[i].groupId != users2[i].groupId || users1[i].isOnline != users2[i].isOnline) {
				flag = false;
			}
		}
	} else {
		flag = false;
	}
	return flag;
}

function isGroupEqual(groups1,groups2) {
	var flag = true;
	if(groups1.length == groups2.length) {
		for(i=0; i<groups1.length; i++) {
			if(groups1[i].id != groups2[i].id || groups1[i].groupName != groups2[i].groupName) {
				flag = false;
			}
		}
	} else {
		flag = false;
	}
	return flag;
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
	checkUsers();
	//setTimeout("getAllMsgs()",1000);
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

function createGroupTable(groups) {
	
	var html = "<table id=\"groupTable\">";
	html += "<tr id=\"groupHead\"><td>编号</td><td>分组</td><td>修改</td><td>删除</td></tr>";
	
	for(i=0; i<groups.length; i++) {
		html += "<tr><td id=\"groupNo\">" + groups[i].id + "</td>";
		html += "<td id=\"groupName\"><input id=\"groupName\" type=\"text\"" + " name=\"groupName\"" + 
		" style=\"width:100px;\"" +	" value=" + groups[i].groupName + " /></td>";
		html += "<td><button id=\"save\" type=\"button\" onclick=\"changeGroup(this)\">修改</button></td>";
		html += "<td><button id=\"delete\" type=\"button\" onclick=\"deleteGroup(this)\">删除</button></td>";
		html += "</tr>";
	}
	
	html += "</table>";
	$("#menu").html(html);

}

function createUserTable(users) {
	var html = "<table id=\"userTable\">";
	html += "<tr id=\"userHead\"><td width=\"50\">编号</td><td width=\"100\">用户名</td><td width=\"50\">分组</td><td width=\"50\">在线</td><td width=\"50\">保存</td><td width=\"50\">聊天</td><td width=\"50\">删除</td></tr>";
	for(i=0; i<users.length; i++) {
		if(userInfo.id == users[i].id) {
			continue;
		}
		html += "<tr><td id=\"userId\">" + users[i].id + "</td>";
		html += "<td id=\"userName\">" + users[i].userName + "</td>";
		html += "<td id=\"group\"><select id=\"select\">";
		html += "<option value=0>" + "无分组" + "</option>";
		for(j=0; j< groups.length;j++) {
			if(users[i].groupId == groups[j].id) {
				html += "<option value=" + groups[j].id + " selected>" + groups[j].groupName + "</option>";
			} else {
				html += "<option value=" + groups[j].id + ">" + groups[j].groupName + "</option>";
			}
		}
		html += "</select></td>";
		if(1 == users[i].isOnline) {
			html += "<td id=\"online\">" + "是" + "</td>";
		} else {
			html += "<td id=\"online\">" + "否" + "</td>";
		}
		html += "<td><button id=\"save\" type=\"button\" onclick=\"modifyGroupMember(this)\">保存</button></td>";
		html += "<td><button id=\"chat\" type=\"button\" onclick=\"chat(this)\">聊天</button></td>";
		html += "<td><button id=\"delete\" type=\"button\" onclick=\"deleteUser(this)\">删除</button></td>";
		html += "</tr>";
	}
	html += "</table>";
	$("#content").html(html);
}

function createFriendsTable(users) {
	var html = "<table id=\"userTable\">";
	html += "<tr id=\"userHead\"><td width=\"50\">编号</td><td width=\"100\">用户名</td><td width=\"80\">分组</td><td width=\"50\">在线</td><td width=\"100\">聊天</td></tr>";
	for(i=0; i<users.length; i++) {
		if(userInfo.id == users[i].id) {
			continue;
		}
		html += "<tr><td id=\"userId\">" + users[i].id + "</td>";
		html += "<td id=\"userName\">" + users[i].userName + "</td>";
		html += "<td id=\"group\">" + groups[0].groupName + "</td>";
		if(1 == users[i].isOnline) {
			html += "<td id=\"online\">" + "是" + "</td>";
		} else {
			html += "<td id=\"online\">" + "否" + "</td>";
		}
		html += "<td><button id=\"chat\" type=\"button\" onclick=\"chat(this)\">聊天</button></td>";
		
		html += "</tr>";
	}
	html += "</table>";
	$("#friends").html(html);
}

function createGroup() {
	userInfo = getUserInfo();
	head = getHead(userInfo);
	var groupName = $("#groupName").val();
	if(null == groupName || "" == groupName) {
		alert("分组名不能为空");
		return;
	}
	$.post(baseUrl + "createGroup", {
		head : head,
		userId : userInfo.id,
		groupName : jQuery.trim(groupName)
	}, function(data, status) {
		var temp = eval("(" + data + ")");
		if (true == temp.success) {
			getAllUsers();
			alert("创建分组成功");
		} else {
			alert(temp.msg);
		}
		;
	}).error(function() {
		alert("创建分组失败");
	});
}

function changeGroup(object) {
	userInfo = getUserInfo();
	head = getHead(userInfo);
	var id = $(object).parents("tr").find("#groupNo").text();
	var groupName = $(object).parents("tr").find("#groupName").find("#groupName").val();
	if(null == groupName || "" == groupName) {
		alert("分组名不能为空");
		return;
	}
	$.post(baseUrl + "changeGroup", {
		head : head,
		id : id,
		groupName : groupName
	}, function(data, status) {
		var temp = eval("(" + data + ")");
		if (true == temp.success) {
			getAllUsers();
			alert("修改分组成功");
		} else {
			alert(d.msg);
		}
	}).error(function() {
		alert("修改分组失败");
	});
}

function deleteGroup(object) {
	userInfo = getUserInfo();
	head = getHead(userInfo);
	var id = $(object).parents("tr").find("#groupNo").text();
	$.post(baseUrl + "deleteGroup", {
		head : head,
		id : id
	}, function(data, status) {
		var temp = eval("(" + data + ")");
		if (true == temp.success) {
			getAllUsers();
			alert("删除分组成功");
		} else {
			alert(d.msg);
		}
		;
	}).error(function() {
		alert("删除分组失败");
	});
}

function modifyGroupMember(object) {
	userInfo = getUserInfo();
	head = getHead(userInfo);
	var groupId = $(object).parents("tr").find("#group").find("#select").val();
	var userId = $(object).parents("tr").find("#userId").text();
	$.post(baseUrl + "modifyGroupMember", {
		head : head,
		userId : userId,
		groupId : groupId
	}, function(data, status) {
		var temp = eval("(" + data + ")");
		if (true == temp.success) {
			alert("保存成功");
		} else {
			alert(d.msg);
		}
		;
	}).error(function() {
		alert("保存失败");
	});
}

function deleteUser(object) {
	userInfo = getUserInfo();
	head = getHead(userInfo);
	var id = $(object).parents("tr").find("#userId").text();
	$.post(baseUrl + "deleteUser", {
		head : head,
		id : id
	}, function(data, status) {
		var temp = eval("(" + data + ")");
		if (true == temp.success) {
			getAllUsers();
			alert("删除用户成功");
		} else {
			alert(d.msg);
		}
		;
	}).error(function() {
		alert("删除用户失败");
	});
}

function chat(object) {
	var recivUserId = $(object).parents("tr").find("#userId").text();
	var recivUserName = $(object).parents("tr").find("#userName").text();
	var url = baseUrl + "chat.html?recivUserId=" +
	recivUserId + "&recivUserName=" + recivUserName;
	var temp = encodeURI(encodeURI(url));
	//window.location.href=temp;
	window.open(temp,"","");
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