/***
 * author: charmyin
 * datetime: 2013-9-9 
 * title: Used to handle the permission part of menuManage.jsp ~
 ***/

 
    
   /* <table id="menuPermissionGrid" class="easyui-datagrid" title="Row Editing in DataGrid" style="width:700px;height:auto"
            data-options="
                iconCls: 'icon-edit',
                singleSelect: true,
                toolbar: '#tb',
                url: 'datagrid_data1.json',
                method: 'get',
                onClickRow: onClickRow
            ">
        <thead>
            <tr>
                <th data-options="field:'itemid',width:80">Item ID</th>
                <th data-options="field:'productid',width:100,
                        formatter:function(value,row){
                            return row.productname;
                        },
                        editor:{
                            type:'combobox',
                            options:{
                                valueField:'productid',
                                textField:'productname',
                                url:'products.json',
                                required:true
                            }
                        }">Product</th>
                <th data-options="field:'listprice',width:80,align:'right',editor:{type:'numberbox',options:{precision:1}}">List Price</th>
                <th data-options="field:'unitcost',width:80,align:'right',editor:'numberbox'">Unit Cost</th>
                <th data-options="field:'attr1',width:250,editor:'text'">Attribute</th>
                <th data-options="field:'status',width:60,align:'center',editor:{type:'checkbox',options:{on:'P',off:''}}">Status</th>
            </tr>
        </thead>
    </table>
 
    <div id="tb" style="height:auto">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">Append</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">Remove</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="accept()">Accept</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="reject()">Reject</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="getChanges()">GetChanges</a>
    </div>*/	


function getPermissionString(){
	
	var gridData = $('#menuPermissionGrid').datagrid('getData');
	//检验grid数据有效性
	for(var i=0; i<gridData.total; i++){
		//存在校验未通过
		var result = $('#menuPermissionGrid').datagrid('validateRow',i);
		if(!result){
			return false;
		}
	}
	//确认无误，保存状态，使得可以获取编辑中的数据
	accept();
	return JSON.stringify(gridData.rows);
}

var editIndex = undefined;
function endEditing(){
    if (editIndex == undefined){return true}
    if ($('#menuPermissionGrid').datagrid('validateRow', editIndex)){
        var ed = $('#menuPermissionGrid').datagrid('getEditor', {index:editIndex,field:'menuId'});
        //var productname = $(ed.target).combobox('getText');
        //$('#menuPermissionGrid').datagrid('getRows')[editIndex]['productname'] = productname;
        $('#menuPermissionGrid').datagrid('endEdit', editIndex);
        editIndex = undefined;
        return true;
    } else {
        return false;
    }
}
function onClickRow(index){
    if (editIndex != index){
        if (endEditing()){
            $('#menuPermissionGrid').datagrid('selectRow', index).datagrid('beginEdit', index);
            editIndex = index;
        } else {
            $('#menuPermissionGrid').datagrid('selectRow', editIndex);
        }
    }
}
function append(){
    if (endEditing()){
        $('#menuPermissionGrid').datagrid('appendRow',{});
        editIndex = $('#menuPermissionGrid').datagrid('getRows').length-1;
        $('#menuPermissionGrid').datagrid('selectRow', editIndex).datagrid('beginEdit', editIndex);
    }
}
function removeit(){
    if (editIndex == undefined){return}
    $('#menuPermissionGrid').datagrid('cancelEdit', editIndex).datagrid('deleteRow', editIndex);
    editIndex = undefined;
}
function accept(){
    if (endEditing()){
        $('#menuPermissionGrid').datagrid('acceptChanges');
    }
}
function reject(){
    $('#menuPermissionGrid').datagrid('rejectChanges');
    editIndex = undefined;
}
function getChanges(){
    var rows = $('#menuPermissionGrid').datagrid('getChanges');
    alert(rows.length+' rows are changed!');
}
 