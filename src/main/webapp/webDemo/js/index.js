function login() {
		var userName = document.getElementById("name").value;
		var userPasswd = document.getElementById("passwd").value;
		var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				window.location.href='http://localhost:8080/webDemo/show.html';
			}
			if(xmlhttp.readyState == 4 && xmlhttp.status != 200) {
				alert("身份校验失败，请重新登录");
			}
		}
		xmlhttp
				.open(
						"POST",
						"http://localhost:8080/webDemo/login",
						true);
		
		var param = "userName=" + userName + "&userPasswd=" + userPasswd;
		xmlhttp.setRequestHeader("user",userName + ":" + userPasswd);
		xmlhttp.send(param);
		
	}

function ajaxGet() {
	$.get("http://localhost:8080/webDemo/queryUserList?userId=1&userPriv=1",function(data,status){
	      alert("数据：" + data + "\n状态：" + status);
	    });
}
	
function ajaxPost() {
	 $.post("http://localhost:8080/webDemo/login",
			  {
		 userName:"root",
		 userPasswd:"123"
			  },
			  function(data,status){
			    alert("Data: " + data + "\nStatus: " + status);
			  });
}
	
