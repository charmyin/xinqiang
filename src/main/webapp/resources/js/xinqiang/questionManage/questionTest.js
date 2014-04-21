/***
 * author: charmyin
 * datetime: 2013-2-26 21:00
 * title: Control the organizationManage.jsp ~
 ***/

//Global the item name
var itemName="";
if(inputTypeValue=="1"){
	itemName = "科目一模拟测试";
}else if(inputTypeValue=="4"){
	itemName = "科目四模拟测试";
}

$(function(){
	$("#spanTitle").text(itemName);
	$("title").html(itemName);
	$("#nextQuestion").click(function(){
		alert("ddddddd");
	});
	
});
