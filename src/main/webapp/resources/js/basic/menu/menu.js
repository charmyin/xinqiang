/***
 * author: charmyin
 * datetime: 2013-2-26 21:00
 * title: Control the menuManage.jsp ~
 ***/

//Global all menu treeObj and setting
var allMenuTreeOjb;
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
				var boo = node.isParent;
				if(!boo){
					openMainTab(node);
				}
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
	loadAllMenuTree();
	
});

var url;

function newUser(){
    $('#dlg').dialog('open').dialog('setTitle','New User');
    $('#fm').form('clear');
    url = 'save_user.php';
}
function editUser(){
    var row = $('#dg').datagrid('getSelected');
    if (row){
        $('#dlg').dialog('open').dialog('setTitle','Edit User');
        $('#fm').form('load',row);
        url = 'update_user.php?id='+row.id;
    }
}
function saveUser(){
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
                $('#dg').datagrid('reload');    // reload the user data
            }
        }
    });
}
function destroyUser(){
    var row = $('#dg').datagrid('getSelected');
    if (row){
        $.messager.confirm('Confirm','Are you sure you want to destroy this user?',function(r){
            if (r){
                $.post('destroy_user.php',{id:row.id},function(result){
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


