/***
 * author: charmyin
 * datetime: 2013-2-26 21:00
 * title: Control the scoreManage.jsp ~
 ***/

//Global the item name
var itemName="用户";

//Global all organization treeObj and setting
var selectedNodeId;
var allOrganizationTreeObj;

//载入分数数据
function loadScoreGrid(rowIndex, rowData){
	$("#scoreGrid1").datagrid({
		url:'score/'+rowData.id+'/1/scorelist',
		loadFilter:pagerFilter,
		method:'get',
		//toolbar:'#toolbar',
		pagination:true,
		collapsible:true,
		title:rowData.name+"&nbsp;&nbsp;&nbsp;&nbsp;科目一",
		rownumbers:true,
		singleSelect:true,
		pageSize:8,
	    pageList:[8,16,32,48,64],
		columns:[[
		         // {field:'ck', checkbox:true },
		         {field:'id', title:'编号', hidden:true},
		          {field:'score', title:'得分', width:'100',align:'center'},
		         {field:'testTimeString',align:'center', width:'200', title:'考试日期'}
		          //{field:'state', title:'是否禁用'},
		          //{field:'remark', title:'备注'}
		]],
		onLoadError: function(msge){
			$.messager.alert('错误信息','服务器连接已断开或服务器内部错误！','error');
		}
	});
	$("#scoreGrid4").datagrid({
		url:'score/'+rowData.id+'/4/scorelist',
		loadFilter:pagerFilter,
		method:'get',
		align:'center',
		//toolbar:'#toolbar',
		pagination:true,
		collapsible:true,
		title:rowData.name+"&nbsp;&nbsp;&nbsp;&nbsp;科目四",
		rownumbers:true,
		singleSelect:true,
		pageSize:8,
	    pageList:[8,16,32,48,64],
		columns:[[
		         // {field:'ck', checkbox:true },
		         {field:'id', title:'编号', hidden:true},
		         {field:'score', title:'得分', width:'100',align:'center'},
		         {field:'testTimeString',align:'center', width:'200',title:'考试日期'}
		          //{field:'state', title:'是否禁用'},
		          //{field:'remark', title:'备注'}
		]],
		onLoadError: function(msge){
			$.messager.alert('错误信息','服务器连接已断开或服务器内部错误！','error');
		}
	});
	
}



var allOrganizationTreeSetting = {
		data:{
			simpleData:{
				enable:true,
				idKey:"id",
				pIdKey:"parentId"
			}
		},
		callback:{
			onClick:function (event,treeId,node) {
				//Load organizationGrid by selected tree node's id as parentId
//				$("#organizationGrid").datagrid({
//					url:'organizationparent/'+node.id+'/all'
//				});
				selectedNodeId = node.id;
				//初始化新增和修改页面的隐藏值
				initParentId();
				$("#userGrid").datagrid({
					url:'user/organizationId/'+node.id+'/allUser', 
					loadFilter:pagerFilter,
					method:'get',
					//toolbar:'#toolbar',
					pagination:true,
					collapsible:true,
					title:"学员列表&nbsp----&nbsp"+node.name,
					rownumbers:true,
					singleSelect:true,
					pageSize:8,
				    pageList:[8,16,32,48,64],
				    onClickRow:function(rowIndex, rowData){
				    	loadScoreGrid(rowIndex, rowData)
				    },
					columns:[[
					         // {field:'ck', checkbox:true },
					         // {field:'id', title:'编号' },
					          {field:'loginId', title:'登录名', width:'100'},
					          {field:'name', title:'昵称', width:'100'}
					         // {field:'dateCreated', title:'注册日期'},
					          //{field:'state', title:'是否禁用'},
					          //{field:'remark', title:'备注'}
					]],
					onLoadError: function(msge){
						$.messager.alert('错误信息','服务器连接已断开或服务器内部错误！','error');
					}
				});
			}
		}
};

