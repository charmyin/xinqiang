/***
 * author: charmyin
 * datetime: 2013-2-26 21:00
 * title: Control the menuManage.jsp ~
 ***/

//Global all menu treeObj and setting
var allMenuTreeObj;
var allMenuTreeSetting = {
		data:{
			simpleData:{
				enable:true,
				idKey:"id",
				pIdKey:"parentId"
			}
		},
		callback:{
			onClick:function (event,treeId,node) {
				//Load menuGrid by selected tree node's id as parentId
				$("#menuGrid").datagrid({
					url:'menuparent/'+node.id+'/menu'
				});
			}
		}
};

//Load all the menu Tree
function loadAllMenuTree(){
	$.ajax({
	  type: "GET",
	  url: "menu/all"
	}).done(function( msg ) {
	  //Load the system manage tree
	  allMenuTreeObj = $.fn.zTree.init($("#div_allMenu_tree"), allMenuTreeSetting, msg);
	 //rename the
	  var rootNode = allMenuTreeObj.getNodeByParam("id","1");
	  rootNode.name = $("title").html();
	  allMenuTreeObj.refresh();
	});
}



/********************************************************Initial the page*****************************************************/
$(function(){
	//载入所有树形控件
	loadAllMenuTree();

	//Load grid
	$("#menuGrid").datagrid({
		url:'menuparent/1/menu', 
		method:'get',
		toolbar:'#toolbar',
		pagination:'true',
		collapsible:true,
		title:'载入中...',
		rownumbers:true, 
		autoSizeColumn:true, 
		singleSelect:'true',
		columns:[[
		          {field:'id', title:'菜单编号' },
		          {field:'name', title:'名称'},
		          {field:'parentId', title:'父级菜单'},
		          {field:'linkUrl', title:'链接地址'},
		          {field:'remark', title:'备注'}
		]],
		onLoadSuccess: function(msg){
			var selectedTreeNodes = allMenuTreeObj.getSelectedNodes();
			if(selectedTreeNodes.length>0){
				$(".panel-title").html(selectedTreeNodes[0].name);
			}else{
				$(".panel-title").html(allMenuTreeObj.getNodes()[0].name);
			}
		},
		onLoadError: function(msge){
			$.messager.alert('错误信息','服务器连接已断开或服务器内部错误！','error');
		}
	});
	
	//Load grid
	//$("#menuGrid").datagrid("load");
	
});

var url;

function newMenu(){
    $('#dlg').dialog('open').dialog('setTitle','新建菜单');
    $('#fm').form('clear');
    url = 'menu/save';
}
function editMenu(){
    var row = $('#menuGrid').datagrid('getSelected');
    if (row){
        $('#dlg').dialog('open').dialog('setTitle','修改菜单:'+row.name);
        $('#fm').form('load',row);
        url = 'menu/update?'+row.id;
    }
}
function saveMenu(){
    $('#fm').form('submit',{
        url: url,
        onSubmit: function(){
            return $(this).form('validate');
        },
        success: function(result){
            var result = eval('('+result+')');
            if (result.errorMsg){
                $.messager.show({
                    title: 'Error',
                    msg: result.errorMsg
                });
            } else {
                $('#dlg').dialog('close');        // close the dialog
                $('#menuGrid').datagrid('reload');    // reload the user data
            }
        }
    });
}
function destroyMenu(){
    var row = $('#menuGrid').datagrid('getSelected');
    if (row){
        $.messager.confirm('提示信息','确定删除选中菜单？',function(r){
            if (r){
                $.post('menu/delete',{id:row.id},function(result){
                    if (result.success){
                        $('#dg').datagrid('reload');    // reload the user data
                    } else {
                        $.messager.show({    // show error message
                            title: 'Error',
                            msg: result.errorMsg
                        });
                    }
                },'json');
            }
        });
    }
}


