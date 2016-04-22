$(document).ready(function() {
	sendUserName = getUrlParam("sendUserName");
	msg = getUrlParam("msg");
	msgType = getUrlParam("msgType");
	sendType = getUrlParam("sendType");
	if(1 == sendType) {
		html = sendUserName + "发来";
	} else if(2 == sendType){
		html = sendUserName + "发来广播";
	} else {
		html = sendUserName + "发来群";
	}
	if(1 == msgType) {
		html += "消息：";
	} else if(2 == msgType) {
		html += "图片：";
	} else {
		html += "文件：";
	}
	html += msg;
	$("#center").html(html);
});