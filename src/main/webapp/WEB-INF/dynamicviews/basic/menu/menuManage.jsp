<%
/**
 * This is the Menu Manage page~ 
 * @author charmyin
 * @since 2013-8-19
 * @serial 1.0
 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/cmstudio.tld" prefix="cmstudio" %>
<!DOCTYPE html>
<html>
	<head>
		<title> ${application_name_cn} </title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<cmstudio:htmlBase/>
		<link rel="shortcut icon" type="image/x-icon" href="resources/${icon_name}"/>
		<link rel="stylesheet" type="text/css" href="resources/css/basic/menu/menu.css" />
		<!--Start importing the jquery files -->
		<cmstudio:importJsCss name="jquery" version="${jquery_version}"/>
		<!--End import the jquery files -->
		<!--Start importing the jeasyui files -->
		<cmstudio:importJsCss name="jeasyui" version="${jeasyui_version}"/>
		<!--End importing the jeasyui files -->
		<!--Start importing the ztree files -->
		<cmstudio:importJsCss name="ztree" version="${ztree_version}"/>
		<!--End importing the ztree files -->
		<script type="text/javascript" src="resources/js/basic/menu/menu.js"></script>		 
	</head>
	<body>
		<div class="easyui-layout" fit="true" style="overflow:hidden;">
			<div data-options="region:'west', title:'菜单管理', split:true" style="width:200px;">
				 <div class="ztree" id="div_allMenu_tree"></div>
			</div>
			<div data-options="region:'center', split:true" style="width:200px;">
		 	    <table id="dg" title="" class="easyui-datagrid" style=""
			            data-options="url:'menuparent/1/menu', method:'get',toolbar:'#toolbar',pagination:'true',rownumbers:'true', fitColumns:'true' ,singleSelect:'true'">
			        <thead>
			            <tr>
			                <th field="id" width="50">菜单编号</th>
			                <th field="name" width="50">名称</th>
			                <th field="parentId" width="50">父级菜单</th>
			                <th field="remark" width="50">备注</th>
			            </tr>
			        </thead>
			    </table>
			    <div id="toolbar">
			        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">新建</a>
			        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">修改</a>
			        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除</a>
			    </div>
			    <div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
			            closed="true" buttons="#dlg-buttons">
			        <div class="ftitle">User Information</div>
			        <form id="fm" method="post" novalidate>
			            <div class="fitem">
			                <label>First Name:</label>
			                <input name="firstname" class="easyui-validatebox" required="true">
			            </div>
			            <div class="fitem">
			                <label>Last Name:</label>
			                <input name="lastname" class="easyui-validatebox" required="true">
			            </div>
			            <div class="fitem">
			                <label>Phone:</label>
			                <input name="phone">
			            </div>
			            <div class="fitem">
			                <label>Email:</label>
			                <input name="email" class="easyui-validatebox" validType="email">
			            </div>
			        </form>
			    </div>
			    <div id="dlg-buttons">
			        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">Save</a>
			        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">Cancel</a>
			    </div>
			</div>
		</div>
	</body>
</html>
