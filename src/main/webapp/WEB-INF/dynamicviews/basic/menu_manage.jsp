<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Menu</title>
	<link rel="shortcut icon" type="image/x-icon" href="resources/zebone.ico"/>
    <base href="../../">
	<link rel="stylesheet" type="text/css" href="resources/vendor/easyui_1.3.2/themes/icon.css" />
	<link rel="stylesheet" type="text/css" href="resources/css/basic/main/index.css" />
	<link rel="stylesheet" type="text/css" href="resources/vendor/easyui_1.3.2/themes/bootstrap/easyui.css" />
	<link rel="stylesheet" type="text/css" href="resources/vendor/ztree/css/zTreeStyle.css" />
	<script src="resources/vendor/jquery_1.8/jquery-1.8.3.min.js" type="text/javascript"></script>
	<script src="resources/vendor/easyui_1.3.2/jquery.easyui.min.js" type="text/javascript"></script>
	<script src="resources/vendor/ztree/jquery.ztree.all-3.5.min.js" type="text/javascript"></script>	
	
	<!-- <script src="resources/vendor/easyui_1.3.2/easyloader.js" type="text/javascript"></script> -->
	
	<script type="text/javascript">
		
		//定义菜单树
		var menu_tree;
		//菜单树配置
		var menu_tree_setting = {
			callback : {
				
			},
			data : {
				simpleData : {
					enable: true,
					idKey: "id",
					pIdKey: "pId",
					rootPId: 0
				}
			}
		};
		//测试用数据
		var treeNodes = [
            {"id":1, "pId":0, "name":"test1"},
            {"id":11, "pId":1, "name":"test11"},
            {"id":12, "pId":1, "name":"test12"},
            {"id":111, "pId":11, "name":"test111"}
        ];
		
		$(function(){
			menu_tree = $.fn.zTree.init($("#menu_tree"), menu_tree_setting, treeNodes);
			
		});
		
	</script>
</head>
<body class="easyui-layout">
    <div data-options="region:'west',title:'菜单管理',split:true" style="width:250px;">
    	<div id="menu_tree" class="ztree"></div>
    </div>  
    <div data-options="region:'center',title:'center title'" style="padding:5px;background:#eee;"></div>  
</body>  
</html>