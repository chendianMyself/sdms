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
<title>添加公寓信息</title>
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

<script>
	function getArgs(){
		var obj=window.dialogArguments;
		
		alert(JSON.stringify(obj));
		
		window.returnValue=true;
	}
</script>
</head>

<body>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="30"><img src="images/tab_03.gif" width="15" height="30" /></td>
        <td width="1101" background="images/tab_05.gif"><img src="images/311.gif" width="16" height="16" /> <span class="STYLE4">添加公寓</span></td>
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
        	<form action="deptAdd.do" method="post">
        	<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#c0de98" >
	          <tr>
	            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">公寓名称(编号)</div></td>
	            <td height="18" bgcolor="#FFFFFF"><div align="left" class="STYLE2 STYLE1" style="padding-left:20px"><input type="text" name="deptNo"/></div></td>
	          </tr>
	          <tr>
	            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">楼层号</div></td>
	            <td height="18" bgcolor="#FFFFFF"><div align="left" class="STYLE2 STYLE1" style="padding-left:20px"><input type="text" name="floors"/></div></td>
	          </tr>
	          <tr>
	            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">备注(说明)</div></td>
	            <td height="18" bgcolor="#FFFFFF"><div align="left" class="STYLE2 STYLE1">
	            	<textarea rows="3" cols="120" name="remark"></textarea>
	            </div></td>
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

