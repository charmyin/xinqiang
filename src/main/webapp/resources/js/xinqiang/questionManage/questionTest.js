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

function initPage(){
	currentIndex+=1;
	correctCount+=1;
	$("#correctCount").html(correctCount);
	$("#progressCount").html(currentIndex+"/"+totalQuestionCount);
}
var myInterval;
function myTimer(){
	timerSeconds--;
	if(timerSeconds==0){
		//Do something
	}
	var minutes = Math.floor(timerSeconds/60);
	var seconds = timerSeconds%60;
	$("#timerCount").html(minutes+"分"+seconds+"秒");
}

$(function(){
	$("#spanTitle").text(itemName);
	$("title").html(itemName);
	$("#nextQuestion").click(function(){
		initPage();
	});
	myInterval=setInterval(function(){myTimer()},1000);
});
