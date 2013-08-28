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
				pagerRefreshBtn();
			}
		}
};

function pagerRefreshBtn(){
	//添加刷新按钮事件
	var pager = $('#menuGrid').datagrid().datagrid('getPager');    // get the pager of datagrid
    pager.pagination({
    	showRefresh:false,
        buttons:[{
            iconCls:'icon-reload',
            handler:function(){
            	$("#menuGrid").datagrid("load");
            }
        }]
    }); 
}

//Load all the menu Tree
function loadMenuTree(){
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

//Used for client pagination
function pagerFilter(data){
    if (typeof data.length == 'number' && typeof data.splice == 'function'){    // is array
        data = {
            total: data.length,
            rows: data
        }
    }
    var dg = $(this);
    var opts = dg.datagrid('options');
    var pager = dg.datagrid('getPager');
    pager.pagination({
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

/********************************************************Initial the page*****************************************************/
$(function(){
	//Load the menu tree
	loadMenuTree();

	//Load grid
	$("#menuGrid").datagrid({
		url:'menuparent/1/menu', 
		loadFilter:pagerFilter,
		method:'get',
		toolbar:'#toolbar',
		pagination:true,
		collapsible:true,
		title:'载入中...',
		rownumbers:true,
		singleSelect:false,
		pageSize:8,
	    pageList:[8,16,32,48,64],
		columns:[[
		          {field:'ck', checkbox:true },
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
	
	//添加刷新按钮
	pagerRefreshBtn();
	
	//Add the parentId
	$("#btn_parentId").click(function(){
		var nodes = allMenuTreeObj.getNodes();
		
		var setting = {
						view: {
							selectedMulti: false
						}
					};
		var SelectSingleMenuTreeObj = $.fn.zTree.init($("#div_SelectSingleMenu_tree"), setting, nodes);
		
		$("#winSelectParentMenu").window("open");
	});
});

//MenuCrud url 
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
        url = 'menu/update?id='+row.id;
    }
}
function saveMenu(){
    $('#fm').form('submit',{
        url: url,
        onSubmit: function(){
            return $(this).form('validate');
        },
        success: function(result){
            if (result.errorMsg){
                $.messager.show({
                	title: '提示<span style="color:red;">!</span>',
                    msg: "<div style='text-align:center;margin-top:10px;'>请选择要删除的行！</div>",
                    style:{
                		right:'',
                		top:document.body.scrollTop+document.documentElement.scrollTop,
                		bottom:''
                	}
                });
            } else {
                $('#dlg').dialog('close');        // close the dialog
                $('#menuGrid').datagrid('reload');    // reload the user data
            }
        }
    });
}
function destroyMenu(){
    var rows = $('#menuGrid').datagrid('getSelections');
    var rowsLength = rows.length;
    if (rowsLength>0){
        $.messager.confirm('提示信息','确定删除选中菜单？',function(r){
            if (r){
            	var idsString='';
            	for(var i=0; i<rows.length; i++){
            		if((i+1)==rowsLength){
            			idsString+=rows[i].id;
            		}else{
            			idsString+=(rows[i].id+',');
            		}
            	}
            	$.post('menu/deleteByIds',{ids:idsString},function(result){
                    if (result.suc){
                    	$.messager.show({
                        	title: '提示',
                            msg: "<div style='text-align:center;margin-top:10px;'>删除成功!</div>",
                            style:{
                        		right:'',
                        		top:document.body.scrollTop+document.documentElement.scrollTop,
                        		bottom:''
                        	}
                        });
                        $('#menuGrid').datagrid('reload');    // reload the user data
                    } else {
                        $.messager.show({    // show error message
                            title: '提示<span style="color:red;">!</span>',
                            msg: "<div style='text-align:center;margin-top:10px;'>"+result.errorMsg+"</div>",
                            style:{
                        		right:'',
                        		top:document.body.scrollTop+document.documentElement.scrollTop,
                        		bottom:''
                        	}
                        });
                    }
                });
            }
        });
    }else{
    	$.messager.show({    // show error message
            title: '提示<span style="color:red;">!</span>',
            msg: "<div style='text-align:center;margin-top:10px;'>请选择要删除的行！</div>",
            style:{
        		right:'',
        		top:document.body.scrollTop+document.documentElement.scrollTop,
        		bottom:''
        	}
        });
    }
}


