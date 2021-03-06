<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/backend/common/taglib.jsp" %>    
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/backend/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>CMS 后台管理工作平台</title>
<style type="text/css">
<!--
body {
	margin-left: 3px;
	margin-top: 0px;
	margin-right: 3px;
	margin-bottom: 0px;
}
.STYLE1 {
	color: #e1e2e3;
	font-size: 12px;
}
.STYLE6 {color: #000000; font-size: 12; }
.STYLE10 {color: #000000; font-size: 12px; }
.STYLE19 {
	color: #344b50;
	font-size: 12px;
}
.STYLE21 {
	font-size: 12px;
	color: #3b6375;
}
.STYLE22 {
	font-size: 12px;
	color: #295568;
}
A:active,A:visited,A:link {
	COLOR: #0629FD;
	TEXT-DECORATION: none
}

A:hover {
	COLOR: #FF6600;
	TEXT-DECORATION: none
}

A.relatelink:active,A.relatelink:visited,A.relatelink:link { 
	color: white;
	TEXT-DECORATION: none
}

A.relatelink:hover {
	COLOR: #FF6600;
	TEXT-DECORATION: none
}

td {
	font-size: 12px;
	color: #003366;
	height:24px
}

.STYLE1 a{
	COLOR: white;
}
.STYLE1 A:active,.STYLE1 A:visited,.STYLE1 A:link {
	COLOR: white;
	TEXT-DECORATION: none
}
.STYLE1 a:hover{
	COLOR: red;
}
-->
</style>
<script type="text/javascript">
	function selectAll(field){
	
		//根据ck框的name属性，获取所有ck框
		var idCheckboxs = document.getElementsByName("id");
		for(var i=0;i<idCheckboxs.length;i++){
			idCheckboxs[i].checked=field.checked;
		//判断顶上ck框的状态
			//if(field.checked){
			//	idCheckboxs[i].checked=true;
			//}else{
			//	idCheckboxs[i].checked=false;
			//}
		}
	}
	function del(){
	
	//判断有哪些ID被选中
		var idCheckboxs = document.getElementsByName("id");
		var checkedIds = [];
		var url = "ChannelServlet?method=del";
		for(var i=0;i<idCheckboxs.length;i++){
			if(idCheckboxs[i].checked){
				checkedIds[checkedIds.length] =idCheckboxs[i].value;
			}
		}
		//构造url
		for(var i=0;i<checkedIds.length;i++){
			if(i==0){
				url = url + "?id="+checkedIds[i];
			}else{
				url = url + "&id="+checkedIds[i];
			}
		}
		
		window.location = url;
	}
	
</script>
</head>
<body>

<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="6%" height="19" valign="bottom"><div align="center"><img src="images/tb.gif" width="14" height="14" /></div></td>
                <td width="94%" valign="bottom"><span class="STYLE1"> 网站频道信息列表</span></td>
              </tr>
            </table></td>
            <td><div align="right"><span class="STYLE1">
             &nbsp;&nbsp;<img src="images/add.gif" width="10" height="10" /> <a href="#">添加</a>   
            &nbsp; <img src="images/del.gif" width="10" height="10" /> <a href="javascript:del()">删除</a>    &nbsp;&nbsp;   &nbsp;
             </span><span class="STYLE1"> &nbsp;</span></div></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td>
    <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
      <tr>
        <td width="4%" height="20" bgcolor="d3eaef" class="STYLE10"><div align="center">
          <input type="checkbox" name="checkbox" onclick="selectAll(this)"  />
        </div></td>
        <td width="100" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">频道名称</span></div></td>
        <td width="50" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">频道描述</span></div></td>
        <td width="100" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">基本操作</span></div></td>
      </tr>
      
      <c:if test="${not empty pv.datas}">
      <c:forEach items="${pv.datas}" var="c">
      <tr>
        <td height="20" bgcolor="#FFFFFF"><div align="center">
          <input type="checkbox" name="id" value="${c.id }" />
        </div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><a href="#" title="点击查看和编辑频道">${c.name}</a></div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${c.description}</div></td>
        
        <td height="20" bgcolor="#FFFFFF"><div align="center" class="STYLE21">
        <a href="ChannelServlet?id=${c.id }&method=del" title="点击删除频道">删除</a> |
        <a href="ChannelServlet?id=${c.id }&method=updateInput" title="点击编辑频道">编辑</a>
        </div></td>
      </tr>
      </c:forEach>
      </c:if>
    </table></td>
  </tr>
  <tr>
    <td height="30">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
    
        <td height="30" width="67%" align=right vAlign="center" noWrap>
        <jsp:include page="/backend/common/pager.jsp">
            <jsp:param value="ChannelServlet" name="url"/>
            
        </jsp:include>      
    </td>
      </tr>
    </table></td>
  </tr>
    </table></td>
  </tr>
</table>
</body>
</html>

