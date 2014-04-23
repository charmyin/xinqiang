<%
/**
 * This is the Question Manage page~ 
 * @author charmyin
 * @since 2013-8-19
 * @serial 1.0
 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/cmstudio.tld" prefix="cmstudio" %>
<!DOCTYPE html>
<html>
	<head>
		<title>  ${application_name_cn} </title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<cmstudio:htmlBase/>
		<link rel="shortcut icon" type="image/x-icon" href="resources/${icon_name}"/>
		<link rel="stylesheet" type="text/css" href="resources/css/xinqiang/questionManage/questionManage.css" />
		<!--Start importing the jquery files -->
		<cmstudio:importJsCss name="jquery" version="${jquery_version}"/>
		<!--End import the jquery files -->
		<!--Start importing the jeasyui files -->
		<cmstudio:importJsCss name="jeasyui" version="${jeasyui_version}"/>
		<!--End importing the jeasyui files -->
		<!--Start importing the ztree files -->
		<cmstudio:importJsCss name="ztree" version="${ztree_version}"/>
		<!--End importing the ztree files -->
		
		<script type="text/javascript">
			var inputTypeValue = "${subjectType}";
		</script>
		<script src="resources/vendor/jqueryupload/js/vendor/jquery.ui.widget.js"></script>
		<script src="resources/vendor/jqueryupload/js/jquery.fileupload.js"></script>
		<script type="text/javascript" src="resources/js/xinqiang/questionManage/questionManage.js"></script>
	</head>
	<body>
		 	    <table id="questionGrid">
			    </table>
			    <div id="toolbar">
			        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newForm()">新建</a>
			        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editForm()">修改</a>
			        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroySelectedItems()">删除</a>
			    </div>
			    <div id="dlg" class="easyui-dialog" data-options="closed:'true',buttons:'#dlg-buttons'">
			        <form id="fm" method="post" >
			            <div class="fitem">
			                <label>题干：</label>
			                <textarea name="content" id="content" class="easyui-validatebox" required="true"></textarea>
			                <!-- A,B,C -->
			                <input type="hidden" id="questionAnswer" name="answer" />
			                <input type="hidden" id="inputtype" name="type" />
			            </div>
			            <div class="fitem" id="uploaddiv">
			                <label>上传图片：</label>
			         <!--        <input id="hidden" class="easyui-validatebox"  type="hidden"> -->
			                <input id="fileupload" type="file" name="myfile" data-url="question/fileUpload">
			            </div>
			            <div class="fitem">
			                <label>
				                答案A(√) <input class="answercb" type="checkbox" name="answera" value="A"/>
			                </label>
			                <textarea name="choosea" id="choosea" class="answer" ></textarea>
			            </div>
			            <div class="fitem">
			                <label>
				                答案B(×)<input class="answercb" type="checkbox" name="answerb" value="B"/>
			                </label>
			                <textarea name="chooseb" id="chooseb" class="answer" ></textarea>
			            </div>
			            <div class="fitem">
			                <label>
				                答案C<input class="answercb" type="checkbox" name="answerc" value="C"/>
			                </label>
			                <textarea name="choosec" id="choosec" class="answer" ></textarea>
			            </div>
			            <div class="fitem">
			                <label>
				                答案D<input class="answercb" type="checkbox" name="answerd" value="D"/>
			                </label>
			                <textarea name="choosed" id="choosed" class="answer" ></textarea>
			            </div>
			            <div class="fitem">
			                <label>备注：</label>
			                <textarea name="remark" placeholder="请输入备注..."></textarea>
			            </div>
			        </form>
			    </div>
			    <div id="dlg-buttons">
				        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveForm()">保存</a>
				        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">关闭</a>
				</div>
	</body>
</html>
