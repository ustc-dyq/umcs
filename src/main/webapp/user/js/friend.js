var userInfo = eval("(" + getCookie(userId) + ")");
var head = userInfo.userName + ":" + userInfo.userPasswd;
var users;
$(document).ready(function() {
	if(0 == userInfo.userPriv) {
		$("#newUser").hide();
		$("#broadCast").hide();
	}
	if(0 ==  userInfo.groupId) {
		$("#groupChat").hide();
	}
	var nameRight = false;
	var passwdRight = false;
	$("#createUser").attr("disabled", "true");
	$("#name").bind({
		keyup : function() {
			if (!testUserName($("#name").val())) {
				nameRight = false;
				$("#createUser").attr("disabled", "true");
			} else {
				if (true == passwdRight) {
					$("#createUser").removeAttr("disabled");
					nameRight = true;
				} else {
					nameRight = true;
				}
			}

		},
		blur : function() {
			if (nameRight == false) {
				layer.alert("用户名格式错误");
			}
		}
	});

	$("#passwd").bind({
		keyup : function() {
			if (!testUserPasswd($("#passwd").val())) {
				passwdRight = false;
				$("#createUser").attr("disabled", "true");
			} else {
				if (true == nameRight) {
					$("#createUser").removeAttr("disabled");
					passwdRight = true;
				} else {
					passwdRight = true;
				}
			}

		},
		blur : function() {
			if (passwdRight == false) {
				layer.alert("密码格式错误");
			}
		}
	});
	$("#createUser").click(function(){
		newUserName = $("#name").val();;
		newUserPasswd = $("#passwd").val();
		$.post("/api/createUser", {
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
	});
	
	$("#groupChat").click(function(){
		var url ="/user/newChat.html?recivId=" + userInfo.groupId +
		"&sendType=" + 3 + "&sendUserId=" + userInfo.id;
		layer.open({
			type: 2,
			  title: "聊天室",
			  shade: [0],
			  area: ['550px', '650px'],
			  
			  content: [url, 'no'], //iframe的url，no代表不显示滚动条
			  
			}); 
	});
	
	$("#broadCast").click(function(){
		var url ="/user/newChat.html?recivId=" + 0 +
		"&sendType=" + 2 + "&sendUserId=" + userInfo.id;
		layer.open({
			type: 2,
			  title: "广播",
			  shade: [0],
			  area: ['550px', '650px'],
			  
			  content: [url, 'no'], //iframe的url，no代表不显示滚动条
			  
			}); 
	});
	
	
	var getAllUsers = function() {
		var url = "/api/queryUserList?" +"head=" + head +
		"&userId=" + userInfo.id + "&userPriv=" + userInfo.userPriv;
		$.get(url,
				function(data,status){
			    var d = eval("("+data+")");
			    if(true == d.success) {
			    	users = d.data.users;
			    	groups = d.data.groups;
			    	if(1 == userInfo.userPriv) {	    		
				    	createUserTable(users);
			    	} else {
			    		$("#newUser").hide();
			    		createFriendsTable(users);
			    	}
			    	
			    }
		    });
	}
	
	
	getAllUsers();
});

var chatToOne = function(object) {
	var recivId = $(object).parents("tr").find("#userId").text();
	var recivName = $(object).parents("tr").find("#userName").text();
	var url ="/user/newChat.html?recivId=" + recivId +
	"&sendType=" + 1 + "&sendUserId=" + userInfo.id;
	layer.open({
		type: 2,
		  title: recivName,
		  shade: [0],
		  area: ['550px', '650px'],
		  
		  content: [url, 'no'], //iframe的url，no代表不显示滚动条
		  
		}); 
};


var isUsersEqual = function(users1,users2) {
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

var createUserTable = function(users) {
	var html = "<table id=\"userTable\">";
	html += "<tr id=\"userHead\"><td width=\"10%\">编号</td><td width=\"10%\">用户名</td><td width=\"10%\">分组</td><td width=\"10%\">在线</td><td width=\"10%\">保存</td><td width=\"10%\">聊天</td><td width=\"10%\">删除</td></tr>";
	for(i=0; i<users.length; i++) {
		if(userInfo.id == users[i].id) {
			continue;
		}
		html += "<tr><td id=\"userId\">" + users[i].id + "</td>";
		html += "<td id=\"userName\">" + users[i].userName + "</td>";
		html += "<td id=\"groupId\"><select id=\"select\">";
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
		html += "<td><button id=\"chat\" type=\"button\" onclick=\"chatToOne(this)\">聊天</button></td>";
		html += "<td><button id=\"delete\" type=\"button\" onclick=\"deleteUser(this)\">删除</button></td>";
		html += "</tr>";
	}
	html += "</table>";
	$("#friends").html(html);
}

var createFriendsTable = function(users) {
	var html = "<table id=\"userTable\">";
	html += "<tr id=\"userHead\"><td width=\"10%\">编号</td><td width=\"10%\">用户名</td><td width=\"10%\">分组</td><td width=\"10%\">在线</td><td width=\"10%\">聊天</td></tr>";
	for(i=0; i<users.length; i++) {
		if(userInfo.id == users[i].id) {
			continue;
		}
		html += "<tr><td id=\"userId\">" + users[i].id + "</td>";
		html += "<td id=\"userName\">" + users[i].userName + "</td>";
		if(1 == users[i].userPriv || null == groups || null == groups[0]) {
			html += "<td id=\"groupId\">" + "无分组" + "</td>";
		} else {
			html += "<td id=\"groupId\">" + groups[0].groupName + "</td>";
		}
		
		if(1 == users[i].isOnline) {
			html += "<td id=\"online\">" + "是" + "</td>";
		} else {
			html += "<td id=\"online\">" + "否" + "</td>";
		}
		html += "<td><button id=\"chat\" type=\"button\" onclick=\"chatToOne(this)\">聊天</button></td>";
		
		html += "</tr>";
	}
	html += "</table>";
	$("#friends").html(html);
}


var checkUsers = function() {
	var url = "/api/queryUserList?" +"head=" + head +
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
		    	} else {
		    		if(!isUsersEqual(users,d.data.users)) {
			    		users = d.data.users;
			    		createFriendsTable(users);
			    	}
		    	}
		    	
		    }
	    });

	setTimeout("checkUsers()",1000);
};


var modifyGroupMember = function(object) {
	var groupId = $(object).parents("tr").find("#groupId").find("#select").val();
	var userId = $(object).parents("tr").find("#userId").text();
	$.post("/api/modifyGroupMember", {
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

var deleteUser = function(object) {
	var id = $(object).parents("tr").find("#userId").text();
	$.post("/api/deleteUser", {
		head : head,
		id : id
	}, function(data, status) {
		var temp = eval("(" + data + ")");
		if (true == temp.success) {
			alert("删除用户成功");
		} else {
			alert(d.msg);
		}
		;
	}).error(function() {
		alert("删除用户失败");
	});
}


checkUsers();	