//Load all the organization Tree
function loadOrganizationTree(){
	$.ajax({
	  type: "GET",
	  url: "organization/all"
	}).done(function( msg ) {
	  //Load the system manage tree
	  allOrganizationTreeObj = $.fn.zTree.init($("#div_allOrganization_tree"), allOrganizationTreeSetting, msg);
	 //rename the
	  var rootNode = allOrganizationTreeObj.getNodeByParam("id","1");
	  rootNode.name = $("title").html();
	  allOrganizationTreeObj.refresh();
	  //if selected a node, then append it ,else append the root node 
	  var selectedNode;
	  if(selectedNodeId){
		  selectedNode = allOrganizationTreeObj.getNodeByParam("id",selectedNodeId);
	  }else{
		  selectedNode = allOrganizationTreeObj.getNodes()[0];
	  }
	  allOrganizationTreeObj.expandNode(selectedNode,true,false,false,false);
	  //Select the node which id is selectedNodeId, then trigger the click event on it
	  allOrganizationTreeObj.selectNode(selectedNode);
	  //sometime it require the server twice, I wonder~
	  $("#"+selectedNode.tId+"_a").trigger("click");
	});
}

//Used for client pagination
function pagerFilter(data){
    if (typeof data.length == 'number' && typeof data.splice == 'function'){    // is array
        data = {
            total: data.length,
            rows: data
        };
    }
    var dg = $(this);
    var opts = dg.datagrid('options');
    var pager = dg.datagrid('getPager');
    pager.pagination({
    	onBeforeRefresh:function(){
    		dg.datagrid("reload");
    	},
        onSelectPage:function(pageNum, pageSize){
            opts.pageNumber = pageNum;
            opts.pageSize = pageSize;
            pager.pagination('refresh',{
                pageNumber:pageNum,
                pageSize:pageSize
            });
            dg.datagrid('loadData',data);
        }
    });
    if (!data.originalRows){
        data.originalRows = (data.rows);
    }
    var start = (opts.pageNumber-1)*parseInt(opts.pageSize);
    var end = start + parseInt(opts.pageSize);
    data.rows = (data.originalRows.slice(start, end));
    return data;
}

//载入
function loadRolesForChoose(){
	var selectedNode = allOrganizationTreeObj.getNodeByParam("id",selectedNodeId);
	var htmlInner = "";
	//The first Get all the role belong to the same organization
	$.ajax({
	  type: "GET",
	  url: "role/orgId/"+selectedNode.id+"/all"
	}).done(function( msg ) {
		if(url.indexOf('update')>-1){
			//Get the uset id
			var row = $('#userGrid').datagrid('getSelected');
			//The second request the role of the user
			$.ajax({
			  type: "GET",
			  url: "user/"+row.id+"/roleNames"
			}).done(function( msg ) {
				//Check the checkboxes which 
				$(".roleChooseClass").each(function(){
					for(var i=0; i<msg.length; i++){
						if($(this).val()==msg[i]){
							$(this).attr("checked","checked");
						}
					}
				});
			});
		}
		
		
		
		//alert(msg[0].name);
		for(var i=0; i<msg.length; i++){
			htmlInner+='<input type="checkbox" class="roleChooseClass" value="'+msg[i].name+'"/>'+msg[i].name;
		}
		$("#innerRoleChoose").html(htmlInner);
	});
}


/********************************************************Initial the page*****************************************************/
$(function(){
	//Disable cache
	jQuery.ajaxSetup({ cache: false });
	//载入成功后，刷新左边树
	//Load the organization tree
	loadOrganizationTree();
	//Load grid
	//选择用户角色
	$("#btn_roles").click(function(){
		$('#role-dlg').dialog('open').dialog('setTitle','选择角色');
		loadRolesForChoose();
	});
});

//OrganizationCrud dialog
var url;

//Initial the parentId
function initParentId(){
	//Input the value and hidden value of parentId input 
	var selectedNode = allOrganizationTreeObj.getNodeByParam("id",selectedNodeId);
	$("#hidden_organizationId").val(selectedNodeId);
	$("#input_organizationName").val(selectedNode.name);
}
 
 
