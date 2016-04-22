function getFormatTime(time) {
	var h = time.getHours();
	var mm = time.getMinutes();
	var s = time.getSeconds();
	return add0(h)+':'+add0(mm)+':'+add0(s);
}

function testUserName(name) {
	var reg = /^[a-zA-Z][a-zA-Z0-9_]{3,}$/;
	if (!reg.test(name)) {
		return false;
	} else {
		return true;
	}
}

function testUserPasswd(passwd) {
	var reg = /^\w{3,}$/;
	if (!reg.test(passwd)) {
		return false;
	} else {
		return true;
	}
}

function add0(m){return m<10?'0'+m:m }

function getUrlParam(name){ 
    //构造一个含有目标参数的正则表达式对象  
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");  
    //匹配目标参数  
    var r = window.location.search.substr(1).match(reg);  
    //返回参数值  
    if (r!=null) return unescape(r[2]);  
    return null;  
}  

function getObjectURL(file) {
	var url = null;
	if (window.createObjectURL != undefined) { // basic
		url = window.createObjectURL(file);
	} else if (window.URL != undefined) { // mozilla(firefox)
		url = window.URL.createObjectURL(file);
	} else if (window.webkitURL != undefined) { // webkit or chrome
		url = window.webkitURL.createObjectURL(file);
	}
	return url;
}

//获取cookie的方法
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