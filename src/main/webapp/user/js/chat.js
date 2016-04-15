var sendUserId;
var recivUserId;
var recivUserName;
var head;
var userInfo;
var limit = 5;
var baseUrl = "http://localhost:8080/webDemo/";
var fileList;
//加载页面初始信息
function load() {
	recivUserId = getUrlParam("recivUserId");
	recivUserName = decodeURI(getUrlParam("recivUserName"));
	
	userInfo = getUserInfo();
	head = getHead(userInfo);
	sendUserId = userInfo.id;
	$("#recivUserName").val(recivUserName);
	queryHistoryMsg(userInfo,limit);
	queryFileList(userInfo);
	recivMsg();
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

//发送消息
function sendMsg() {
	userInfo = getUserInfo();
	head = getHead(userInfo);
	var msg = $("#msg").val();
	if(null == msg || "" == msg || "" == (jQuery.trim(msg))) {
		alert("不能发送空消息");
		return;
	}
	msg = jQuery.trim(msg);
	var oldText = $("#text").val();
	var time = new Date();
	
	if(null != oldText && "" != oldText) {
		newText = oldText + "\n" + userInfo.userName + " " + getFormatTime(time) + " " + msg;
	} else {
		newText = userInfo.userName + " " + getFormatTime(time) + "  " + msg;
	}
	$("#text").val(newText);
	$("#msg").val("");
	var msgType = 1;
	
	$.post(baseUrl + "sendMsg",
			  {
		 head:head,
		 sendUserId:sendUserId,
		 recivUserId:recivUserId,
		 msg:msg,
		 msgType:msgType
			  },
			  function(data,status){
				  var d = eval("("+data+")");
				  if(true != d.success) {
					  alert(d.msg)
				  }
			  }
			  ).error(function(){
					alert("发送消息失败");
				});
	
}

function queryHistoryMsg(userInfo,limit) {
	userInfo = getUserInfo();
	head = getHead(userInfo);
	var url = baseUrl + "queryHistoryMsg?" + "head=" + head +
	"&sendUserId=" + sendUserId + "&recivUserId=" + recivUserId + "&limit=" + limit;
	$.get(url,
			function(data,status){
		    var d = eval("("+data+")");
		    if(true == d.success) {
		    	var sendMsgs = d.data;
		    	var newText="";
		    	for(i=0;i<sendMsgs.length;i++) {
		    		if(sendMsgs[i].msgType == 1) {
						  var time = new Date(sendMsgs[i].sendTime);
						  if(recivUserId == sendMsgs[i].sendUserId) {
							  newText += recivUserName + " " + getFormatTime(time) + "  " + sendMsgs[i].msg;
						  }
						  if(sendUserId == sendMsgs[i].sendUserId) {
							  newText += userInfo.userName + " " +getFormatTime(time) + "  " + sendMsgs[i].msg;
						  }
						  newText +="\n";
					  }
		    	}
		    	$("#text").val(newText);
		    }
	    });
	
}


function recivMsg() {
	userInfo = getUserInfo();
	head = getHead(userInfo);
	var url = baseUrl + "recivMsg?" + "head=" + head +
	"&sendUserId=" + recivUserId +"&recivUserId=" + sendUserId;
	$.get(url,
			function(data,status){
		    var d = eval("("+data+")");
		    if(true == d.success) {
		    	var recivMsgs = d.data;
		    	for(i=0;i<recivMsgs.length;i++) {
		    		if(recivMsgs[i].msgType == 1) {
						  var oldText = $("#text").val();
						  var newText;
						  var time = new Date(recivMsgs[i].recivTime);
						  if(null != oldText && "" != oldText) {
								newText = oldText + "\n" + recivUserName + " " + getFormatTime(time) + "  " + recivMsgs[i].msg;
							} else {
								newText = recivUserName + " " +getFormatTime(time) + "  " + recivMsgs[i].msg;
							}
						  $("#text").val(newText);
					  }
		    	}
			  
		    }
	    });
	queryFileList()
	setTimeout("recivMsg()",500);
}

function clearText() {
	$("#text").val("");
	limit = 0;
}

function uploadFile(){
    $.ajaxFileUpload({
            url:baseUrl + "file/upload",            //需要链接到服务器地址  
            type: 'post',
            data:{
       		 head:head,
    		 sendUserId:userInfo.id,
    		 recivUserId : recivUserId
    			  },
            secureuri:true, 
            fileElementId:"file",                        //文件选择框的id属性  
            success: function(data, status){ 
            	var d = eval("("+data.body.innerText+")");
            	if(true == d.success) {
            		alert("上传成功");
            	} else {
            		alert(d.msg);
            	}
            	
            },error: function (data, status, e){  
            	alert("上传失败请重新上传");  
            }  
        });  
}  

function createFileTable(files) {
	
	var html = "<table id=\"fileList\">";
	html += "<tr id=\"fileHead\"><td width=\"50\">编号</td><td width=\"100\">发送人</td><td width=\"100\">文件名</td><td width=\"100\">下载</td><td width=\"100\">删除</td></tr>";
	if(null == files) {
		html += "</table>";
		$("#files").html(html);
		return;
	}
	for(i=0; i<files.length; i++) {
		
		html += "<tr><td id=\"fileId\">" + files[i].id + "</td>";
		
		
		html += "<td id=\"userName\">" + recivUserName + "</td>";
		html += "<td id=\"file\"><a href=" + files[i].remoteUrl + ">"+ files[i].originName +"</a></td>";
		url = "download?" + "head=" + head + "&id=" + files[i].id;
		html += "<td id=\"file\"><a href=" + url + ">下载</a></td>";
		html += "<td><button id=\"delete\" type=\"button\" onclick=\"deleteFile(this)\">删除</button></td>";
		html += "</tr>";
	}
	html += "</table>";
	$("#right").html(html);
}

function queryFileList() {
	var url = baseUrl + "queryFileList?" +"head=" + head +
	"&recivUserId=" + userInfo.id + "&sendUserId=" + recivUserId;
	$.get(url,
			function(data,status){
		    var d = eval("("+data+")");
		   
		    
		    if(true == d.success) {
		    	 if(null == fileList || "" == fileList) {
		    		 fileList = d.data;
		    		 createFileTable(fileList);
				 } else {
				     if(!isFileEqual(fileList,d.data)) {
				    	 fileList = d.data;
			    		 createFileTable(fileList);
				     }
				 }
		    	
		    }
	    });
}

function isFileEqual(files1,files2) {
	var flag = true;
	if(files1.length == files2.length) {
		for(i=0; i<files1.length; i++) {
			if(files1[i].id != files2[i].id) {
				flag = false;
			}
		}
	} else {
		flag = false;
	}
	return flag;
}

function deleteFile(object) {
	var id = $(object).parents("tr").find("#fileId").text();
	var url = baseUrl + "deleteFile?" +"head=" + head +
	"&id=" + id;
	$.get(url,
			function(data,status){
		    var d = eval("("+data+")");
		    if(true == d.success) {		    	
		    	alert("删除成功");		    	
		    }
	    }).error(function() {
			alert("删除失败");
		});
}

function queryMore() {
	var data = getCookie('data');
	var d = eval("("+data+")");
	var userInfo = d.data;
	limit += 5;
	queryHistoryMsg(userInfo,limit);
}

function getFormatTime(time) {
	var h = time.getHours();
	var mm = time.getMinutes();
	var s = time.getSeconds();
	return add0(h)+':'+add0(mm)+':'+add0(s);
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

function getCookie(name) {
	 var cookieValue = null;
	   if (document.cookie && document.cookie != '') {
	    var cookies = document.cookie.split(';');
	    for (var i = 0; i < cookies.length; i++) {
	     var cookie = jQuery.trim(cookies[i]);
	     if (cookie.substring(0, name.length + 1) == (name + '=')) {
	      cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
	      break;
	     }
	    }
	   }
	   return cookieValue;
}
