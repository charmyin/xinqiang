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
    	.innerTitle{
    		margin:0 10px 0 10px
    	}
    </style>

    <!-- Bootstrap core CSS -->
    <link href="resources/vendor/bootstrap-3.1.1/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap theme -->
    <link href="resources/vendor/bootstrap-3.1.1/css/bootstrap-theme.min.css" rel="stylesheet">

    <!--Start importing the jquery files -->
	<cmstudio:importJsCss name="jquery" version="${jquery_version}"/>
	<!--End import the jquery files -->

    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]><script src="resources/vendor/bootstrap-3.1.1/js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body role="document">
		<div class="list-group" id="chooseOptions">
					<div class="list-group-item" style="text-align:center;">
						<h2><span class="label label-info" id="showTitle"></span></h2>
					</div>
					<div class="list-group-item" style="text-align:center;">
						<a class="btn btn-primary btn-lg" role="button" id="startTest">开始考试</a>
					</div>
		  </div>
     
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
   <!--Start importing the jquery files -->
	<cmstudio:importJsCss name="jquery" version="${jquery_version}"/>
	<!--End import the jquery files -->
    <script src="resources/vendor/bootstrap-3.1.1/js/bootstrap.min.js"></script>
    <script type="text/javascript">
		    var inputTypeValue = "${subjectType}";
		    var url="";
		    
			
			$(function(){
				if(inputTypeValue=="1"){
			    	url="questionTest/1/test";
			    	$("#showTitle").html("科目一模拟考试");
			    }
			    
				if(inputTypeValue=="4"){
					url="questionTest/4/test";
			    	$("#showTitle").html("科目四模拟考试");
			    }
				
				$("#startTest").click(function(){
					window.location=url;
				});
			});
    
    </script>
  </body>
</html>
