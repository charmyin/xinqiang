<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/cmstudio.tld" prefix="cmstudio" %>

<!DOCTYPE html>
<html>
  <head>
    <title>${html_title}</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="shortcut icon" type="image/x-icon" href="resources/${icon_name}"/>
    <cmstudio:htmlBase/>
	<link rel="stylesheet" type="text/css" href="resources/css/basic/main/index.css" />
	<!--Start importing the jquery files -->
	<cmstudio:importJsCss name="jquery" version="${jquery_version}"/>
	<!--End import the jquery files -->
	<!--Start importing the jeasyui files -->
	<cmstudio:importJsCss name="jeasyui" version="${jeasyui_version}"/>
	<!--End importing the jeasyui files -->
	<!--Start importing the jeasyui files -->
	<cmstudio:importJsCss name="ztree" version="${ztree_version}"/>
	<!--End importing the jeasyui files -->
	<script type="text/javascript" src="resources/js/basic/index.js"></script>
  </head>
  
  <body style="overflow:hidden;" id="bodyIndexMain">
      <div class="easyui-layout" id="divLayout_Main" data-options="fit:true" style="overflow:hidden;">
      	<div region='north' title="Zebone 前端集成开发平台(EasyUI)" style="width:100%; height:100px;background:blue;">
      		<div style="height:100%; width:100%; background:url(resources/img/basic/main/head.jpg) no-repeat #8badcc; ">
      			
      			<div style="float:right; padding:24px 20px 0 0;">
      				<a href="" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-sum'">桌面版</a>
      				<a id="aMenubutton_Main" href="#" class="easyui-menubutton" data-options="menu:'#mm1', iconCls:'icon-tip'" >切换主题</a>
      				<a href="#" class="easyui-menubutton" data-options="menu:'#mm2', iconCls:'icon-help'">首选项</a>
      				<a href="#" class="easyui-linkbutton" id="logout" data-options="plain:true, iconCls:'icon-no'">退出</a>
      			</div>
      			<div id="mm1" style="width:150px;">
      				<div data-options="iconCls: 'icon-undo'" class="divOnChangeTheme" value="default">Default</div>
      				<div data-options="iconCls: 'icon-redo'" class="divOnChangeTheme" value="bootstrap">Bootstrap</div>
      			</div>
      			<div id="mm2">
      				<div data-options="iconCls: 'icon-edit'" onclick="alert($(this).html())">修改密码</div>
      				<div data-options="iconCls: 'icon-save'" onclick="alert($(this).html())">系统锁定</div>
      			</div>
      		</div>
		</div>
      	<div region="west" split="true" id="divRegionWest_Main" title="Navigator 是个导航栏" style='width:280px; height:auto;'>
      		<div class="easyui-accordion" id="divAccordion_main" data-options="fit:true">
      			<div title="模版子系统" id="divPanelModule_main" data-option="iconCls:'icon-ok'" style="width:100%; overflow-x:hidden; overflow-y:auto;">
						<div id="ulCatalogueTree_Main" class="ztree"></div><!---->
				</div>
				
				<div title="开发人员工具" data-option="iconCls:'icon-help'" style="padding:10px;">
					<h3 style="color:#0099FF;">Second accordion</h3>
					<p>
						The accordion allows you to provide multiple panels and display one at a time. Each panel has built-in support for expanding and collapsing. Clicking on a panel header to expand or collapse that panel body. The panel content can be loaded via ajax by specifying a 'href' property. Users can define a panel to be selected. If it is not specified, then the first panel is taken by default.
					</p>
				</div>
				<div title="权限管理" data-option="iconCls:'icon-search'" style="padding:10px;">
				
				</div>
      		</div>
      	</div>
      	<div region="center" split="true" style="width:auto;">
      		
      		<div id="divTab_Main" class="easyui-tabs"  data-options="fit:true" style="">
      			<div title="欢迎使用本平台">
      				<h2 style="color:#0099FF; text-align:center;">关于振邦</h2>
					<p style="padding:0 60px 0; font-size:16px; text-indent:32px;">
						江苏振邦医用信息系统有限公司(简称振邦医疗)，是环亚医用科技集团旗下的全资控股公司，在常州、北京、广州、宁波分别设有研发中心。公司以全力打造数字化医院和助力区域医疗为己任，致力于中国医疗卫生事业的信息化建设,为客户提供最先进的管理咨询、解决方案、信息化软件与服务，从事整体规划、软件研发、系统集成、运维服务和标准体系建设等整套工作,是中国领先的智慧医疗整体解决方案提供商。
					</p>
      			</div>
      		</div>
      	</div>
      	<div region="south" style="width:100%; text-align: center; padding: 0; margin:0; line-height:23px; overflow:hidden;">
      		Powered by  ${company_name}© ${company_poweredyear}  &nbsp;<a href="http://www.zebone.cn" style="text-decoration: none;" target="_blank">${company_website}</a>
      	</div>
      </div>
      <!--等待界面-->
	  <div id='divLoading_Main'><span>登录成功~</span></div>
  </body>
</html>

