<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/cmstudio.tld" prefix="cmstudio" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Zebone:基础研发平台</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<!-- 指向http://xxx.xxx.xxx:xxxx/cmstudio/ -->
		<cmstudio:htmlBase/>
		<link rel="shortcut icon" type="image/x-icon" href="resources/five.ico"/>
		<link rel="stylesheet" type="text/css" href="resources/css/basic/authorize/login.css" />
		<!--Start importing the jquery files -->
		<cmstudio:importJsCss name="jquery" version="${jquery_version}"/>
		<!--End import the jquery files -->
		<!--Start importing the jeasyui files -->
		<cmstudio:importJsCss name="jeasyui" version="${jeasyui_version}"/>
		<!--End importing the jeasyui files -->
		<script src="resources/vendor/cookiejs/cookie.min.js" type="text/javascript"></script>
		<script type="text/javascript">
			//从cookie获取用户信息
		    function getUserInfoFromCookie() {
				var userName = cookie.get('loginUserName');
				var password = cookie.get('loginUassword');
				$('#inputUsername').val(userName);
				$('#inputPassword').val(password);
			}
			//将光标移动到焦点上
			//参数为元素的id
			function setEnterPressEvent() {
				//监听enter按键事件
				//username文本框，enter后跳转下个文本框
				$('#inputUsername').bind('keypress', function (e) {
					var code = e.keyCode ? e.keyCode : e.which;
					if(code == 13){
						$('#inputPassword').focus();
					}
				});
				//密码文本框，enter后跳转下个文本框
				$('#inputPassword').bind('keypress', function (e) {
					var code = e.keyCode ? e.keyCode : e.which;
					if(code == 13){
						document.authForm.valiCode.focus();
					}
				});
				//验证码文本框，enter后提交表单
				$('#inputValiCode').bind('keypress', function (e) {
					var code = e.keyCode ? e.keyCode : e.which;
					if(code == 13){
						submitLoginForm();
					}
				});
			}
			//提交表单
			 function submitLoginForm() {
				//验证所填入信息
				if(!$('#authForm').form('validate')){
					return;
				}			
				
				var userName = $('#inputUsername').val();
				var password = $('#inputPassword').val();
				
				//保存cookie
				if($('#inputRememberMe').is(':checked')){
					if(userName){
						cookie.set('loginUserName', userName);
					}
					if(password){
						//TODO 需要加密
						cookie.set('loginPassword', password);
					}
					
					//alert("表单提交中，cookie中存入用户名密码，刷新后显示~");
				}
				
				if($('#inputDesktop_login').is(':checked')){
					window.location.href="http://localhost:8080/bbrj/jsframes/jQueryDesktop/index.html";
				}else{
					validUserByAjax(userName, password);
				}
			}
			
			//Ajax形式验证用户凭据
			function validUserByAjax(userName, password){
				//alert(userName+password);
				$.ajax({
					  url: "/${application_name}/identity/authenticate",
					  type:"POST",
					  data: {
					    passphrase : password,
					    username : userName
					  },
					  dataType: "json",
					  success: function( data ) {
					    if(data.status == 'ok'){
					    	//alert("aaaa");
					    	window.location.href="staticviews/basic/index.html";
					    }else{
					    	$.messager.show({
								title:'登录提示',
								msg:data.msg,
								timeout : 2000,
								showType:'slide',
								style:{
									right:'',
									top:document.body.scrollTop+document.documentElement.scrollTop+150,
									bottom:''
								}
							});
					    }
					  },
					  error: function(data){
						// show message window on top center
						$.messager.show({
							title:'登录提示',
							msg:data.msg,
							timeout : 2000,
							showType:'slide',
							style:{
								right:'',
								top:document.body.scrollTop+document.documentElement.scrollTop+150,
								bottom:''
							}
						});
						  
					  }
					});
				
			}
			
			//监视easyloader加载组件完成的事件
			$(function(){
				 
				//去除加载mask效果
				if($("#divLoading").length > 0){
					$("#loginwindow").window('close');
					$('#divLoading').fadeOut("slow", function () {
						$(this).remove();
						$("#loginwindow").window('open');
						document.authForm.username.focus();
					});
				}
				
				if($.inArray("validatebox", event) !== -1){
					//添加验证规则 
					//固定长度
					$.extend($.fn.validatebox.defaults.rules, {
						fixedLength: {
							validator: function(value, param){
								return value.length === param[0];
							},
							message: '字符串长度应为{0}'
						}
					});
				}
				
				
				
				//页面加载完成后，执行以下操作
				// domReady(function () {
				//获取cookie中的用户名和密码
				getUserInfoFromCookie();
				//载入easyui,form框架
				//easyloader.load('plugins/jquery.form.js');
				//重置表单
				$('#aResetForm').click(function (event) {
					$('#authForm').form('reset');
					//设置authForm首个控件焦点
					document.authForm.username.focus();
				});
				//提交表单
				$('#aSubmitForm').click(function (event) {
					event.preventDefault();
					submitLoginForm();
				});
				//设定enter按钮事件
				setEnterPressEvent();
				
			});

		</script>
		 
	</head>
	<body>
		<div id="loginwindow" class="easyui-window" title="Zebone&reg;--振邦信息化平台入口" data-options="iconCls:'icon-tip',closable:false, minimizable:false, maximizable:false, resizable:false, shadow:true" style="width:500px; height:300px; padding:2px;">
			<div class="easyui-layout" fit="true" style="overflow:hidden;">
				<div region="west">
				</div>
				<div region="center" style="width:250px">
					<div class="easyui-tabs" fit="true">
					 	<div title="身份认证" iconCls="icon-save">
					 			<div id="userconfirmDiv">
				 					<form id="authForm" name="authForm" action="#" method="post">
				 						<ul id="formUl">
				 							<li class="inputText">
				 								<label for="inputUsername">用户名:</label>
					 							<input class="easyui-validatebox" type="text" id="inputUsername" name="username" type="password" placeholder="用户名" data-options="required:true, validType:'length[1,15]'" autofocus/>
				 							</li>
				 							<li class="inputText">
				 								<label style="" for="inputPassword">密&nbsp;&nbsp;码:</label>
					 							<input class="easyui-validatebox" type="password" id="inputPassword" name="password" type="password" placeholder="密码" data-options="required:true, validType:'length[1,15]'" />
				 							</li>
				 							<li id="liValiCode">
				 								<label style="" for="inputValiCode">验证码:</label>
				 								<img src="resources/img/basic/authorize/validateCode1.jpg" id="imgValiCode"/>
					 							<input class="easyui-validatebox" id="inputValiCode" name="valiCode" placeholder="验证码" data-options="required:true, validType:'fixedLength[5]'" />
				 							</li>
				 							<li class="liConfig">
				 								<div id="checkboxConfig">
											      <label>
											        <input type="checkbox" id="inputRememberMe" class="bootstrap"/> <strong>记住密码</strong>
											      </label>
											      <label>
											        <input type="checkbox" id="inputDesktop_login" class="bootstrap"/> <strong>桌面系统</strong>
											      </label>
											    </div>
				 							</li>
				 						</ul>
				 					</form>
					 				<div id="operationBtnsDiv" >
					 					<div style="height:6px;"></div>
					 					<a href="#" id="aSubmitForm" class="easyui-linkbutton" iconCls="icon-ok">提交</a>
					 					&nbsp;
					 					<a href="#" id="aResetForm" class="easyui-linkbutton" iconCls="icon-cancel">重置</a>
					 				</div>
					 			</div>
					 	</div>
					 	<div title="帮助提示" iconCls="icon-help">
					 		<div style="margin:70px 0 0 70px;" id="weather-temp">版权所有，翻版不究^_^</div>
					 	</div>
					</div>
				</div>
				<div region="south" style="height:30px;line-height:28px;text-align:center;">
					Powered by  Zebone © 2011-2013  &nbsp;<a href="http://www.zebone.cn" style="text-decoration: none;" target="_blank">http://www.zebone.cn</a>
				</div>
			</div>
		</div>
	</body>
 
 	<!--等待界面-->
	<div id='divLoading'><span>首页载入中~</span></div>
 
</html>
