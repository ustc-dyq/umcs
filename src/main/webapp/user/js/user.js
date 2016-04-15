$(document).ready(
		function() {
			var userInfo = eval("(" + getCookie(userId) + ")");
			head = userInfo.userName + ":" + userInfo.userPasswd;
			$("#imgShow").attr("src","/user/headImg/" + userInfo.imgName);
			$("#newName").val(userInfo.userName);
			$("#newPasswd").val(userInfo.userPasswd);
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
                if (size > 2 * 1024 * 1024) {
                    alert("上传的图片大小不能超过2M！");
                    return false;
                }
				var objUrl = getObjectURL(this.files[0]);
				
				if (objUrl) {
					document.getElementById("imgShow").src = objUrl;
				}
			});
			
			$("#uploadImg").click(function() {
				$.ajaxFileUpload({
					url : "/api/headImg/upload", // 需要链接到服务器地址
					type : 'post',
					data : {
						head : head,
						id : userInfo.id
					},
					secureuri : true,
					fileElementId : 'imgFile', // 文件选择框的id属性
					success : function(data, status) {
						var d = eval("(" + data.body.innerText + ")");
						if (true == d.success) {
							alert("上传头像成功");
							document.getElementById("imgShow").src =  "/user/headImg/" + d.data;
						} else {
							alert(d.msg);
						}
					},
					error : function(data, status, e) {
						alert("上传失败请重新上传");
					}
				});
			});
			var nameRight = true;
			var passwdRight = true;
			$("#newName").bind({
				keyup : function() {
					if (!testUserName($("#newName").val())) {
						nameRight = false;
						$("#changeUser").attr("disabled", "true");
					} else {
						if (true == passwdRight) {
							$("#changeUser").removeAttr("disabled");
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

			$("#newPasswd").bind({
				keyup : function() {
					if (!testUserPasswd($("#newPasswd").val())) {
						passwdRight = false;
						$("#changeUser").attr("disabled", "true");
					} else {
						if (true == nameRight) {
							$("#changeUser").removeAttr("disabled");
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
			
			$("#changeUser").click(function() {
				newUserName = $("#newName").val();
				newUserPasswd = $("#newPasswd").val();
				$.post("/api/modifyUser", {
					head : head,
					userId : userInfo.id,
					userName : jQuery.trim(newUserName),
					userPasswd : newUserPasswd
				}, function(data, status) {
					var temp = eval("(" + data + ")");
					if (true == temp.success) {
						alert("修改用户成功");
						userInfo.userName = newUserName;
						userInfo.userPasswd = newUserPasswd;
						var cookietime = new Date();
						cookietime.setTime(new Date().getTime() + (60 * 60 * 1000));//coockie保存一小时
						$.cookie(userInfo.id, JSON.stringify(userInfo),{expires:cookietime});
					} else {
						alert(temp.msg);
					}
					;
				}).error(function() {
					alert("修改用户失败,用户名重复");
				});
			});

		});




