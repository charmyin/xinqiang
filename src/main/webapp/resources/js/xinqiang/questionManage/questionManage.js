/***
 * author: charmyin
 * datetime: 2013-2-26 21:00
 * title: Control the organizationManage.jsp ~
 ***/
//当前新增或者修改的问题编号
var currentId;

//Global the item name
var itemName="";
if(inputTypeValue=="1"){
	itemName = "科目一试题";
}else if(inputTypeValue=="4"){
	itemName = "科目四试题";
}

function loadGrid(){
	
	$("#questionGrid").datagrid({
		url:'question/'+inputTypeValue+'/all', 
		method:'get',
		toolbar:'#toolbar',
		pagination:true,
		collapsible:true,
		title:"试题库库管理&nbsp----&nbsp"+itemName,
		rownumbers:true,
		singleSelect:true,
		pageSize:8,
	    pageList:[16,32,48,64],
		columns:[[
		          {field:'ck', checkbox:true },
		          {field:'id', title:'编号' , width:40},
		          {field:'type', title:'类型' , width:40, hidden:true},
		          {field:'choosea', title:'A选项' , width:40, hidden:true},
		          {field:'chooseb', title:'B选项' , width:40, hidden:true},
		          {field:'choosec', title:'C选项' , width:40, hidden:true},
		          {field:'choosed', title:'D选项' , width:40, hidden:true},
		          {field:'content', title:'题干', width:450},
		          {field:'answer', title:'答案' , width:60},
		          {field:'remark', title:'备注', width:450}
		]],
		onLoadError: function(msge){
			$.messager.alert('错误信息','服务器连接已断开或服务器内部错误！','error');
		}
	});
}
var myPlayer;
var videoIndex=0;
function loadImgOrVideo(imgName){
	
	if(imgName.indexOf("flv") > -1){
		videoIndex++;
		$('<video id="video'+videoIndex+'" class="video-js vjs-default-skin imgVideoShow" width="300" height="150"><source src=upload/'+imgName+' type="video/x-flv"></video>').appendTo("#uploaddiv");
		//var myPlayer = videojs('video1');
		videojs("video"+videoIndex,{ "controls": true, "autoplay": false, "preload": "auto" }).ready(function(){
			  myPlayer = this;
			  // EXAMPLE: Start playing the video.
			  myPlayer.play();
			});
	}else{
		$("<img class='imgVideoShow' style='width:200px; height:100px;' src='upload/"+imgName+"' />").appendTo("#uploaddiv");
	}
	$("<button class='deletePicBtn'/>").text("删除").appendTo("#uploaddiv").click(function(event){
		event.preventDefault();
		$.each(_V_.players, function (key, player) { 
		    if (player.isReady) { player.destroy(); } 
		    else { delete _V_.players[player.id]; } 
		}); 
		$(".imgVideoShow").remove();
	    $("#imageVideoPath").val("");
	    $(this).remove();
	    return;
	});;    
}

/********************************************************Initial the page*****************************************************/
$(function(){
	
	videojs.options.flash.swf = "resources/vendor/videojs/video-js.swf";
		
	//Disable cache
	jQuery.ajaxSetup({ cache: false });
	loadGrid();
});


function initUploadImg(){
	$('#fileupload').fileupload({
		singleFileUploads:true,
        formData: {questionId: currentId, subjectType:inputTypeValue},
        done: function (e, data) {
        	//alert("dddddd");
        	$(".imgVideoShow").remove();
        	$(".deletePicBtn").remove();
        	var result = eval("("+data.result+")");
        	$("#imageVideoPath").val(result.msg);
        	//回显图片或者视频
        	loadImgOrVideo(result.msg);
        },
        add: function (e, data) {
        	 data.submit();
        }
    });
}
//OrganizationCrud dialog
var url;

