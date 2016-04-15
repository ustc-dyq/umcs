var userInfo = eval("(" + getCookie(userId) + ")");
var head = userInfo.userName + ":" + userInfo.userPasswd;
$(document).ready(function() {
	
	getAllGroups();
	$("#createGroup").click(function(){
		var groupName = $("#groupName").val();
		if(null == groupName || "" == groupName) {
			alert("分组名不能为空");
			return;
		}
		$.post("/api/createGroup", {
			head : head,
			userId : userInfo.id,
			groupName : jQuery.trim(groupName)
		}, function(data, status) {
			var temp = eval("(" + data + ")");
			if (true == temp.success) {
				getAllGroups();
				alert("创建分组成功");
			} else {
				alert(temp.msg);
			}
			
		}).error(function() {
			alert("创建分组失败");
		});
	});
	
});

function changeGroup(object) {
	var id = $(object).parents("tr").find("#groupNo").text();
	var groupName = $(object).parents("tr").find("#groupName").find("#groupName").val();
	if(null == groupName || "" == groupName) {
		alert("分组名不能为空");
		return;
	}
	$.post("/api/changeGroup", {
		head : head,
		id : id,
		groupName : groupName
	}, function(data, status) {
		var temp = eval("(" + data + ")");
		if (true == temp.success) {
			alert("修改分组成功");
		} else {
			alert(d.msg);
		}
	}).error(function() {
		alert("修改分组失败");
	});
}

function deleteGroup(object) {
	var id = $(object).parents("tr").find("#groupNo").text();
	$.post("/api/deleteGroup", {
		head : head,
		id : id
	}, function(data, status) {
		var temp = eval("(" + data + ")");
		if (true == temp.success) {
			getAllGroups();
			alert("删除分组成功");
		} else {
			alert(d.msg);
		}
		;
	}).error(function() {
		alert("删除分组失败");
	});
}

function getAllGroups() {
	var url = "/api/queryUserList?" +"head=" + head +
	"&userId=" + userInfo.id + "&userPriv=" + userInfo.userPriv;
	$.get(url,
			function(data,status){
		    var d = eval("("+data+")");
		    if(true == d.success) {
		    	groups = d.data.groups;
		    	createGroupTable(groups);		    	
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
	$("#groupList").html(html);

}


