/***
 * author: charmyin
 * datetime: 2013-2-26 21:00
 * title: The main page of this application~
 ***/
//移除加载页面
var removeLoadingDiv = function () {
	$('#divLoading_Main').fadeOut("slow", function () {
		$(this).remove();
	});
}

//Open tab
var  openMainTab = function (node){
	var isNotExisted = true;//tab不存在
	var tabs = $("#divTab_Main").tabs('tabs');
	for( var i=0; i<tabs.length; i++){
		if('divTab_Main_' + node.tId == $(tabs[i]).attr('id')){
			$("#divTab_Main").tabs('select', i);	
			isNotExisted = false;//tab已存在
		}
	}
	if(isNotExisted){
		$('#divTab_Main').tabs('add', {
			id: 'divTab_Main_' + node.tId,//tab的Id格式为divTab_Main_12
			title: node.name,
			content: '<iframe id="iframeTab_'+ node.tId+'" src="'+ node.linkUrl+'" style="border:none; overflow:auto;width:100%;height:99%;"></iframe>',// '',
			closable: true,
			fit: true,
			tools:[{
				iconCls: 'icon-mini-refresh',
				handler: function(){
					$('#iframeTab_' + node.tId).attr("src",$('#iframeTab_' + node.tId).attr("src"));
				}
			}]
		});
		$('#divTab_Main_'+ node.tId ).css({'padding':'0', 'margin':'0', 'overflow':'auto' });
			}
		}
 
	//doc加载后执行
	$(function () {
		//去除加载mask效果
		if($("#divLoading_Main").length > 0){
			$('#divLoading_Main').fadeOut("slow", function () {
				$(this).remove();
			});
		}
	
		//加载tree
		var zTreeObj;
		var divDevelopToolTreeObj;
		var moduleSystemTreeObj;
		var menuTreeSetting= {
				/*async:{
					enable:true,
					url:"menu/all",
					type:"get"						
				},*/
				data:{
					simpleData:{
						enable:true,
						idKey:"id",
						pIdKey:"parentId"
					}
				},
				callback:{
					onClick:function (event,treeId,node) {
						var boo = node.isParent;
						if(!boo){
							openMainTab(node);
						}
					}
				}
		};
		
		$.ajax({
		  type: "GET",
		  url: "menu/all"
		}).done(function( msg ) {
		  //Load the system manage tree
		  zTreeObj = $.fn.zTree.init($("#divSystemManage_main_tree"), menuTreeSetting, msg);
		  var newNode = zTreeObj.getNodeByParam("id","2");
		  zTreeObj.removeNode(zTreeObj.getNodes()[0]);
		  zTreeObj.addNodes(null, newNode.children);
		  
		  divDevelopToolTreeObj = $.fn.zTree.init($("#divDevelopTool_tree"), menuTreeSetting, msg);
		  var newNode1 = divDevelopToolTreeObj.getNodeByParam("id","4");
		  divDevelopToolTreeObj.removeNode(divDevelopToolTreeObj.getNodes()[0]);
		  divDevelopToolTreeObj.addNodes(null, newNode1.children);
		  
		  moduleSystemTreeObj = $.fn.zTree.init($("#moduleSystem_tree"), menuTreeSetting, msg);
		  var newNode2 = moduleSystemTreeObj.getNodeByParam("id","6");
		  moduleSystemTreeObj.removeNode(moduleSystemTreeObj.getNodes()[0]);
		  moduleSystemTreeObj.addNodes(null, newNode2.children);
		
		});
		
		//Change the theme
		$(".divOnChangeTheme").click(function () {
			changeTheme($(this).attr('value'));
		});
		var changeTheme = function (theme){
			//link.attr('href', '/easyui/themes/'+theme+'/easyui.css');
		}
		
		//Logout the system
		$("#logout").click(function(){
			$.messager.confirm('退出提示', '确定退出系统？', function(r){
				if (r){
					window.location.href="identity/logout";
				}
			});
			
		});
	});