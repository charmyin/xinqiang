<%
/**
 * This is the Question Test Manage page~ 
 * @author charmyin
 * @since 2014-4-21 15:34:28
 * @serial 1.0
 */
%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tlds/cmstudio.tld" prefix="cmstudio" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <cmstudio:htmlBase/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" type="image/x-icon" href="resources/${icon_name}"/>

    <title>模拟测试</title>
    <style type="text/css">
    	.list-group-item label{
    		font-size:15px;
    		font-weight:bold;	
    	}
    </style>

    <!-- Bootstrap core CSS -->
    <link href="resources/vendor/bootstrap-3.1.1/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap theme -->
    <link href="resources/vendor/bootstrap-3.1.1/css/bootstrap-theme.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="theme.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]><script src="resources/vendor/bootstrap-3.1.1/js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body role="document">
	
	
		<div class="list-group">
					<a class="list-group-item" style="text-align:center;" >
						<h4><span class="label label-info">用户：小明i金将军</span>&nbsp;<span class="label label-info" id="spanTitle">模拟科目一</span></h4>
					</a>
					<a class="list-group-item" >
						<span class="label label-primary">进度：<strong id="progressCount">1/100</strong></span>
						<span class="label label-success">正确：<strong id="correctCount">0</strong></span>
						<span class="label label-danger">错误 : <strong id="wrongCount">0</strong></span>
						<span class="label label-warning">时间：<strong id="timerCount">45分00秒</strong></span>
					</a>
		            <a class="list-group-item active" style="color:#fff; font-size:17px; font-weight:bold;">
			              <strong>1. &nbsp;</strong>Donec id elit non mi porta gravida at eget metus. Maecenas sed diam eget risus varius blandit.
		            </a>
		            <a class="list-group-item">
						  <label>
						    <input type="checkbox" value="">
						    	A.  Option one is this and that&mdash;be sure to include why it's greatOption one is this and that&mdash;be sure to include why it's gre
						  </label>
					</a>
		            <a class="list-group-item">
						  <label>
						    <input type="checkbox" value="">
						    	B.  Optand that&mdash;be sure to include why it's gre
						  </label>
					</a>
					<a class="list-group-item">
						  <label>
						    <input type="checkbox" value="">
						    	C.  Option one is this and that&mdash;be sure to include why it's gre
						  </label>
					</a>
					<a class="list-group-item">
						  <label>
						    <input type="checkbox" value="">
						    	D.  Option one is this and that&mdash;be sure to inclube sure to include why it's gre
						  </label>
					</a>
					
					<div class="list-group-item" style="text-align:center;">
						<a class="btn btn-primary btn-lg" role="button" id="nextQuestion">下一题</a>
					</div>
		  </div>
     
     <!-- <ul class="list-group">
            <li class="list-group-item"> 
            	<button type="button" class="btn btn-xs btn-success">1</button>
        		<button type="button" class="btn btn-xs btn-success">2</button>
        		<button type="button" class="btn btn-xs btn-success">3</button>
        		<button type="button" class="btn btn-xs btn-danger">4</button>
        		<button type="button" class="btn btn-xs btn-success">5</button>
        		<button type="button" class="btn btn-xs btn-danger">6</button>
        		<button type="button" class="btn btn-xs btn-success">7</button>
        		<button type="button" class="btn btn-xs btn-danger">8</button>
        		<button type="button" class="btn btn-xs btn-success">9</button>
        		<button type="button" class="btn btn-xs btn-success">10</button>
        		<button type="button" class="btn btn-xs btn-success">11</button>
        		<button type="button" class="btn btn-xs btn-danger">12</button>
        		<button type="button" class="btn btn-xs btn-success">13</button>
        		<button type="button" class="btn btn-xs btn-danger">14</button>
        		<button type="button" class="btn btn-xs btn-success">15</button>
        		<button type="button" class="btn btn-xs btn-danger">16</button>
        		<button type="button" class="btn btn-xs btn-success">17</button>
        		<button type="button" class="btn btn-xs btn-success">18</button>
        		<button type="button" class="btn btn-xs btn-success">19</button>
        		<button type="button" class="btn btn-xs btn-danger">20</button>
        		<button type="button" class="btn btn-xs btn-success">21</button>
        		<button type="button" class="btn btn-xs btn-danger">22</button>
        		<button type="button" class="btn btn-xs btn-success">23</button>
        		<button type="button" class="btn btn-xs btn-danger">24</button>
        		<button type="button" class="btn btn-xs btn-success">25</button>
        		<button type="button" class="btn btn-xs btn-success">26</button>
        		<button type="button" class="btn btn-xs btn-success">27</button>
        		<button type="button" class="btn btn-xs btn-danger">28</button>
        		<button type="button" class="btn btn-xs btn-success">29</button>
        		<button type="button" class="btn btn-xs btn-danger">30</button>
        		<button type="button" class="btn btn-xs btn-success">31</button>
        		<button type="button" class="btn btn-xs btn-danger">32</button>
      		</li>
          </ul> -->
     
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
   <!--Start importing the jquery files -->
	<cmstudio:importJsCss name="jquery" version="${jquery_version}"/>
	<!--End import the jquery files -->
    <script src="resources/vendor/bootstrap-3.1.1/js/bootstrap.min.js"></script>
    <script type="text/javascript">
    	//科目X
    	var inputTypeValue = "${subjectType}";
    	//总题目数量
    	var totalQuestionCount=0;
    	var timerSeconds;
    	if(inputTypeValue=="1"){
    		totalQuestionCount=100;
    		timerSeconds=2700;
    	}else{
    		totalQuestionCount=50;
    		timerSeconds=1800;
    	}
    	//当前题目位置
    	var currentIndex=1;
    	//错误数量
    	var wrongCount=0;
    	//正确数量
    	var correctCount=0;
    	
    	
    </script>
    <script type="text/javascript" src="resources/js/xinqiang/questionManage/questionTest.js"></script>
  </body>
</html>