function newForm(){
	$(".imgVideoShow").remove();
	$(".deletePicBtn").remove();
	currentId=0;
    $('#dlg').dialog('open').dialog('setTitle','新建'+itemName+'');
    $('#fm').form('clear');
    url = 'question/save';
    $("#inputtype").val(inputTypeValue);
    $("#uploaddiv").hide();
   // initUploadImg();
}
function editForm(){
	 $('#fm').form('clear');
	 $(".imgVideoShow").remove();
	 $(".deletePicBtn").remove();
    var row = $('#questionGrid').datagrid('getSelected');
  
    currentId=row.id;
    if (row){
        $('#dlg').dialog('open').dialog('setTitle','修改'+itemName);
        $('#fm').form('load',row);
        url = 'question/update?id='+row.id;
    }else {
	    	$.messager.show({
	        	title: '提示<span style="color:red;">!</span>',
	            msg: "<div style='text-align:center;margin-top:10px;'>请选择要修改的记录！</div>",
	            style:{
	        		right:'',
	        		top:document.body.scrollTop+document.documentElement.scrollTop,
	        		bottom:''
	        	}
	        });
	    	return;
    }
    if(row.imageVideoPath){
    	loadImgOrVideo(row.imageVideoPath);
    }
    if(row.answer.indexOf('A') > -1){
    	$( "input[name=answera]" ).attr('checked', true);
    }
    if(row.answer.indexOf('B') > -1){
    	$( "input[name=answerb]" ).attr('checked', true);
    }
    if(row.answer.indexOf('C') > -1){
    	$( "input[name=answerc]" ).attr('checked', true);
    }
    if(row.answer.indexOf('D') > -1){
    	$( "input[name=answerd]" ).attr('checked', true);
    }
    //$( "td:eq( 2 )" )
    $("#uploaddiv").show();
    initUploadImg();
}

function saveForm(){
	var answerFinal="";
	$( ".answercb:checked" ).each(function(index){
		if(index!=0){
			answerFinal+=",";
		}
		answerFinal+=$(this).val();
	}); 
	if(answerFinal==""){
		$.messager.show({
        	title: '提示<span style="color:red;">!</span>',
            msg: "<div style='text-align:center;margin-top:10px;'>请选择答案！</div>",
            style:{
        		right:'',
        		top:'',
        		bottom:''
        	}
        });
		return;
		
	}else{
		$("#questionAnswer").val(answerFinal);
	}
    $('#fm').form('submit',{
        url: url,
        onSubmit: function(){
        	//组件验证，未通过则返回false
        	return $(this).form('validate');
        },
        success: function(resultString){
        	var result = eval("("+resultString+")");
            if (result.suc){
              //  $('#dlg').dialog('close');        // close the dialog
            	//alert(result.msg);
            	url = 'question/update?id='+result.msg;
            	currentId=result.msg;
                $.messager.show({
                	title: '提示',
                    msg: "<div style='text-align:center;margin-top:10px;'>保存成功!</div>",
                    style:{
                		right:'',
                		top:document.body.scrollTop+document.documentElement.scrollTop,
                		bottom:''
                	}
                });
                $('#questionGrid').datagrid('reload');
                $("#uploaddiv").show();
                initUploadImg();
            } else {
            	$.messager.show({
                	title: '提示<span style="color:red;">!</span>',
                    msg: "<div style='text-align:center;margin-top:10px;'>"+result.errorMsg+"</div>",
                    style:{
                		right:'',
                		top:document.body.scrollTop+document.documentElement.scrollTop,
                		bottom:''
                	}
                });
            }
        }
    });
}
function destroySelectedItems(){
    var rows = $('#questionGrid').datagrid('getSelections');
    var rowsLength = rows.length;
    if (rowsLength>0){
        $.messager.confirm('提示信息','确定删除选中'+itemName+'？',function(r){
            if (r){
            	var idsString='';
            	for(var i=0; i<rows.length; i++){
            		if((i+1)==rowsLength){
            			idsString+=rows[i].id;
            		}else{
            			idsString+=(rows[i].id+',');
            		}
            	}
            	$.post('organization/deleteByIds',{ids:idsString},function(result){
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
                    	//Reload left tree and refresh the datagrid
                    	loadOrganizationTree();
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


