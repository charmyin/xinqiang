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
//返回开始界面
var urlBack="questionTest/"+inputTypeValue+"/welcome";

function initPage(){
	currentIndex+=1;
	$("#correctCount").html(correctCount);
	$("#wrongCount").html(wrongCount);
	$("#progressCount").html(currentIndex+"/"+totalQuestionCount);
	//当最后一题时候，交卷
	if(currentIndex==totalQuestionCount){
		$("#nextQuestion").text("交卷");
		$( "#nextQuestion").unbind( "click" );
		$("#nextQuestion").click(function(){
			postScore("答题完毕");
		});
	}
}
var myInterval;
function myTimer(){
	timerSeconds--;
	var minutes = Math.floor(timerSeconds/60);
	var seconds = timerSeconds%60;
	$("#timerCount").html(minutes+"分"+seconds+"秒");
	if(timerSeconds==0){
		//Do something
		clearInterval(myInterval);
		postScore("时间结束");
	}
}

function postScore(message){
	$.ajax({
		  type: "POST",
		  url: "score/saveScore",
		  data:{score:correctCount, type:inputTypeValue}
		}).done(function( msg ) {
			//alert(msg);
			alert(message+",共错"+wrongCount+"题");
			window.location=urlBack;
		});
	
}

var currentQuestion;
var videoIndex=0;
var videoFlag=false;
var imageFlag=false;

function setQuestion(){
	
	$.ajax({
		  type: "GET",
		  url: "questionTest/"+inputTypeValue+"/randomQuestion"
		}).done(function( msg ) {
			currentQuestion=msg;
			$(".chooseItems").remove();
			$("#questionContent").html("<strong>"+currentIndex+".</strong>"+msg.content);
			//判断题
			if(msg.choosec=="" && msg.choosed==""){
				var contentb ='	<a class="list-group-item chooseItems"><label><input class="answerClass" type="radio" name="answerradio" value="B"/><strong class="innerTitle">错误.</strong></label></a>';
				$("#questionContent").after(contentb);
				var contenta ='	<a class="list-group-item chooseItems"><label><input class="answerClass" type="radio" name="answerradio" value="A"/><strong class="innerTitle">正确.</strong>'+msg.choosea+'</label></a>';
				$("#questionContent").after(contenta);
			}else{
				//选择题
				var contentd ='	<a class="list-group-item chooseItems"><label><input class="answerClass" type="checkbox" name="answerd" value="D"/><strong class="innerTitle">D.</strong>'+msg.choosed+'</label></a>';
				$("#questionContent").after(contentd);
				var contentc ='	<a class="list-group-item chooseItems"><label><input class="answerClass" type="checkbox" name="answerc" value="C"/><strong class="innerTitle">C.</strong>'+msg.choosec+'</label></a>';
				$("#questionContent").after(contentc);
				var contentb ='	<a class="list-group-item chooseItems"><label><input  class="answerClass" type="checkbox" name="answerb" value="B"/><strong class="innerTitle">B.</strong>'+msg.chooseb+'</label></a>';
				$("#questionContent").after(contentb);
				var contenta ='	<a class="list-group-item chooseItems"><label><input class="answerClass" type="checkbox" name="answera" value="A"/><strong class="innerTitle">A.</strong>'+msg.choosea+'</label></a>';
				$("#questionContent").after(contenta);
			}
			if(videoFlag){
			//清理所有的flv播放器
				videoFlag=false;
				$(".imgVideoShow").remove();
				$.each(_V_.players, function (key, player) { 
				    if (player.isReady) { player.destroy(); } 
				    else { delete _V_.players[player.id]; } 
				}); 
			}
			if(imageFlag){
				imageFlag=false;
				$(".imgVideoShow").remove();
			}
			if(msg.imageVideoPath.indexOf("jpg") > -1){
				var imageTag = "<div class='list-group-item imgVideoShow'><img style='width:300px; height:160px;' src='upload/"+msg.imageVideoPath+"' /></div>";
				$("#questionContent").after(imageTag);
				imageFlag=true;
			}
			
			if(msg.imageVideoPath.indexOf("flv") > -1){
				videoIndex++;
				videoFlag=true;
				var videoTag = '<div class="list-group-item imgVideoShow"><video id="video'+videoIndex+'" class="video-js vjs-default-skin imgVideoShow" width="360" height="150"><source src=upload/'+msg.imageVideoPath+' type="video/x-flv"></video></div>';
				$("#questionContent").after(videoTag);
				videojs("video"+videoIndex,{ "controls": true, "autoplay": false, "preload": "auto" }).ready(function(){
					  myPlayer = this;
					  myPlayer.play();
				});
			}
		  });
}
var answerFinal="";

//如果没有选择答案，返回1，否则返回0
function compareAnswer(){
	$(".answerClass:checked").each(function(index){
		if(index!=0){
			answerFinal+=",";
		}
		answerFinal+=$(this).val();
	});
	if(answerFinal==""){
		alert("请选择答案！");
		return 1;
	}
	//alert(answerFinal+"---right: --"+currentQuestion.answer);
	if(answerFinal==currentQuestion.answer){
		//alert("答案正确，注解:"+currentQuestion.remark);
		correctCount+=1;
	}else{
		alert("答案错误!!答案："+currentQuestion.answer+".  注解："+currentQuestion.remark);
		wrongCount+=1;
	}
	answerFinal="";
	return 0;
	
}

$(function(){
	
	videojs.options.flash.swf = "resources/vendor/videojs/video-js.swf";
	
	$("#spanTitle").text(itemName);
	$("title").html(itemName);
	$("#nextQuestion").click(function(){
		//如果没有选择答案，返回1，否则返回0
		var result = compareAnswer();
		if(result==0){
			setQuestion();
			//设题数，正确错误等
			initPage();
		}
		
	});
	myInterval=setInterval(function(){myTimer()},1000);
	//初始化第一题
	setQuestion();
});
