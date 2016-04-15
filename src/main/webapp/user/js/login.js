$(document).ready(function() {
	$("#name").focus();
	$("#login").attr("disabled", "true");
	var nameRight = false;
	var passwdRight = false;
	$("#name").bind({
		keyup : function() {
			if (!testUserName($("#name").val())) {
				nameRight = false;
				$("#login").attr("disabled", "true");
			} else {
				if (true == passwdRight) {
					$("#login").removeAttr("disabled");
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
				$("#login").attr("disabled", "true");
			} else {
				if (true == nameRight) {
					$("#login").removeAttr("disabled");
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

	$("#login").click(function() {
		var userName = $("#name").val();
		var userPasswd = $("#passwd").val();
		var head = userName + ":" + userPasswd;
		$.post("/api/preLogin", {
			head : head,
			userName : userName,
			userPasswd : userPasswd
		}, function(data, status) {
			temp = eval("(" + data + ")");
			if (false == temp.success) {
				alert(temp.msg);
			} else {
				var cookietime = new Date();    
				cookietime.setTime(new Date().getTime() + (60 * 60 * 1000));//coockie保存一小时
				$.cookie(temp.data.id, JSON.stringify(temp.data),{expires:cookietime});
				//document.cookie = userName + "=" + JSON.stringify(temp.data);
				window.location.href = "/user/display.html?userId=" + temp.data.id;
			}
		}).error(function() {
			alert("账户或密码错误，请重新登录");
		});
	});
});
