<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加学生</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.STYLE1 {font-size: 12px}
.STYLE4 {
	font-size: 12px;
	color: #1F4A65;
	font-weight: bold;
}

a:link {
	font-size: 12px;
	color: #06482a;
	text-decoration: none;

}
a:visited {
	font-size: 12px;
	color: #06482a;
	text-decoration: none;
}
a:hover {
	font-size: 12px;
	color: #FF0000;
	text-decoration: underline;
}
a:active {
	font-size: 12px;
	color: #FF0000;
	text-decoration: none;
}
.STYLE7 {font-size: 12}

-->
</style>
<script type="text/javascript" src="js/jquery.js"></script>
<script>
	function test(opt){
		//alert(opt);
		//获取选中的公寓的楼层数量
		var floors=opt.getAttribute("floors");
		//alert(floors);
		//获取楼层select
		var sel=document.getElementById("floorsSelect");
		
		//拼接楼层select内部的option
		var opts="";
		for(var i=1;i<=floors;i++){
			opts+="<option id='floor"+i+"' value='"+i+"'>"+i+"楼</option>";
		}
		//把拼接好的楼层option字符串,写入下拉列表
		sel.innerHTML=opts;
	}
	function init(){
		//从request中获取上次搜索时使用的参数
		var deptId="${deptId}",floors="${floors}";
	
		//获取指定公寓id代表的公寓的option对象
		var dept=document.getElementById("dept"+deptId);
		//选中指定公寓
		dept.selected="selected"
		//获取指定公寓的楼层信息
		test(dept);
		//选中上次搜索时使用的楼层参数
		//注意先后顺序
		document.getElementById("floor"+floors).selected="selected";
		getRoom();
	}
	function getRoom(){
		var deptId=document.getElementById("deptSelect").value;
		var floors=document.getElementById("floorsSelect").value;
		//jquery的ajax方法调用,需要一个json格式的参数
		$.ajax({
			url:"roomListAjax.do?deptId="+deptId+"&floors="+floors,
			method:"get",
			dataType: "json",
			success:function(data){
				
				//使用js对象构造下拉列表选项
				var opts="<option value='0'>请选择房间</option>";
				
				for(var i=0;i<data.length;i++){
					var roomId=data[i].id;
					var roomNo=data[i].roomNo;
					opts+="<option value='"+roomId+"'>"+roomNo+"</option>";
				}
				//将构造好的选项放入下拉框
				document.getElementById("roomSelect").innerHTML=opts;
			},
			//请求发生错误时,执行该方法
			error:function(){
				alert("请求错误");
			}
	});
}
</script>
</head>

<body>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="30"><img src="images/tab_03.gif" width="15" height="30" /></td>
        <td width="1101" background="images/tab_05.gif"><img src="images/311.gif" width="16" height="16" /> <span class="STYLE4">添加学生</span></td>
        <td width="281" background="images/tab_05.gif">&nbsp;</td>
        <td width="14"><img src="images/tab_07.gif" width="14" height="30" /></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td>
    
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="9" background="images/tab_12.gif">&nbsp;</td>
        <td bgcolor="#f3ffe3">
        	<form action="studentUpdate.do" method="post">
        	<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#c0de98" >
	          <tr>
	            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">学生姓名</div></td>
	            <td height="18" bgcolor="#FFFFFF"><div align="left" class="STYLE2 STYLE1" style="padding-left:20px"><input type="text" name="name"/></div></td>
	          </tr>
	          <tr>
	            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">公寓及楼层</div></td>
	            <td height="18" bgcolor="#FFFFFF"><div align="left" class="STYLE2 STYLE1" style="padding-left:20px">
	            	<select name="deptId"  id="deptSelect" style="width:120px">
	            			<option>请选择公寓</option>
	            			<c:forEach items="${deptList }" var="dept">
	            			<!-- 使用公寓的id作为value,保证每一个选项的值都不重复,可以精确的定位到选择的公寓上 -->
	            			<!-- 为每个option定义一个自定义属性用于记录当前这个公寓拥有的楼层数量 -->
	            			<option value="${dept.id }" floors="${dept.floors }" id="dept${dept.id }"  onclick="test(this)">-----${dept.deptNo }-----</option>
	            			</c:forEach>
	            		</select>&nbsp;&nbsp;&nbsp;&nbsp;
	            		请选择楼层&nbsp;&nbsp;
	            		<select name="floors" id="floorsSelect"onchange="getRoom()">
	            			<option value="0">请先选择公寓</option>
	            		</select>
	            		&nbsp;&nbsp;请选择宿舍&nbsp;&nbsp;
	            		<select name="roomId" id="roomSelect">
	            			<option value="0">请先选择楼层</option>
	            		</select>
	            		&nbsp;&nbsp;&nbsp;&nbsp;
	            </div></td>
	            
	          </tr>
	          <tr>
	            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">学生学号</div></td>
	            <td height="18" bgcolor="#FFFFFF"><div align="left" class="STYLE2 STYLE1" style="padding-left:20px"><input type="text" name="no"/></div></td>
	          </tr>
	           <tr>
	            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">学生电话</div></td>
	            <td height="18" bgcolor="#FFFFFF"><div align="left" class="STYLE2 STYLE1" style="padding-left:20px"><input type="text" name="tel"/></div></td>
	          </tr>
	          <tr>
	            <td height="18" bgcolor="#FFFFFF" colspan="2" style="padding-left: 120px"><input type="submit" value="添加"/></td>
	          </tr>
	        </table>
        	</form>
        </td>
        <td width="9" background="images/tab_16.gif">&nbsp;</td>
      </tr>
    </table>
    
    </td>
  </tr>
  <tr>
    <td height="29">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="29"><img src="images/tab_20.gif" width="15" height="29" /></td>
        <td background="images/tab_21.gif">&nbsp;</td>
        <td width="14"><img src="images/tab_22.gif" width="14" height="29" /></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>

